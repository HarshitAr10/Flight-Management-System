
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class Booking {
    

private String name;
static private String flight;
private String date;

private static PreparedStatement pst;
    private static ResultSet rs;
    int seatsBooked;
public Booking(String n1, String f1, String d1)
{
    this.name = n1;
    this.flight = f1;
    this.date = d1;
}




public  int getFlightSeats(String f, String d) throws ParseException{
SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date d1 = sdf1.parse(d);
java.sql.Date sqlStartDate = new java.sql.Date(d1.getTime());
    try{
    pst = dbconnection.getConnection().prepareStatement("select count(flight) from booking where flight = ? and date = ?"); 
    pst.setString(1, f); 
    pst.setDate(2, sqlStartDate); 
    rs = pst.executeQuery(); 
    rs.next(); 
    seatsBooked = rs.getInt(1);
}
    
    catch (Exception e) {
                  e.printStackTrace();
             }
    return seatsBooked; 

}

public void addBooking() throws ParseException
{
SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date d1 = sdf1.parse(date);
java.sql.Date sqlStartDate = new java.sql.Date(d1.getTime());
java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
 try{
   PreparedStatement pst = dbconnection.getConnection().prepareStatement("INSERT INTO Booking (Name,Flight,Date,Timeposi) Values (?,?,?,?)");
    pst.setString(1,name);
    pst.setString(2,flight);
    pst.setDate(3,sqlStartDate);
    pst.setTimestamp(4, currentTimestamp);
    pst.execute();

}
catch(SQLException a)
{
    System.out.println("addbook");
    a.printStackTrace();
    System.exit(1);
}
}

public static void addBookingfromw(String n, String f, String d) throws ParseException
{
SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date d1 = sdf1.parse(d);
java.sql.Date sqlStartDate = new java.sql.Date(d1.getTime());
java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
 try{
   PreparedStatement pst = dbconnection.getConnection().prepareStatement("INSERT INTO Booking (Name,Flight,Date,Timeposi) Values (?,?,?,?)");
    pst.setString(1,n);
    pst.setString(2,f);
    pst.setDate(3,sqlStartDate);
    pst.setTimestamp(4, currentTimestamp);
    pst.execute();

}
catch(SQLException a)
{
    a.printStackTrace();
    System.exit(1);
}
}






public static boolean getbookedname(String n, String d) throws ParseException
        
{
    boolean p = false;
    String name2;
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date d1 = sdf1.parse(d);
java.sql.Date sqlStartDate = new java.sql.Date(d1.getTime());
    try{
   PreparedStatement pst = dbconnection.getConnection().prepareStatement("SELECT * FROM Booking where  Name = ? and Date =?");
    pst.setString(1,n);
     pst.setDate(2,sqlStartDate);
    rs = pst.executeQuery();  
  
    if(rs.next()) 
    {
     name2 = rs.getString(1);
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


public static String getflightname(String n, String d) throws ParseException
        
{
  String name4=null;
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date d1 = sdf1.parse(d);
java.sql.Date sqlStartDate = new java.sql.Date(d1.getTime());
    try{
   PreparedStatement pst = dbconnection.getConnection().prepareStatement("SELECT Flight FROM Booking where  Name = ? and Date =?");
    pst.setString(1,n);
     pst.setDate(2,sqlStartDate);
      rs = pst.executeQuery(); 
    rs.next();
    name4 = rs.getString(1);
   
 
}
catch(SQLException a)
{
    
}
  return name4;
}




public static  void cancelBooking(String n, String d) throws ParseException
{
SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date d1 = sdf1.parse(d);
java.sql.Date sqlStartDate = new java.sql.Date(d1.getTime());
 try{
   PreparedStatement pst = dbconnection.getConnection().prepareStatement("Delete from Booking where Name = ? and Date = ?");
    pst.setString(1,n);
    pst.setDate(2,sqlStartDate);
    pst.execute();

}
catch(SQLException a)
{
    System.out.println("cancelbook");
    a.printStackTrace();
    System.exit(1);
}
 
 
}


public static  void cancelBooking2(String n,String f,  String d) throws ParseException
{
SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date d1 = sdf1.parse(d);
java.sql.Date sqlStartDate = new java.sql.Date(d1.getTime());
 try{
   PreparedStatement pst = dbconnection.getConnection().prepareStatement("Delete from Booking where Name = ? and Flight = ? and Date = ?");
    pst.setString(1,n);
    pst.setString(2,f);
    pst.setString(3,sqlStartDate.toString());
    pst.executeUpdate();

}
catch(SQLException a)
{
    System.out.println("cancelbook");
    a.printStackTrace();
    System.exit(1);
}
 
 
}





public String getfname()
{
    return flight;
}

public static ArrayList <Booking>  getstatusFlight( String f, String d) 
{
    ArrayList <Booking> Flightstatus = null;
    try
    {
    pst = dbconnection.getConnection().prepareStatement("SELECT * FROM Booking where Flight = ? and Date = ?");
    pst.setString(1,f);
    pst.setString(2,d);
    rs = pst.executeQuery();
    
    
    Flightstatus = new ArrayList<Booking>();
             while (rs.next())
             {      
                 Flightstatus.add(new Booking(rs.getString("Name"),rs.getString("Flight"),rs.getString("Date")));

             }
    }
              
catch (Exception e) {
    System.out.println("getstatusflight");
                  e.printStackTrace();
             }
    return Flightstatus;
}




public static ArrayList <Booking>  getBookeddetails() 
{
    ArrayList <Booking> booked = null;
    try
    {
    pst = dbconnection.getConnection().prepareStatement("SELECT * FROM Booking");
    rs = pst.executeQuery();
    
    
    booked = new ArrayList<Booking>();
             while (rs.next())
             {      
                 booked.add(new Booking(rs.getString("Name"),rs.getString("Flight"),rs.getString("Date")));

             }
            
    
    }
              
catch (Exception e) {
                  e.printStackTrace();
             }
    return booked;
}

public int bookingcount( String f) throws SQLException
{
    pst = dbconnection.getConnection().prepareStatement("SELECT COUNT(*) FROM booking where Flight = ?");
    pst.setString(1,f);
    rs = pst.executeQuery();
    rs.next();
    int rowCount = rs.getInt(1);
return rowCount;
}

public static ArrayList <Booking>  getBookingstatus(String n) 
{
    ArrayList <Booking> Flightstatus = null;
    try
    {
    pst = dbconnection.getConnection().prepareStatement("SELECT * FROM Booking where name = ?");
    pst.setString(1,n);
    rs = pst.executeQuery();
    
    
    Flightstatus = new ArrayList<Booking>();
             while (rs.next())
             {      
                 Flightstatus.add(new Booking(rs.getString("Name"),rs.getString("Flight"),rs.getString("Date")));

             }
    }
              
catch (Exception e) {
                  e.printStackTrace();
             }
    return Flightstatus;
}

 public static List<Booking> getBookingsFF(String flightName) throws SQLException {
        List<Booking> results = null;
        try (Connection connection = dbconnection.getConnection();
                PreparedStatement selectBookingsByFlight = connection.prepareStatement("SELECT Name, Date FROM JAVA.Booking WHERE Flight=?");) {
            selectBookingsByFlight.setString(1, flightName);
            try (ResultSet resultSet = selectBookingsByFlight.executeQuery()) {
                results = new ArrayList<>();
                while (resultSet.next()) {
                    results.add(new Booking(
                            resultSet.getString("Name"),
                            flightName, 
                            resultSet.getDate("Date").toString()));
                }
            }
        }
        return results;
    }


public static void removeBookings(String flightName) throws SQLException {
        try (Connection connection = dbconnection.getConnection();
                PreparedStatement removeBookingsForFlight = connection.prepareStatement("DELETE FROM JAVA.Booking WHERE Flight=?");) {
            removeBookingsForFlight.setString(1, flightName);
            removeBookingsForFlight.executeUpdate();
        }
    }
public static int getCountForFlightAndDay(String flightName, String date) throws SQLException, ParseException {
        int result;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
java.util.Date d1 = sdf1.parse(date);
java.sql.Date sqlStartDate = new java.sql.Date(d1.getTime());
        try (Connection connection = dbconnection.getConnection();
                PreparedStatement getCountByFlightAndDay = connection.prepareStatement("SELECT COUNT(Flight) FROM JAVA.Booking WHERE Flight=? AND Date=?");) {
            getCountByFlightAndDay.setString(1, flightName);
            getCountByFlightAndDay.setDate(2, sqlStartDate);
            try (ResultSet resultSet = getCountByFlightAndDay.executeQuery()) {
                resultSet.next();
                result = resultSet.getInt(1);
            }
        }
        return result;
    }
public String getname() {
        return name;
    }
public String getflight() {
        return flight;
    }
public String getdate() {
        return date;
    }

}
