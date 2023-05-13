#FROM openjdk:17
FROM maven:3.8.3-openjdk-17

WORKDIR /usr/src/app
#RUN git pull origin master
COPY . /usr/src/app
RUN ls -la
RUN mvn clean package
#COPY target/docker_demo.jar docker_demo.jar

#ENTRYPOINT ["java", "-jar", "docker_demo.jar"]
ENTRYPOINT ["java", "-jar", "target/docker_demo.jar"]
#RUN mvn clean package