
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Flightdetails  {
    
     private String Flightname;
     private static int numseats;
    private static PreparedStatement pst;
    private static ResultSet rs;
    
    public Flightdetails(String fn1, int ns)
    {
        this.Flightname = fn1;
        this.numseats = ns;
    }
    
    
    public void addflight()
    {
        try{
            
            pst = dbconnection.getConnection().prepareStatement("INSERT INTO FLIGHT(Name, Seats) Values (?,?)");
            pst.setString(1,Flightname);
            pst.setString(2,Integer.toString(numseats));
            pst.execute();

}
catch(SQLException a)
{
    a.printStackTrace();
    System.exit(1);
}

    }
    
    public String getName()
    {
        return Flightname;
    }
    public  static int  getseat(String f)
    {
        try{
            
            pst = dbconnection.getConnection().prepareStatement("Select Seats from FLIGHT where Name = ?");
            pst.setString(1,f);
            rs = pst.executeQuery();  
            rs.next(); 
           numseats = rs.getInt(1)-1;
}
catch(SQLException a)
{
    System.out.println("getseat");
    a.printStackTrace();
    System.exit(1);
}
        
        return numseats;
    }
    
public  static void  Dropflight(String f)
    {
        try{
            
            pst = dbconnection.getConnection().prepareStatement("Delete from Flight where Name = ?");
            pst.setString(1,f);
            pst.executeUpdate();  
}
catch(SQLException a)
{
  
    a.printStackTrace();
    System.exit(1);
}
    }
    
    
    

 public static ArrayList <Flightdetails>  getflight() 
{
    ArrayList <Flightdetails> Flights = null;
    try
    {
    pst = dbconnection.getConnection().prepareStatement("SELECT * FROM Flight");
    rs = pst.executeQuery();
    Flights = new ArrayList<Flightdetails>();
             while (rs.next())
             {      
                 Flights.add(new Flightdetails(rs.getString("Name"),rs.getInt("Seats")));

             }
            
    
    }
              
catch (Exception e) {
                  e.printStackTrace();
             }
    return Flights;
}
 

 

}
        
    




