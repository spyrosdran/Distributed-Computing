from zeep import Client


class ClientProtocol:

    # Constructor
    def __init__(self):
        self.bank_client = Client('http://localhost:8000/?wsdl')

    # Printing the menu
    def print_menu(self):
        print("\n--------------")
        print("1 --> Balance")
        print("2 --> Deposit")
        print("3 --> Withdraw")
        print("4 --> Stop")

    # Prompting the user to select from the menu and checking the input
    def select(self):
        selection = None

        # Valid input check
        while True:
            try:
                selection = int(input("Select: "))
            except ValueError:
                print("Please, enter a number from 1 to 4")
                continue
            else:
                break

        return selection

    # Validating the amount of money to deposit or to withdraw
    def read_money(self):
        money = None

        # Valid input check
        while True:
            try:
                money = int(input())
            except ValueError or (money < 0 or not (money % 20 == 0 or money % 50 == 0)):
                print("You may only enter positive multiples of 20€ or 50€. Please try again: ")
                continue
            else:
                break

        return money

    # Get Balance Request
    def get_balance(self, userID):
        return self.bank_client.service.balance(userID)

    # Deposit Request
    def deposit(self, userID, money):
        return self.bank_client.service.deposit(userID, money)

    # Withdraw Request
    def withdraw(self, userID, money):
        return self.bank_client.service.withdraw(userID, money)

    # Log In Request
    def login(self, username, password):
        return self.bank_client.service.login(username, password)

    # Log Out Request
    def logout(self, userID):
        return self.bank_client.service.logout(userID)

