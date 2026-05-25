# Estágio de Build (Compilação)
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# Estágio de Execução (Roda a aplicação com Java atualizado)
FROM eclipse-temurin:21-jre
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]