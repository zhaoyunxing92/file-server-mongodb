# file-server-mongodb
基于mongodb文件服务器，相同文件只保留一份，同时图片压缩0.3，获取图片目前暂支持修改图片的宽和高，旋转、水印、裁剪后续添加

## 项目配置
> [application.yml](./src/main/resources/application.yml)
由于没有使用`GridFS`所以保存的文件不要超过16M
## 获取图片, 可以设置图片的大小

>http://localhost:8101/file/img/5b1695b888ae35145016e5f0

>http://localhost:8101/file/img/5b02fadd973bb623f4706671?process=h_100

>http://localhost:8101/file/img/5b02fadd973bb623f4706671?process=h_100,w_100

