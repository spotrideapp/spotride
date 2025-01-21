./gradlew clean &&
./gradlew build &&
docker build --tag=spotride:latest . &&
docker compose up