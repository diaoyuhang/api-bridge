docker stop api-bridge
docker rm -f api-bridge
docker rmi -f api-bridge:0.1

docker build -t api-bridge:0.1 .
docker run -d -p 8080:8080 --name api-bridge api-bridge:0.1