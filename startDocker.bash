cd front
mvn install
docker build -t front .
#docker run -p 8888:8888 front
cd ../
cd front2
mvn install
docker build -t front2 .
#docker run -p 8081:8081 front
cd ../
cd back
docker build . -t node_workshop
#docker run -p 3000:3000 -d node_workshop