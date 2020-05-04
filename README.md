# Carpark
Carpark RESTful API. System to manage a carpark with three different slot types and billing policies.

### Instructions
Execute the following commands
`mvn clean install`
`mvn spring-boot:run`

### API GUI documentation
http://localhost:5000/carpark/swagger-ui.html

### The API intents to work as follows
`/configure`   configures the initial settings of the carpark

`/enter/{carId}`  tells the system a car with id `carId` has entered to the carpark

`/exit/{carId}`   tells the system a car with id `carId` has exited to the carpark

`/pricingpolicy`   configures a pricing hour rate and fixed rate
