FROM openjdk:8-jdk-alpine
RUN echo "Asia/Shanghai" > /etc/timezone
ADD target/falsework-admin.jar falsework-admin.jar
EXPOSE 8000
CMD java -jar falsework-admin.jar --server.port=8000 --spring.profiles.active=test
