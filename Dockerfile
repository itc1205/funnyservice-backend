FROM openjdk:21
ADD ./funnycraft.7z funnycraft.7z
ADD ./funnylauncher.zip funnylauncher.zip
ADD build/libs/*.jar *.jar
ENTRYPOINT ["java","-jar","*.jar"]