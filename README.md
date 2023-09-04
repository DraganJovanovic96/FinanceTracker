README - Running a Spring Boot Application with PostgreSQL
This README provides instructions on how to set up and run a Spring Boot application with PostgreSQL as the database. This guide assumes that you have have Java and Maven installed on your system.

Prerequisites
Before you begin, ensure you have the following installed on your system:

Java Development Kit (JDK): You'll need a compatible JDK installed. 
Spring Boot 2.5.x (or later) and Java 18.

Maven: You'll need Maven installed to build and manage dependencies for your Spring Boot project.

PostgreSQL: Install and set up PostgreSQL on your system. Make sure you have the database server running and have the necessary database credentials (username and password).

Project Setup
Clone or download the Spring Boot project from your version control system or obtain the project source code.

Open the project in your preferred integrated development environment (IDE).

Navigate to the project's root directory.

Configure PostgreSQL Database
I recommend that you set it in Environment variable
username=postgres;password=root
as an example of credentials

Configurate active profile 
set the active profile as LOCAL
spring.profiles.active=LOCAL

Update the following properties to configure your PostgreSQL database:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
Replace your_database_name with your PostgreSQL database information or create the table with the name finances.

Build and Run the Application
You can build and run your Spring Boot application using your IDE:

Open your IDE and import the project.

Build the project to resolve dependencies.

Find the main class (annotated with @SpringBootApplication) in this case FinanceTrackerApplication and run it as a Java application.

Your Spring Boot application should start, and you'll see log messages in the console.

Access the Application
Once your Spring Boot application is running, you can access it through tools like Postman or curl to interact with the RESTful endpoints.

This application has security enabled, you will need to Register and use the received JWT to access other endpoints in the application.

Some endpoints are admin only so make sure that your user has admin privileges. Here's an example of the user:

{
    "firstname" : "John",
    "lastname" : "Doe",
   "email" : "hello@gmail.com",
   "password" : "password",
   "role" : "ADMIN",
   "dateOfBirth" : "1983-04-03",
   "mobileNumber" : "0639561297",
   "address" : "Novi Sad",
   "imageUrl" : "None for now"
}

This application has all of the optional tasks done,so make sure you try them out.
The default URL for your application is http://localhost:8080/api/v1/.
