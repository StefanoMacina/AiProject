FROM maven:3-eclipse-temurin-17-alpine AS deps

WORKDIR /app
COPY pom.xml /app

RUN mvn go-offline:resolve-dependencies





FROM maven:3-eclipse-temurin-17-alpine AS build

WORKDIR /app
COPY --from=deps /root/.m2/repository /root/.m2/repository
COPY . /app

RUN mvn package -DskipTests -o




FROM maven:3-eclipse-temurin-17-alpine AS dev

WORKDIR /app
COPY --from=deps /root/.m2/repository /root/.m2/repository
COPY ./docker-entrypoint.sh /docker-entrypoint.sh

RUN apk add inotify-tools
RUN chmod +x /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]


# Utilizza l'immagine JDK come base
#FROM openjdk:17-jdk-alpine
#
## Imposta la directory di lavoro all'interno del container
#WORKDIR /app
#
## Copia il file JAR dell'applicazione nella directory di lavoro
#COPY target/app.jar .
#
## Espone la porta 8080
#EXPOSE 8080
#
## Comando per eseguire l'applicazione
#ENTRYPOINT ["java", "-jar", "app.jar"]
