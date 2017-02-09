


var method = SQLDataBase.prototype;

/**
 * constructor
 * @param db, the database api, host, the name in the form of a string,
 *  user, the user in the form of a string, pass, the password in the form of a string
 */
 function SQLDataBase(db ,host,user,pass) {
    this.connection = db.createConnection({
       host: host,
       user:  user,
       password: pass,
       database: "restaurant"
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
    
    
    
        var sql = "INSERT INTO EMPLOYEES VALUES('" + json_input.lnam + "','" + json_input.fname + "',NULL,'" + json_input.perm + "'," + json_input.stat + ",'" + json_input.pass + "','" + json_input.address + "'," + json_input.cell + "," + json_input.phone + ")"; 
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
                pass: rows[0].PASS
            };
            return callBack(data);
    });
}

module.exports = SQLDataBase;
