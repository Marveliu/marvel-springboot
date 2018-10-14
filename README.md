# marvel-springboot

[![standard-readme compliant](https://img.shields.io/badge/standard--readme-OK-green.svg?style=flat-square)](https://github.com/RichardLitt/standard-readme)

## Table of Contents

- [Background](#background)
- [Features](#features)
- [Todo](#todo)
- [Usages](#usages)
- [Branches](#branches)
- [Maintainers](#maintainers)
- [Contribute](#contribute)
- [Reference](#reference)
- [License](#license)

## Background

在实习的时候发现使用别人搭好的轮子总觉得缺了什么，特别是在后期往往牵一发而动全身。并萌生了搭一个功能详尽并且自我熟悉的脚手架，主要目的如下:

1. 深入了解一下springboot2.0
2. 搭建一个可用可靠的前后端分离的后端脚手架


## features

- 集成queryDsl使用spring data jpa
- 符合restful api的基于jwt的shiro权限控制
- redis实现动态密钥生成以及数据缓存
- 分布式任务调度quartz集成
- 基于postman的接口测试以及contiperf性能单测
- 提取公共代码封装的BaseServiceImpl进行CRUD
- mapstruct实现类之间的转换
- 全局响应与异常处理

## Todo

- 整合queryDsl,封装BaseServiceImpl
- rabbitMq消息队列
- 做一个并发场景测试
- 基于Slog注解的并发日志

## usages

### 传统部署

> 可以在application.yaml中进行定制

1. 建立数据库:marvel_springboot
1. 启动redis:redis-server
2. 生成Qclass:maven package

### Docker一键部署

特别的，本项目使用docker-compose进行了容器编排，在安装了docker的机器上面，可以在根目录输入：

```
docker-compose up
```

启动一下服务：

1. app:对应marvel_springboot
2. mysql:起一个5.7版本的mysql
3. nginx:参考nginx/conf.d/marvel.conf
4. redis:参考redis/*，**redis需要bind 0.0.0.0，支持远程访问**

## branches

1. [master](https://github.com/Marveliu/marvel-springboot/tree/master):整合框架
2. [springCloud](https://github.com/Marveliu/marvel-springboot/tree/native):springCloud
3. [native](https://github.com/Marveliu/marvel-springboot/tree/native):部分原生手写


## Maintainers

[@Marveliu](https://github.com/Marveliu)

## Reference

1. [Druid连接池以及Druid监控](https://www.jianshu.com/p/139405d267d3)
2. [QueryDsl](https://www.jianshu.com/p/99a5ec5c3bd5)
3. [基于springboot2+ shiro+jwt的真正rest api资源无状态认证权限管理框架](https://gitee.com/tomsun28/bootshiro)

## Contribute

PRs accepted.

Small note: If editing the README, please conform to the [standard-readme](https://github.com/RichardLitt/standard-readme) specification.

## License

Apache © 2018 Marveliu
