FROM openjdk:18
EXPOSE 8080

COPY .mvn/ .mvn
COPY mvnw pom.xml ./ 
COPY src ./src
 
CMD ["./mvnw", "spring-boot:run"]
