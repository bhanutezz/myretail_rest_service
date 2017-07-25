# myRetail RESTful Service Proof of Concept

## Case Study Overview
myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 

The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 

Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.

[myretail_rest_service](https://github.com/bhanutezz/myretail_rest_service) performs following actions:
* Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number
    Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value":              13.49,"currency_code":"USD"}}
* Product name is reteieved from API and pricing information is retrieved from MongoDB.
* Performs HTTP PUT request by processing JSON data similar to GET response and update the product price in MongoDB.
## Technology Stack
Goal is to make this web service as efficient as possible so that it can be easily understood and developed.
For this purpose, I have used below technoloy stack which are widely used to integrate different technolgies together.
* Java 1.8(1.7 is enough to run this application)
* Spring webmvc 4.3.6.RELEASE
* MongoDB 3.4.2
* Apache Maven 3.3.9
* Apache Tomcat 8.0.33
* Robo 3T(RoboMongo tool to manage MongoDB database) 
* POSTMAN chrome plugin(To monitor REST GET and PUT request)
* Eclipse IDE
* Github to clone the projecct and maintain code repository

## Setup and Running [myretail_rest_service](https://github.com/bhanutezz/myretail_rest_service)
To run this application you need to install below technologies,
* Clone this project and import it into eclipse.
* Install Java 1.8
* Install Tomcat 
* Install MongoDB
   * Create database, collections and insert docuemnts
   * Create Database: use myretail
   * Create Collection: db.createCollection("product_pricing")
   * Inser document: db.product_pricing.insert({"value" : 14.49, "currency_code" : "USD", "productid" : 13860432})
   **Note:** You can do all the above operatons using Robo 3T tool
 * Install Maven and build the war file using "mvn clean install" command
 * Deploy the war file in tomcat/webapps folder
 * Start the MongoDB process
 * start the tomcat server by executing startup shell scrip file from bin folder.
 * Launch POSTMAN application
   * http://localhost:8080/myretail_rest_service/products/13860429 run GET request by selecting GET option
   * http://localhost:8080/myretail_rest_service/products/13860429 run PUT request by selecting PUT option 
   after inserting raw JSON data similar to GET response. You can check the updated price in MongoDB.


