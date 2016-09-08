# BallBox
a project
一个简单的小程序
tab的模式
完成界面搭建，接下来提交各种账号审核
等更新了





接口说明文档
后台地址
http://www.taoqiuqiu.com/admin.php
账号 admin  密码 admin
服务器地址
URL = "http://www.taoqiuqiu.com/index.php?m=json&a=";
IMGURL =”http://www.taoqiuqiu.com/”
注：图片的地址前面加  IMGURL
返回值说明
code :0 或 -1 失败   ，1正常 ，2或其他为其他情况
msg:提示信息


1、主页面广告图
URL + "index"
传值：无
返回：
fflist:图片列表（目前只有一张）
url：跳转链接
img：图片地址
isshow：是否展示  1展示，0不展示
{"code":1,"msg":"\u67e5\u8be2\u6210\u529f","fflist":{"url":"http:\/\/www.baidu.com","img":"static\/items\/m_57cfa6366ba37.jpg","isshow":"1"}}

2、获取用户信息（包含注册）
URL + "userstatus"
传值：imei 用户imei值
返回：
fflist：列表
xslb:新手奖励 1已完成  0未完成
isqd：是否签到 1已完成 0未完成
isqq：qq分享 1已完成  0,20%,40%,60%,80%未完场
qqshare_num:qq已分享次数  0~5次 最多5次
money：用户当前余额
userid:用户id值（这里获取之后 后面都用这个咯）
{"code":1,"msg":"\u67e5\u8be2\u6210\u529f","fflist":{"xslb":1,"isqd":0,"isqq":0,"qqshare_num":0,"money":"5","userid":"10002"}}

3、广告点击奖励
URL + "ggdj"
传值：userid 用户uid值
返回：
fflist：1 成功 0失败
{"code":1,"msg":"\u5e7f\u544a\u70b9\u51fb\u5df2\u5956\u52b1","fflist":"1"}




4、每日签到
URL + "qian"
传值：userid 用户uid值
返回：
code:1成功，今日已签到
fflist：用户的剩余金额
{"code":1,"msg":"\u7b7e\u5230\u6210\u529f","fflist":"7"}
{"code":"2","msg":"\u5df2\u7b7e\u5230","fflist":"7"}

5、qq分享
URL + "qqshare"
传值：userid 用户uid值
返回：
code：1分享成功， 2完成任务 ，-1今日已经分享过了 ，0分享失败
fflist：当前分享的次数，当分享次数达到第五次的时候code会返回2，提示分享完成
{"code":1,"msg":"\u5206\u4eab\u6210\u529f","fflist":1}
{"code":2,"msg":"\u5b8c\u6210\u5206\u4eab\u4efb\u52a1","fflist":1}
{"code":"-1","msg":"\u4eca\u5929\u5df2\u5206\u4eab5\u6b21\u4e86","fflist":5}

6、摇一摇抽奖
URL + "yaoyyao"
传值：userid 用户uid值
返回：
code:0摇一摇失败（参数错误等）  ，-1 用户余额不足，1中奖了，2没有中奖
fflist： 当code=0,-1,2的时候 fflist无用。  当code=1的时候 fflist为用户中奖的金额
{"code":"0","msg":"userid\u4e0d\u4e3a\u7a7a","fflist":""}
{"code":"-1","msg":"\u7528\u6237\u91d1\u989d\u4e0d\u8db3","fflist":""}
{"code":"2","msg":"\u5f88\u62b1\u6b49\uff0c\u6ca1\u6709\u4e2d\u5956","fflist":"0"}
{"code":"1","msg":"\u606d\u559c\u60a8\u4e2d\u5956\u4e86","fflist":"15"}

7、摇一摇抽奖——中奖纪录
URL + "yaoyyao_log"
传值：无
返回：
fflist：中奖纪录
直接解析显示。。。 (不晓得是否是这样，具体后面再调整)
{"code":1,"msg":"\u67e5\u8be2\u6210\u529f","fflist":["\u7528\u6237ID1000****,\u83b7\u5f9716\u79ef\u5206","\u7528\u6237ID1000****,\u83b7\u5f9715\u79ef\u5206","\u7528\u6237ID1000****,\u83b7\u5f9746\u79ef\u5206","\u7528\u6237ID1000****,\u83b7\u5f9724\u79ef\u5206","\u7528\u6237ID1000****,\u83b7\u5f9727\u79ef\u5206","\u7528\u6237ID1000****,\u83b7\u5f9740\u79ef\u5206","\u7528\u6237ID1000****,\u83b7\u5f9744\u79ef\u5206","\u7528\u6237ID1000****,\u83b7\u5f9748\u79ef\u5206","\u7528\u6237ID1000****,\u83b7\u5f9744\u79ef\u5206","\u7528\u6237ID1000****,\u83b7\u5f9742\u79ef\u5206","\u7528\u6237ID1000****,\u83b7\u5f9732\u79ef\u5206"]}

8、兑换记录
URL + "dh_log"
传值：userid 用户id
返回：
code : 0失败，1成功，2无数据
fflist：列表
reason：兑换的商品
money：兑换的积分
status：状态
time：时间（时间搓格式）
{"code":"1","msg":"\u67e5\u8be2\u6210\u529f","fflist":[{"reason":"\u6447\u4e00\u6447\u62bd\u5956","money":"15","status":"\u5df2\u5151\u6362","time":"1473300643"}]}

9、用户中心文字广告
URL + "adword"
传值：无
返回：
fflist：
wadtitle:公告标题
wadurl：点击跳转的url
{"code":1,"msg":"\u67e5\u8be2\u6210\u529f","fflist":{"wadtitle":"\u6700\u65b0\u516c\u544a\uff1a\u5feb\u8fdb\u6765\u770b\u770b\u554a","wadurl":"http:\/\/www.baidu.com"}}



