FROM hub.c.163.com/oliver_lee/mytomcat:v7
MAINTAINER limingming
#RUN /bin/bash -c "source /etc/profile" && java -version
#RUN java -version
COPY * /usr/lzt/ 
ENV JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
ENV CLASSPATH=.:$JAVA_HOME/lib:$JAVA_HOME/jre/lib:$CLASSPATH
ENV PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH
RUN /bin/sh -c "cd /usr/lzt && mvn clean && mvn compile && mvn package && cp target/hello-1.0-SNAPSHOT.war /var/lib/tomcat7/webapps"
#RUN apt-get update && apt-get install maven -y

