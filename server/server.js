/**
 * Required Modules
 */
var express = require("express");
var app = express();

var DataBase = require('./dataBase.js');

var mysql = require("mysql");
/**
 * Setup
 */
var router = express.Router();
var path = __dirname + '/views/';
var data_base = new DataBase(mysql,'localhost','shane','devPassword');
app.use("/",router);
app.listen(3004,function(){
  console.log("Live at Port 3004");
});

/**
 * TCP calls to be used in diration of operation
 */
router.use(function (req,res,next) {
  console.log("/" + req.method);
  next();
});

router.post("/getTestData",function(req,res,next){
  console.log("Got Request");
  var testdata = {
  retrieve: true,
  rowNum: 0
}
data_base.interact(testdata,function(resp){
  res.json({name:resp.first});
  });
});

router.post("/getAccount", function(req,res,next){
  
});



