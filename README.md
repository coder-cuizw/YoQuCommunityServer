[TOC]

##  后端API开发接口文档

### 1. 用户接口

#### 1.1 登陆接口

##### 1.1.1 功能描述

> 使用学生的学号进行登陆

##### 1.1.2 请求方式

> 请求方式：GET
>
> 请求URL：http://localhost:8081/community/user/login

##### 1.1.3 请求参数

> | 字段 | 字段类型 | 字段说明 |
> | ---- | -------- | -------- |
> | uid  | Integer  | 用户学号 |

##### 1.1.4 请求示例

> http://localhost:8081/community/user/login&uid=181203221

##### 1.1.5 返回结果

登陆失败

```json
{
    "code": 0,
    "msg": "登陆失败"
}
```

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
        "unionId": "12312312312312"
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
