# product-info

## myretail-product-info api

The repository contains source code for myretail-product-info-api RESTFUL service. The service provides GET and PUT functionality .

* GET product id, name, and pricing for a tcin (product ID in this case) passed as input
* UPDATE pricing for an id passed as an input along with response body in database

## Technology

Role	Technology
Source Control	Git
Runtime	Java 8
Database	Redis
HTTP Server	Spring Boot (embedded Tomcat)
Task Automation	Gradle
Unit Tests	JUnit
IDE	Intellij Idea

## Getting Setup

To begin, please install the following on your machine:

Technology	Version	Installation Link
Java SE Development Kit	1.8u51 (64-bit)	http://www.oracle.com/technetwork/java/javase/downloads/java-archive-javase8-2177648.html#jdk-8u51-oth-JPR
Intellij Idea IDE for J2EE Developers	Community Edition	https://www.jetbrains.com/idea/download/
Redis Download, Install and Setup: For Mac: https://redis.io/topics/quickstart , For Windows:  https://github.com/ServiceStack/redis-windows 
After downloading and installing the softwares above, clone this repository or download from git and import it as an existing gradle project into your Intellij Idea workspace.

## Insert data into Redis for the first time:

1) Can be done using Redis CLI (OR)
2) Run Application-Data-Insert.java in this repository

## Method URI	Example	Action
GET	/product-info/v1/{id}	http://localhost:8080/product_info/v1/1386428	Retrieves Item data with id 
PUT	/product-info/v1/{id}	http://localhost:8080/product_info/v1/15117729?price=5.99&currency_code=USD	Updates pricing data for id
For PUT , please provide response param as provided in link above.(both price value and currency code are mandatory parameters)

    
