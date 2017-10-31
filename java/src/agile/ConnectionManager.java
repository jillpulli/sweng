package agile;


import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionManager {
	
    public static DataSource getMySQLDataSource() {
        MysqlDataSource mysqlDS = new MysqlDataSource();
            mysqlDS.setServerName("127.0.0.1");
            mysqlDS.setUser("myersjac");
            mysqlDS.setPassword("p@ssw0rd");
        return mysqlDS;
    }
}
