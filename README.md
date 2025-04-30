# Quick Cloud Framework (QCF)

## 项目简介
Quick Cloud Framework (QCF) 是一个基于Spring Boot 2.7.18的快速开发框架，旨在提供一套完整的微服务开发解决方案。该框架采用模块化设计，提供了丰富的功能组件和最佳实践，帮助开发者快速构建高质量的企业级应用。

## 技术栈
- Java 11
- Spring Boot 2.7.18
- Maven
- Lombok
- MapStruct

## 项目结构
项目采用多模块设计，主要包含以下模块：

### 1. qcf-dependencies
- 依赖管理模块
- 统一管理所有子模块的依赖版本
- 提供统一的依赖配置

### 2. qcf-framework
- 核心框架模块
- 提供基础功能组件
- 包含通用工具类和基础配置

### 3. qcf-module-system
- 系统功能模块
- 提供系统级功能实现
- 包含用户管理、权限控制等核心功能

### 4. qcf-server
- 服务启动模块
- 应用入口
- 服务配置和启动管理

### 5. qcf-ui
- 前端界面模块
- 提供用户交互界面
- 实现前后端分离架构

## 快速开始

### 环境要求
- JDK 11+
- Maven 3.6+
- IDE (推荐使用IntelliJ IDEA)

### 构建项目
```bash
mvn clean install
```

### 运行项目
```bash
cd qcf-server
mvn spring-boot:run
```

## 特性
1. 模块化设计，便于扩展和维护
2. 统一的依赖管理，避免版本冲突
3. 集成常用开发工具和框架
4. 提供完整的微服务开发解决方案
5. 支持前后端分离架构

## 开发规范
1. 遵循阿里巴巴Java开发规范
2. 使用统一的代码格式化配置
3. 完善的注释和文档
4. 统一的命名规范

## 贡献指南
欢迎提交Issue和Pull Request，共同完善项目。

## 许可证
本项目采用MIT许可证，详情请参见LICENSE文件。

## 联系方式
如有任何问题或建议，欢迎通过以下方式联系我们：
- 提交Issue
- 发送邮件至项目维护者 