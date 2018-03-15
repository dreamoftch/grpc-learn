# grpc-learn

### 运行步骤

1.生成proto java code:
./gradlew proto:clean proto:build

2.启动server:
./gradlew server:run
可以看到:
Server started, listening on 50051

3.启动client:
./gradlew client:run
可以看到:
resp: hello tianch
