import java.util.ArrayList;
import java.util.Scanner;

public class ClientProtocol {

	public String prepareRequest() {
		
		//Initializing variables
		ArrayList<String> input = new ArrayList<String>();
		int selection, amount;
		boolean isAmountValid = false;
		Scanner scanner = new Scanner(System.in);
		
		printMenu();
		selection = scanner.nextInt();
		
		//Checking if user writes an integer from 1 to 4
		while(selection<1 || selection>4) {
			System.out.println("Please try again");
			printMenu();
			selection = scanner.nextInt();
		}
		
		/*
		Preparing the request 
		1 --> balance request ("balance")
		2 --> deposit request ("deposit#xxxx")
		3 --> withdraw request ("withdraw#xxxx")
		4 --> stop
		*/
		switch(selection) {
		
			//Balance request
			case 1:
				//return "balance" as a request
				return "balance";
				
			//Deposit
			case 2:
				//input contains an action (deposit or withdraw) and an amount of money separated with #
				input.add("deposit");
				System.out.println("Amount to deposit: ");
				amount = scanner.nextInt();
				
				//Valid input check
				isAmountValid = checkAmount(amount);
				
				while(!isAmountValid) {
					System.out.println("Enter a valid amount of money. You may only give 20€ and 50€ bills.");
					System.out.println("Amount to deposit: ");
					amount = scanner.nextInt();
					isAmountValid = checkAmount(amount);
				}
				
				input.add(Integer.toString(amount));
				break;
				
			//Withdraw
			case 3:
				//input contains an action (deposit or withdraw) and an amount of money separated with #
				input.add("withdraw");
				System.out.println("Amount to withdraw: ");
				amount = scanner.nextInt();
				
				//Valid input check
				isAmountValid = checkAmount(amount);
				
				while(!isAmountValid) {
					System.out.println("Enter a valid amount of money. We may only give you 20€ and 50€ bills.");
					System.out.println("Amount to withdraw: ");
					amount = scanner.nextInt();
					isAmountValid = checkAmount(amount);
				}
				
				input.add(Integer.toString(amount));
				break;
			
			//Stop
			default:
				//return "stop" as a request
				return "stop";
		}
		
		//If the request isn't balance or stop then we return a string with the action and the money
		//e.g. deposit#100 or withdraw#50
		return input.get(0)+"#"+input.get(1);
		
	}
	
	public void processReply(String inmsg) {
		//Just printing the server reply
		System.out.println(inmsg);		
	}
	
	void printMenu() {
		//printing the menu
		System.out.println("1 --> Balance");
		System.out.println("2 --> Deposit");
		System.out.println("3 --> Withdraw");
		System.out.println("4 --> Stop");
		System.out.println("Select: ");
	}
	
	boolean checkAmount(int amount) {
		//checking if the amount of money is positive and broken into 20€ and 50€ bills
		if(amount>=0 && (amount%20==0 || amount%50==0)) return true;
		return false;
	}

	public boolean checkSignInStatus(String inmsg) {
		//checking if logging into the system was successful
		if(inmsg.equals("success")) return true;
		return false;
	}

}
