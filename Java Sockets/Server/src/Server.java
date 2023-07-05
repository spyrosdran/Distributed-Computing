import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static final int PORT = 1234;

	public static void main(String[] args) throws IOException {
		
		//Initializing variables
		ServerSocket connectionSocket = new ServerSocket(PORT);
		Socket dataSocket = connectionSocket.accept();
		ServerProtocol app = new ServerProtocol();
		String inmsg, outmsg, username, password;
		boolean isUserAuthorized = false;
		
		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		
		System.out.println("Ready to listen");
		System.out.println("Authorizing user...");
		
		//Receiving and checking if the credentials are valid
		username = in.readLine();
		password = in.readLine();
		isUserAuthorized = app.authorizeUser(username,password);
		
		if(isUserAuthorized) {
			//Send reply that the user logged in successfully
			outmsg = "success";
			out.println(outmsg);
			
			System.out.println("Serving a client...");
			
			//Waiting for user requests and replying to them
			inmsg = in.readLine();
			
			while(inmsg != null && !inmsg.equals("stop")) {
				outmsg = app.processRequest(inmsg);
				out.println(outmsg);
				inmsg = in.readLine();
			}
			
		}
		else {
			out.println("Failed to login");
		}
		
		System.out.println("Terminating");
		app.closeDatabaseConnection();
		dataSocket.close();
		connectionSocket.close();
	}

}
