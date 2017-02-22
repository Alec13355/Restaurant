


var method = SQLDataBase.prototype;

/**
 * constructor
 * @param db, the database api, host, the name in the form of a string,
 *  user, the user in the form of a string, pass, the password in the form of a string
 */
 function SQLDataBase(db ,host,user,pass,database) {
    this.connection = db.createConnection({
       host: host,
       user:  user,
       password: pass,
       database: database
    });
    this.connection.connect(function(err) {
    if (err) {
        throw err;
        return;
    }
    console.log("Connection Established With MYSQL");
});
};


/**Agnostic function that will do depending on json_instructions object
 * @param json_instructions object
 */
method.setUser = function(json_input,callBack){
    
    
    
        var sql = "INSERT INTO EMPLOYEES VALUES('" + json_input.lnam + "','" + json_input.fname + "',NULL,'" + json_input.perm + "'," + json_input.stat + ",'" + json_input.pass + "','" + json_input.address + "','" + json_input.cell + "','" + json_input.phone + "', " + json_input.rate + " )"; 
        console.log(sql);
        var tempcon = this.connection;
        this.connection.query(sql, function(err,rows){
            if(err){
                throw err;
            }
            console.log("returning");
           var sizeSQL = "SELECT * FROM EMPLOYEES";
            console.log(sizeSQL);
           tempcon.query(sizeSQL, function(err,rows_){
            if(err){
                throw err;
            }
            
            //return callBack(json_input.lnam + "_" + json_input.fname + "_" + rows_.length);
            return callBack(rows_[rows_.length-1].L_NAME + "_" + rows_[rows_.length-1].F_NAME + "_" + rows_.length);
            
        });
    }); 
        
}

method.setTable = function(json_info,callBack){
    var sql = "INSERT INTO TABLES VALUES('" + json_info.name + "',NULL," + json_info.xcoord + "," + json_info.ycoord + ");";
    console.log(sql);
    var con = this.connection;
    con.query(sql, function(err,rows){
        if(err){
            throw err;
        }
        return callBack({success:1});

    });
}

method.getTable = function(json_info,cb){
    var sql = "SELECT * FROM TABLES";
    console.log(sql);
    var con = this.connection;
    con.query(sql,function(err,rows){
        if(err){
            throw err;
        }
        if(rows[i]){
            var i = json_info.index;
            var data = {
            name: rows[i].NAME,
            id: rows[i].TABLE_ID,
            x_coord: rows[i].X_COORD,
            y_coord: rows[i].Y_COORD
        }
        return cb(data);
        }else{
            return cb({done:1});
        }
        
    });
}

method.getUser = function(json_user,callBack){
    var sql = "SELECT * FROM EMPLOYEES WHERE L_NAME = '" + json_user.lname + "' AND F_NAME = '" + json_user.fname + "' AND EMPLOYEE_ID = " + json_user.id ;
    console.log(sql);
    this.connection.query(sql, function(err,rows){
        if(err){
            throw err;
        }
        var data = {
                last: rows[0].L_NAME,
                first: rows[0].F_NAME,
                emp_id: rows[0].EMPLOYEE_ID,
                perm_string: rows[0].PERM_STRING,
                address: rows[0].ADDRESS,
                cell: rows[0].CELL,
                phone: rows[0].PHONE,
                status: rows[0].STATUS,
                pass: rows[0].PASS,
                rate: rows[0].PAY_HR
            };
            return callBack(data);
    });
}

method.getTableByName = function(json_info,cb){
    var name = json_info.name;
    var sql = "SELECT * FROM TABLES WHERE NAME = '" + name + "'";
    console.log(sql);
    this.connection.query(sql,function(err,rows){
        if(err){
            throw err;
        }
         
        var data = {
            name: rows[0].NAME,
            id: rows[0].TABLE_ID,
            x_coord: rows[0].X_COORD,
            y_coord: rows[0].Y_COORD
        }
        return cb(data);
    });
}

method.getLogEvent = function(json_info,callBack){
    var data;
    var connection  = this.connection;
    pars(json_info.user, function(rp){
    var sql = "SELECT * FROM EMPLOYEE_LOG WHERE EMPLOYEE_ID = " + rp.id   
    connection.query(sql,function(err,rows){
        if(err){
            throw err;
        }
        if(rows.length>0){
           data = rows[json_info.index];
           return callBack(data);
        }
        
    });
    
    });
    
}

function pars(user,cb){
    console.log(user);
    var last="";
    var first="";
    var id="";
    var buffer;
    var state = 0;
    for(var x=0;x<user.length;x++){
        buffer = user.charAt(x);
        if(buffer==='_'){
            state++;
        }else{
            if(state==0){
                last+=buffer;
            }
            if(state==1){
                first+=buffer;
            }
            if(state==2){
                id+=buffer;
            }
        }
       
    }
    var out = {
        first:first,
        last:last,
        id:id
    };
    console.log(out);
    return cb(out)
}


