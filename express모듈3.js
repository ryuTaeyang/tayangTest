let fs = require("fs");
let express = require("express");

let app = express();
//__dirname을 웹폴더로 지정
app.use(express.static(__dirname + "/public"));

// app.use(function (request, response, next) {
fs.readFile("naver.html", "utf8", function (error, data) {
  response.send(data);
  next();
});
// });
app.use(function (request, response, next) {
  console.log("test1");
  request.tmp = 100;
  request.val = "value";
  next();
});
app.use(function (request, response, next) {
  console.log(request.tmp + " : " + request.val);
  response.send(request.tmp + " : " + request.val);

  next();
});

app.use(function (request, response, next) {
  console.log("test3");
  next();
});

app.use(function (request, response, next) {});

app.listen(8888, function () {
  console.log(8888);
});
