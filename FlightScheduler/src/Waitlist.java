
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public class Waitlist {
 String  wPassenger;
String wflight;
String wdate;
 private static PreparedStatement pst;
    private static ResultSet rs;
    int count;
public Waitlist(String p, String f, String d)
{
    this.wPassenger = p;
    this.wflight = f;
    this.wdate = d;
    
}
public String getwFlight()
{
    return wflight;
}
public String getCustomerName()
{
    return wPassenger ;
}
public String getDay()
{
    return wdate;
}
 
public void addWaitlist() throws ParseException, SQLException
{

            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

            pst = dbconnection.getConnection().prepareStatement("insert into waitlistcheck (name, flight, date, timeStamp) values (?, ?, ?,?)");
            pst.setString(1, getCustomerName());
            pst.setString(2, getwFlight());
            pst.setString(3, getDay());
            pst.setTimestamp(4, currentTimestamp);
            count = pst.executeUpdate();
            
 
}
public static ArrayList <Waitlist> getWaitingList(String d) {
        ArrayList <Waitlist> Waitliststatus = null;
        

        try {
            pst = dbconnection.getConnection().prepareStatement("SELECT * FROM WAITLISTCHECK WHERE DATE = ? ORDER BY TIMESTAMP ASC");
            pst.setString(1,d);
            rs = pst.executeQuery();
          Waitliststatus = new ArrayList<Waitlist>();

            while (rs.next()) {
                
                Waitliststatus.add(new Waitlist(rs.getString("Name"),rs.getString("Flight"),rs.getString("Date")));
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Waitliststatus;
    }

public static  void cancelWaitlist(String n, String d) throws ParseException
{
SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date d1 = sdf1.parse(d);
java.sql.Date sqlStartDate = new java.sql.Date(d1.getTime());
 try{
   PreparedStatement pst = dbconnection.getConnection().prepareStatement("Delete from Waitlistcheck where Name = ? and Date = ?");
    pst.setString(1,n);
    pst.setDate(2,sqlStartDate);
    pst.execute();

}
catch(SQLException a)
{
    System.out.println("cancelWaitlist");
    a.printStackTrace();
    System.exit(1);
}
 
 
}






public static  boolean getwaitlistname(String n, String d) throws ParseException
        
{
    String name = null;
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date d1 = sdf1.parse(d);
java.sql.Date sqlStartDate = new java.sql.Date(d1.getTime());
    boolean p = false;
    try{
   PreparedStatement pst = dbconnection.getConnection().prepareStatement("SELECT * FROM Waitlistcheck where  Name = ? and Date =?");
    pst.setString(1,n);
    pst.setDate(2,sqlStartDate);
    rs = pst.executeQuery();  
       if(rs.next()) 
    {
     name= rs.getString(1);
     pst.execute();  
     p= true;
        
    }
    else
    {
       p= false;
     }
}
catch(SQLException a)
{
   
}

       

       return p;
    
}
public static  boolean waitlistcheck() throws ParseException
        
{
    boolean p = false;
    try{
   PreparedStatement pst = dbconnection.getConnection().prepareStatement("SELECT * FROM Waitlistcheck");
    rs = pst.executeQuery();  
    if(rs.next()) 
    {
    
     p= true;
        
    }
    else
    {
       p= false;
     }
}
catch(SQLException a)
{
    
}

       return p;
    
}








public static  String getwaitlisted(String f, String d) throws ParseException
        
{
    String name2 = null;
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date d1 = sdf1.parse(d);
java.sql.Date sqlStartDate = new java.sql.Date(d1.getTime());
    
    try{
   PreparedStatement pst = dbconnection.getConnection().prepareStatement("SELECT * FROM Waitlistcheck where  Flight = ? and Date =? ORDER BY TIMESTAMP ASC");
    pst.setString(1,f);
    pst.setDate(2,sqlStartDate);
    rs = pst.executeQuery();  
    rs.next();  
    name2= rs.getString(1);
    
   
}
catch(SQLException a)
{
    
}

       

       return name2;
    
}

public static ArrayList <Waitlist> getWaitingstatus(String d) {
        ArrayList <Waitlist> Waitliststatus = null;
        

        try {
            pst = dbconnection.getConnection().prepareStatement("SELECT * FROM WAITLISTCHECK WHERE Name = ? ORDER BY TIMESTAMP ASC");
            pst.setString(1,d);
            rs = pst.executeQuery();
          Waitliststatus = new ArrayList<Waitlist>();

            while (rs.next()) {
                
                Waitliststatus.add(new Waitlist(rs.getString("Name"),rs.getString("Flight"),rs.getString("Date")));
                 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Waitliststatus;
    }



public static List<Waitlist> getWaitlistFF(String flight) throws SQLException {
        List<Waitlist> results = null;
        try (Connection connection = dbconnection.getConnection();
                PreparedStatement selectWaitlistForFlight = connection.prepareStatement("SELECT * FROM WAITLISTCHECK WHERE Flight= ?");)
        {
            selectWaitlistForFlight.setString(1, flight);
            try (ResultSet resultSet = selectWaitlistForFlight.executeQuery()) {
                results = new ArrayList<Waitlist>();
                while (resultSet.next()) {
                    results.add(new Waitlist(rs.getString("Name"),rs.getString("Flight"),rs.getString("Date")));
                }
            }
        }
        
        return results;
    }

 public static void removeWaitlist(String flightName) throws SQLException {
        try{
   PreparedStatement pst = dbconnection.getConnection().prepareStatement("Delete from Waitlistcheck where Flight= ?");
    pst.setString(1,flightName);
   
    pst.execute();

}
catch(SQLException a)
{
    a.printStackTrace();
    System.exit(1);
}
    
}
}
