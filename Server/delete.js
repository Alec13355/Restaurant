
var method = DeleteModule.prototype;

/**
 * Constructor
 * @param {*} sqlCommand 
 */
function DeleteModule(sqlCommands,connection) {
    this.cone = connection;
    this.sqlStatements = sqlCommands;
};

method.execute = function(callBack){
    var con = this.cone;
    var sql = this.sqlStatements;
    console.log(sql);
    con.query(sql,function(err,rows){
        if(err){
            throw err;
        }
        return callBack({success:1});
    });

    
}



module.exports = DeleteModule;
