# Dockerfile

 # Pull base image.
FROM tomcat:8.0

ENV	FM_NIO_VERSION=0.0.1-SNAPSHOT \
	FM_NIO_URL=https://github.com/garvanza/fm-nio/releases/download/${FM_NIO_VERSION}/fm-nio-${FM_NIO_VERSION}.war

VOLUME /opt/fm

RUN set -x curl -L -o webapps/fm-nio.war $URL

# build image 
## #> docker build -t garvanza/fm-nio:0.0.1-SNAPSHOT .

# make web container linking to db
## #>docker run -d -p 8081:8080 -v fm/:/opt/fm/  -v /tmp/catalinalogs/:/usr/local/tomcat/logs --name web --link db:db garvanza/fm-nio:0.0.1-SNAPSHOT

# start
## #> docker start web
