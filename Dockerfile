FROM openjdk:11-jdk-slim
COPY "./target/Back_Gr42_Eq03-0.0.1-SNAPSHOT.war" "app.war"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.war"]