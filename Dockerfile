# ─── Stage 1: 프론트엔드 빌드 ───────────────────────────────
FROM node:20-alpine AS frontend
WORKDIR /app/frontend
COPY frontend/package*.json ./
RUN npm ci
COPY frontend/ ./
RUN npm run build

# ─── Stage 2: 백엔드 빌드 ───────────────────────────────────
FROM gradle:8.13-jdk21 AS backend
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle/ gradle/
COPY gradlew ./
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon 2>/dev/null || true
COPY src/ src/

# frontend WORKDIR이 /app/frontend 이므로 outDir ../src/main/resources/static
# = /app/src/main/resources/static 로 정확히 떨어짐
COPY --from=frontend /app/src/main/resources/static/ src/main/resources/static/

RUN ./gradlew bootJar -x test --no-daemon

# ─── Stage 3: 실행 이미지 ───────────────────────────────────
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN apk add --no-cache tzdata && \
    cp /usr/share/zoneinfo/Asia/Seoul /etc/localtime && \
    echo "Asia/Seoul" > /etc/timezone
COPY --from=backend /app/build/libs/*.jar app.jar
RUN mkdir -p /app/data
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
