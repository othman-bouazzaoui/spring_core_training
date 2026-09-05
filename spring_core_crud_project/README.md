## JAVA Version is 21
## To run This project use Tomcat10.1  !!!

## ALWAYS RUN ``` mvn clean install``` before running the application
please use Smart Tomcat plugin to run the application in your IDE (IntelliJ or Eclipse with the classic way) or use the command line with cargo plugin below

## RUN the APP Using mvn command with [CARGO plugin](https://codehaus-cargo.github.io/cargo/Maven+3+Plugin+Getting+Started.html)
```mvn cargo:run```

## MVC
http://localhost:9191/spring_core_crud_project/users  
## REST API
http://localhost:9191/spring_core_crud_project/api/users

### Dockerizing your application 
- 1 must run mysql & init the database using initBD.sql file
```
docker run --name mysql --network app-network -e MYSQL_ROOT_PASSWORD=root -p 3306:3306  mysql:latest
```
⚠️ please make sure that the mysql container is running before executing the next command (it take ~1 minute)
```
docker exec -e MYSQL_PWD=root -i mysql mysql -u root < BD/initBD.sql
```

- 2 Build the docker image and run application
```
docker build -t spring_core_crud_project:1.0 .
```
```
docker run --network app-network --env MYSQL_HOSTNAME=mysql -p 9191:8080 -p 8000:8000 --name spring_app spring_core_crud_project:1.0
```
- troubleshooting
```
docker exec -it spring_app bash
cat /usr/local/tomcat/logs/localhost.*.log
```
## clean docker containers
```
docker rm mysql -f
docker rm spring_app -f
```

## RUN All With Docker Compose
⚠️ sometimes the mysql container takes time to start, so you may need to restart the spring_app container after the mysql container is up and running
```
docker-compose up --build
``` 
``` 
docker-compose down
```

