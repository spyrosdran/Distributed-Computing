import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerProtocol {
	
	int userID;
	String uname = "root";
	String pass = "";
	String url = "jdbc:mysql://localhost:3306/bank";
	Connection con;
	java.sql.Statement statement;
	
	public ServerProtocol() {
		
		userID = -1;
		
		//Initializing database connection
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
	
	public boolean authorizeUser(String username, String password) {
		
		//Checking if user exists and returning true or false
		String query = "SELECT id FROM accounts WHERE username = '" + username + "' AND password = '" + password + "'";
		int rows = 0;
		
		try {
			ResultSet result = statement.executeQuery(query);
			
			while(result.next()) {
				rows++;
				userID = Integer.parseInt(result.getString(1));
			}
			
			if(rows==1) {
				System.out.println("User authorized");
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public String processRequest(String inmsg) {
		
		String query;
		String response;
		
		//Balance request
		if(inmsg.equals("balance")) {
			
			query = "SELECT balance FROM accounts WHERE id = " + userID;
			
			try {
				ResultSet result = statement.executeQuery(query);
				result.next();
				String balance = result.getString(1);
				return "The balance is " + balance;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			//The request is either deposit or withdraw
			String[] parts = inmsg.split("#");
			String action = parts[0];
			String amount = parts[1];
			int money = Integer.parseInt(amount);
			
			//Retrieving the balance and adding or subtracting the amount of money according to the action given
			query = "SELECT balance FROM accounts WHERE id = " + userID;
			
			try {
				ResultSet result = statement.executeQuery(query);
				result.next();
				int balance = Integer.parseInt(result.getString(1));
				
				if(action.equals("deposit")) {
					balance += money;
				}
				
				else if (action.equals("withdraw")) {
					if(money > balance) {
						return "You can withdraw up to " + balance + "€";
					}
					balance -= money;
				} 
				//if the action is neither deposit or withdraw then there is an error
				else {
					return "Error while parsing request";
				}
				
				//updating the balance in database
				query = "UPDATE accounts SET balance = " + balance + " WHERE id = " + userID;
				
				int rowsUpdated = statement.executeUpdate(query);
				
				//returning a success message
				if(rowsUpdated == 1) {
					if(action.equals("deposit")) return "Deposit successful";
					else return "Withdraw successful";
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return "Failure";
	}

	public void closeDatabaseConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
