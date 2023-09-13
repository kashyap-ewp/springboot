FROM openjdk:17-oracle
EXPOSE 8083
ADD target/consumer-app.jar consumer-app.jar
ENTRYPOINT ["java","-jar","consumer-app.jar"]