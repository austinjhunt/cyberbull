# Austin Hunt - August 2021
# Build with
# ./gradlew build  && docker build --build-arg JAR_FILE=build/libs/\*.jar -t austinjhunt/cyberbull .
FROM openjdk:11-jdk-slim-buster
RUN addgroup --system cyberbull && adduser --system cyberbull && usermod -aG cyberbull cyberbull
USER cyberbull:cyberbull
ARG JAR_FILE=target/*.jar
COPY . .
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]