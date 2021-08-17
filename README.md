#Restaurant search API

This application allows the user to check available restaurants based on up to 5 five criteria: name, distance, rating, price, and cuisine. I've built this project as a webserver because we can create a user interface in the future to improve the user experience.

I made some assumptions based on the assessment description:
The users must provide at least one criterion, but they can use up to five if they want.
Search by name and cuisine will return a response when it matches partial or exact.
We will handle the distance, price average, and rating as Integer values.
We have some limits for each field:
distance: 1 to 10
rating: 1 to 5
price: 10 to 50
We will always return the top five results if it's available and we will sort them by distance first, then high rating and last but not least lower price.
---
#How to Run

**Requirements:**
* Java JDK 11

Follow the instructions to build and run the project locally.

```shell
## It will run the automated tests and build the project
./mvnw clean package

## To up and run the project, run the following command.
java -jar target/restaurant-search-0.0.1-SNAPSHOT.jar --server.port=8081

## Voilà!, you can perform some requests.
curl "http://localhost:8081/restaurant?cuisine=chinese&rating=5"
curl "http://localhost:8081/restaurant?cuisine=American&rating=10"          
```
---
#Greetings

I pretty like did this assessment. I tried to use some design patterns and keep the code simple.
I'm going to appreciate it if you give your feedback on my implementation.

Thank you!