package gasBooking;

import Customers.*;

import java.sql.Time;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Booking extends GasConnection{
    public double otp=5678,amount=825.0,refund=0;
    public String dt, delDate,Status,DelMobileNo="7684569784",status;
    public Date dt_1,dt_2;

    public Booking(String name, String street, String area, String pincode, String mobile, int numberOfCylinders) {
        super(name, street, area, pincode, mobile, numberOfCylinders);
    }
    public void getDates() {
        System.out.println("enter booking date: ");
        dt = new Scanner(System.in).nextLine();
        dt_1 = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            dt_1 = dateFormat.parse(dt);
        } catch (Exception e) {
            System.out.println("The error in getDates function: " + e);
        }

        //delivery details
        System.out.println("Enter delivery date: ");
        delDate = new Scanner(System.in).nextLine();
        try {
            dt_2 = dateFormat.parse(delDate);
        } catch (Exception e) {
            System.out.println("error parsing 2nd date");
        }

        //find the diff b/w 2 dates
        try {
            long difference = dt_2.getTime() - dt_1.getTime();

            //diff in days
            long newDifference = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
            if (newDifference > 7) {
                status = "P"; //pending status
            }
        } catch (Exception e) {
            System.out.println("error while finding difference " + e);
        }
    }
         public void validate() {
            //get the diff b/w 2 dates
        long elapsedms=dt_1.getTime()-lastdate.getTime();
        long diff= TimeUnit.DAYS.convert(elapsedms, TimeUnit.MILLISECONDS);

            System.out.println("diff b/w 2 dates is: "+diff);

            if(numberOfCylinders==1){
                //for single cylinder
                if(diff<30){
                    System.out.println("booking cannot be done");
                    //numberOfCylinders=0
                    status = "C"; //status cancelled
                }else{
                    System.out.println("status:booked");
                    status = "B"; //status booked
                    lastdate=dt_1; //current booking will become last booking
                }
            }
            else if(numberOfCylinders==2){
                if(diff<50){
                    System.out.println("booking cannot be done");
                    //numberOfCylinders=0
                    status = "C"; //status cancelled
                }else{
                    System.out.println("status:booked");
                    status = "B"; //status booked
                    lastdate=dt_1; //current booking will become last booking
                }
            }
        }
    }

