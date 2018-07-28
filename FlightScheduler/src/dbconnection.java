
import java.sql.*;



public class dbconnection {
    
    private static Connection con;
    private static String URL = "jdbc:derby://localhost:1527/FlightSchedulerDBhba5107";
    private static String user = "java";
    private static String pass = "java";
    
    public static Connection getConnection()
    {
     try{
         
        con = DriverManager.getConnection(URL,user,pass);
        
        }
        
        catch (SQLException a)
        {
            a.printStackTrace();
            System.exit(1);         
        } 
        return con;
    }

   
    
}
