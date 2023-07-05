from client_protocol import ClientProtocol

# Initialization
protocol = ClientProtocol()
userID = -3
request = None
response = None
money = None

# Asking for the credentials
username = input("Username: ")
password = input("Password: ")

# Waiting for the response
response = protocol.login(username, password)

# If the userID wasn't retrieved, then print the server message
if not response.isdigit():
    print(response)
# Else proceed
else:
    # Retrieving userID
    userID = int(response)

    # User sees the menu and can request for balance, deposit or withdrawal
    while True:
        # Printing the menu and getting user selection
        protocol.print_menu()
        selection = protocol.select()

        # Act properly according to the user selection
        if selection == 1:
            response = protocol.get_balance(userID)
        elif selection == 2:
            print("Money to deposit: ")
            money = protocol.read_money()
            response = protocol.deposit(userID, money)
        elif selection == 3:
            print("Money to withdraw: ")
            money = protocol.read_money()
            response = protocol.withdraw(userID, money)
        else:
            response = "Failure"
            break;

        print("")
        print(response)

    # User logs out
    response = protocol.logout(userID)
    print(response)
