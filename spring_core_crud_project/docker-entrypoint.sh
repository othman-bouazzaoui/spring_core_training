#!/bin/bash
#
# Docker Entrypoint Script for Spring Core CRUD Project
# Handles graceful startup and shutdown with catalina.sh
#

set -e

# ============================================
# Environment Setup
# ============================================

export CATALINA_HOME=${CATALINA_HOME:-/usr/local/tomcat}
export CATALINA_BASE=${CATALINA_BASE:-/usr/local/tomcat}
export JAVA_HOME=${JAVA_HOME:-/opt/java/openjdk}

# ============================================
# JVM Startup Options
# ============================================

export JAVA_OPTS="${JAVA_OPTS:-}"
export JAVA_OPTS="${JAVA_OPTS} -Xms512m"
export JAVA_OPTS="${JAVA_OPTS} -Xmx1024m"
export JAVA_OPTS="${JAVA_OPTS} -XX:+UseG1GC"
export JAVA_OPTS="${JAVA_OPTS} -XX:MaxGCPauseMillis=200"
export JAVA_OPTS="${JAVA_OPTS} -XX:+PrintGCDetails"

# ============================================
# Catalina Startup Options
# ============================================

export CATALINA_OPTS="${CATALINA_OPTS:-}"
export CATALINA_OPTS="${CATALINA_OPTS} -Dfile.encoding=UTF-8"
export CATALINA_OPTS="${CATALINA_OPTS} -Duser.timezone=UTC"

export CATALINA_OPTS=$CATALINA_OPTS" --add-modules java.se "
export CATALINA_OPTS=$CATALINA_OPTS" --add-exports java.base/jdk.internal.ref=ALL-UNNAMED "
export CATALINA_OPTS=$CATALINA_OPTS" --add-opens java.base/java.lang=ALL-UNNAMED "
export CATALINA_OPTS=$CATALINA_OPTS" --add-opens java.base/java.nio=ALL-UNNAMED "
export CATALINA_OPTS=$CATALINA_OPTS" --add-opens java.base/sun.nio.ch=ALL-UNNAMED "
export CATALINA_OPTS=$CATALINA_OPTS" --add-opens java.management/sun.management=ALL-UNNAMED "
export CATALINA_OPTS=$CATALINA_OPTS" --add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED "

# ============================================
# Pre-startup Checks
# ============================================

echo "=========================================="
echo "Spring Core CRUD Project - Docker Startup"
echo "=========================================="
echo ""
echo "CATALINA_HOME: $CATALINA_HOME"
echo "CATALINA_BASE: $CATALINA_BASE"
echo "JAVA_HOME: $JAVA_HOME"
echo ""

# Verify directories exist
if [ ! -d "$CATALINA_HOME" ]; then
    echo "ERROR: CATALINA_HOME not found: $CATALINA_HOME"
    exit 1
fi

if [ ! -d "$CATALINA_BASE/logs" ]; then
    mkdir -p "$CATALINA_BASE/logs"
fi

if [ ! -d "$CATALINA_BASE/temp" ]; then
    mkdir -p "$CATALINA_BASE/temp"
fi

echo "Java Version:"
"$JAVA_HOME/bin/java" -version 2>&1
echo ""
echo "Starting Apache Tomcat..."
echo "=========================================="
echo ""

# ============================================
# Signal Handling for Graceful Shutdown
# ============================================

# Trap SIGTERM and SIGINT for graceful shutdown
trap 'echo "Received shutdown signal, stopping Tomcat..."; "$CATALINA_HOME/bin/catalina.sh" stop -force; exit 0;' SIGTERM SIGINT

# ============================================
# Execute Original Entrypoint
# ============================================

# Execute the command passed to the container
# This will run catalina.sh run (or any other command)
exec "$@"
