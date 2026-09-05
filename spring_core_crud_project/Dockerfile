FROM tomcat:10-jre21-temurin-jammy

RUN apt-get update \
    && apt-get -y upgrade \
    && apt-get -y --no-install-recommends install curl dos2unix \
    && apt-get purge -y pcre2-utils || true \
    && apt-get autoremove -y \
    && rm -rf /usr/local/tomcat/webapps.dist/docs \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

RUN addgroup spring --gid 1001 && useradd --uid 1001 --gid 1001 spring -g spring

LABEL name="spring_crud_project"
LABEL version="1.0"

SHELL ["/bin/bash", "-o", "pipefail", "-c"]

COPY target/spring_core_crud_project.war /usr/local/tomcat/webapps/spring_core_crud_project.war
COPY docker-entrypoint.sh /docker-entrypoint.sh

# Fix line endings (CRLF to LF) for Windows-based files
RUN dos2unix /docker-entrypoint.sh || sed -i 's/\r$//' /docker-entrypoint.sh

# Set permissions and ownership BEFORE changing user
RUN chmod +x "/docker-entrypoint.sh" \
    && chown root:root /docker-entrypoint.sh \
    && sed -i "s/port=\"8080\"/port=\"8080\" maxHttpHeaderSize=\"65536\"/g" /usr/local/tomcat/conf/server.xml \
    && chown -R spring:spring /usr/local/tomcat

USER spring

EXPOSE 8080 8000 5701

ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["catalina.sh","run"]

