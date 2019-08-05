[TOC]

* [后端API开发接口文档](#%E5%90%8E%E7%AB%AFapi%E5%BC%80%E5%8F%91%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3)
  * [1\. 用户接口](#1-%E7%94%A8%E6%88%B7%E6%8E%A5%E5%8F%A3)
    * [1\.1 登陆接口](#11-%E7%99%BB%E9%99%86%E6%8E%A5%E5%8F%A3)
      * [1\.1\.1 功能描述](#111-%E5%8A%9F%E8%83%BD%E6%8F%8F%E8%BF%B0)
      * [1\.1\.2 请求方式](#112-%E8%AF%B7%E6%B1%82%E6%96%B9%E5%BC%8F)
      * [1\.1\.3 请求头](#113-%E8%AF%B7%E6%B1%82%E5%A4%B4)
      * [1\.1\.4 请求示例](#114-%E8%AF%B7%E6%B1%82%E7%A4%BA%E4%BE%8B)
      * [1\.1\.5 返回结果](#115-%E8%BF%94%E5%9B%9E%E7%BB%93%E6%9E%9C)
      * [1\.1\.6 返回参数](#116-%E8%BF%94%E5%9B%9E%E5%8F%82%E6%95%B0)
      * [1\.1\.7 用户信息参数](#117-%E7%94%A8%E6%88%B7%E4%BF%A1%E6%81%AF%E5%8F%82%E6%95%B0)
    * [1\.2 绑定微信用户](#12-%E7%BB%91%E5%AE%9A%E5%BE%AE%E4%BF%A1%E7%94%A8%E6%88%B7)
      * [1\.2\.1 功能描述](#121-%E5%8A%9F%E8%83%BD%E6%8F%8F%E8%BF%B0)
      * [1\.2\.2 请求方式](#122-%E8%AF%B7%E6%B1%82%E6%96%B9%E5%BC%8F)
      * [1\.2\.3 请求头](#123-%E8%AF%B7%E6%B1%82%E5%A4%B4)
      * [1\.2\.4 请求参数](#124-%E8%AF%B7%E6%B1%82%E5%8F%82%E6%95%B0)
      * [1\.2\.5 请求示例](#125-%E8%AF%B7%E6%B1%82%E7%A4%BA%E4%BE%8B)
      * [1\.2\.6 返回结果](#126-%E8%BF%94%E5%9B%9E%E7%BB%93%E6%9E%9C)
      * [1\.2\.7 返回参数](#127-%E8%BF%94%E5%9B%9E%E5%8F%82%E6%95%B0)
      * [1\.2\.8 用户信息参数](#128-%E7%94%A8%E6%88%B7%E4%BF%A1%E6%81%AF%E5%8F%82%E6%95%B0)
  * [2\. 通用帖子接口](#2-%E9%80%9A%E7%94%A8%E5%B8%96%E5%AD%90%E6%8E%A5%E5%8F%A3)
    * [2\.1 获取帖子列表](#21-%E8%8E%B7%E5%8F%96%E5%B8%96%E5%AD%90%E5%88%97%E8%A1%A8)
      * [2\.1\.1 功能描述](#211-%E5%8A%9F%E8%83%BD%E6%8F%8F%E8%BF%B0)
      * [2\.1\.2 请求方式](#212-%E8%AF%B7%E6%B1%82%E6%96%B9%E5%BC%8F)
      * [2\.1\.3 请求参数](#213-%E8%AF%B7%E6%B1%82%E5%8F%82%E6%95%B0)
      * [2\.1\.4 请求示例](#214-%E8%AF%B7%E6%B1%82%E7%A4%BA%E4%BE%8B)
      * [2\.1\.5 返回结果](#215-%E8%BF%94%E5%9B%9E%E7%BB%93%E6%9E%9C)
      * [2\.1\.6 返回参数](#216-%E8%BF%94%E5%9B%9E%E5%8F%82%E6%95%B0)
      * [2\.1\.7 页面信息参数](#217-%E9%A1%B5%E9%9D%A2%E4%BF%A1%E6%81%AF%E5%8F%82%E6%95%B0)
      * [2\.1\.8 列表参数](#218-%E5%88%97%E8%A1%A8%E5%8F%82%E6%95%B0)
  * [3\. 失物找回接口](#3-%E5%A4%B1%E7%89%A9%E6%89%BE%E5%9B%9E%E6%8E%A5%E5%8F%A3)
    * [3\.1 获取失物找回列表](#31-%E8%8E%B7%E5%8F%96%E5%A4%B1%E7%89%A9%E6%89%BE%E5%9B%9E%E5%88%97%E8%A1%A8)
      * [3\.1\.1 功能描述](#311-%E5%8A%9F%E8%83%BD%E6%8F%8F%E8%BF%B0)
      * [3\.1\.2 请求方式](#312-%E8%AF%B7%E6%B1%82%E6%96%B9%E5%BC%8F)
      * [3\.1\.3 请求参数](#313-%E8%AF%B7%E6%B1%82%E5%8F%82%E6%95%B0)
      * [3\.1\.4 请求示例](#314-%E8%AF%B7%E6%B1%82%E7%A4%BA%E4%BE%8B)
      * [3\.1\.5 返回结果](#315-%E8%BF%94%E5%9B%9E%E7%BB%93%E6%9E%9C)
      * [3\.1\.6 返回参数](#316-%E8%BF%94%E5%9B%9E%E5%8F%82%E6%95%B0)
      * [3\.1\.7 页面信息参数](#317-%E9%A1%B5%E9%9D%A2%E4%BF%A1%E6%81%AF%E5%8F%82%E6%95%B0)
      * [3\.1\.8 列表参数](#318-%E5%88%97%E8%A1%A8%E5%8F%82%E6%95%B0)
    * [3\.2 发送失物信息接口](#32-%E5%8F%91%E9%80%81%E5%A4%B1%E7%89%A9%E4%BF%A1%E6%81%AF%E6%8E%A5%E5%8F%A3)
      * [3\.2\.1 功能描述](#321-%E5%8A%9F%E8%83%BD%E6%8F%8F%E8%BF%B0)
      * [3\.2\.2 请求方式](#322-%E8%AF%B7%E6%B1%82%E6%96%B9%E5%BC%8F)
      * [3\.2\.3 请求示例](#323-%E8%AF%B7%E6%B1%82%E7%A4%BA%E4%BE%8B)
      * [3\.2\.4 返回结果](#324-%E8%BF%94%E5%9B%9E%E7%BB%93%E6%9E%9C)
      * [3\.2\.5 返回结果参数说明](#325-%E8%BF%94%E5%9B%9E%E7%BB%93%E6%9E%9C%E5%8F%82%E6%95%B0%E8%AF%B4%E6%98%8E)
    * [3\.3 更新失物状态接口](#33-%E6%9B%B4%E6%96%B0%E5%A4%B1%E7%89%A9%E7%8A%B6%E6%80%81%E6%8E%A5%E5%8F%A3)
      * [3\.3\.1 功能描述](#331-%E5%8A%9F%E8%83%BD%E6%8F%8F%E8%BF%B0)
      * [3\.3\.2  请求方式](#332--%E8%AF%B7%E6%B1%82%E6%96%B9%E5%BC%8F)
      * [3\.3\.3 请求示例](#333-%E8%AF%B7%E6%B1%82%E7%A4%BA%E4%BE%8B)
      * [3\.4\.5 返回结果](#345-%E8%BF%94%E5%9B%9E%E7%BB%93%E6%9E%9C)
  * [4\. 举报接口](#4-%E4%B8%BE%E6%8A%A5%E6%8E%A5%E5%8F%A3)
    * [4\.1 获取举报列表](#41-%E8%8E%B7%E5%8F%96%E4%B8%BE%E6%8A%A5%E5%88%97%E8%A1%A8)
      * [4\.1\.1 功能描述](#411-%E5%8A%9F%E8%83%BD%E6%8F%8F%E8%BF%B0)
      * [4\.1\.2 请求方式](#412-%E8%AF%B7%E6%B1%82%E6%96%B9%E5%BC%8F)
      * [4\.1\.3 请求参数](#413-%E8%AF%B7%E6%B1%82%E5%8F%82%E6%95%B0)
      * [4\.1\.4 请求示例](#414-%E8%AF%B7%E6%B1%82%E7%A4%BA%E4%BE%8B)
      * [4\.1\.5 返回结果](#415-%E8%BF%94%E5%9B%9E%E7%BB%93%E6%9E%9C)
      * [4\.1\.6 返回参数](#416-%E8%BF%94%E5%9B%9E%E5%8F%82%E6%95%B0)
      * [4\.1\.7 页面信息参数](#417-%E9%A1%B5%E9%9D%A2%E4%BF%A1%E6%81%AF%E5%8F%82%E6%95%B0)
      * [4\.1\.8 列表参数](#418-%E5%88%97%E8%A1%A8%E5%8F%82%E6%95%B0)
  * [5\. 反馈接口](#5-%E5%8F%8D%E9%A6%88%E6%8E%A5%E5%8F%A3)
    * [5\.1 获取反馈列表](#51-%E8%8E%B7%E5%8F%96%E5%8F%8D%E9%A6%88%E5%88%97%E8%A1%A8)
      * [5\.1\.1 功能描述](#511-%E5%8A%9F%E8%83%BD%E6%8F%8F%E8%BF%B0)
      * [5\.1\.2 请求方式](#512-%E8%AF%B7%E6%B1%82%E6%96%B9%E5%BC%8F)
      * [5\.1\.3 请求参数](#513-%E8%AF%B7%E6%B1%82%E5%8F%82%E6%95%B0)
      * [5\.1\.4 请求示例](#514-%E8%AF%B7%E6%B1%82%E7%A4%BA%E4%BE%8B)
      * [5\.1\.5 返回结果](#515-%E8%BF%94%E5%9B%9E%E7%BB%93%E6%9E%9C)
      * [5\.1\.6 返回参数](#516-%E8%BF%94%E5%9B%9E%E5%8F%82%E6%95%B0)
      * [5\.1\.7 页面信息参数](#517-%E9%A1%B5%E9%9D%A2%E4%BF%A1%E6%81%AF%E5%8F%82%E6%95%B0)
      * [5\.1\.8 列表参数](#518-%E5%88%97%E8%A1%A8%E5%8F%82%E6%95%B0)
  * [6\. 私信接口](#6-%E7%A7%81%E4%BF%A1%E6%8E%A5%E5%8F%A3)
    * [6\.1 获取私信列表](#61-%E8%8E%B7%E5%8F%96%E7%A7%81%E4%BF%A1%E5%88%97%E8%A1%A8)
      * [6\.1\.1 功能描述](#611-%E5%8A%9F%E8%83%BD%E6%8F%8F%E8%BF%B0)
      * [6\.1\.2 请求方式](#612-%E8%AF%B7%E6%B1%82%E6%96%B9%E5%BC%8F)
      * [6\.1\.3 请求参数](#613-%E8%AF%B7%E6%B1%82%E5%8F%82%E6%95%B0)
      * [6\.1\.4 请求示例](#614-%E8%AF%B7%E6%B1%82%E7%A4%BA%E4%BE%8B)
      * [6\.1\.5 返回结果](#615-%E8%BF%94%E5%9B%9E%E7%BB%93%E6%9E%9C)
      * [6\.1\.6 返回参数](#616-%E8%BF%94%E5%9B%9E%E5%8F%82%E6%95%B0)
      * [6\.1\.7 页面信息参数](#617-%E9%A1%B5%E9%9D%A2%E4%BF%A1%E6%81%AF%E5%8F%82%E6%95%B0)
      * [6\.1\.8 列表参数](#618-%E5%88%97%E8%A1%A8%E5%8F%82%E6%95%B0)
      

##  后端API开发接口文档

### 1. 用户接口

#### 1.1 登陆接口

##### 1.1.1 功能描述

> 使用Token进行登陆

##### 1.1.2 请求方式

> 请求方式：GET
>
> 请求URL：http://localhost:8081/community/user/login

##### 1.1.3 请求头

> | 字段          | 字段类型 | 字段说明                      |
> | ------------- | -------- | ----------------------------- |
> | Authorization | String   | 验证的token，每个接口必须携带 |

##### 1.1.4 请求示例

> http://localhost:8081/community/user/login

##### 1.1.5 返回结果

登陆成功

```json
{
    "code": 1,
    "msg": "登陆成功",
    "data": {
        "id": 1,
        "uid": 181203221,
        "realName": "崔志文",
        "sex": true,
        "state": false,
        "registerTime": "Jul 22, 2019 7:33:15 AM",
        "permission": true,
        "unionId": "xxxxxxxxxxxx",
        "openId": "xxxxxxxxxxxxx"
    }
}
```

##### 1.1.6 返回参数 

> | 返回字段 | 字段类型 | 说明                             |
> | :------- | :------- | -------------------------------- |
> | code     | int      | 返回结果状态。0：失败；1：成功。 |
> | msg      | String   | 返回信息                         |
> | data     | Json     | 用户信息                         |

##### 1.1.7 用户信息参数

| 字段         | 字段类型 | 说明                                      |
| :----------- | :------- | :---------------------------------------- |
| id           | int      | ID。                                      |
| uid          | int      | 学号。                                    |
| realName     | String   | 真实姓名。                                |
| sex          | Boolean  | 用户性别。false：女；true：男。           |
| state        | Boolean  | 用户状态。false：正常；true：禁言状态。   |
| registerTime | String   | 注册时间。                                |
| permission   | Boolean  | 用户权限。false：普通用户；true：管理员。 |
| unionId      | String   | 微信UnionID。                             |

#### 1.2 绑定微信用户

##### 1.2.1 功能描述

> 使用Token绑定用户保存数据到邮院社区数据库中

##### 1.2.2 请求方式

> 请求方式：POST
>
> 请求格式：application/json
>
> 请求URL：http://localhost:8081/community/user/login

##### 1.2.3 请求头

> | 字段          | 字段类型 | 字段说明                      |
> | ------------- | -------- | ----------------------------- |
> | Authorization | String   | 验证的token，每个接口必须携带 |

##### 1.2.4 请求参数

> | 字段         | 字段类型 | 说明                                      |
> | :----------- | :------- | :---------------------------------------- |
> | uid          | int      | 学号。                                    |
> | realName     | String   | 真实姓名。                                |
> | sex          | Boolean  | 用户性别。false：女；true：男。           |
> | state        | Boolean  | 用户状态。false：正常；true：禁言状态。   |
> | registerTime | String   | 注册时间。                                |
> | permission   | Boolean  | 用户权限。false：普通用户；true：管理员。 |
> | unionId      | String   | 微信UnionID。                             |
> | openId       | String   | 微信OpenID。                              |

##### 1.2.5 请求示例

> http://localhost:8081/community/user/binding

> POST格式

```json
{
    "uid": 181203221,
    "realName": "崔志文",
    "sex": true,
    "state": false,
    "permission": true,
    "unionId": "xxxxxxxxx",
    "openId": "xxxxxxxxxxxx"
}
```

##### 1.2.6 返回结果

绑定成功

```json
{
    "code": 1,
    "msg": "绑定成功",
    "data": {
        "id": 1,
        "uid": 181203221,
        "realName": "崔志文",
        "sex": true,
        "state": false,
        "registerTime": "Jul 22, 2019 7:33:15 AM",
        "permission": true,
        "unionId": "xxxxxxxxxxxx",
        "openId": "xxxxxxxxxxxxx"
    }
}
```

##### 1.2.7 返回参数 

> | 返回字段 | 字段类型 | 说明                             |
> | :------- | :------- | -------------------------------- |
> | code     | int      | 返回结果状态。0：失败；1：成功。 |
> | msg      | String   | 返回信息                         |
> | data     | Json     | 用户信息                         |

##### 1.2.8 用户信息参数

| 字段         | 字段类型 | 说明                                      |
| :----------- | :------- | :---------------------------------------- |
| id           | int      | ID。                                      |
| uid          | int      | 学号。                                    |
| realName     | String   | 真实姓名。                                |
| sex          | Boolean  | 用户性别。false：女；true：男。           |
| state        | Boolean  | 用户状态。false：正常；true：禁言状态。   |
| registerTime | String   | 注册时间。                                |
| permission   | Boolean  | 用户权限。false：普通用户；true：管理员。 |
| unionId      | String   | 微信UnionID。                             |
| openId       | String   | 微信OpenID。                              |

### 2. 通用帖子接口

#### 2.1 获取帖子列表

##### 2.1.1 功能描述

>  获取表白墙或树洞列表

##### 2.1.2 请求方式

> 请求方式：GET
>
> 请求URL：http://localhost:8081/community/common/getArticles

##### 2.1.3 请求参数

> | 字段     | 字段类型 | 说明                         |
> | -------- | -------- | ---------------------------- |
> | postType | Integer  | 帖子类型。0：表白墙；1：树洞 |
> | pageNum  | Integer  | 页数。                       |
> | pageSize | Integer  | 每页条目数。                 |

##### 2.1.4 请求示例

> http://localhost:8081/community/common/getArticles?postType=0&pageNum=1&pageSize=2

##### 2.1.5 返回结果

```json
{
    "code": 1,
    "msg": "请求成功",
    "data": {
        "pageNum": 1,
        "pageSize": 2,
        "pages": 22,
        "total": 43,
        "list": [
            {
                "id": 67,
                "uid": 181203221,
                "title": "猜猜我在找谁!!",
                "postType": 0,
                "postTime": "Jul 27, 2019 4:02:44 PM",
                "postContent": "没错找的就是你!!!!",
                "isAnonymous": false
            },
            {
                "id": 66,
                "uid": 181203221,
                "title": "猜猜我在找谁!!",
                "postType": 0,
                "postTime": "Jul 26, 2019 9:00:26 PM",
                "postContent": "没错找的就是你!!!!",
                "isAnonymous": false
            }
        ]
    }
}
```

##### 2.1.6 返回参数 

> | 返回字段 | 字段类型 | 说明                             |
> | :------- | :------- | -------------------------------- |
> | code     | int      | 返回结果状态。0：失败；1：成功。 |
> | msg      | String   | 返回信息                         |
> | data     | Json     | 分页数据信息                     |

##### 2.1.7 页面信息参数

> | 字段     | 字段类型 | 字段说明       |
> | -------- | -------- | -------------- |
> | pageNum  | Integer  | 当前页数。     |
> | pageSize | Integer  | 每页数据条数。 |
> | pages    | Integer  | 总页数。       |
> | total    | Integer  | 总数据条数。   |
> | list     | Integer  | 数据列表。     |

##### 2.1.8 列表参数

> | 字段        | 字段类型 | 说明                            |
> | :---------- | :------- | :------------------------------ |
> | id          | int      | ID。                            |
> | uid         | int      | 学号。                          |
> | title       | String   | 标题。                          |
> | postType    | Integer  | 发布类型。                      |
> | postTime    | Date     | 发布时间。                      |
> | postContent | String   | 文章内容。                      |
> | isAnonymous | Boolean  | 是否匿名。false：否；true：是。 |

### 3. 失物找回接口

#### 3.1 获取失物找回列表

##### 3.1.1 功能描述

> 获取失物找回的帖子列表

##### 3.1.2 请求方式

> 请求方式：GET
>
> 请求URL：http://localhost:8081/community/found/getFounds

##### 3.1.3 请求参数

> | 字段     | 字段类型 | 说明         |
> | -------- | -------- | ------------ |
> | pageNum  | Integer  | 页数。       |
> | pageSize | Integer  | 每页条目数。 |

##### 3.1.4 请求示例

> http://localhost:8081/community/found/getFounds?pageNum=1&pageSize=2

##### 3.1.5 返回结果

```json
{
    "code": 1,
    "msg": "请求成功",
    "data": {
        "pageNum": 1,
        "pageSize": 2,
        "pages": 17,
        "total": 34,
        "list": [
            {
                "id": 10,
                "uid": 181203126,
                "title": "我丢了",
                "postTime": "Jul 29, 2019 5:10:11 PM",
                "contactNumber": "13672490475",
                "lostTime": "一点",
                "lostName": "叶友贵",
                "lostClass": "移动互联186",
                "address": "广东省广州市",
                "articleState": true,
                "lostDescribe": "请速速找回"
            },
            {
                "id": 11,
                "uid": 181203126,
                "title": "我丢了",
                "postTime": "Jul 29, 2019 5:10:11 PM",
                "contactNumber": "13672490475",
                "lostTime": "一点",
                "lostName": "叶友贵",
                "lostClass": "移动互联186",
                "address": "广东省广州市",
                "articleState": false,
                "lostDescribe": "请速速找回"
            }
        ]
    }
}
```

##### 3.1.6 返回参数 

> | 返回字段 | 字段类型 | 说明                             |
> | :------- | :------- | -------------------------------- |
> | code     | int      | 返回结果状态。0：失败；1：成功。 |
> | msg      | String   | 返回信息                         |
> | data     | Json     | 分页数据信息                     |

##### 3.1.7 页面信息参数

> | 字段     | 字段类型 | 字段说明       |
> | -------- | -------- | -------------- |
> | pageNum  | Integer  | 当前页数。     |
> | pageSize | Integer  | 每页数据条数。 |
> | pages    | Integer  | 总页数。       |
> | total    | Integer  | 总数据条数。   |
> | list     | Integer  | 数据列表。     |

##### 3.1.8 列表参数

> | 字段          | 字段类型 | 说明                                    |
> | :------------ | :------- | :-------------------------------------- |
> | id            | int      | ID。                                    |
> | uid           | int      | 学号。                                  |
> | title         | String   | 标题。                                  |
> | postTime      | Date     | 发布时间。                              |
> | contactNumber | Integer  | 手机号码。                              |
> | lostTime      | String   | 丢失时间描述。                          |
> | lostClass     | String   | 丢失人班级。                            |
> | address       | String   | 丢失地址。                              |
> | articleState  | Boolean  | 找回状态。false：未找回；true：已找回。 |
> | lostDescribe  | String   | 失物描述。                              |

#### 3.2 发送失物信息接口

##### 3.2.1 功能描述

> 发表失物的信息

##### 3.2.2 请求方式

> 请求方式：POST
>
> 请求格式：application/json
>
> 请求URL：http://localhost:8081/community/found/postFound

##### 3.2.3 请求示例

> 请求数据示例
>
> ```json
> {
>     "uid": 181203126,
>     "title": "我的手机丢了",
>     "contactNumber": "13672490475",
>     "lostTime": "凌晨",
>     "lostName": "叶友贵",
>     "lostClass": "移动互联186",
>     "lostDescribe": "一台小苹果，小姐姐捡到不用还了",
>     "address": "广东省广州市"
> }
> ```
>
> 请求数据字段说明
>
>  |     参数      | 是否必须 | 参数类型 |     说明     |
>  | :-----------: | :------: | :------: | :----------: |
>  |      uid      |    是    |   int    |     学号     |
>  |     title     |    是    |  String  |     标题     |
>  | contactNumber |    是    |  String  |   联系电话   |
>  |   lostTime    |    是    |  String  | 丢失时间描述 |
>  |   lostName    |    是    |  String  |   失主姓名   |
>  |   lostClass   |    是    |  String  |     班级     |
>  | lostDescribe  |    是    |  String  | 丢失物品描述 |
>  |    address    |    是    |  String  |   丢失地址   |

##### 3.2.4 返回结果

```json
{
    "code": 40001,
    "msg": "发送成功",
    "data": 2
}
```

##### 3.2.5 返回结果参数说明

> | 字段 | 类型 |             说明             |
> | :--: | :--: | :--------------------------: |
> | data | int  | 发表失物信息成功后的该文章id |

#### 3.3 更新失物状态接口

##### 3.3.1 功能描述

> 在找回失物后需要更新失物状态

##### 3.3.2  请求方式

> 请求方式：POST
>
> 请求格式：application/json
>
> 请求地址：http://localhost:8081/community/found/updateFoundStatus

##### 3.3.3 请求示例

> 请求数据示例
>
> ```json
> {
> 	"id":"1",
> 	"articleState":"true"
> }
> ```
>
> 请求参数说明

> |     字段     | 是否必须 |  类型   |           说明            |
> | :----------: | :------: | :-----: | :-----------------------: |
> |      id      |    是    |   int   |        当前文章id         |
> | articleState |    是    | Boolean | 失物状态(true  或  false) |

##### 3.3.4 返回结果

> 更新成功

```json
{
    "code": 60001,
    "msg": "更新成功"
}
```

> 更新失败

```json
{
    "code": 60002,
    "msg": "更新失败"
}
```

### 4. 举报接口

#### 4.1 获取举报列表

##### 4.1.1 功能描述

> 用于管理员查看举报帖子的数据

##### 4.1.2 请求方式

> 请求方式：GET
>
> 请求URL：http://localhost:8081/community/report/getReports

##### 4.1.3 请求参数

> | 字段     | 字段类型 | 说明         |
> | -------- | -------- | ------------ |
> | pageNum  | Integer  | 页数。       |
> | pageSize | Integer  | 每页条目数。 |

##### 4.1.4 请求示例

> http://localhost:8081/community/report/getReports?pageNum=1&pageSize=2

##### 4.1.5 返回结果

```json
{
    "code": 1,
    "msg": "请求成功",
    "data": {
        "pageNum": 1,
        "pageSize": 2,
        "pages": 9,
        "total": 18,
        "list": [
            {
                "id": 26,
                "uid": 181203221,
                "reportType": "我乐意",
                "reportTime": "Jul 30, 2019 6:03:11 PM",
                "state": false,
                "articleType": 0,
                "articleId": 1,
                "content": "我有话要说，其实也没什么话，就随便投诉下."
            },
            {
                "id": 25,
                "uid": 181203221,
                "reportType": "我乐意",
                "reportTime": "Jul 30, 2019 5:23:07 PM",
                "state": false,
                "articleType": 0,
                "articleId": 1,
                "content": "我有话要说，其实也没什么话，就随便投诉下."
            }
        ]
    }
}
```

##### 4.1.6 返回参数 

> | 返回字段 | 字段类型 | 说明                             |
> | :------- | :------- | -------------------------------- |
> | code     | int      | 返回结果状态。0：失败；1：成功。 |
> | msg      | String   | 返回信息                         |
> | data     | Json     | 分页数据信息                     |

##### 4.1.7 页面信息参数

> | 字段     | 字段类型 | 字段说明       |
> | -------- | -------- | -------------- |
> | pageNum  | Integer  | 当前页数。     |
> | pageSize | Integer  | 每页数据条数。 |
> | pages    | Integer  | 总页数。       |
> | total    | Integer  | 总数据条数。   |
> | list     | Integer  | 数据列表。     |

##### 4.1.8 列表参数

> | 字段        | 字段类型 | 字段说明                                    |
> | ----------- | -------- | ------------------------------------------- |
> | id          | Integer  | ID。                                        |
> | uid         | Integer  | 学号。                                      |
> | reportType  | String   | 举报类型。                                  |
> | reportTime  | Date     | 举报发布时间。                              |
> | state       | Boolean  | 处理状态。                                  |
> | articleType | Integer  | 帖子类型。0：表白墙；1：树洞；2：丢失找回； |
> | articleId   | Integer  | 帖子ID。                                    |
> | content     | String   | 举报说明。                                  |

### 5. 反馈接口

#### 5.1 获取反馈列表

##### 5.1.1 功能描述

> 对于本程序的反馈建议。

##### 5.1.2 请求方式

> 请求方式：GET
>
> 请求URL：http://localhost:8081/community/recommend/getRecommends

##### 5.1.3 请求参数

> | 字段     | 字段类型 | 说明         |
> | -------- | -------- | ------------ |
> | pageNum  | Integer  | 页数。       |
> | pageSize | Integer  | 每页条目数。 |

##### 5.1.4 请求示例

> http://localhost:8081/community/recommend/getRecommends?pageNum=1&pageSize=2

##### 5.1.5 返回结果

```json
{
    "code": 1,
    "msg": "请求成功",
    "data": {
        "pageNum": 1,
        "pageSize": 2,
        "pages": 4,
        "total": 7,
        "list": [
            {
                "id": 8,
                "uid": 181203221,
                "time": "Jul 29, 2019 11:55:21 PM",
                "content": "我有话要说，其实也没什么话，就随便投诉下."
            },
            {
                "id": 7,
                "uid": 181203221,
                "time": "Jul 29, 2019 11:51:48 PM",
                "content": "我有话要说，其实也没什么话，就随便投诉下."
            }
        ]
    }
}
```

##### 5.1.6 返回参数 

> | 返回字段 | 字段类型 | 说明                             |
> | :------- | :------- | -------------------------------- |
> | code     | int      | 返回结果状态。0：失败；1：成功。 |
> | msg      | String   | 返回信息                         |
> | data     | Json     | 分页数据信息                     |

##### 5.1.7 页面信息参数

> | 字段     | 字段类型 | 字段说明       |
> | -------- | -------- | -------------- |
> | pageNum  | Integer  | 当前页数。     |
> | pageSize | Integer  | 每页数据条数。 |
> | pages    | Integer  | 总页数。       |
> | total    | Integer  | 总数据条数。   |
> | list     | Integer  | 数据列表。     |

##### 5.1.8 列表参数

> | 字段    | 字段类型 | 字段说明 |
> | ------- | -------- | -------- |
> | id      | Integer  | ID。     |
> | uid     | Integer  | 学号     |
> | time    | Date     | 时间。   |
> | content | String   | 内容。   |

### 6. 私信接口

#### 6.1 获取私信列表

##### 6.1.1 功能描述

> 私信聊天接口。

##### 6.1.2 请求方式

> 请求方式：GET
>
> 请求URL：http://localhost:8081/community/msg/getMsg

##### 6.1.3 请求参数

> | 字段       | 字段类型 | 说明                                                         |
> | ---------- | -------- | ------------------------------------------------------------ |
> | posterId   | Integer  | 通过发送人ID。可不选，不选为只通过下面的接收人ID查。两个必选一个，同时选则获取的双人聊天信息。 |
> | receiverId | Integer  | 通过接收人ID。可不选，不选为只通过上面的发送人ID查。两个必选一个，同时选则获取的双人聊天信息。 |
> | pageNum    | Integer  | 页数。                                                       |
> | pageSize   | Integer  | 每页条目数。                                                 |

##### 6.1.4 请求示例

> 情况一：http://localhost:8081/community/msg/getMsg?posterId=181203221&pageNum=1&pageSize=2
>
> 情况二：http://localhost:8081/community/msg/getMsg?receiverId=181203221&pageNum=1&pageSize=2
>
> 情况三：http://localhost:8081/community/msg/getMsg?posterId=181203221&receiverId=181414241&pageNum=1&pageSize=2

##### 6.1.5 返回结果

```json
{
    "code": 1,
    "msg": "请求成功",
    "data": {
        "pageNum": 1,
        "pageSize": 2,
        "pages": 1,
        "total": 1,
        "list": [
            {
                "id": 1,
                "posterId": 181203221,
                "receiverId": 181414241,
                "time": "Jul 30, 2019 9:11:19 PM",
                "content": "悄悄告诉你一件事，有人跟你表白"
            }
        ]
    }
}
```

##### 6.1.6 返回参数 

> | 返回字段 | 字段类型 | 说明                             |
> | :------- | :------- | -------------------------------- |
> | code     | int      | 返回结果状态。0：失败；1：成功。 |
> | msg      | String   | 返回信息                         |
> | data     | Json     | 分页数据信息                     |

##### 6.1.7 页面信息参数

> | 字段     | 字段类型 | 字段说明       |
> | -------- | -------- | -------------- |
> | pageNum  | Integer  | 当前页数。     |
> | pageSize | Integer  | 每页数据条数。 |
> | pages    | Integer  | 总页数。       |
> | total    | Integer  | 总数据条数。   |
> | list     | Integer  | 数据列表。     |

##### 6.1.8 列表参数

> | 字段       | 字段类型 | 字段说明       |
> | ---------- | -------- | -------------- |
> | id         | Integer  | ID。           |
> | posterId   | Integer  | 发送人ID。     |
> | receiverId | Integer  | 接收人ID。     |
> | time       | Date     | 消息产生时间。 |
> | content    | String   | 消息内容。     |
