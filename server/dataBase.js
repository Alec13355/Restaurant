


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

/** Method to add employee or edit current employee
 * @param json_input object to hold information about employee
 */
method.setUser = function(json_input,callBack){
        var searchsql = "SELECT * FROM EMPLOYEES WHERE  L_NAME = '" + json_input.lnam + "' AND F_NAME = '" + json_input.fname + "'";
        console.log(searchsql);
        var con = this.connection;
        this.connection.query(searchsql, function(err,out_rows){
            if(out_rows.length>0){
                var updatesql = "UPDATE EMPLOYEES SET PERM_STRING = '" + json_input.perm + "', STATUS = " + json_input.stat + " , PASS = '" + json_input.pass + "' , ADDRESS = '" + json_input.address + "' , CELL = '" + json_input.cell + "' , PHONE = '" + json_input.phone + "' , PAY_HR = " + json_input.rate + ", ROUTING = '" + json_input.routing + "', SOCIAL = '" + json_input.social + "', BANK_NUM = '" + json_input.bank_num + "'";
                console.log(updatesql);
                con.query(updatesql, function(err,rows){
                    return callBack(out_rows[0].L_NAME + "_" + out_rows[0].F_NAME + "_" + out_rows[0].out_rows);
                });
            }else{
                var sql = "INSERT INTO EMPLOYEES VALUES('" + json_input.lnam + "','" + json_input.fname + "',NULL,'" + json_input.perm + "'," + json_input.stat + ",'" + json_input.pass + "','" + json_input.address + "','" + json_input.cell + "','" + json_input.phone + "', " + json_input.rate + ",'" + json_input.routing + "','" + json_input.social + "','" + json_input.bank_num + "' )"; 
                console.log(sql);
                var tempcon = this.connection;
                con.query(sql, function(err,rows){
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
                return callBack(rows_[rows_.length-1].L_NAME + "_" + rows_[rows_.length-1].F_NAME + "_" + rows_.length);
                        
                    });
                }); 
            }
        });
        
        
        
        
}
/**
 * Gets reservation by index
 * @param json object with index
 * @return reservation information in json
 */
method.getResservation = function(json_input, callback){
    var sql = "SELECT * FROM RESERVATIONS";
    var con = this.connection;
    con.query(sql,function(err,rows){
        if(err){
            throw err;
        }
        var i = json_input.index;
        if(rows[i].TABLE_ID){
            var data={
            DESCRIPTION:rows[i].DESCRIPTION,
            ID:rows[i].ID,
            TABLE_ID:rows[i].TABLE_ID,
            STATUS:rows[i].STATUS,
            CUSTOMER_ID:rows[I].CUSTOMER_ID
            };
            return callback(data);
        }
        

    });
}
/**
 * Gets table by ID number_of_seats
 * @param json object with ID 
 * @return json object with table information
 */
method.getTableByID = function(json,callback){
    var sql = "SELECT * FROM TABLES WHERE TABLE_ID = " + json.id;
    this.connection.query(sql,function(err,rows){
        if(err){
            throw err;
        }
        if(rows.length>0){
             var data = {
            name: rows[0].NAME,
            id: rows[0].TABLE_ID,
            x_coord: rows[0].X_COORD,
            y_coord: rows[0].Y_COORD,
            number_seats: rows[0].NUM_SEATS,
            status: rows[0].STATUS
        }
        return callback(data);
        }
    });
}
/**[0].le
 * Places a reservation
 * @param json object with reservation information
 */
method.placeReservation = function(json_input, callBack){
    var con = this.connection;
    var sqlsearch = "SELECT * RESERVATIONS WHERE DESCRIPTION = '" + json_input.desc + "'";
    con.query(sqlsearch,function(err,rows){
        if(rows.length>0){
            var sqlupdate = "UPDATE RESERVATIONS SET TABLE_ID = " + json_input.table_id + ", STATUS = " + json_input.status + ", CUSTOMER_ID = " + json_input.customerID + " WHERE DESCRIPTION = '" + json_input.desc + "'";
            console.log(sqlupdate);
            con.query(sqlupdate, function(err,rows){
                if(err){
                    throw err;
                }
            }); 
        }else{
            var insertsql = "INSERT INTO RESERVATIONS VALUES ('" + json_input.desc + "',NULL," + json_input.table_id + "," + json_input.status + "," + json_input.customerID + ")";
            console.log(insertsql);
            con.query(insertsql,function(err,rows){
                if(err){
                    throw err;
                }

            });
        }
    }); 
}

