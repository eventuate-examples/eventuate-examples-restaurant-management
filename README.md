
# Restaurant management application

Food to Go is a fictitious, on-demand logistics company that delivers takeout orders from restaurants to customers.
A key part of the application is the restaurant management service, which maintains a database of restaurants that can be queried for availability to deliver an order to a customer at a particular time.
This version of the restaurant management service has an architecture based on microservices, event sourcing and Command Query Responsibility Segregation (CQRS).
It is written in Java and uses the Eventuate Platform, Spring Boot, and Redis.

Don't forget to take a look at the other [Eventuate example applications](http://eventuate.io/exampleapps.html).

# Architecture

The following diagram shows the architecture of the application.

<img class="img-responsive" src="http://eventuate.io/demos/eventuate-restaurant-management-architecture.png">

The application is built using CQRS.
The command side service handles creates, updates and deletes.
It defines the following REST endpoints:

* POST /restaurant - create a restaurant
* PUT /restaurant/*id* - update a restaurant
* DELETE /restaurant/*id* - delete a restaurant

The command side service stores restaurants in the Eventuate eventstore.

The query side service handles GET requests.
It subscribes to Restaurant events and maintains a denormalized representation of the restaurants using Redis for fast querying.
The query side service defines the following REST endpoints:

* GET /restaurant/*id* - finds a restaurant
* GET /availablerestaurants?zipcode=xx&dayOfWeek=xx&hour=xx&minute=xx - finds restaurants that are available to deliver to the specified zip code at the specified time

# Signing up for Eventuate

To run the application you need credentials for the Eventuate platform.
You can get them by [signing up here](https://signup.eventuate.io/).

# Building the application

You can then build the application using this Gradle command:

```
./gradlew assemble
```

Note: to use Gradle you just need to have the JDK in your path. You do not need to install it.

# Running the service

Now that you built the application you can run the application using these commands:

```
docker-compose up -d
```

# Using the application

Now that the application is running you can use `curl` or some other tool to invoke the REST endpoints.

# Got questions?

Don't hesitate to create an issue or [contact us](http://eventuate.io/contact.html).
