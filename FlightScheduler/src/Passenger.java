
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;




public class Passenger extends flightscheduler{
     private String Fname;
    private static PreparedStatement pst;
    private static ResultSet rs;
public Passenger(String n1)
{
    this.Fname = n1;
}
public void addpassenger()
{
    try{
   pst = dbconnection.getConnection().prepareStatement("INSERT INTO Passenger (Name) Values (?)");
    pst.setString(1,Fname);
    
    pst.execute();

}
catch(SQLException a)
{
    a.printStackTrace();
    System.exit(1);
}



}
public String getname()
{
    return Fname;
}
public static ArrayList <String>  getpassenger() 
{
    ArrayList <String> Passengers = null;
  
    try
    {
    pst = dbconnection.getConnection().prepareStatement("SELECT * FROM Passenger");
    rs = pst.executeQuery();
    Passengers = new ArrayList<String>();
             while (rs.next())
             {      
                 Passengers.add(new String(
                 rs.getString("Name")));

             }
            
    }
              
catch (Exception e) {
                  e.printStackTrace();
             }
    return Passengers;
}

  

}


