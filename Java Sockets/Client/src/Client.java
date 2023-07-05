import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	
	private static final String HOST = "localhost";
	private static final int PORT = 1234;

	public static void main(String[] args) throws IOException {
		
		//Opening connection
		Socket dataSocket = new Socket(HOST, PORT);
		try {

			//Initializing variables
			String inmsg, outmsg, username, password;
			InputStream is = dataSocket.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(is));
			OutputStream os = dataSocket.getOutputStream();
			PrintWriter out = new PrintWriter(os,true);
			boolean loggedIn = false;
			Scanner scanner = new Scanner(System.in);
			ClientProtocol app = new ClientProtocol();
			
			//Getting credentials
			System.out.println("Username: ");
	        username = scanner.nextLine();
	        out.println(username);
	        System.out.println("Password: ");
	        password = scanner.nextLine();
	        out.println(password);
			
			//Checking if credentials are valid
			inmsg = in.readLine();
			loggedIn = app.checkSignInStatus(inmsg);
			
			//If the credentials are valid, then the user proceeds
			if(loggedIn) {
				System.out.println("Sign in successful");				
				outmsg = app.prepareRequest();
				
				while(!outmsg.equals("stop")) {	
					out.println(outmsg);
					inmsg = in.readLine();
					app.processReply(inmsg);
					outmsg = app.prepareRequest();
				}
			}
			else {
				System.out.println("Invalid credentials. Please try again.");
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dataSocket.close();
			System.out.println("Connection closed");
		}	

	}

}