method.removeOrder = function(json_input, callBack){
    var sql = "DELETE FROM ORDERS WHERE DESCRIP ='" + json_input.descript + "'";
    console.log(sql);
    this.connection.query(sql,function(err,rows){
        if(err){
            throw err;
        }
    });
}

/**
 * Method create or edit a table
 * @param json object with table information
 * 
 */
method.setTable = function(json_info,callBack){
    var con = this.connection;
    var searchsql = "SELECT * FROM TABLES WHERE NAME = '" + json_info.name + "'";
    console.log(searchsql);
    con.query(searchsql, function(err,rows){
        if(err){
            throw err;
        }
        if(rows.length>0){
            var updatesql = "UPDATE TABLES SET X_COORD = " + json_info.xcoord + ", Y_COORD = " + json_info.ycoord + ", NUM_SEATS = " + json_info.number_of_seats + ", STATUS = " + json_info.stat + " WHERE NAME = '" + json_info.name + "', EMPLOYEE_ID = " + json_info.employeeID;
            console.log(updatesql);
            con.query(updatesql, function(err,rows){
                if(err){
                    throw err;
                }
            });
        }else{
             var sql = "INSERT INTO TABLES VALUES('" + json_info.name + "',NULL," + json_info.xcoord + "," + json_info.ycoord + "," + json_info.number_of_seats + "," + json_info.stat + "," + json_info.employeeID + ");";
            console.log(sql);
    
    con.query(sql, function(err,rows){
        if(err){
            throw err;
        }
        return callBack({success:1});

    });
}
        
});
    
   
}
/**
 * Gets table information
 * @param json object with index
 * @return json object with table information
 */
method.getTable = function(json_info,cb){
    var sql = "SELECT * FROM TABLES";
    console.log(sql);
    var con = this.connection;
    var i = json_info.index;
    con.query(sql,function(err,rows){
        if(err){
            throw err;
        }
        if(rows[i]){
            var data = {
            name: rows[i].NAME,
            id: rows[i].TABLE_ID,
            x_coord: rows[i].X_COORD,
            y_coord: rows[i].Y_COORD,
            number_seats: rows[i].NUM_SEATS,
            status: rows[i].STATUS
        }
        return cb(data);
        }
        
    });
}
/**
 * gets user information
 * @param json object with employee USER_NAME
 * @return json object with employee information
 */
method.getUser = function(json_user,callBack){
    var sql = "SELECT * FROM EMPLOYEES WHERE L_NAME = '" + json_user.lname + "' AND F_NAME = '" + json_user.fname + "' AND EMPLOYEE_ID = " + json_user.id ;
    console.log(sql);
    this.connection.query(sql, function(err,rows){
        if(err){
            throw err;
        }
        if(rows.length>0){
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
                rate: rows[0].PAY_HR,
                routing: rows[0].ROUTING,
                social: rows[0].SOCIAL,
                bank_num: rows[0].BANK_NUM
            };
            return callBack(data);
        }
        
    });
}
/**
 * Gets users by index
 * @param json object with index
 * @return json object with user information
 */
method.getUserByIndex = function(json_info,callback){
    var sql = "SELECT * FROM EMPLOYEES";
    console.log(sql);
    this.connection.query(sql, function(err,rows){
        if(err){
            throw err;processSegment
        }
        var i = json_info.index;
        if(rows[i]){
            var data = {
                last: rows[i].L_NAME,
                first: rows[i].F_NAME,
                emp_id: rows[i].EMPLOYEE_ID,
                perm_string: rows[i].PERM_STRING,
                address: rows[i].ADDRESS,
                cell: rows[i].CELL,
                phone: rows[i].PHONE,
                status: rows[i].STATUS,
                pass: rows[i].PASS,
                rate: rows[i].PAY_HR,
                routing: rows[i].ROUTING,
                social: rows[i].SOCIAL,
                bank_num: rows[i].BANK_NUM
            };
            return callBack(data);
        }
        
    });
}
/**
 * Gets Table information with name
 * @param json with table name
 * @return json with table information
 */
