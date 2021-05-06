FROM java:alpine
ENV spring.profiles.active test
COPY . /opt/llc/myApp/eureka-8761/
CMD ["java","-jar", "-Dfile.encoding=UTF-8" ,"/opt/llc/myApp/eureka-8761/eureka-1.0.0.jar"]
