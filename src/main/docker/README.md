# Start Database Docker Container

Copy file `docker-compose.yml`  to the database host and execute `$> docker-compose up`.

# Create Web Server Docker Image

Copy files `Dockerfile` and `suttondemo.war` to the Web server host and execute `$> docker build .` (don't forget the point after `build`).

# Start Web Server Docker Container

## Start the Web Server on the same host as the database (only for testing)

`$> docker run -p 8080:8080 --rm -e "SUTTON_DB_HOST_PORT=host.docker.internal:3306" <IMAGE_ID>`

## Start the Web Server on a different host than the database (production)

1. Define an environment variable: `$> export SUTTON_DB_HOST_PORT="10.10.10.10:3306"`
2. Start the container: `$> docker run -p 8080:8080 --rm -e SUTTON_DB_HOST_PORT <IMAGE_ID>`

# Test the Web Server

Assuming that the Web server is running on 10.10.10.20, open URL `http://10.10.10.20:8080/suttondemo/api/persons` in a Web browser or Postman. You must see an empty JSON collection (`[]`) as result (the database is empty).
