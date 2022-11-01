# FROM maven:3.8.5-openjdk-17
#
# WORKDIR /demoClean
# COPY . .
# RUN mvn clean install
#
# CMD java -jar /demoClean/target/demoClean-0.0.1-SNAPSHOT.jar

FROM maven:3.8.5-openjdk-17

CMD cd /code; mvn spring-boot:run