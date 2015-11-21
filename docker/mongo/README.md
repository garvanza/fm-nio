## pull & run
docker run -d -v db/:/data/db --name db -p 27017:27017 mongo:3.0

## start
docker start db