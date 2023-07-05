import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.BankInterfaceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        //Starting the server and listening to port 8999

        try {
            Server server = ServerBuilder.forPort(8999).addService(new BankInterfaceImpl()).build();

            server.start();
            System.out.println("Server started at " + server.getPort());
            server.awaitTermination();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
        }
    }
}
