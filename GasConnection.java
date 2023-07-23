package Customers;

import java.text.SimpleDateFormat;
import java.util.*;
public class GasConnection extends Customer {
    public int numberOfCylinders;
    String date;
    static int connectionNumber=100;
    {
        connectionNumber+=1;
    }
    public Date lastdate=null;
    SimpleDateFormat dateFormat=new SimpleDateFormat( "dd/MM/yyyy");

    public GasConnection(String name, String street, String area, String pincode, String mobile, int numberOfCylinders) {
        super(name, street, area, pincode, mobile);
        this.numberOfCylinders = numberOfCylinders;
    }
    public void getLastDate(){
        System.out.println("enter the last date: ");
        date=new Scanner(System.in).nextLine();
        try{
            lastdate=dateFormat.parse(date);}
        catch(Exception e){
            System.out.println("error in getLastDate: "+e);
        }
    }
}
