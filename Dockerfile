FROM java:8
MAINTAINER zhaoyunxing "2385585770@qq.com"

LABEL version=1.0 \
      desc="基于mongo的文件服务" \
      author="zhaoyunxing"

WORKDIR /file
ADD /target/file-server-mongodb-1.0.jar ./file-server-mongodb-1.0.jar

# 解决时区问题
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
     && echo 'Asia/Shanghai' > /etc/timezone

ENTRYPOINT ["java","-jar","./file-server-mongodb-1.0.jar"]

EXPOSE 7100