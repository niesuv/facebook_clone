@echo off
echo Building Image Config-server

cd ./backend/services/config-server
call mvn compile com.google.cloud.tools:jib-maven-plugin:3.4.4:build -Dimage=niesuv/config-server:2.0
cd ../../../

echo --------------------------------------
echo Building Image Gateway-service
cd ./backend/services/gateway-service
call mvn compile com.google.cloud.tools:jib-maven-plugin:3.4.4:build -Dimage=niesuv/gateway-service:2.0
cd ../../../

echo --------------------------------------
echo Building Image Post-service
cd ./backend/services/post-service
call mvn compile com.google.cloud.tools:jib-maven-plugin:3.4.4:build -Dimage=niesuv/post-service:2.0
cd ../../../

echo --------------------------------------
echo Building Image S3-service
cd ./backend/services/s3-service
call mvn compile com.google.cloud.tools:jib-maven-plugin:3.4.4:build -Dimage=niesuv/s3-service:2.0
cd ../../../


echo --------------------------------------
echo Building Image User-service
cd ./backend/services/user-service
call mvn compile com.google.cloud.tools:jib-maven-plugin:3.4.4:build -Dimage=niesuv/user-service:2.0
cd ../../../