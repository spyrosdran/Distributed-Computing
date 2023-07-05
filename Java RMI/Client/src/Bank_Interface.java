import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface Bank_Interface extends Remote{
	
	public int logIn(String username, String password) throws RemoteException;
	
	public int requestBalance(int userID) throws RemoteException;
	
	public boolean withdraw(int userID, int money) throws SQLException, RemoteException;
	
	public boolean deposit(int userID, int money) throws SQLException, RemoteException;

	public boolean checkIfLoggedIn(int userID) throws SQLException, RemoteException;
	
	public void logOut(int userID) throws SQLException, RemoteException;
}
