FROM openjdk
VOLUME /tmp
ADD ./build/libs/rancher-test-1.0.0.jar app.jar
#RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java","-jar","/app.jar"]
