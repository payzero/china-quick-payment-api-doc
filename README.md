# QuickPay API文档 #

## 技术综述 ##

QuickPay API整体采用RESTful API的风格，以application/json格式进行数据的传递。
本文档中所有的HTTP方法（无论其是GET/POST/DELETE/PUT或任何其他方法），其HTTP HEADER中必须加入OAuth2风格的Http header属性:

~~~
Authorization: Bearer xxxxxxxxxxx
~~~

其中xxxxxxxxxxx为Payzero用于验证调用方身份的JWT风格的token, 该token的获得是通过使用Payzero分配的username和password并调用登录接口。其有效时间为4小时，且JWT本身包含了过期的时间的信息。关于JWT token，可参考 <https://jwt.io/>。4小时到期之前，可重新调用登录接口获取token，token之间均为独立关系，获取新的token不会影响未过期的老token的使用。

## 接口地址##
使用如下链接代替本文中出现的{payzero\_api\_url}字样

* 测试环境: https://dev-quickpay-api.payzero.cn
* 生产环境: https://quickpay-api.payzero.cn

## 接口介绍##

### 1. 登录获取token ###
* url: {payzero\_api\_url}/auth/login
* method: POST
* request body parameter 

|字段名称|参数|例子|说明|
|:--|:--|:--|:--|
|用户名 | username| "abc" |  |
|密码 | password| "p@ssw0rd" |  |

* POSTMAN调用示例:
![](doc/auth.jpg)

* response:

|字段名称|参数|例子|说明|
|:--|:--|:--|:--|
|用户名|username| "admin"|
|token| token| "xxxxxx" | |
~~~
{
    "success": true,
    "errorMsg": null,
    "errorCode": null,
    "data": {
        "username": "admin",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwiaWF0IjoxNTUzMDY2OTg1LCJleHAiOjE1NTMwODEzODV9.KIfG8LOk2FoMgqXAU8DCg_cbci36OqbLUIHUdIXDsF0"
    }
}
~~~

### 2. 订单信息上传 ###
#### 2.1 订单批次上传 ####
若商户有一个批次的订单需要处理，请先调用本接口创建订单批次。创建完订单批次后，登录商户端后台可查看到订单批次。根据商户是否需要代垫资服务，订单批次的手续费状态可能为"等待支付手续费"(需垫资)或"已确认"(无需代垫资)。若为"等待支付手续费"，需通知相关人员进行财务打款并通知我方确认收款。待订单批次手续费确认后，可进行后续调用。

* url: {payzero\_api\_url}/orderBatch
* method: POST
* request body parameter 

请传入一个数组的order类型对象，order类型的结构如下

|字段名称|参数|类型|是否必填|例子|说明|
|:--|:--|:--|:--|:--|:--|
| 货币代码 | currency | String | 是 | "CNY" | 请固定为CNY |
| 需申报的电子口岸代码 | customsCode | String | 否 | "HG016" | 调用前请咨询相关技术人员。若需申报则为必填。 |
| 海关关区代码| customsAreaCode | String | 否 | "5130" | 若需申报且申报海关为广州海关时必填 |
| 检验检疫机构代码 | customsJyOrg | String | 否 | "440009" |  若需申报且申报为广州海关时必填|
| 进口类型 | customsInType | String | 否 | "1" | 若需申报且申报天津电子口岸时为必填，1-保税进口，2-直邮进口 |
| 商户订单编号 | mchtOrderNo | String| 是 | "2019032000000123" | 请确保商户订单不重复 |
| 订单下单时间 | orderDatetime | Date| 否 | "2019-03-20T06:57:29.396Z" | Date类型 |
| 订购人姓名 | payerName | String | 否 | "张三" | 若需申报则必填 |
| 订购人身份证号 | payerNumber | String | 否 | "310113198010101234" | 若需申报则必填|
| 订购人电话 | payerPhone | String | 否 | "18512001234" | 若需申报则必填 |
| 订单额度 | paymentAmount | Long | 是 | 4023 | 请务必注意单位为分 |
| 订单内主要商品信息 | subject | String | 是 | "XXXX化妆品" | |
| 订单内商品列表 | items | items类型数组 | 否 | | 仅针对需要托管179文对接接口至Payzero的合作伙伴，需要传输该字段，items的结构参见后续说明 |

items类型的结构如下:

|字段名称|参数|类型|是否必填|例子|说明|
|:--|:--|:--|:--|:--|:--|
| 商品名称 | subject | String | 是 | "XXXX口红" | 需179文对接托管客户则必填 |
| 商品链接 | itemLink | String | 是 | "http://www.baidu.com" | 需179文对接托管客户则必填 |
| 货号 | articleNum | String | 否 | "WO11111" |  |


* request example: 

