Start RabbitMq for Spring cloud bus usage. 
	docker run -d --name rabbitmq -p 15672:15672 -p 5672:5672   rabbitmq:3-management-alpine
	Rabbit Mq:http://localhost:15672/   - guest guest
	
Setup the folder for all logs - Then make sure to set the log path in git config folder as well.

Make sure MySQL & Mongo DB are setup

Start all the servers
	config server		8888
	EUREKA server		8761
	audit server		8081
	login server		8082
	product server		8083
	cart server			8084
	order server		8085