FROM openjdk
WORKDIR user/lib
ADD ./target/EurekaServer-0.0.1-SNAPSHOT.jar  /user/lib/EurekaServer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","EurekaServer-0.0.1-SNAPSHOT.jar"]
