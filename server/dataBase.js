


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
};

/**Agnostic function that will do depending on json_instructions object
 * @param json_instructions object
 */
method.interact = function(json_instructions,callBack){
    if(json_instructions.retrieve){
        var sql = "SELECT * FROM EMPLOYEES";
        this.connection.query(sql, function(err,rows){
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
            return callBack(data);
        });
    }
}

module.exports = SQLDataBase;
