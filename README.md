
// how to build, deploy and test

mvn clean install
java -jar target/*.jar

//application url to test

http://localhost:9001/currentweather.html
[port 9001 (i.e. available local port) can be configured in application.properties before maven build.]

//actuator - monitoing/logging url

http://localhost:9002/actuator/
[port 9002 configured in applicaiton.properties]

// technologies used

Java 8, spring boot, maven, embedded tomcat, html, jquery, ajax, mockito, junit, spring boot test etc...

 // todo: logging to be implemented, BDD tests
