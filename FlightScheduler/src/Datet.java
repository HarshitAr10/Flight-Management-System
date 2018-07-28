

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;



public class Datet {
    
    private final Date dateavailable;
    private static PreparedStatement pst;
    private static ResultSet rs;
    
public Datet (Date d )
{
    this.dateavailable = d;
    
}
public Date getd()
{
    return dateavailable;
}

public void addDate() throws ParseException
{


 try{
   PreparedStatement pst = dbconnection.getConnection().prepareStatement("INSERT INTO Datet (Dateavailable) Values (?)");
    pst.setDate(1, dateavailable);
    pst.execute();

}
catch(SQLException a)
{
    a.printStackTrace();
    System.exit(1);
}
}
     public static ArrayList <Datet>  getdate() 
{
    ArrayList <Datet> dates = null;
    try
    {
    pst = dbconnection.getConnection().prepareStatement("SELECT * FROM Datet");
    rs = pst.executeQuery();
    dates = new ArrayList<Datet>();
             while (rs.next())
             {      
               
                 dates.add(new Datet(rs.getDate("Dateavailable")));

             }
            
    
    }
              
catch (Exception e) {
                  e.printStackTrace();
             }
    return dates;
}


    
}
