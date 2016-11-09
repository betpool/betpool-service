# path for hello world
http://localhost:8080/java-rs/rest/sum
# path for sum
http://localhost:8080/java-rs/rest/sum/100

# Testing
# install ab
sudo apt-get install apache2-utils
# run ab
ab -c 20 -n 10000 http://localhost:8080/java-rs/resources/load

# or using jmeter config from jmeter-test.jmx
