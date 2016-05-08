
# Restaurant management application

Food to Go is a fictitious, on-demand logistics company that delivers takeout orders from restaurants to customers.
A key part of the application is the restaurant management service, which maintains a database of restaurants that can be queried for availability to deliver an order to a customer at a particular time.
This version of the restaurant management service is written in Java and uses event sourcing and Command Query Responsibility Segregation (CQRS), the Eventuate Platform, Spring Boot, and Redis.

Don't forget to take a look at the other [Eventuate example applications](http://eventuate.io/exampleapps.html).

# Architecture

The following diagram shows the architecture of the application.

<img class="img-responsive" src="http://eventuate.io/demos/eventuate-restaurant-management-architecture.png">

The application is built using CQRS.
The command side service handles creates, updates and deletes.
It defines the following REST endpoints:

* POST /restaurant - create a
* PUT /restaurant/<id> - update a restaurant
* DELETE /restaurant/<id> - delete a restaurant

The command side service stores restaurants in the Eventuate eventstore.

The query side service handles GET requests.
It subscribes to Restaurant events and maintains a denormalized representation of the restaurants using Redis for fast querying.
The query side service defines the following REST endpoints:

* GET /restaurant/<id> - finds a restaurant
* GET /availablerestaurants?zipcode=xx&dayOfWeek=xx&hour=xx&minute=xx - finds restaurants that are available to deliver to the specified zip code at the specified time

# Signing up for Eventuate

To run the application you need credentials for the Eventuate platform.
You can get them by [signing up here](https://signup.eventuate.io/).

# Running Redis

In order to run the tests and to run the application you need Redis.
The easiest way to run Redis is with docker-compose:

```
docker-compose up -d redis
```

# Building the application

Before building and/or running application, you must set an environment variable that tells the application how to connect to Redis:

```
export SPRING_REDIS_HOST=<DOCKER_IP_ADDRESS>
```

You can then build the application using this Gradle command:

```
./gradlew clean build
```

Note: to use Gradle you just need to have the JDK in your path. You do not need to install it.

Note: that the the end-to-end tests in the `e2e-test` module will fail because the service is not running.
Don't worry, the test failures are ignored.

# Running the service

Now that you built the application you can run the application using these commands:

```
docker-compose up -d
```

# Running the end to end tests

Now that the service is running you can run the end-to-end tests:

```
./gradlew :e2e-test:cleanTest :e2e-test:test
```

Note: the environment variable `DOCKER_HOST_IP` must be set to the hostname/IP address of where the service is running, e.g:

```
export DOCKER_HOST_IP=$(docker-machine ip default)
```

See the shell script `build-and-test-all.sh` for more details.

# Got questions?

Don't hesitate to create an issue or [contact us](http://eventuate.io/contact.html).
