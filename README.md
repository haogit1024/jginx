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
* 加入日志框架
* ~~参数配置和配置文件~~
* 添加自定义异常和异常监听(暂时延期)
* 生成文件和配置文件功能
* 添加管理功能(初始化配置文件, 关闭, 重启等)
* 添加 session, cookie 支持
* 反向代理和负载均衡