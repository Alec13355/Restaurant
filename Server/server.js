/**
 * Required Modules
 */
var express = require("express");
var app = express();
var bodyParser = require('body-parser');

var DataBase = require('./dataBase.js');

var mysql = require("mysql");
/**
 * Setup
 */
//use bodyParser() to let us get the data from a POST
app.use(bodyParser.json());
var port = 2224;
app.use(bodyParser.urlencoded({ 
   extended: true 
}));
var router = express.Router();
var path = __dirname + '/views/';
var data_base = new DataBase(mysql,'mysql.cs.iastate.edu','dbu309yt4','ZDQyY2E5OTQ2','db309yt4');
//var data_base = new DataBase(mysql,'localhost','shane','devPassword','restaurant');
app.use("/",router);
app.listen(port,function(){
  console.log("Live at Port " + port);
});

/**
 * TCP calls to be used in diration of operation
 */
router.use(function (req,res,next) {
  console.log("/" + req.method);
  next();
});

router.post("/createAccount", function(req,res){
  data_base.setUser(req.body, function(resp){
    res.json({user:resp});
  });
});

router.post("/log", function(req,res){
  data_base.logEvent(req.body, function(resp){
    res.json(resp);
  });
});

router.post("/getLog", function(req,res){
  data_base.getLogEvent(req.body, function(resp){
    res.json(resp);
  });
});

router.post("/getFood", function(req,res){
  data_base.getFood(req.body, function(resp){
    res.json(resp);
  });
});

router.post("/addFood", function(req,res){
  data_base.addFood(req.body, function(resp){
    res.json(resp);
  });
});

router.post("/getEmployeeWithID", function(req,res){
  data_base.getEmployeeWithID(req.body, function(resp){
    res.json(resp);
  });
});

router.post("/placeOrder", function(req,res){
  data_base.placeOrder(req.body, function(resp){
    res.json(resp);
  });
});

router.post("/placeReservations", function(req,res){
  data_base.placeReservation(req.body,function(resp){
    res.json(resp);
  });
});

router.post("/getReservation", function(req,res){
  data_base.getResservation(req.body,function(resp){
    res.json(resp);
  });
});

router.post("/getTableByID", function(req,res){
  data_base.getTableByID(req.body,function(resp){
   res.json(resp); 
  });
});

router.post("/getOrder", function(req,res){
  data_base.getOrders(req.body, function(resp){
    res.json(resp);
  });
  });

router.post("/setTable", function(req,res){
  data_base.setTable(req.body, function(resp){
    res.json(resp);
  });
});

router.post("/getTable", function(req,res){
  data_base.getTable(req.body, function(resp){
    res.json(resp);
  });
});

router.post("/removeOrder", function(req,res){
  data_base.removeOrder(req.body, function(resp){
    res.json(resp);
  });
});



router.post("/getTableWithName", function(req,res){
  data_base.getTableByName(req.body, function(resp){
    res.json(resp);
  })
});

router.post("/getUserByIndex", function(req,res){
  data_base.getUserByIndex(req.body, function(resp){
    res.json(resp);
  })
});

router.post("/addCustomer", function(req,res){
  data_base.addCustomer(req.body,function(resp){
    res.json(resp);
  });
});

router.post("/getCustomer", function(req,res){
  data_base.getCustomer(req.body,function(resp){
    res.json(resp);
  });
});

router.post("/getFoodByIndex", function(req,res){
  data_base.getFoodByIndex(req.body, function(resp){
    res.json(resp);
  });
});

router.post("/delete", function(req,res){
  data_base.executeDelete(req.body, function(resp){
    res.json(resp);
  });
});

router.post("/getAccount", function(req,res){
  console.log(req.body.name)
  if(req.body.name){
  var userName = req.body.name;
  console.log(userName);
  /*Parse String */
  var lastName="";
  var firstName="";
  var ID="";
  var state=0;
  var buffer;
  for(var x=0;x<userName.length;x++){
    buffer = userName.charAt(x);
    if(buffer==='_'){
      state++;
    }else{
      if(state==0){
        lastName+=buffer;
      }
      if(state==1){
        firstName+=buffer;
      }
      if(state==2){
        ID+=buffer;
    }
    }
    
  }
  
  var input = {
    fname: firstName,
    lname: lastName,
    id:ID
  };
  
  data_base.getUser(input,function(resp){
    res.json(resp);
  });
  
}else{
    console.log("No Input")
  }
  
});


