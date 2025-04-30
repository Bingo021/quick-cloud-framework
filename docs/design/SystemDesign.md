# Quick Cloud Framework (QCF) 系统设计文档

## 1. 系统架构设计

### 1.1 整体架构图

```plantuml
@startuml
!theme plain
skinparam componentStyle rectangle

package "QCF Framework" {
    [qcf-dependencies] as deps
    [qcf-framework] as framework
    [qcf-module-system] as system
    [qcf-server] as server
    [qcf-ui] as ui
}

package "External Services" {
    [Database] as db
    [Cache] as cache
    [Message Queue] as mq
}

deps --> framework
framework --> system
system --> server
server --> ui

server --> db
server --> cache
server --> mq

@enduml
```

### 1.2 技术栈架构

```plantuml
@startuml
!theme plain
skinparam componentStyle rectangle

package "Frontend" {
    [Vue.js] as vue
    [Element UI] as element
    [Axios] as axios
}

package "Backend" {
    [Spring Boot] as spring
    [Spring Security] as security
    [MyBatis] as mybatis
    [Redis] as redis
}

package "DevOps" {
    [Maven] as maven
    [Git] as git
    [Jenkins] as jenkins
}

vue --> spring
element --> spring
axios --> spring

spring --> security
spring --> mybatis
spring --> redis

maven --> spring
git --> spring
jenkins --> spring

@enduml
```

## 2. 核心模块设计

### 2.1 基础框架模块

```plantuml
@startuml
!theme plain

class BaseExceptionHandler {
    +handleException()
    +handleValidationException()
    +handleBusinessException()
}

class LogManager {
    +info()
    +error()
    +debug()
    +warn()
}

class Validator {
    +validate()
    +validateObject()
    +validateField()
}

class DataConverter {
    +convert()
    +format()
    +parse()
}

class CacheManager {
    +get()
    +put()
    +remove()
    +clear()
}

BaseExceptionHandler --> LogManager
Validator --> LogManager
DataConverter --> LogManager
CacheManager --> LogManager

@enduml
```

### 2.2 安全模块

```plantuml
@startuml
!theme plain

class SecurityConfig {
    +configure()
    +authenticationManager()
    +passwordEncoder()
}

class JwtTokenProvider {
    +createToken()
    +validateToken()
    +getUsername()
}

class SecurityFilter {
    +doFilter()
    +validateToken()
}

class PermissionEvaluator {
    +hasPermission()
    +hasRole()
}

SecurityConfig --> JwtTokenProvider
SecurityConfig --> SecurityFilter
SecurityFilter --> PermissionEvaluator

@enduml
```

## 3. 数据库设计

### 3.1 核心实体关系图

```plantuml
@startuml
!theme plain

entity User {
    + id: Long
    + username: String
    + password: String
    + email: String
    + status: Integer
}

entity Role {
    + id: Long
    + name: String
    + code: String
    + description: String
}

entity Permission {
    + id: Long
    + name: String
    + code: String
    + type: String
}

entity Menu {
    + id: Long
    + name: String
    + path: String
    + component: String
    + icon: String
}

User "1" -- "n" Role
Role "n" -- "n" Permission
Role "n" -- "n" Menu

@enduml
```

## 4. 接口设计

### 4.1 认证接口时序图

```plantuml
@startuml
!theme plain

actor User
participant "Frontend" as FE
participant "Backend" as BE
participant "Database" as DB

User -> FE: 输入用户名密码
FE -> BE: POST /api/auth/login
BE -> DB: 验证用户信息
DB --> BE: 返回用户信息
BE -> BE: 生成JWT Token
BE --> FE: 返回Token
FE -> User: 显示登录成功

@enduml
```

### 4.2 权限验证时序图

```plantuml
@startuml
!theme plain

actor User
participant "Frontend" as FE
participant "Backend" as BE
participant "Security" as SEC

User -> FE: 请求受保护资源
FE -> BE: 请求API (带Token)
BE -> SEC: 验证Token
SEC -> SEC: 检查权限
alt 有权限
    SEC --> BE: 允许访问
    BE --> FE: 返回数据
    FE --> User: 显示数据
else 无权限
    SEC --> BE: 拒绝访问
    BE --> FE: 返回错误
    FE --> User: 显示错误
end

@enduml
```

## 5. 部署架构

### 5.1 部署架构图

```plantuml
@startuml
!theme plain

node "Load Balancer" as lb
node "Application Server 1" as app1
node "Application Server 2" as app2
node "Database Server" as db
node "Cache Server" as cache
node "File Server" as file

lb --> app1
lb --> app2
app1 --> db
app2 --> db
app1 --> cache
app2 --> cache
app1 --> file
app2 --> file

@enduml
```

## 6. 开发规范

### 6.1 代码结构规范

```
qcf-framework/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── quick/
│   │   │           └── cloud/
│   │   │               ├── common/        # 通用工具类
│   │   │               ├── config/        # 配置类
│   │   │               ├── exception/     # 异常处理
│   │   │               ├── security/      # 安全相关
│   │   │               └── util/          # 工具类
│   │   └── resources/
│   └── test/                              # 测试代码
└── pom.xml
```

### 6.2 命名规范

1. 包名：全小写，使用公司域名反写
2. 类名：大驼峰命名法
3. 方法名：小驼峰命名法
4. 变量名：小驼峰命名法
5. 常量名：全大写，下划线分隔

## 7. 安全设计

### 7.1 安全架构图

```plantuml
@startuml
!theme plain

package "Security Layer" {
    [Authentication] as auth
    [Authorization] as authz
    [Encryption] as enc
    [Audit] as audit
}

package "Application Layer" {
    [API Gateway] as gateway
    [Business Logic] as logic
}

package "Data Layer" {
    [Database] as db
    [Cache] as cache
}

auth --> gateway
authz --> gateway
enc --> logic
audit --> logic

gateway --> logic
logic --> db
logic --> cache

@enduml
```

## 8. 监控设计

### 8.1 监控架构图

```plantuml
@startuml
!theme plain

node "Application" as app
node "Log Collector" as log
node "Metrics Collector" as metrics
node "Alert Manager" as alert
node "Dashboard" as dash

app --> log
app --> metrics
log --> alert
metrics --> alert
alert --> dash

@enduml
``` 