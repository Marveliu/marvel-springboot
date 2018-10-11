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

> springboot项目开发脚手架

提出公共代码，减少重复性的体力劳动，聚焦业务逻辑实现。主要技术架构如下：

- spring boot 2.0
- spring data jpa
- shiro
- redis
- rabbitMq
- dubbo
- quartz
- queryDsl

## features

1. 角色用户的权限管理
2. 全局异常处理以及状态码
3. 操作日志
4. 任务调度
5. 动态数据源配置

## Todo

- queryDsl封装BaseServiceImpl

## usages

1. 启动redis:redis-server
2. 生成Qclass:maven package

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
4. [springboot整合shiro-登录认证和权限管理](http://www.ityouknow.com/springboot/2017/06/26/springboot-shiro.html)

## Contribute

PRs accepted.

Small note: If editing the README, please conform to the [standard-readme](https://github.com/RichardLitt/standard-readme) specification.

## License

Apache © 2018 Marveliu
