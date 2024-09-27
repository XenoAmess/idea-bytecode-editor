cd ./shade_asm
call mvnw clean install
cd ..
set JAVA_HOME=C:\jdk-17\
call gradlew clean buildPlugin
