FROM java:alpine
ENV spring.profiles.active test
COPY . /opt/llc/myApp/cpt-8102/
CMD ["java","-jar", "-Dfile.encoding=UTF-8" ,"/opt/llc/myApp/cpt-8102/compute-service-8102-1.0.0.jar"]
