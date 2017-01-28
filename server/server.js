var express = require("express");
var dataBase = require('./dataBase.js');
var app = express();
var router = express.Router();
var path = __dirname + '/views/';

router.use(function (req,res,next) {
  console.log("/" + req.method);
  next();
});

router.get("/",function(req,res){
  res.sendFile(path + "index.html");
});

app.use("/",router);


app.listen(3001,function(){
  console.log("Live at Port 3000");
});