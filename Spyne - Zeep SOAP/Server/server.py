from spyne import Application, rpc, ServiceBase
from spyne.protocol.soap import Soap11
from spyne.model.primitive import Integer
from spyne.model.primitive import String
from spyne.server.wsgi import WsgiApplication
from wsgiref.simple_server import make_server
import logging
import MySQLdb


class BankService(ServiceBase):
    # Establishing database connection
    db = MySQLdb.connect(user="root",
                         host="localhost",
                         passwd="",
                         db="bank")

    cur = db.cursor()

    @rpc(String, String, _returns=String)
    def login(self, username, password):

        # Initialization
        userID = None
        logged_in = None

        # Retrieving the userID
        query = "SELECT id, loggedIn FROM accounts WHERE username = '" + username + "' AND password = '" + password + "'"
        BankService.cur.execute(query)
        rows = BankService.cur.rowcount

        # Invalid credentials
        if rows == 0:
            return "Invalid credentials"

        for row in BankService.cur.fetchall():
            userID = int(row[0])
            logged_in = int(row[1])

        # If user exist and isn't logged in, then return the userID and update the database
        if rows == 1 and logged_in == 0:
            query = "UPDATE accounts SET loggedIn = 1 WHERE id = " + str(userID)
            BankService.cur.execute(query)
            return str(userID)
        else:
            # User already logged in
            return "User already logged in"

    @rpc(Integer, _returns=String)
    def logout(ctx, userID):
        # Updating the database for the log out
        query = "UPDATE accounts SET loggedIn = 0 WHERE id = " + str(userID)
        BankService.cur.execute(query)
        return "Bye bye"

    @rpc(Integer, _returns=String)
    def balance(ctx, userID):
        # Retrieving balance
        balance = -1
        query = "SELECT balance FROM accounts WHERE id = " + str(userID)
        BankService.cur.execute(query)
        for row in BankService.cur.fetchall():
            balance = row[0]

        return "Your balance is " + str(balance) + "€"

    @rpc(Integer, Integer, _returns=String)
    def deposit(ctx, userID, money):
        # Valid amount of money check
        if money < 0 or not (money % 20 == 0 or money % 50 == 0):
            return "Invalid amount of money. Please try again..."

        # Retrieving the balance
        balance = -1
        query = "SELECT balance FROM accounts WHERE id = " + str(userID)
        BankService.cur.execute(query)
        for row in BankService.cur.fetchall():
            balance = row[0]

        # Updating the balance in the database
        balance += money

        query = "UPDATE accounts SET balance = " + str(balance) + " WHERE id = " + str(userID)
        BankService.cur.execute(query)
        rows = BankService.cur.rowcount

        # Returning the appropriate message
        if rows == 1:
            return "Deposit successful"

        return "Deposit failed"

    @rpc(Integer, Integer, _returns=String)
    def withdraw(ctx, userID, money):
        # Valid amount of money check
        if money < 0 or not (money % 20 == 0 or money % 50 == 0):
            return "Invalid amount of money. Please try again..."

        # Retrieving the balance
        balance = -1
        query = "SELECT balance FROM accounts WHERE id = " + str(userID)
        BankService.cur.execute(query)
        for row in BankService.cur.fetchall():
            balance = row[0]

        # Valid input check
        if int(money) > balance:
            return "You may withdraw up to " + str(balance) + "€"

        # Updating the balance in the database
        balance -= money

        query = "UPDATE accounts SET balance = " + str(balance) + " WHERE id = " + str(userID)
        BankService.cur.execute(query)
        rows = BankService.cur.rowcount

        # Returning the appropriate message
        if rows == 1:
            return "Withdraw successful"

        return "Withdraw failed"


# Binding the service and starting the server
application = Application([BankService], 'BankService.soap',
                          in_protocol=Soap11(validator='lxml'),
                          out_protocol=Soap11(pretty_print=True))

wsgi_application = WsgiApplication(application)

logging.basicConfig(level=logging.DEBUG)
logging.getLogger('spyne.protocol.xml').setLevel(logging.DEBUG)

logging.info("listening to http://127.0.0.1:8000")
logging.info("wsdl is at: http://localhost:8000/?wsdl")

server = make_server('127.0.0.1', 8000, wsgi_application)
server.serve_forever()
