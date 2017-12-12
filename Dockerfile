FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./target/obvestilniSistem-1.0.0.jar /app

EXPOSE 8081

CMD ["java", "-jar", "obvestilniSistem-1.0.0.jar"]