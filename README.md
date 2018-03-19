
1.  java version "1.8.0_161
    Apache Maven 3.3.9
2.  Build
    $mvn clean install
3.  Preparing repots
    $mvn site
    $mvn site:stage
    check: <project>/target/stage/index.html
4.  Run in a jetty server:
    mvn jetty:run
    Once started, the application should be available at
    http://localhost:8081/

