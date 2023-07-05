package service;

import io.grpc.stub.StreamObserver;
import proto.Bank;
import proto.BankInterfaceGrpc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankInterfaceImpl extends BankInterfaceGrpc.BankInterfaceImplBase {
    /*
    * We observe here that some words have an "@", this are Annotations. Annotations are used to provide supplement
    * information about a program. We can autogenerate this functions, in Intellij we can use the shortcut ctrl + O to
    * do this.
    * */

    int balance;
    String uname = "root";
    String pass = "";
    String url = "jdbc:mysql://localhost:3306/bank";
    String query;
    Connection con;
    java.sql.Statement statement;

    //Initializing database connection and logging in the user after performing appropriate checks
    @Override
    public void logIn(Bank.logInMessage request, StreamObserver<Bank.logInReply> responseObserver) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,uname,pass);
            statement = con.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String username = request.getUsername();
        String password = request.getPassword();

        //Checking if user exists and returning -1 or the userID
        String query = "SELECT id, loggedIn FROM accounts WHERE username = '" + username + "' AND password = '" + password + "'";
        int rows = 0;
        int userID = -1;
        boolean loggedIn = false;
        Bank.logInReply reply = Bank.logInReply.newBuilder().setUserID(-2).build();;

        //if there is one row returned, then the user proceeds by logging in
        try {
            ResultSet result = statement.executeQuery(query);

            while(result.next()) {
                rows++;
                userID = Integer.parseInt(result.getString(1));
            }

            if(rows==1) {
                reply = Bank.logInReply.newBuilder().setUserID(userID).build();
            }

            responseObserver.onNext(reply);
            responseObserver.onCompleted();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    //Retrieving and returning the balance
    @Override
    public void requestBalance(Bank.balanceMessage request, StreamObserver<Bank.balanceReply> responseObserver) {

        Bank.balanceReply reply = Bank.balanceReply.newBuilder().setBalance(-1).build();
        int userID = request.getUserID();
        query = "SELECT balance FROM accounts WHERE id = " + userID;

        try {
            ResultSet result = statement.executeQuery(query);
            result.next();
            balance = Integer.parseInt(result.getString(1));

            reply = Bank.balanceReply.newBuilder().setBalance(balance).build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    //Withdrawing the desired amount of money
    @Override
    public void withdraw(Bank.withdrawMessage request, StreamObserver<Bank.withdrawReply> responseObserver) throws SQLException {

        Bank.withdrawReply reply = Bank.withdrawReply.newBuilder().setActionSuccessful(false).build();
        int userID = request.getUserID();
        int money = request.getMoney();
        balance = -1;

        try {
            ResultSet result = statement.executeQuery(query);
            result.next();
            balance = Integer.parseInt(result.getString(1));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        if(balance >= money && money > 0) {
            balance -= money;
            query = "UPDATE accounts SET balance = " + balance + " WHERE id = " + userID;

            int rowsUpdated = statement.executeUpdate(query);

            //returning a success message
            if(rowsUpdated == 1) {
                reply = Bank.withdrawReply.newBuilder().setActionSuccessful(true).build();
                responseObserver.onNext(reply);
                responseObserver.onCompleted();
            }
        }

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    //Depositing the desired amount of money
    @Override
    public void deposit(Bank.depositMessage request, StreamObserver<Bank.depositReply> responseObserver) throws SQLException {

        Bank.depositReply reply = Bank.depositReply.newBuilder().setActionSuccessful(false).build();
        int userID = request.getUserID();
        int money = request.getMoney();
        balance = -1;

        try {
            ResultSet result = statement.executeQuery(query);
            result.next();
            balance = Integer.parseInt(result.getString(1));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        if(balance >= money && money > 0) {
            balance += money;
            query = "UPDATE accounts SET balance = " + balance + " WHERE id = " + userID;

            int rowsUpdated = statement.executeUpdate(query);

            //returning a success message
            if(rowsUpdated == 1) {
                reply = Bank.depositReply.newBuilder().setActionSuccessful(true).build();
                responseObserver.onNext(reply);
                responseObserver.onCompleted();
            }
        }

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
