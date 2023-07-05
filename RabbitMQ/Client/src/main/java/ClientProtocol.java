import java.util.Scanner;

public class ClientProtocol {

    public int select(){
        Scanner scanner = new Scanner(System.in);
        int selection;

        printMenu();
        selection = scanner.nextInt();

        //Valid input check
        while(selection<1 || selection>4) {
            System.out.println("Please try again");
            printMenu();
            selection = scanner.nextInt();
        }

        return selection;
    }

    public String readMoney(){
        Scanner scanner = new Scanner(System.in);
        int money;

        money = scanner.nextInt();
        while(money<0 || !(money%20==0 || money%50==0)) {
            System.out.println("You may only enter positive multiples of 20€ or 50€. Please try again: ");
            money = scanner.nextInt();
        }

        return Integer.toString(money);
    }

    public String balanceRequest(int userID) {
        return userID + "#balance#-1";
    }

    public String depositRequest(int userID, String money) {
        return userID+"#deposit#"+money;
    }

    public String withdrawRequest(int userID, String money) {
        return userID+"#withdraw#"+money;
    }

    public String logInRequest(String username, String password) {
        return "login#" + username + "#" + password;
    }

    public String logOutRequest(int userID) {
        return "logout#"+ userID +"#-1";
    }

    public void printMenu() {
        //printing the menu
        System.out.println("\n--------------");
        System.out.println("1 --> Balance");
        System.out.println("2 --> Deposit");
        System.out.println("3 --> Withdraw");
        System.out.println("4 --> Stop");
        System.out.println("Select: ");
    }
}
