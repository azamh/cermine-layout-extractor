FROM maven:3.6.0-jdk-8
RUN mkdir /app
WORKDIR /app
# ADD /export/home/ahosseini/.m2 /root/.
ADD . .
# RUN mvn clean install 
RUN mvn compile
WORKDIR /app