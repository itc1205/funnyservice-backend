FROM openjdk:21
ADD build/libs/*.jar *.jar
ENTRYPOINT ["java","-jar","*.jar"]