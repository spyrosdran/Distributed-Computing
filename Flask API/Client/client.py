import requests


# Printing the menu
def print_menu():
    print("\n--------------")
    print("1 --> Balance")
    print("2 --> Deposit")
    print("3 --> Withdraw")
    print("4 --> Stop")


# Prompting the user to select from the menu and checking the input
def select():
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
def read_money():
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

# Initialization
userID = -3
url = "http://127.0.0.1:5000"

# Asking for the credentials
username = input("Username: ")
password = input("Password: ")
data = {"username": username, "password": password}

# Waiting for the response
response = requests.post(url + "/login", data=data)

# If the userID wasn't retrieved, then print the server message
if not response.text.isdigit():
    print(response.text)
# Else proceed
else:
    # Retrieving userID
    userID = response.text

    # User sees the menu and can request for balance, deposit or withdrawal
    while True:
        # Printing the menu and getting user selection
        print_menu()
        selection = select()

        # Act properly according to the user selection
        # Balance Request
        if selection == 1:
            data = {"userID": userID}
            response = requests.post(url + "/balance", data=data)
        # Deposit Request
        elif selection == 2:
            print("Money to deposit: ")
            money = read_money()
            data = {"userID": userID, "money": money}
            response = requests.post(url + "/deposit", data=data)
        # Withdraw Request
        elif selection == 3:
            print("Money to withdraw: ")
            money = read_money()
            data = {"userID": userID, "money": money}
            response = requests.post(url + "/withdraw", data=data)
        # The user has chosen to log out
        else:
            break;

        print("")
        print(response.text)

    # User logs out
    data = {"userID": userID}
    response = requests.post(url + "/logout", data=data)
    print("")
    print(response.text)

