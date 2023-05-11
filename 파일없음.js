let http = require("http");
http
  .createServer(function (req, res) {
    res.writeHead(404);
    res.end();
  })
  .listen(8888, function () {
    console.log("페이지 없음...");
  });
