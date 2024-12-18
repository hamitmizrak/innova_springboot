
# JDK Image Create
FROM openjdk:17

#ARG JAR_FILE=/target/innova_springboot.jar
ARG JAR_FILE=/target/*.jar

# Yukarıdaki Değişkeni Almak
ADD ${JAR_FILE} blog.jar

# Çalışma Alanı
ENTRYPOINT ["java","-jar","/blog.jar"]