~~~
[
  {
    "currency": "CNY",
    "customsCode": "HG022",
    "customsAreaCode": "5130",
    "customsJyOrg": "440009",
    "customsInType": "2",
    "items": [
      {
        "articleNum": "HH00001",
        "itemLink": "http://www.baidu.com",
        "subject": "测试商品1"
      },
       {
        "articleNum": "HH00002",
        "itemLink": "http://www.baidu.com",
        "subject": "测试商品2"
      }
    ],
    "mchtOrderNo": "F20190402123",
    "orderDatetime": "2019-04-02T11:11:46.740Z",
    "payerName": "李白",
    "payerNumber": "310327198009270027",
    "payerPhone": "13800138000",
    "paymentAmount": 35302,
    "subject": "测试商品1"
  },
  {
    "currency": "CNY",
    "customsCode": "HG022",
    "customsAreaCode": "5130",
    "customsJyOrg": "440009",
    "customsInType": "2",
    "mchtOrderNo": "F20190402124",
    "orderDatetime": "2019-04-02T11:11:46.740Z",
    "payerName": "杜甫",
    "payerNumber": "360104199010101234",
    "payerPhone": "13300000000",
    "paymentAmount": 35000,
    "subject": "测试商品xxxx"
  }
]
~~~

* response:

|字段名称|参数|例子|说明|
|:--|:--|:--|:--|
|订单批次id| orderBatchId |  59 |
|订单批次号| dispBatchNum | "20190320162344842"|
|批次内订单总额(分)| orderTotalAmount | 70302 | 单位为分 |
|创建时间| createdTime | 1553070224475  | |

~~~
{
    "success": true,
    "errorMsg": null,
    "errorCode": null,
    "data": {
        "orderBatchId": 59,
        "merchantId": 1,
        "dispBatchNum": "20190320162344842",
        "isReconed": false,
        "feeAmount": null,
        "orderTotalAmount": 70302,
        "objectId": null,
        "feedbackObjectId": null,
        "createdTime": 1553070224475,
        "updatedTime": 1553070224817
    }
}
~~~


订单批次上传完成之后，在商户端可以查看到该订单批次信息:

![](doc/ob_screenshot.png)

#### 2.2 订单批次触发支付 ####
调用本接口对本批次内的订单进行支付与推关。（若为垫资模式，需先线下支付手续费）本接口同步返回成功只表明系统接收到开始支付的指令，支付完成时间取决于协商的合作模式等其他因素。在发送支付指令成功之后，可以进行20分钟/次的订单批量批次支付&推送结果轮询。

* url: {payzero\_api\_url}/orderBatch/{orderBatchId}/pay
* method: GET
* request Path Variable: url路径参数为创建该订单批次时返回的orderBatchId

* response: success参数返回为true时即表示系统已成功安排本批次支付任务

~~~
{
    "success": true,
    "errorMsg": null,
    "errorCode": null,
    "data": {
       “分配任务完成”
    }
}
~~~

* 错误代码: 若success为false，可能出现的errorCode和其对应解释如下

|errorCode|errorMsg| 备注|
|:--|:--|:--|
|50001| 未找到支持的银行卡和协议，可能与垫资模式的设置不正确有关 | 联系技术支持
|50010| 未找到已签约的银行卡进行支付 | 登录商户后台进行绑卡 |
|50054| 没有需要被分配执行的订单 | 
|50056| 系统记录的银行卡当前额度可能无法支持支付本批次订单，请增加系统内银行卡额度并确保实际资金与额度大致匹配 | 在商户后台中录入的银行卡“当前余额“总额不足以支付当前订单批次，请确保银行卡真是余额足够支付整个订单批次，并在系统中填写入真实余额


#### 2.3 订单批次支付&推送结果查询 ####
用于查询订单批次的支付及推单结果。若支付失败，可重新调用订单批次支付接口，系统将只重新支付当前支付失败的订单，多次反复支付失败，需联系技术人员排查支付失败原因。若推单失败，请运营人员自行登录商户后台修改申报的订购人身份信息，编辑并点击重新推单。

* url: {payzero\_api\_url}/orderBatch/{orderBatchId}/summary
* method: GET
* request Path Variable: url路径参数为创建该订单批次时返回的orderBatchId

* response: 

|字段名称|参数|例子|说明|
|:--|:--|:--|:--|
|批次内订单总数| total |  159 |
|支付成功总数| paySuccessCount |150 | 单位为分 |
|支付失败总数|payFailedCount | 2|
|其他支付中间状态总数|payOtherCount |7  | |
|申报成功总数|declareSuccessCount |130  | |
|申报失败总数|declareFailedCount |7  | |
|其他申报中间状态总数|declareOtherCount | 22  | |
|总已支付金额(分)|totalPaymentAmount | 10489449  | |

~~~
{
    "data": {
        "declareFailedCount": 7,
        "declareOtherCount": 22,
        "declareSuccessCount": 130,
        "payFailedCount": 2,
        "payOtherCount": 7,
        "paySuccessCount": 150,
        "total": 159,
        "totalPaymentAmount": 10489449
    },
    "errorCode": null,
    "errorMsg": null,
    "success": true
}
~~~

#### 2.4 单笔订单回执查询 

#### 2.5 订单批次回执查询







