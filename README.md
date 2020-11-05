# Master Thesis project in SpringBoot framework
Online bookshop RESTful web application built in SpringBoot framework for **testing RESTful methods for their performances and resource consumption** as part of my Master Thesis. This app uses:
* **Hibernate ORM** for data mapping
* **Spring Data JPA** for data querying
* **Postgres 11 DB**
* **ProjectLombok** for reducing boiler-plate code
* **MapStruct** for quick bi-directional conversion of DTO and Model classes
* **Liquibase** for data migration 
* **Micrometer Metrics** for exposing needed metrics via **JMX**


## How to launch app
1) First, in order for application to be able to be run, create `sb_app_db_ver2` databasein your DB tool (such as pgAdmin4, DBeaver, DataGrip, etc.)
2) Make sure that all parameters for database access (database name, db username, password, etc.) located in *application.properties* file are properly set
3) Change location in your terminal to be inside of "SpringBootMasterThesis" project
4) Run `mvn clean package` command in terminal (Linux)
5) Run `mvn spring-boot:run` in terminal (Linux) or via by executing `main` method of `SpringBootMasterThesisApplication.java` class in your IDE.


## REST API endpoints
Here are some API endpoints to target (using Postman or similar tool) including some JSON bodies for HTTP Request:

### GET methods
Get single Author with id=1 [http://localhost:8080/api/authors/1](http://localhost:8080/api/authors/1)<br/>
Get all Categories [http://localhost:8080/api/categories](http://localhost:8080/api/categories)<br/>
Get single Book[http://localhost:8080/api/books/1](http://localhost:8080/api/books/1)<br/>

## POST methods
Create Author [http://localhost:8080/api/authors](http://localhost:8080/api/authors)

```json
{
	"firstName": "Franz",
	"lastName": "Kafka"
}
```

<br/>Create Category [http://localhost:8080/api/categories](http://localhost:8080/api/categories)
```json
{
	"name" : "Crime",
	"isDeleted": false
}
```

<br/>Create Book [http://localhost:8080/api/books](http://localhost:8080/api/books)
```json
{
	"title": "The Trial",
	"price": 15.50,
	"amount": 300,
	"isDeleted": false,
	"authors": [1],
	"categories": [1]
}	
```

Create Order [http://localhost:8080/api/orders?username=mica](http://localhost:8080/api/orders?username=mica)
**NOTE**: In order this method to work `user` table must contain a row with column `username` that has a value 'mica'.<br/>
JSON Body:
```json
{
    "orders": [
        {
            "bookId": 1,
            "amount": 4
        }
    ],
    "totalPrice": 62.00
}
```

### PUT and DELETE methods
Endpoints are same like in GET method section and you need to change **same** JSON body yourself by following format from POST methods section (JSON bodies are of course for PUT methods)