method.getTableByName = function(json_info,cb){
    var name = json_info.name;
    var sql = "SELECT * FROM TABLES WHERE NAME = '" + name + "'";
    console.log(sql);
    this.connection.query(sql,function(err,rows){
        if(err){
            throw err;
        }
        if(rows.length>0){
            var data = {
            name: rows[0].NAME,
            id: rows[0].TABLE_ID,
            x_coord: rows[0].X_COORD,
            y_coord: rows[0].Y_COORD,
            number_seats: rows[0].NUM_SEATS,
            status: rows[0].STATUS
        }
        return cb(data);
        }
        
    });
}
/**
 * gets log events
 * @return json with logs
 * @param json with employee id
 *
 */
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
/**
 * 
 * @param {*} user string of username 
 * @param {*} cb callback with parsed information
 */
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

/**
 * logs events with database
 */
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

method.getScheduleWithID = function(json_info,callBack){
    var employeeID = json_info.emp_id;
    var sql = "SELECT * FROM SCHEDULE WHERE sched_id = " + employeeID;
    console.log(sql);
    this.connection.query(sql, function(err,rows){
        if(err){
            throw err;
        }
        if(rows.length>0){
            return callBack(rows);
        }else{
            return callBack({status:empty});
        }
    }); 
}

method.addSchedule = function(json_info,callBack){
    var day = json_info.day;
    var start = json_info.start;
    var end = json_info.end;
    var emp_id = json_info.emp_id;


}


/**
 * gets food items
 */
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
            desc: rows[0].DESCR, 
            options: rows[0].OPTIONS
        };
        return callBack(data);
        }
        
    });
}


method.getCustomer = function(json_info,callBack){
    var sql = "SELECT * FROM CUSTOMER WHERE USER_NAME = '" + json_info.user + "'"; 
    console.log(sql);
    this.connection.query(sql, function(err,rows){
        if(err){
            throw err;
        }
        return callBack({user:rows[0].USER_NAME, id:rows[0].CUSTOMER_ID , email: rows[0].EMAIL,pass: rows[0].PASS});
    });
}
/**
 * adds the customer to the database
 */
method.addCustomer = function(json_info,callBack){
    var searchsql = "SELECT * FROM CUSTOMER WHERE USER_NAME = '" + json_info.user + "'";
    console.log(searchsql);
    var con = this.connection;
    con.query(searchsql, function(err,rows){
        if(err){
            throw err;
        }
        if(rows[0].length>0){
            var sqlupdate = "UPDATE CUSTOMER SET EMAIL = " + json_info.email + ", PASS = '" + json_info.pass + "' WHERE USER_NAME = '" + json_info.user + "'" ;
            console.log(sqlupdate);
            con.query(sqlupdate, function(err,rows){
                if(err){
                    throw err;
                }
                return callBack({success:1});
            });
        }else{
            var sql = "INSERT INTO CUSTOMER VALUES('" + json_info.user + "',NULL,'" + json_info.email + "','" + json_info.pass + "')"; 
        console.log(sql);
        con.query(sql, function(err,rows){
        if(err){
            throw err;
        }
        return callBack({success:1});
    });
        }
        
    })
    
}

/**
 * adds food to the database
 */
