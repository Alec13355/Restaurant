


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


module.exports = SQLDataBase;
