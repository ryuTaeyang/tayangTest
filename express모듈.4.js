//쿼리데이터 읽기
//localhost:8888?test=1234&name=홍길동
let express = require("express");
let app = express();

app.use(function (request, response) {
  //   console.log(request.query.name + " : " + request.query.test);
  let { name, test: value } = request.query;
  response.send(name + " : " + value);
});

app.listen(8888, function () {
  console.log(8888);
});