method.addFood = function(json_info,cb){
    var con = this.connection;
    var sql = "SELECT * FROM FOOD WHERE NAME = '" + json_info.name + "'";
    console.log(sql);
    con.query(sql,function(err,rows){
        if(rows.length>0){
            var quant = rows[0].QUANTITY + json_info.quan;
            sql = "UPDATE FOOD SET QUANTITY = " + quant  + ",PRICE = " + json_info.price + ",DESCR = '" + json_info.desc + "',OPTIONS = '" + json_info.opt + "' WHERE NAME = '" + json_info.name + "' ;";
            console.log(sql); 
            con.query(sql,function(err,rows){
                if(err){
                    throw err;
                }
                return cb({added:1});
            });
        }else{
            sql = "INSERT INTO FOOD VALUES('" + json_info.name + "',NULL," + json_info.quan + "," + json_info.price + ",'" + json_info.desc + "','" + json_info.opt + "');"
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
/**
 * add order to the database
 */
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
/**
 * gets and order from the database
 */
method.getOrders = function(json_info,cb){
    var con = this.connection;
    var i = json_info.index;
    var sql = "SELECT * FROM ORDERS";
    console.log(sql);
    con.query(sql, function(err,rows){
        if(err){
            throw err;
        }
        if(rows.length > 0 && i < rows.length){
            var json = {
            desc: rows[i].DESCRIP,
            order_id: rows[i].ORDER_ID,
            componentString: rows[i].FOODSTRING,
            local: rows[i].LOCATION,
            price: rows[i].PRICE
            };
            return cb(json);
        }else{
            return cb({none:1}); 
        }
        

    });
}
/**
 * gets a food json via id of food
 */
method.getFoodByID = function(json_info,cb){
    var json;
    var food = json_input.food;
    parseFID(0,json,food,function(res){
        return cb(res);
    });

    
}
/**
 * gets food by index
 */
method.getFoodByIndex = function(json_info,cb){
    var con = this.connection;
    var i = json_info.index
    var sql = "SELECT * FROM FOOD";
    console.log(sql);
    con.query(sql,function(err,rows){
        if(rows.length>0){
            var data = {
            name: rows[i].NAME,
            food_id: rows[i].FOOD_ID,
            quantity: rows[i].QUANTITY,
            price: rows[i].PRICE,
            desc: rows[i].DESCR,
            options:rows[i].OPTIONS
             
        };
        return callBack(data);
        }
        
    });
}
/**
 * 
 * @param {*} index index number 
 * @param {*} json json with information
 * @param {*} foodIDS string of food ids
 * @param {*} cb call back function
 */
function parseFID(index,json,foodIDS,cb){
    var con = this.connection;
    var cache = "";
    
    for(var x=0;x<food.length;x++){
        if(foodIDS.charAt(x)==="-"){
            var foodData = processSegment(cache);
          var sql = "SELECT * FROM FOOD WHERE FOOD_ID =" + foodData.foodID;
           console.log(sql);
           cache="";
           con.query(sql,function(err,rows){
               if(err){
                   throw err;
               }
               json["NAME"+index] = rows[0].NAME;
               json["FOOD_ID"+index] = rows[0].ORDER_ID;
               json["QUANTITY"+index] = rows[0].QUANTITY;
               json["PRICE"+index] = rows[0].PRICE;
               json["DESCR"+index] = rows[0].DESCR;
               json["OPTIONS"+index] = foodData.options;
               parseFID(index+1,json,foodIDS.substring(x+1,foodIDS.length),cb);
           }); 
        }else{
            cache+=foodIDS.charAt(x);
        }
    }
    return cb(json);
}
/**
 * Parses the component string parts
 * @param {*} segString part of the component string 
 */
function processSegment(segString){
    var parsedInfo;
    var temp = "";
    var optionNum = 0;
    var options;
    for(var x=0;x<segString.length;x++){
        if(segString.charAt(x)==="("){
            parseedInfo.foodID = temp;
            temp="";
        }else{
            if(segString.charAt(x)===","){
                parsedInfo["OPTION" + optionNum] = temp;
                temp="";
                optionNum++;
            }else{
                if(segString.charAt(x)===")"){
                    options["OPTION" + optionNum] = temp;
                    parsedInfo.options = options;
                    return parsedInfo;
                }else{
                    temp+=segString.charAt(x);
                }
            }
            
            
        }
        
        

    }
}





module.exports = SQLDataBase;
