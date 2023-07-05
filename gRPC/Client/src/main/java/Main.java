import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.BankInterfaceGrpc;
import proto.BankInterfaceGrpc;
import proto.Bank;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Listening to port 8999 and creating the bank stub, in order to manage the requests and responses
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8999).usePlaintext().build();
        BankInterfaceGrpc.BankInterfaceBlockingStub bankStub = BankInterfaceGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);

        //Asking for credentials
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        //If the credentials are valid, then user logs in
        Bank.logInReply logIn = bankStub.logIn(Bank.logInMessage.newBuilder().setUsername(username).setPassword(password).build());

        int userID = logIn.getUserID();

        if(userID > 0) {
            System.out.println("Successfully logged in\n");

            printMenu();
            int selection = select();
            int balance;
            int money;
            boolean isActionSuccessful = false;

            //Print the menu and communicate with the server as long as the user doesn't select option 4 --> Stop
            while(selection!=4) {

                //Retrieving the balance
                Bank.balanceReply balanceReply = bankStub.requestBalance(Bank.balanceMessage.newBuilder().setUserID(userID).build());
                balance = balanceReply.getBalance();

                if(balance >= 0) {
                    switch(selection) {

                        //Balance
                        case 1:
                            System.out.println("Your balance is " + balance + "€");
                            break;

                        //Withdraw
                        case 2:
                            System.out.println("Money to withdraw: ");
                            money = readMoney();

                            if (money <= balance) {

                                //Sending the request and checking if the action was successful
                                Bank.withdrawReply withdrawReply = bankStub.withdraw(Bank.withdrawMessage.newBuilder().setUserID(userID).setMoney(money).build());

                                isActionSuccessful = withdrawReply.getActionSuccessful();
                                if(isActionSuccessful)	System.out.println("Money withdrawn");
                                else System.out.println("Withdraw failed");

                            }
                            else System.out.println("Withdraw amount should be lower or equal to your balance");

                            break;

                        //Deposit
                        case 3:
                            System.out.println("Money to deposit: ");
                            money = readMoney();

                            //Sending the request and checking if the action was successful
                            Bank.depositReply depositReply = bankStub.deposit(Bank.depositMessage.newBuilder().setUserID(userID).setMoney(money).build());

                            isActionSuccessful = depositReply.getActionSuccessful();
                            if(isActionSuccessful)	System.out.println("Deposit successful");
                            else System.out.println("Deposit failed");

                    }

                } else System.out.println("Error while retrieving balance");

                printMenu();
                selection = select();
            }

            //The user pressed 4 and therefore he proceeds by logging out
            System.out.println("Bye bye");

        }
        {
            System.out.println("Invalid credentials. Please try again.");
        }

        scanner.close();

        //Closing connection with the server
        channel.shutdown();
    }

    //Printing the menu
    static void printMenu() {

        System.out.println("\n--------------");
        System.out.println("1 --> Balance");
        System.out.println("2 --> Withdraw");
        System.out.println("3 --> Deposit");
        System.out.println("4 --> Stop");
        System.out.println("Select: ");

    }

    //Checking for valid user input from menu
    static int select() {

        Scanner scanner = new Scanner(System.in);

        int selection = scanner.nextInt();
        while(selection<1 || selection>4) {
            System.out.println("You may only enter a number from 1 to 4. Please try again.");
            printMenu();
            selection = scanner.nextInt();
        }

        return selection;
    }

    //Checking if the amount of money is positive and broken into 20€ and 50€ bills
    static int readMoney() {

        Scanner scanner = new Scanner(System.in);
        int money;

        money = scanner.nextInt();
        while(money<0 || !(money%20==0 || money%50==0)) {
            System.out.println("You may only enter positive multiples of 20€ or 50€. Please try again: ");
            money = scanner.nextInt();
        }

        return money;
    }
}
