# 一个简单的http服务器

## TODO
* ~~http status enum~~
* ~~http header builder~~
* 根据文件类型获取Content-type
* 只支持html, js, css, text, image, video格式的资源, 其他一律返回下载相应头
* 除文本外的其他资源文件的响应体改为byte[]
* 支持Range请求头
* static resource handle 50%
* log