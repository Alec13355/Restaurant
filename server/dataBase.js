var mysql = require("mysql");

var connection;

var dataBase = function (host,user,pass) {
    connection = mysql.createConnection({
       host: host,
       user:  user,
       password: pass,
       database: "restaurant"
    });
};

dataBase.prototype.getInterface (json_instructions,callBack){
    if(json_instructions.retrieve){
        var sql = "SELECT * FROM EMPLOYEES";
        connection.query(sql, function(err,rows){
            if(err){
                throw err;
            }
            var data = {
                last: rows[json_instructions.rowNum].L_NAME,
                first: rows[json_instructions.rowNum].F_NAME,
                emp_id: rows[json_instructions.rowNum].EMPLOYEE_ID,
                perm_string: rows[json_instructions.rowNum].PERM_STRING,
                status: rows[json_instructions.rowNum].STATUS
            };
            return cb(data);
        });
    }
}