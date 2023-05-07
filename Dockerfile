FROM maven:3.8.3-jdk-8 as build
WORKDIR /app
COPY . .
RUN mvn package -DskipTests

# 指定基础镜像
FROM openjdk:8-jdk-alpine

# 添加工作目录
WORKDIR /app

# 复制应用程序 jar 包到容器中
COPY --from=build /app/target/Delayed-Tasks-1.0-SNAPSHOT.jar /app/Delayed-Tasks.jar

# 暴露应用程序运行端口
EXPOSE 8080

# 指定容器启动命令
CMD ["java", "-jar", "Delayed-Tasks.jar"]
