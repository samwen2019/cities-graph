About cities-graph
------------------

cities-graph is a single API microservice can be used to determines if two cities are connected.

Two cities are considered connected if there’s a series of roads that can be traveled from one city to another.

http://localhost:8080/connected?origin=city1&destination=city2

The API responds with ‘yes’ if city1 is connected to city2, ’no’ if city1 is not connected to city2. Any unexpected input will result in a ’no’ response.

city.txt is an example of input data file. The file contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.

How to build 
-------------

The project uses maven 3.6, you will need git, java jdk 1.8 and maven 3.6 to build:

git clone https://github.com/samwen2019/cities-graph.git

cd cities-graph

mvn package

This command will generate target/cities-graph-0.0.1-SNAPSHOT.jar.

How to run
----------

Assuming current directory is cities-graph, 

java -jar target/cities-graph-0.0.1-SNAPSHOT.jar

It uses <a href="city.txt">./city.txt</a> in current directory as default data file.

Also, you can run the program with a specified file as following:

java -jar target/cities-graph-0.0.1-SNAPSHOT.jar --datafile=test/data.txt

How to test
------------

mvn test

It will run unit tests and generate an html version of unit tests coverage reports in unit-test-coverage-report folder.

Check <a href="test/mvn-test.txt">test/mvn-test.txt</a> for a screen dump.

The file <a href="test/curl-test.txt">test/curl-test.txt</a> includes a dozen manual curl tests and results.

Full 100% code coverage is achieved.

Check unit-test-coverage-report/index.html for code coverage of the test.

Here are two screen shots of the test coverage reports:

![jacoco-1.png](https://github.com/samwen2019/cities-graph/raw/master/unit-test-coverage-report/jacoco-1.png)

![jacoco-2.png](https://github.com/samwen2019/cities-graph/raw/master/unit-test-coverage-report/jacoco-2.png)

How to release
---------------

mvn install

This command will compile the code, run unit tests, and generate reports, java docs and multiple files:

- cities-graph-0.0.1-SNAPSHOT.pom
- cities-graph-0.0.1-SNAPSHOT-sources.jar
- cities-graph-0.0.1-SNAPSHOT.jar
- cities-graph-0.0.1-SNAPSHOT-javadoc.jar

Your local maven repository will receive copies for the 4 snapshot files.

Check javadocs/index.html for java documents for the project.

![javadocs.png](https://github.com/samwen2019/cities-graph/raw/master/javadocs/javadocs.png)

Swagger API Doc / UI
----------------------

When the program is running, the url to access Swagger API Documentation Interface is:

http://localhost:8080/swagger-ui.html.

Here are a screen shots of the web pages:

![swagger-page-1.png](https://github.com/samwen2019/cities-graph/raw/master/test/swagger-page-1.png)

How it works
------------

The program uses LinkedList to store adjacency cities of a city. A HashMap is used to store all cities and their adjacency cities. In the HashMap, a city name is a key and the value is the adjacency cities of the city.

BFS (breadth first search) is used. The searching for destination city is started from origin city. It checks through all adjacency cities first. If an adjacency city does not match destination and not visited before, it saves the city in a queue. After all adjacency cities are visited, it pops out the first city in the queue and used it to start a next round. It will return immediately if a match is found. If the queue is empty, it means all possibilities are exhausted and destination cannot be reached.

Todo List
----------

1. curl-test.txt can be convert to a test script
2. Dockerfile to deliver final release as docker image
3. A script to automate release process
4. The requirement, "Any unexpected input should result in a ’no’ response" is too strong. It breaks conventions. It is necessary to get into more details.
5. In a sense, the swagger api url breaks above requirement.
6. After No. 4 answered, we can decide to turn on Spring Boot Actuator.
7. Enable Spring Cloud supports to run the microservice in spring cloud environment. 