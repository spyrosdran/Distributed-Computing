import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RMI_Client {

	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT; // 1099
	
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		try {
			Registry registry = LocateRegistry.getRegistry(HOST, PORT);
			String rmiObjectName = "Bank_Interface";
			Bank_Interface ref = (Bank_Interface)registry.lookup(rmiObjectName);
			
			//Asking for credentials
			System.out.println("Username: ");
	        String username = scanner.nextLine();
	        System.out.println("Password: ");
	        String password = scanner.nextLine();
	        
	        //If the credentials are valid, then user logs in
	        int userID = ref.logIn(username, password);
	        
	        if(userID > 0) {
	        	System.out.println("Successfully logged in\n");
	        	
	        	printMenu();
	        	int selection = select();
	        	int balance;
	        	int money;
	        	boolean isActionSuccessful = false;
	        	
	        	//Print the menu and communicate with the server until
	        	//the user decides to stop
	        	while(selection!=4) {
	        		
	        		balance = ref.requestBalance(userID);
	        		
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
	        					
	        					isActionSuccessful = ref.withdraw(userID, money);
		        				if(isActionSuccessful)	System.out.println("Money withdrawn");
		        				else System.out.println("Withdraw failed");
	        					
	        				}
	        				else System.out.println("Withdraw amount should be lower or equal to your balance");
	        				
	        				break;
	        			
	        			//Deposit
	        			case 3:
	        				System.out.println("Money to deposit: ");
	        				money = readMoney();
	        				
	        				isActionSuccessful = ref.deposit(userID, money);
	        				if(isActionSuccessful)	System.out.println("Deposit successful");
	        				else System.out.println("Deposit failed");
	        				
	        			}
	        			
	        		} else System.out.println("Error while retrieving balance");
	        		
	        		printMenu();
	        		selection = select();
	        	}
	        	
	        	//The user decides to stop
	        	ref.logOut(userID);
	        	System.out.println("Bye bye");
	    		
	        }
	        else if (userID == -1) {
	        	System.out.println("Invalid credentials. Please try again.");
	        }
	        else {
	        	System.out.println("User already logged in from another client.");
	        }
	        
	        scanner.close();
	        
	        
		} catch (RemoteException re) {
			System.out.println("Remote Exception");
			re.printStackTrace();
		} catch (Exception e) {
			System.out.println("Other Exception");
			e.printStackTrace();
		}

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
