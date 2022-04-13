Start activeMq 							-p 8161:8161 -p 61616:61616
	Chech DockerCommands.txt file
	http://localhost:8161/index.html  - admin & admin	
	Go to 'Manage my broker' link
	
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