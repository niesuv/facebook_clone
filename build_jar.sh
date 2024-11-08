@echo off
echo Building Image Config-server

cd ./backend/services/config-server
mvn clean package
cp target/*.jar ../../../target/
cd ../../../

echo --------------------------------------
echo Building Image Gateway-service
cd ./backend/services/gateway-service
mvn clean package
cp target/*.jar ../../../target/
cd ../../../

echo --------------------------------------
echo Building Image Post-service
cd ./backend/services/post-service
mvn clean package
cp target/*.jar ../../../target/
cd ../../../

echo --------------------------------------
echo Building Image S3-service
cd ./backend/services/s3-service
mvn clean package
cp target/*.jar ../../../target/
cd ../../../


echo --------------------------------------
echo Building Image User-service
cd ./backend/services/user-service
mvn clean package
cp target/*.jar ../../../target/
cd ../../../