method.logEvent = function(json_info,callBack){
    var data = {
        error: 0
    };
    
    var connection  = this.connection;
    
    pars(json_info.user,function cb(rp){

    
    var check_sql = "SELECT * FROM EMPLOYEE_LOG WHERE EMPLOYEE_ID = " + rp.id;
    console.log(check_sql);
    connection.query(check_sql,function(err,rows_){
        if(err){
            throw err;
        }
        if( rows_.length==0 || (json_info.isIn != rows_[rows_.length-1].CLOCK_IN && json_info.isOut != rows_[rows_.length-1].CLOCK_OUT)){
            
            var sql = "INSERT INTO EMPLOYEE_LOG VALUES( " + rp.id + " ,NULL, " + json_info.isIn + "," + json_info.isOut + ",'" + json_info.timeStamp + "');";
            console.log(sql);
            connection.query(sql,function(err,rows){
                if(err){
                    throw err;
                }   
                console.log("Logged in time for employee");
                return callBack(data);
            });
    }    
});

});
   
}

method.getFood = function(json_info,callBack){
    var con = this.connection;
    var foodName = json_info.name;
    var sql = "SELECT * FROM FOOD WHERE NAME = '" + foodName + "'";
    console.log(sql);
    con.query(sql,function(err,rows){
        if(rows.length>0){
            var data = {
            name: rows[0].NAME,
            food_id: rows[0].FOOD_ID,
            quantity: rows[0].QUANTITY,
            price: rows[0].PRICE,
            desc: rows[0].DESCR 
        };
        return callBack(data);
        }
        
    });
}

method.addFood = function(json_info,cb){
    var con = this.connection;
    var sql = "SELECT * FROM FOOD WHERE NAME = '" + json_info.name + "'";
    console.log(sql);
    con.query(sql,function(err,rows){
        if(rows.length>0){
            sql = "UPDATE FOOD SET QUANTITY = " + rows[0].QUANTITY + json_info.quan  + ",PRICE = " + json_info.price + ",DESCR = '" + json_info.desc + "' WHERE NAME = '" + json_info.name + "' ;";
            console.log(sql); 
            con.query(sql,function(err,rows){
                if(err){
                    throw err;
                }
                return cb({added:1});
            });
        }else{
            sql = "INSERT INTO FOOD VALUES('" + json_info.name + "',NULL," + json_info.quan + "," + json_info.price + ",'" + json_info.desc + "');"
            console.log(sql);
            con.query(sql,function(err,rows_){
                if(err){
                    throw err;
                }
                return cb({added:1});
            });
        }
    });
}

method.placeOrder = function(json_info,cb){
    var con = this.connection;
    console.log(json_info);
    var desc = json_info.desc;
    var componentString = json_info.components;
    var local = json_info.local;
    var price = json_info.price;
    var sql = "INSERT INTO ORDERS VALUES('" + desc + "',NULL,'" + componentString + "','" + local + "'," + price + ");"; 
    console.log(sql);
    con.query(sql, function(err,rows){
        if(err){
            throw err;
        }
        return cb({success:1});
    });
}

method.getOrders = function(json_info,cb){
    var con = this.connection;
    var i = json_info.index;
    var sql = "SELECT * FROM ORDERS";
    console.log(sql);
    con.query(sql, function(err,rows){
        if(err){
            throw err;
        }
        if(rows.length > 0){
            var json = {
            desc: rows[i].DESCRIP,
            order_id: rows[i].ORDER_ID,
            componentString: rows[i].FOODSTRING,
            local: rows[i].LOCATION,
            price: rows[i].PRICE
            };
            return cb(json);
        }
        

    })
}

method.getFoodByID = function(json_info,cb){
    var json;
    var food = json_input.food;
    parseFID(0,json,food,function(res){
        return cb(res);
    });

    
}

function parseFID(index,json,foodIDS,cb){
    var con = this.connection;
    for(var x=0;x<food.length;x++){
        if(food.charAt(x)==="-"){
          var sql = "SELECT * FROM FOOD WHERE FOOD_ID =" + cache;
           console.log(sql);
           con.query(sql,function(err,rows){
               if(err){
                   throw err;
               }
               json["NAME"+index] = rows[0].NAME;
               json["FOOD_ID"+index] = rows[0].ORDER_ID;
               json["QUANTITY"+index] = rows[0].QUANTITY;
               json["PRICE"+index] = rows[0].PRICE;
               json["DESCR"+index] = rows[0].DESCR;
               parseFID(index+1,json,foodIDS.substring(x+1,foodIDS.length),cb);
           }); 
        }else{
            cache+=food.charAt(x);
        }
    }
    return cb(json);
}




module.exports = SQLDataBase;
