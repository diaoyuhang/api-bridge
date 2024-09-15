FROM openjdk:17.0.2-jdk

WORKDIR /usr/local/api-bridge

ADD api-bridge-0.0.9.jar api-bridge.jar
ENTRYPOINT ["java","-Xms1024m","-Xmx1024m","-jar","api-bridge.jar"]