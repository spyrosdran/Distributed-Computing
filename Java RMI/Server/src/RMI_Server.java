import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMI_Server {
	
	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT; // 1099

	public static void main(String[] args) throws NotBoundException, IOException {

		// Should be first, especially if server is NOT localhost
				System.setProperty("java.rmi.server.hostname", HOST);
		                
				// Remote object creation
				Bank_Interface robj = new Bank_Interface_Implementation();
				
				// Choose the appropriate method:
				// get if RMI registry is already running, create otherwise 
				// Registry registry = LocateRegistry.getRegistry(HOST, PORT);
				Registry registry = LocateRegistry.createRegistry(PORT);
				
				// Bind remote object to a name and publish in rmi registry
		                
				String rmiObjectName = "Bank_Interface";
				registry.rebind(rmiObjectName, robj);
				System.out.println("Remote object bounded.");
				
				// Server is running until we press a key
				
				System.out.println("Press <Enter> for exit.");
				System.in.read();
				
				// Free space and clear rmi registry
				
				UnicastRemoteObject.unexportObject(robj, true);
				registry.unbind(rmiObjectName);
				System.out.println("Remote object unbounded.");

	}

}
