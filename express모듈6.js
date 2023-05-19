let express = require("express");
let app = express();
//params :
//localhost:8888/page/234
app.get("/page/:id", function (request, response) {
  console.log(request);
  console.log(request.params.id);
  response.send("test zz");
});

app.listen(8888);
