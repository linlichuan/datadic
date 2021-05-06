FROM java:alpine
ENV spring.profiles.active test
COPY . /opt/llc/myApp/zh-api/
CMD ["java","-jar", "-Dfile.encoding=UTF-8" ,"/opt/llc/myApp/zh-api/zh-service-8103-1.0.0.jar"]
