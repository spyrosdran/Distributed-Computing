import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bank_Interface_Implementation extends UnicastRemoteObject implements Bank_Interface {
	
	int balance;
	String uname = "root";
	String pass = "";
	String url = "jdbc:mysql://localhost:3306/bank";
	String query;
	Connection con;
	java.sql.Statement statement;

	//Initializing database connection
	protected Bank_Interface_Implementation() throws RemoteException {
		super();
		
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

	//Checking if the user is already logged in.
	//If user isn't logged in, then we proceed into logging in and updatinf the database
	@Override
	public int logIn(String username, String password) throws RemoteException{

		//Checking if user exists and returning -1 or the userID
		String query = "SELECT id, loggedIn FROM accounts WHERE username = '" + username + "' AND password = '" + password + "'";
		int rows = 0;
		int userID = -1;
		boolean loggedIn = false;
				
		try {
			ResultSet result = statement.executeQuery(query);
					
			while(result.next()) {
				rows++;
				userID = Integer.parseInt(result.getString(1));
				loggedIn = (Integer.parseInt(result.getString(2)) == 1);
			}
					
			if(rows==1 && !loggedIn) {
				query = "UPDATE accounts SET loggedIn = " + 1 + " WHERE id = " + userID;
				statement.executeUpdate(query);
				
				return userID;
			}
			else if (loggedIn) {
				return -2;
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return userID;	
	}

	//Requesting for the balance and returning the amount of money
	@Override
	public int requestBalance(int userID) throws RemoteException{
		query = "SELECT balance FROM accounts WHERE id = " + userID;
		
		try {
			ResultSet result = statement.executeQuery(query);
			result.next();
			balance = Integer.parseInt(result.getString(1));
			
			return balance;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	//Withdrawing the desired amount of money and returning if the action was successful or not
	@Override
	public boolean withdraw(int userID, int money) throws SQLException, RemoteException {
		
		balance = requestBalance(userID);
		
		if(balance >= money && money > 0) {
			balance -= money;
			query = "UPDATE accounts SET balance = " + balance + " WHERE id = " + userID;
			
			int rowsUpdated = statement.executeUpdate(query);
			
			//returning a success message
			if(rowsUpdated == 1) return true;
		} 
		
		return false;
	}

	//Depositing the desired amount of money and returning if the action was successful or not
	@Override
	public boolean deposit(int userID, int money) throws SQLException, RemoteException{

		balance = requestBalance(userID);
		
		if(money > 0) {
			balance += money;
			query = "UPDATE accounts SET balance = " + balance + " WHERE id = " + userID;
			int rowsUpdated = statement.executeUpdate(query);
			
			//returning a success message
			if(rowsUpdated == 1) return true;
		}
		
		
		return false;
	}
	
	@Override
	public boolean checkIfLoggedIn(int userID) throws SQLException, RemoteException {
		
		boolean loggedIn = false;
		
		query = "SELECT loggedIn FROM accounts WHERE id = " + userID;
		
		try {
			ResultSet result = statement.executeQuery(query);
			result.next();
			loggedIn = (Integer.parseInt(result.getString(1)) == 1);
			
			return loggedIn;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loggedIn;
		
	}
	
	//Updating the database and logging the user out
	@Override
	public void logOut(int userID)throws SQLException, RemoteException{
		query = "UPDATE accounts SET loggedIn = " + 0 + " WHERE id = " + userID;
		statement.executeUpdate(query);
	}

}
