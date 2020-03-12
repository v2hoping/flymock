可以支持min-max、count、min-max.dmin-dmax、min-max.dcount、count.dmin-dmax、count.dcount、+step。
不同类型类型适用的规则不同。

例子:

String mockJson = Mock.mockToJson("{\"name|1-3\":\"@cname()\"}");

System.out.println(mockJson); //生成{"name":"许霞许霞许霞"}


## 适用类型

### 属性值是字符串 String

1、``` name|min-max:string ```

e.g:{"name|1-3":"@cname()"}、{"name|1-3":"string"}

生成重复字符串，重复次数大于等于min，小于等于max

2、``` name|count:string ```

e.g:{"name|3":"@cname()"}、{"name|3":"string"}

生成重复字符串，重复次数等于count

### 属性值是整型 Integer

1、``` name|+num:integer ```

e.g:{"name|+3":1}

属性值自动加N，初始值为integer。

2、``` name|min-max:integer ```

e.g:{"name|2-3":1}

生成一个大于等于 min、小于等于 max 的整数，属性值 integer 只是用来确定类型。

### 属性值是小数 float

1、``` name|+num:float ``` 

e.g:{"name|+3":1.2}

属性值自动加num，初始值为float

2、``` name|min-max:float ``` 

e.g:{\"name|2-3\":1.2}"

生成一个浮点数，整数部分大于等于 min、小于等于 max，属性值 float 只是用来确定类型。

3、``` name|min-max.dmin-dmax:float ``` 

e.g:{"name|1-2.1-2":1.2}

生成一个浮点数，整数部分大于等于 min、小于等于 max，小数部分保留 dmin 到 dmax 位。

4、``` name|min-max.count:float ``` 

e.g:{"name|3-5.2":1.2}

生成一个浮点数，整数部分大于等于 min、小于等于 max，小数部分保留 count 位。

5、``` name|count.dmin-dmax ```

e.g:{"name|4.1-3":1.2}

生成一个浮点数，整数部分等于count，小数部分保留 dmin 到 dmax 位。

### 属性值是布尔类型 boolean

1、``` name|count:boolean ```

e.g:{"name|1":true}

随机生成一个布尔值，值为 true 的概率是 1/2，值为 false 的概率同样是 1/2。

2、``` name|min-max:boolean ```

e.g:{"name|2-3":true}

随机生成一个布尔值，值为 value 的概率是 min / (min + max)，值为 !value 的概率是 max / (min + max)。

### 属性值是对象 Object

1、```name|count: object```

e.g:{"aa|1":{"name":"@cname()","educations":[{"date":1557457171390,"name":"vhJ"}]}}

从属性值 object 中随机选取 count 个属性。

2、```name|min-max': object```

e.g:{"aa|1-2":{"name":"@cname()","educations":[{"date":1557457171390,"name":"vhJ"}]}}

从属性值 object 中随机选取 min 到 max 个属性。

### 属性值是数组 Array

1、```name|count: array```

e.g:{"aa|1":[{"date":1557457171390,"name":"vhJ"},{"date":111111,"name":"是"}]}

复制数组元素count次

2、```name|min-max': array```

e.g:{"aa|3-4":[{"date":1557457171390,"name":"vhJ"},{"date":111111,"name":"是"}]}

复制数组元素大于等于min，小于等于max次
