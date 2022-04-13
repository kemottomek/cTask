FROM adoptopenjdk/openjdk11:jdk-11.0.6_10-alpine as build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw dependency:go-offline -B

ADD . /tmp/

RUN cd /tmp && chmod 777 mvnw && ./mvnw package -DskipTests
RUN cd /tmp/target  && cp $(find . -name '*.jar' -type f) /tmp/app.jar


FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
COPY --from=build /tmp/app.jar /tmp
CMD [ "java", "-jar", "/tmp/app.jar"]
