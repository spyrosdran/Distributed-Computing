import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerProtocol {

    String uname = "root";
    String pass = "";
    String url = "jdbc:mysql://localhost:3306/bank";
    Connection con;
    java.sql.Statement statement;

    //Initializing database connection
    public ServerProtocol() {

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
    }

    //Processing the request and returning a response
    public String processRequest(String request) throws SQLException {

        //Splitting the request --> userID#action#money
        //userID may be equal to "login" or "logout". If that's the case, then action contains the ID which will log in or out.
        String[] parts = request.split("#");
        String userID = parts[0];
        String action = parts[1];
        String money = parts[2];

        String query;
        String response;

        //Authorizing user
        if (userID.equals("login")) return Integer.toString(authorizeUser(action,money));

        //Logging out
        if (userID.equals("logout")) return logOut(Integer.parseInt(action));

        //Balance request
        if(action.equals("balance")) {

            query = "SELECT balance FROM accounts WHERE id = " + userID;

            try {
                ResultSet result = statement.executeQuery(query);
                result.next();
                String balance = result.getString(1);
                return "Your balance is " + balance + "€";

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        //The request is either deposit or withdraw
        else {
            int amountOfMoney = Integer.parseInt(money);

            //Retrieving the balance and adding or subtracting the amount of money according to the given action
            query = "SELECT balance FROM accounts WHERE id = " + userID;

            try {
                ResultSet result = statement.executeQuery(query);
                result.next();
                int balance = Integer.parseInt(result.getString(1));

                //Checking if the amount of money to deposit or to withdraw is valid
                if (!isAmountOfMoneyValid(amountOfMoney)) return "Invalid amount of money. Please try again...";

                //Deposit request
                if(action.equals("deposit")) {
                    balance += amountOfMoney;
                }

                //Withdraw request
                else if (action.equals("withdraw")) {
                    if(amountOfMoney > balance) {
                        return "You may withdraw up to " + balance + "€";
                    }
                    balance -= amountOfMoney;
                }

                //If the action is neither deposit nor withdraw then there is an error
                else {
                    return "Error while parsing request";
                }

                //Updating the balance in database
                query = "UPDATE accounts SET balance = " + balance + " WHERE id = " + userID;

                int rowsUpdated = statement.executeUpdate(query);

                //Returning a success message
                if(rowsUpdated == 1) {
                    if(action.equals("deposit")) return "Deposit successful";
                    else return "Withdraw successful";
                }

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        //Unknown error
        return "Unknown error";
    }

    //User authorization
    public int authorizeUser(String username, String password) {

        /*
            Checking if user exists and returning the appropriate number
            userID = -2 --> User already logged in
            userID = -1 --> Invalid credentials
            userID > 0 --> User logs in and makes requests
         */
        String query = "SELECT id, loggedIn FROM accounts WHERE username = '" + username + "' AND password = '" + password + "'";
        int rows = 0;
        int userID = -1;
        int loggedIn = 0;

        try {
            ResultSet result = statement.executeQuery(query);

            while(result.next()) {
                rows++;
                userID = Integer.parseInt(result.getString(1));
                loggedIn = Integer.parseInt(result.getString(2));
            }

            if(rows==1 && loggedIn == 0) {
                query = "UPDATE accounts SET loggedIn = " + 1 + " WHERE id = " + userID;
                statement.executeUpdate(query);
                return userID;
            }
            else if (loggedIn == 1) {
                return -2;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return -1;
    }

    //Logging out and returning "Bye bye" response to client
    public String logOut(int userID) throws SQLException {
        String query = "UPDATE accounts SET loggedIn = " + 0 + " WHERE id = " + userID;
        statement.executeUpdate(query);
        return "Bye bye";
    }

    //Checking if the amount of money to withdraw or to deposit is valid
    private Boolean isAmountOfMoneyValid(int money){
        if(money<0 || !(money%20==0 || money%50==0)) return false;
        return true;
    }

}
