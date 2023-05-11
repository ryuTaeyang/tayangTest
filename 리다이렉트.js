let http = require("http");
http
  .createServer(function (req, res) {
    res.writeHead(302, { Location: "http://naver.com" });
    res.end();
  })
  .listen(8888, function () {
    console.log("리다이렉트 구동중");
  });
