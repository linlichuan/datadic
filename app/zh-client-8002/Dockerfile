FROM java:alpine
ENV spring.profiles.active test
COPY . /opt/llc/myApp/zh-client/
CMD ["java","-jar", "-Dfile.encoding=UTF-8" ,"/opt/llc/myApp/zh-client/zh-client-1.0.0.jar"]
