//사용자의 브라우저에 따른 페이지 분기
//브라우저?

let express = require("express");

let app = express();

app.use(function (request, response, next) {
  let userAgent = request.header("user-agent").toLowerCase(); // headers 에 사용자 정보 들어가있음
  if (userAgent.indexOf("edg") > -1) {
    response.send("엣쥐");
  } else if (userAgent.indexOf("chrome") > -1) {
    response.send("끄롬 접속중");
  } else {
    response.send("아직 체크 않함");
  }
  response.send("<h1>" + userAgent.indexOf("Edg") + "</h1>");
}); // 미들웨어 중간에서 무언가 실행 가능 여러개 추가가능.
app.use(function (request, response, next) {}); // 미들웨어 중간에서 무언가 실행 가능 여러개 추가가능.
app.use(function (request, response) {}); // 미들웨어 중간에서 무언가 실행 가능 여러개 추가가능. 다음 미들웨어가없으면 next 생략.

app.listen(8888, function () {
  //실행.
  console.log("8888");
});
