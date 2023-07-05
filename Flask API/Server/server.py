from flask import Flask, request
from flask_cors import CORS
from server_protocol import ServerProtocol

# Creating the app, editing the CORS policy and initializing the server protocol
app = Flask("BankAPI")
CORS(app)
protocol = ServerProtocol()


# Log In Request
@app.route("/login", methods=["POST"])
def login():
    args = request.form
    username = args["username"]
    password = args["password"]
    return protocol.login(username, password)


# Log Out Request
@app.route("/logout", methods=["POST"])
def logout():
    args = request.form
    userID = args["userID"]
    return protocol.logout(userID)


# Balance Request
@app.route("/balance", methods=["POST"])
def balance():
    args = request.form
    userID = args["userID"]
    return "Your balance is " + protocol.balance(userID) + "€"


# Deposit Request
@app.route("/deposit", methods=["POST"])
def deposit():
    args = request.form
    userID = args["userID"]
    money = int(args["money"])
    return protocol.deposit(userID, money)


# Withdraw Request
@app.route("/withdraw", methods=["POST"])
def withdraw():
    args = request.form
    userID = args["userID"]
    money = int(args["money"])
    return protocol.withdraw(userID, money)


app.run()
