# 配置文件

在springboot配置文件application.properties中支持

### 配置项 

| 配置项  |  值  |  作用  | 例子  |
|-------|:---:|-----------|-------:|
| spring.fly.mock.contain.${filed}  | 占位符、字符串 | 所有字段包含filed的对象都按占位符规则生成 | spring.fly.mock.contain.name=@cname() |
| spring.fly.mock.equal.${filed}  |  占位符、字符串  | 所有字段等于filed的对象都按占位符规则生成 | spring.fly.mock.equal.name=小王   |
| spring.fly.mock.enable  | true/false   | 是否开启flymock，默认开启 |  spring.fly.mock.enable=true   |

