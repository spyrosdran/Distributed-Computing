import MySQLdb


class ServerProtocol:

    # Constructor
    def __init__(self):
        self.db = MySQLdb.connect(user="root",
                                  host="localhost",
                                  passwd="",
                                  db="bank")

        self.cur = self.db.cursor()

    # Log in
    def login(self, username, password):
        userID = None
        logged_in = None

        query = "SELECT id, loggedIn FROM accounts WHERE username = '" + username + "' AND password = '" + password + "'"
        self.cur.execute(query)
        rows = self.cur.rowcount

        # Invalid credentials
        if rows == 0:
            return "Invalid credentials"

        for row in self.cur.fetchall():
            userID = int(row[0])
            logged_in = int(row[1])

        if rows == 1 and logged_in == 0:
            return str(userID)
        else:
            # User already logged in
            return "User already logged in"

    # Log out
    def logout(self, userID):
        query = "UPDATE accounts SET loggedIn = " + str(0) + " WHERE id = " + str(userID)
        self.cur.execute(query)
        return "Bye bye"

    # Retrieving and returning balance
    def balance(self, userID):
        balance = -1
        query = "SELECT balance FROM accounts WHERE id = " + str(userID)
        self.cur.execute(query)
        for row in self.cur.fetchall():
            balance = row[0]

        return str(balance)

    # Depositing money
    def deposit(self, userID, money):

        if not self.is_amount_of_money_valid(money):
            return "Invalid amount of money. Please try again..."

        balance = int(self.balance(userID))
        balance += money

        query = "UPDATE accounts SET balance = " + str(balance) + " WHERE id = " + str(userID)
        self.cur.execute(query)
        rows = self.cur.rowcount

        if rows == 1:
            return "Deposit successful"

        return "Deposit failed"

    # Withdrawing money
    def withdraw(self, userID, money):

        if not self.is_amount_of_money_valid(money):
            return "Invalid amount of money. Please try again..."

        balance = int(self.balance(userID))

        # Valid input check
        if int(money) > balance:
            return "You may withdraw up to " + str(balance) + "€"

        balance -= money

        query = "UPDATE accounts SET balance = " + str(balance) + " WHERE id = " + str(userID)
        self.cur.execute(query)
        rows = self.cur.rowcount

        if rows == 1:
            return "Withdraw successful"

        return "Withdraw failed"

    def is_amount_of_money_valid(self, money):
        if money < 0 or not (money % 20 == 0 or money % 50 == 0): return False
        return True
