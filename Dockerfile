FROM maven:3.9.6 AS builder

# Копирование файлов проекта
COPY ./ /usr/src/app
WORKDIR /usr/src/app

# Сборка проекта Maven
RUN mvn clean package

FROM tomcat:9.0
# Копирование собранного WAR файла
COPY --from=builder /usr/src/app/target/webapp.war /usr/local/tomcat/webapps/webapp.war