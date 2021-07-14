FROM openjdk:11.0.11-jdk-oracle
ADD target/shop-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar shop-0.0.1-SNAPSHOT.jar