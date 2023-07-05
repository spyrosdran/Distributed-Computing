import com.rabbitmq.client.*;

import java.sql.SQLException;

public class RPCServer {

    //Declaring queue name
    private static final String RPC_QUEUE_NAME = "bank_queue";
    //Initializing server protocol
    private static ServerProtocol app = new ServerProtocol();

    public static void main(String[] argv) throws Exception {

        //Establishing connection with the RabbitMQ Server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
        channel.queuePurge(RPC_QUEUE_NAME);

        channel.basicQos(1);

        System.out.println(" [x] Awaiting RPC requests");

        //What to do in case of callback (Lambda function)
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                    .Builder()
                    .correlationId(delivery.getProperties().getCorrelationId())
                    .build();

            String response = "";

            try {
                //Request retrieval
                String request = new String(delivery.getBody(), "UTF-8");
                System.out.println("Request: " + request);
                //Processing of the request takes place in the server protocol, which returns a response
                response = app.processRequest(request);
                System.out.println("Response: " + response);
                System.out.println("----------");
            }

            catch (RuntimeException | SQLException e) {
                System.out.println(" [.] " + e);
            }

            finally {
                channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, response.getBytes("UTF-8"));
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };

        //Server starts consuming
        channel.basicConsume(RPC_QUEUE_NAME, false, deliverCallback, (consumerTag -> {}));
    }
}
