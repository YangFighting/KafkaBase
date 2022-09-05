# 图解Kafka

视频来源：https://www.bilibili.com/video/BV1fY4y1V7Z1?spm_id_from=333.337.search-card.all.click

已经看到：https://www.bilibili.com/video/BV1fY4y1V7Z1?p=8

## 环境准备

kafka版本：[0.10.1](https://archive.apache.org/dist/kafka/0.10.1.0/kafka-0.10.1.0-src.tgz)

jdk版本：1.8

scala: 2.10.6（下载 https://scala-lang.org/download/2.10.6.html）（版本来自kafka 0.10.1 说明）

gradle: 3.1（https://gradle.org/next-steps/?version=3.1&format=bin）

gradle安装和 idea 配置教程

https://blog.csdn.net/qq_41113081/article/details/110557821

https://www.cnblogs.com/mindzone/p/12880854.html



kafka 的环境搭建：https://blog.csdn.net/u013256816/article/details/78533725

### 遇到的问题

```
Caused by: org.codehaus.groovy.control.MultipleCompilationErrorsException: startup failed:
```

kafka-0.10.1.0-src 删掉，重新执行 gradle idea，还是报错，  gradle idea  --warning-mode all 查看所有告警

```
The RepositoryHandler.jcenter() method has been deprecated. This is scheduled to be removed in Gradle 8.0.
```

将 gradle版本由7.5.1更换 为3.1

