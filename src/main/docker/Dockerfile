FROM tomcat:8.5.65-jdk8-corretto

ADD *.war /usr/local/tomcat/webapps/

ENV SUTTON_DB_HOST_PORT="localhost:3306"

EXPOSE 8080

CMD ["catalina.sh", "run"]
