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
* 自定义StringUtils
* 自定义log
* 参数配置和配置文件