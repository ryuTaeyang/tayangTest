let express = require("express");
let app = express();

app
  .use(function (request, response) {
    //reponse
    //send(): 데이터 전송
    //status(): 상태 전송
    //sendStatus(): 코드만 전송
    response.status(404).send(`
    <body></body>
    <script>
      let i = 0;
      setInterval(() => {
        document.body.innerHTML = "<h1>" + ++i + "초 후 네이버로 이동</h1>";
      }, 1000);
      setTimeout(()=> {
        location.href = "http://www.naver.com";
      }, 5 * 1000);
    </script>
  `);
  })
  .listen(8888, function () {
    console.log("8888");
  });
