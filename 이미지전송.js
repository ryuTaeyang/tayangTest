let http = require("http");
let fs = require("fs");
http
  .createServer(function (req, res) {
    fs.readFile("22222.jpg", function (err, data) {
      if (!err) {
        res.writeHead(200, { "content-Type": "audio/mpeg" });
        res.end(data);
      } else {
        console.log(err);
      }
    });
  })
  .listen(8888, function () {});
