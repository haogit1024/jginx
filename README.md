# 一个简单的http服务器

## TODO
* ~~http status enum~~
* ~~http header builder~~
* ~~根据文件类型获取Content-type~~
* ~~除文本外的其他资源文件的响应体改为byte[]~~
* ~~支持Range请求头~~
  ~~1. 解析requestHeader Range: bytes={start}-{end}~~
  ~~2. 返回responseHeader Range: bytes 0-10/1560323~~
  ~~3. 文件分块读取~~
* ~~static resource handle 50%~~
* ~~优化response~~
* ~~打包可运行jar包~~
* ~~自定义StringUtils~~
* 自定义log
* 参数配置和配置文件
* 关闭功能
  1. 像tomcat一样监听一个端口, 用于关闭操作
  2. 如果要关闭, 必须要用内网请求这个端口, 并且发送一个特殊的信息
* 生成文件和配置文件功能