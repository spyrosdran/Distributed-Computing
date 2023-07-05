
## Distributed Computing
✨ This repository is dedicated to implementing a very simple bank system, which provides the following functionality:

 - Log In
 - Log Out
 - Balance
 - Deposit
 - Withdraw

## Technologies Used 
🪄 You can see implementations using the following technologies:

 - Flask REST API
 - Java RMI
 - Java Sockets
 - RabbitMQ
 - SOAP with Spyne and Zeep
 - gRPC

## Database
💾 Every version of the bank system relies on a database. Use the **Database/bank.sql** file to import the database. You may use tools like XAMPP for **phpMyAdmin**, in order to directly **import the database** from the above file.

## Dockerfiles
Some of the projects contain Dockerfiles, which you can use to build images and containers. Build and run the images like this:

```docker build -t <tag> .```

```docker run -p <in-container-port>:<actual-port> <tag>```

Then proceed to test the clients.
