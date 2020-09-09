FROM java:8
ADD target/*.jar web-1.0.jar
VOLUME /tmp
EXPOSE 8848
ENTRYPOINT ["java","-jar" ,"web-1.0.jar"]