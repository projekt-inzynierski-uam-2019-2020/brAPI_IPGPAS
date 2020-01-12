FROM maven:3-jdk-8-slim
COPY . /root/brapi-app-build
WORKDIR /root/brapi-app-build
RUN mvn -q clean package -DskipTests

FROM openjdk:8-jre-alpine3.9
EXPOSE 8080
WORKDIR /opt/brapi-app
COPY --from=0 /root/brapi-app-build/target/brapi_igpas-0.0.1-SNAPSHOT.jar .
COPY text_data/ .
CMD java -jar brapi_igpas-0.0.1-SNAPSHOT.jar
