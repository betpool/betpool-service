To install DataStax Distribution of Apache Cassandra follow:
http://docs.datastax.com/en/cassandra/3.x/cassandra/install/installDeb.html

To setup db:
cqlsh -f src/main/db/create.cql

To build and run the project:
mvn clean package
java -jar target/{name}.jar

Query examples:
curl -v -H "Accept: application/hal+json" -H "Content-Type: application/json" -H "userId: 1000" -X POST -d '{"eventId": 2000, "outcomeId": 3000, "value": 35.5, "confirmedOdd": "1:3"}' http://localhost:8081/bets
curl -v -H "Accept: application/hal+json" -H "Content-Type: application/json" -H "userId: 1000" -X GET http://localhost:8081/bets/1000
curl -v -H "Accept: application/hal+json" -H "Content-Type: application/json" -H "userId: 1000" -X GET http://localhost:8081/bets
