FROM maven:3-jdk-8-slim
COPY . /root/brapi-admin-app-build
WORKDIR /root/brapi-admin-app-build
RUN mvn -q clean package -DskipTests

FROM openjdk:8-jre-alpine3.9
EXPOSE 8080
WORKDIR /opt/brapi-admin-app
COPY --from=0 /root/brapi-admin-app-build/target/brapimanagementservice-0.0.1-SNAPSHOT.jar .
CMD java -jar brapimanagementservice-0.0.1-SNAPSHOT.jar