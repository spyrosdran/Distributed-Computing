import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.*;

public class RPCClient implements AutoCloseable {

    private Connection connection;
    private Channel channel;
    private String requestQueueName = "bank_queue";
    private static ClientProtocol app = new ClientProtocol();
    private static RPCClient bankRPC;

    public RPCClient() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    public static void main(String[] argv) throws IOException {

        //Declaring the variables to be used
        Scanner scanner = new Scanner(System.in);
        String username, password, request, response;
        int userID = -3;

        /*
            userID = -3 --> Initial value
            userID = -2 --> User already logged in
            userID = -1 --> Invalid credentials
            userID = 0 --> Process terminates
            userID > 0 --> User logs in and makes requests
         */

        try (RPCClient bankRpc = new RPCClient()) {
            //If user wants to stop, then by selecting 4 on the menu, userID automatically becomes 0 and then the program stops
            while (userID != 0) {

                    //userID is initialized as -3, in order to ask for credentials
                    if (userID == -3){

                        //Credential input prompts
                        System.out.println("Username: ");
                        username = scanner.nextLine();
                        System.out.println("Password: ");
                        password = scanner.nextLine();

                        //Creating the log in message --> login#username#password
                        request = app.logInRequest(username, password);

                        //Attempting to log in
                        System.out.println("Attempting to log in...");
                        response = bankRpc.call(request);

                        //If the response is -1 then the credentials are invalid and the process terminates
                        if (response.equals("-1")) {
                            System.out.println("Invalid credentials, please try again...");
                            userID = 0;
                        }
                        //If the response is -2 then the user is already logged in and the process terminates
                        else if (response.equals("-2")) {
                            System.out.println("User already logged in...");
                            userID = 0;
                        }
                        //Else the client stores his userID, so as to be able to make future requests
                        else userID = Integer.parseInt(response);

                    }
                    //This is the section which is run when the user has been authorized and has got his userID from the DB
                    else {
                        //Initializing and declaring variables
                        request = "";
                        String money;
                        int selection;

                        //Printing the menu and reading user input
                        System.out.println("Login successful");
                        selection = app.select();

                        //Preparing the appropriate request
                        while (selection != 4){

                            switch (selection){
                                case 1: request = app.balanceRequest(userID); break;
                                case 2:
                                    System.out.println("Money to deposit: ");
                                    money = app.readMoney();
                                    request = app.depositRequest(userID, money);
                                    break;
                                case 3:
                                    System.out.println("Money to withdraw: ");
                                    money = app.readMoney();
                                    request = app.withdrawRequest(userID, money);
                                    break;
                                default: break;
                            }

                            //Sending the message and waiting for the response
                            response = bankRpc.call(request);

                            //Printing the response
                            System.out.println(response);

                            //Printing the menu and reading the selection
                            selection = app.select();
                        }

                        //User has chosen to log out
                        request = app.logOutRequest(userID);
                        response = bankRpc.call(request);
                        userID = 0;
                        System.out.println(response);
                    }
            //End of while loop
            }

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        finally {
            scanner.close();
        }
    }

    public String call(String message) throws IOException, InterruptedException, ExecutionException {
        final String corrId = UUID.randomUUID().toString();

        String replyQueueName = channel.queueDeclare().getQueue();
        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));

        final CompletableFuture<String> response = new CompletableFuture<>();

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response.complete(new String(delivery.getBody(), "UTF-8"));
            }
        }, consumerTag -> {
        });

        String result = response.get();
        channel.basicCancel(ctag);
        return result;
    }

    public void close() throws IOException {
        connection.close();
    }
}