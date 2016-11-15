# path for summer
http://localhost:8080/summer?n=100

# Testing
# install ab
sudo apt-get install apache2-utils
# run ab
ab -c 20 -n 10000 http://localhost:8080/summer?n=100

# or using jmeter config from jmeter-test.jmx
