About cities-graph
------------------

cities-graph is a single API microservice can be used to determines if two cities are connected.

Two cities are considered connected if there’s a series of roads that can be traveled from one city to another.

http://localhost:8080/connected?origin=city1&destination=city2

The API responds with ‘yes’ if city1 is connected to city2, ’no’ if city1 is not connected to city2. Any unexpected input should result in a ’no’ response.


How to run
----------

The program is tested with java version "1.8.0_131", you will need java 1.8 runtime to run.
 
java -jar target/cities_graph-0.0.1-SNAPSHOT.jar

It use <a href="city.txt">./city.txt</a> as data file

The file contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.

Also, you can run the program with different file as following:

java -jar target/cities_graph-0.0.1-SNAPSHOT.jar --datafile=test/data.txt

How to run unit tests
---------------------

The project uses maven 3.6, you will need java jdk 1.8+ and maven 3.6+ to build

mvn test

It will run unit test and generate a html version of <a href="unit-test-coverage-report/index.html">unit test coverage reports</a> in ./unit-test-coverage-report folder.

Check <a href="test/mvn-test.txt">test/mvn-test.txt</a> for a screen dump of run mvn test
Check <a href="unit-test-coverage-report/index.html">unit-test-coverage-report/index.html</a> for code coverage of the test

Full 100% code coverage is archieved.

The file <a href="test/curl-test.txt">test/curl-test.txt</a> is a dozen manual curl tests result.

How to how to build 
---------------------

mvn package

This command will generate target/cities_graph-0.0.1-SNAPSHOT.jar file.

Swagger API Doc / UI
----------------------

Run

java -jar target/cities_graph-0.0.1-SNAPSHOT.jar

goto url: http://localhost:8080/swagger-ui.html

You can start to access Swagger Api Documentation interface.

<a href="test/swagger-page-1.png">test/swagger-page-1.png<>/a> and <a href="test/swagger-page-2.png">test/swagger-page-2.png</a> two screen shots of the web page.



