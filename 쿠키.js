let http = require("http");
http
  .createServer(function (req, res) {
    //쿠키 종료 시간 지정
    let oneday = new Date();
    oneday.setDate(oneday.getDate() + 1);
    let sevenday = new Date();
    sevenday.setDate(oneday.getDate() + 7);

    //쿠키 지정
    res.writeHead(200, {
      "Content-Type": "text/html",
      "set-cookie": [
        `key1 = val1;Expires = ${oneday.toUTCString()}`,
        `key2 = val2;Expires = ${oneday.toUTCString()}`,
        `key3 = val3;Expires = ${sevenday.toUTCString()}`,
      ],
    });

    //쿠키 사용 방법
    res.end(`<h1>${req.headers.cookie}</h1>`);
  })
  .listen(8888, function () {
    console.log("쿠키");
  });
