/*
 Navicat Premium Data Transfer

 Source Server         : mongoDB
 Source Server Type    : MongoDB
 Source Server Version : 30204
 Source Host           : localhost:27017
 Source Schema         : spitdb

 Target Server Type    : MongoDB
 Target Server Version : 30204
 File Encoding         : 65001

 Date: 10/09/2020 16:34:37
*/


// ----------------------------
// Collection structure for spit
// ----------------------------
db.getCollection("spit").drop();
db.createCollection("spit");

// ----------------------------
// Documents of spit
// ----------------------------
db.getCollection("spit").insert([ {
    _id: "1",
    content: "少时诵诗书所所所所所所所",
    userid: "1",
    nickname: "demoData",
    visits: NumberInt("1"),
    thumbup: NumberInt("1"),
    share: NumberInt("1"),
    comment: NumberInt("1"),
    state: "demoData",
    _class: "com.tensquare.spit.pojo.Spit"
} ]);
db.getCollection("spit").insert([ {
    _id: "2",
    content: "加班到半夜",
    userid: "1013",
    nickname: "凯撒",
    visits: NumberInt("2001"),
    parentid: "1",
    thumbup: 5,
    state: "",
    _class: "",
    comment: NumberInt("1"),
    share: NumberInt("0")
} ]);
db.getCollection("spit").insert([ {
    _id: "3",
    content: "手机流量超了怎么办!!!",
    userid: "1013",
    nickname: "凯撒",
    visits: NumberInt("2002"),
    parentid: "2",
    thumbup: 2,
    state: "",
    _class: "",
    comment: NumberInt("0"),
    share: NumberInt("0")
} ]);
db.getCollection("spit").insert([ {
    _id: "4",
    content: "坚持就是胜利",
    userid: "1014",
    nickname: "诺诺",
    visits: NumberInt("2003"),
    parentid: "2",
    thumbup: NumberInt("2"),
    state: "",
    _class: "",
    comment: NumberInt("0"),
    share: NumberInt("0")
} ]);
db.getCollection("spit").insert([ {
    _id: "1299943916547739648",
    content: "vfvfvfvfvfvfvfvfvfvfv",
    userid: "vvv",
    nickname: "cdcdcdcdcdcdcdcdcdcd",
    visits: NumberInt("10"),
    thumbup: NumberInt("1"),
    share: NumberInt("1"),
    comment: NumberInt("1"),
    state: "2",
    parentid: "2",
    _class: "com.tensquare.spit.pojo.Spit"
} ]);
db.getCollection("spit").insert([ {
    _id: "1299953146205966336",
    content: "ccdcdcdcdcdcdcdcd",
    userid: "1014",
    nickname: "sssssss",
    visits: NumberInt("1"),
    thumbup: NumberInt("1"),
    share: NumberInt("1"),
    comment: NumberInt("1"),
    state: "3",
    parentid: "3",
    _class: "com.tensquare.spit.pojo.Spit"
} ]);
db.getCollection("spit").insert([ {
    _id: "1299974624674713600",
    publishtime: ISODate("2020-08-30T07:37:57.54Z"),
    visits: NumberInt("0"),
    thumbup: NumberInt("0"),
    share: NumberInt("0"),
    comment: NumberInt("0"),
    state: "1",
    parentid: "1",
    _class: "com.tensquare.spit.pojo.Spit"
} ]);
db.getCollection("spit").insert([ {
    _id: "1299974775015346176",
    publishtime: ISODate("2020-08-30T07:38:33.384Z"),
    visits: NumberInt("0"),
    thumbup: NumberInt("0"),
    share: NumberInt("0"),
    comment: NumberInt("0"),
    state: "1",
    parentid: "2",
    _class: "com.tensquare.spit.pojo.Spit"
} ]);
db.getCollection("spit").insert([ {
    _id: "1299975362607976448",
    publishtime: ISODate("2020-08-30T07:40:53.477Z"),
    visits: NumberInt("0"),
    thumbup: NumberInt("0"),
    share: NumberInt("0"),
    comment: NumberInt("0"),
    state: "1",
    parentid: "1",
    _class: "com.tensquare.spit.pojo.Spit"
} ]);
db.getCollection("spit").insert([ {
    _id: "1299976046107561984",
    publishtime: ISODate("2020-08-30T07:43:36.436Z"),
    visits: NumberInt("0"),
    thumbup: NumberInt("0"),
    share: NumberInt("0"),
    comment: NumberInt("0"),
    state: "1",
    parentid: "1",
    _class: "com.tensquare.spit.pojo.Spit"
} ]);
db.getCollection("spit").insert([ {
    _id: "1299976432952414208",
    publishtime: ISODate("2020-08-30T07:45:08.667Z"),
    visits: NumberInt("0"),
    thumbup: NumberInt("0"),
    share: NumberInt("0"),
    comment: NumberInt("0"),
    state: "1",
    parentid: "1",
    _class: "com.tensquare.spit.pojo.Spit"
} ]);
db.getCollection("spit").insert([ {
    _id: "1299977622050181120",
    publishtime: ISODate("2020-08-30T07:49:52.17Z"),
    visits: NumberInt("0"),
    thumbup: NumberInt("0"),
    share: NumberInt("0"),
    comment: NumberInt("0"),
    state: "1",
    parentid: "1",
    _class: "com.tensquare.spit.pojo.Spit"
} ]);
db.getCollection("spit").insert([ {
    _id: "1299978253938855936",
    publishtime: ISODate("2020-08-30T07:52:22.824Z"),
    visits: NumberInt("0"),
    thumbup: NumberInt("0"),
    share: NumberInt("0"),
    comment: NumberInt("0"),
    state: "1",
    parentid: "1",
    _class: "com.tensquare.spit.pojo.Spit"
} ]);
db.getCollection("spit").insert([ {
    _id: "1299978607715815424",
    publishtime: ISODate("2020-08-30T07:53:47.171Z"),
    visits: NumberInt("0"),
    thumbup: NumberInt("0"),
    share: NumberInt("0"),
    comment: NumberInt("1"),
    state: "1",
    parentid: "1",
    _class: "com.tensquare.spit.pojo.Spit"
} ]);
db.getCollection("spit").insert([ {
    _id: "1303663926084505600",
    content: "斗罗大陆",
    publishtime: ISODate("2020-09-09T11:57:55.54Z"),
    userid: "1111",
    nickname: "唐家三少",
    visits: NumberInt("0"),
    thumbup: NumberInt("0"),
    share: NumberInt("0"),
    comment: NumberInt("1"),
    state: "1",
    parentid: "0",
    _class: "com.tensquare.spit.pojo.Spit"
} ]);
