# Getting Started

The Spring Boot application is developed using TDD approach to address [Software Development Books](https://stephane-genicot.github.io/DevelopmentBooks.html) kata.

### Pre-requisites to explore the project
* Java 17
* Maven 3
* Git
* IntelliJ IDEA(preferred) or any other IDE
* Postman

### How to run the application
* Clone the project from GitHub to your local system using 'git clone' command
* Open the project in IntelliJ IDEA as select Maven Project type when the popup appears 
* Type and run 'mvn clean verify' to download all maven dependencies by pressing CTRL twice
* Find 'BookStoreApiApplication.java' and run 'main' method.
* Alternatively, you can create a maven configuration in IntelliJ using 'spring-boot:run'
* The application will run on 'http://localhost:8080'
* Import 'Book-Store.postman_collection.json' as Postman collection
* Several example of request json have been provided in the Postman collection
* The POST endpoint will be available [here](http://localhost:8080/bookstore/total-price)
