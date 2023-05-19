//라우팅

let express = require("express");
let app = express();

//설정 할때 use 많이씀
app.use(function (request, response) {
  console.log("use");
  next();
});

//get,post 주로씀

app.get("/", function (request, response) {
  response.send("index.html");
});

app.get("/test", function (request, response) {
  response.send("naver.html");
});

app.get("*", function (request, response) {
  response.status(404).redirect("/");
});

app.listen(8888, function () {
  console.log(8888);
});
