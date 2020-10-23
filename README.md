
# Restaurant management application

Food to Go is a fictitious, on-demand logistics company that delivers takeout orders from restaurants to customers.
A key part of the application is the restaurant management service, which maintains a database of restaurants that can be queried for availability to deliver an order to a customer at a particular time.
This version of the restaurant management service has an architecture based on microservices, event sourcing and Command Query Responsibility Segregation (CQRS).
It is written in Java and uses the Eventuate Platform, Spring Boot, and Redis.

Don't forget to take a look at the other [Eventuate example applications](http://eventuate.io/exampleapps.html).

# Got questions?

Don't hesitate to create an issue or see

* [Website](http://eventuate.io)
* [Mailing list](https://groups.google.com/d/forum/eventuate-users)
* [Slack](https://eventuate-users.slack.com). [Get invite](https://eventuateusersslack.herokuapp.com/)
* [Contact us](http://eventuate.io/contact.html).

# Architecture

The following diagram shows the architecture of the application.

<img class="img-responsive" src="http://eventuate.io/demos/eventuate-restaurant-management-architecture.png">

The application is built using CQRS.
The command side service handles creates, updates and deletes.
It defines the following REST endpoints:

* POST /restaurant - create a restaurant
* PUT /restaurant/*id* - update a restaurant
* DELETE /restaurant/*id* - delete a restaurant

The command side service stores restaurants in the Eventuate event store.

The query side service handles GET requests.
It subscribes to Restaurant events and maintains a denormalized representation of the restaurants in Redis for fast querying.
The query side service defines the following REST endpoints:

* GET /restaurant/*id* - finds a restaurant
* GET /availablerestaurants?zipcode=xx&dayOfWeek=xx&hour=xx&minute=xx - finds restaurants that are available to deliver to the specified zip code at the specified time

# About Eventuate&trade;

![](http://eventuate.io/i/logo.gif)

The application is built using [Eventuate](http://eventuate.io/), which is an application platform for writing transactional microservices.
It provides a simple yet powerful event-driven programming model that is based on event sourcing and Command Query Responsibility Segregation (CQRS).
Eventuate solves the distributed data management problems inherent in a microservice architecture.
It consists of a scalable, distributed event store and client libraries for various languages and frameworks including Java, Scala, and the Spring framework.

* [Eventuate Local](http://eventuate.io/usingeventuate.html) - an open-source event store that is built using MySQL and Kafka

# Building and running the application.

This is a Java 8, Gradle project. However, you do not need to install Gradle since it will be downloaded automatically. You just need to have Java 8 installed.

First, build the application:

```
./gradlew assemble
```

Next, you can launch the application using [Docker Compose](https://docs.docker.com/compose/)

```
./gradlew mysqlbinlogComposeUp
```

## Using the application

Finally, you can use the Swagger UI provided by the services to create, update, delete and view restaurants:

* `http://localhost:8081/swagger-ui.html` - Restaurant command-side service
* `http://localhost:8082/swagger-ui.html` - Restaurant query-side service

(Hint: best to open these URLs in separate tabs)

# Got questions?

Don't hesitate to create an issue or see

* [Website](http://eventuate.io)
* [Mailing list](https://groups.google.com/d/forum/eventuate-users)
* [Slack](https://eventuate-users.slack.com). [Get invite](https://eventuateusersslack.herokuapp.com/)
* [Contact us](http://eventuate.io/contact.html).
