# 一个简单的http服务器

## TODO
> 第一期目标尽量简单, 都先从无到有
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
* ~~添加自定义异常和异常监听~~
* 添加管理功能(初始化配置文件, 关闭, 重启等, 改用json为配置文件并降低目标, 浓缩为一个配置文件)
* 反向代理和负载均衡

## 命令操作
1. 采用监听一个新端口实现