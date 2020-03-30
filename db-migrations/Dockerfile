# Derivando da imagem oficial do MySQL
FROM mysql:5.7
ENV MYSQL_DATABASE simulacao_juros
EXPOSE 3306
# CMD service nginx start ; while true ; do sleep 100; done;
COPY ./scripts/ /docker-entrypoint-initdb.d/
ENTRYPOINT service mysql start && /bin/bash
# RUN /etc/init.d/mysql status

#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /db-migration/src
COPY pom.xml /db-migration
# RUN mvn -f /db-migration/pom.xml clean install -P prod