FROM java:alpine
ENV spring.profiles.active test
COPY ./target/api-service-1.0.0.jar /opt/llc/myApp/api/
VOLUME /opt/llc/myApp/api:/opt/llc/myApp/api
EXPOSE 8101
CMD ["java", "-jar", "-Dfile.encoding=UTF-8", "/opt/llc/myApp/api/api-service-1.0.0.jar"]
