let http = require("http");

let server = http.createServer(function (req, res) {
  //req(request) : 요청
  //res(response) : 응답
});
server.listen(8888, function () {
  //listen 서버 실행
  console.log("8888포트에서 서버 실행");
});

server.on("connection", function (obj) {
  console.log(obj);
  console.log("누군가 접속");
});

//서버 종료
setTimeout(function () {
  server.close(function (error) {
    if (error) {
      console.log(error);
    } else {
      console.log("정상 종료");
    }
  });
}, 10000);

//종료 이벤트
server.on("close", function () {
  console.log("종료 이벤트");
});
