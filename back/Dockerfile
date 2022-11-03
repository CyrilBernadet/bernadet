FROM openjdk:18

COPY ../keys/server.crt.pem /usr/java/openjdk-18/jre/lib/security
RUN \
    cd /usr/java/openjdk-18/jre/lib/security \
    && keytool -keystore cacerts -storepass changeit -noprompt -trustcacerts -importcert -alias keycloakcert -file server.crt.pem

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
 
COPY src ./src
 
CMD ["./mvnw", "spring-boot:run"]
