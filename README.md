# ITS
Interstellar Transport System

# Requirements
For building and running the application you need:
- JDK 1.8
- Maven 3+
- Angular CLI 9+
- Node 12 +

# Running the application locally
There are several ways to run ITS application on your local machine. We can either run both frontend and backend application separately as 2 separated application or both as one application.

If you want to run them separately, here is what you need to do.

For the frontend application, you can run the following command from the root of the repository:
```shell
cd .\src\main\frontend\
npm install
ng serve
```
This will start the application on port 4200 of the host machine: http://localhost:4200/

For the backend application, one way is to execute the `main` method in the `za.co.discovery.assignment.ItsApplication` class from your preferred IDE.
Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:
```shell
mvn spring-boot:run
```
The application will start and run on port 8080 of your local machine.


If you decide to run both frontend and backend applications as 1, you can run the following command from the root of the repository:
```shell
cd .\src\main\frontend\
npm install
ng build --prod
```
This will build/compile the frontend application and create a static folder to be serve by the backend.
The next step is to run the following command from the root of the repository:
```shell
mvn clean install package
java -jar .\target\freddy-1.0.0_SNAPSHOT.jar
```
The first command will also build the backend and create an artifact (jar file) that containts the frontend.
The next command starts the application on sport 8080: http://localhost:8080.

The Swagger Documentation of the application will be accessible from this Url: http://localhost:8080/swagger-ui.html


# About ITS
Interstellar Transport System (ITS) is an application that allows us to find the shortest path between 2 nodes/planets.
It uses Dijkstra's algorithm to determine the shortest path between the nodes.
For more info about Dijkstra's algorithm, you can visit this link: https://www.youtube.com/watch?v=gdmfOwyQlcI
