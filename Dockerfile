FROM openjdk:11
COPY target/phonebookapp.jar /usr/app/
WORKDIR /usr/app/
ENTRYPOINT ["java", "-jar", "phonebookapp.jar"]
EXPOSE 8080