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
app.listen(3000,function(){
  console.log("Live at Port 3000");
});

//////////////////test 
var testdata = {
  retrieve: true,
  rowNum: 0
}
data_base.interact(testdata,function(res){
  console.log(res.first);
});
////////////////

/**
 * TCP calls to be used in diration of operation
 */
router.use(function (req,res,next) {
  console.log("/" + req.method);
  next();
});






