Using docker compose:

Mongo DB value shoud be of mongo atlas.
MySQL should be of clever cloud.

docker build -t deancha12/eureka-naming-server:0.0.1-SNAPSHOT .
docker build -t deancha12/spring-cloud-config-server:0.0.1-SNAPSHOT .
docker build -t deancha12/audit-rabbitmq-server:0.0.1-SNAPSHOT .
docker build -t deancha12/login-server:0.0.1-SNAPSHOT .
docker build -t deancha12/product-server:0.0.1-SNAPSHOT .
docker build -t deancha12/cart-server:0.0.1-SNAPSHOT .
docker build -t deancha12/order-server:0.0.1-SNAPSHOT .

docker-compose up

All the url's will be having localhost as HOST. Ex: http://localhost:8082/actuator
-----------------------------------------------------------------------------------------
In this branch the following are added:
	Removed logback properties - Beacuse was not able to file log file in container - Need to check on this.


------------------------------------------------------------------------------------------
Start RabbitMq for Spring cloud bus usage. 
	docker run -d --name rabbitmq -p 15672:15672 -p 5672:5672   rabbitmq:3-management-alpine
	Rabbit Mq:http://localhost:15672/   - guest guest
	
Setup the folder for all logs - Then make sure to set the log path in git config folder as well.

Make sure MySQL & Mongo DB are setup

Start all the servers
	config server		8888
	EUREKA server		8761
	audit rabbit server	8081
	login server		8082
	product server		8083
	cart server			8084
	order server		8085