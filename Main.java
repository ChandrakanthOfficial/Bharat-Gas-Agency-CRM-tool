import gasBooking.Delivery;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static gasSupplier.gasAgency.*;

public class Main {
    static int count;
    static int bcount = 0, ccount = 0, dcount = 0, pcount = 0;
    static String dpname;

    public static void cylinderCount(Delivery[] obj) {
        String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};
        for (Delivery delivery : obj) {
            count = 0;
            System.out.println("In the month of " + (months[delivery.dt_2.getMonth()]) + " : ");
            System.out.println(" * in " + delivery.area);
            if (delivery.status.equals("D")) {
                count += delivery.numberOfCylinders;
            }
            System.out.println("\n");
        }
    }

    public static void checkLateDel(Delivery[] obj) {
        String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};
        int[] month = new int[12];

        for (Delivery delivery : obj) {
            if (delivery.status.equals("D") && delivery.amount == 783.75) {
                month[delivery.dt_2.getMonth()] += 1;
            }
        }


        System.out.println("--------------Late Delivery--------------");
        ;
        for (int i = 0; i < 12; i++) {
            if (month[i] != 0) {
                System.out.println(" * in " + months[i] + " There are " + month[i]);
            }
        }

        System.out.println("\n");

    }

    public static void numOfSingleCylinders(Delivery[] obj) {
        System.out.println("--------------Single Cylinder Holders--------------");
        for (int i = 0; i < obj.length; i++) {
            if (obj[i].numberOfCylinders == 1) {
                System.out.println("* Customer Name: " + obj[i].name);
                System.out.println("* Mobile No: " + obj[i].mobile);
                System.out.println("* Gas Connection No: " + (i + 101));
            }
        }
        System.out.println("\n");
    }

    public static void DeliveryDetails(Delivery[] obj) {
        System.out.println("--------------DeliveryDetails--------------");
        System.out.println("Enter name of delivery person: ");
        dpname = new Scanner(System.in).next();
        for (Delivery delivery : obj) {
            if (delivery.status.equals("D") && delivery.delPersonName.equals(dpname)) {
                System.out.println("* customer name: " + delivery.name);
                System.out.println(" - " + delivery.Street + ", " + delivery.area + ", " + delivery.pincode);
            }
        }
        System.out.println("\n");
    }
    public static void printReport(Delivery[] obj) {
        System.out.println("--------------DeliveryReport--------------");
        for(int i=0;i<obj.length;i++){
            if(obj[i].status.equals("D")){
                dcount++;
            }
            else if(obj[i].status.equals("B")){
                bcount++;
            }
            else if(obj[i].status.equals("P")){
                pcount++;
            }
            else
                System.out.println("Status Invalid");

            }
        System.out.println("* Booked");
        System.out.println(" - "+bcount+" booked");
        System.out.println("* Delivered");
        System.out.println(" - "+dcount+" delivered");
        System.out.println("* Cancelled");
        System.out.println(" - "+ccount+" cancelled");
        System.out.println("* Pending");
        System.out.println(" - "+pcount+" pending");
        System.out.println("\n");
        }

    public static void printInvoice(Delivery[] obj) {
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        String invoiceDate=sdf.format(d);
        for(int i=0;i<obj.length;i++){
            if(obj[i].status.equals("D")){
                System.out.println("-----------------------------------------------------------------");
                System.out.println("                            Invoice                              ");
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Gas Agency Code: "+agencyCode+"\t\t\t"+"Date of invoice: "+invoiceDate);
                System.out.println("Gas Agency Name: "+agencyName+"\t\t"+"Agency Phone No: "+phNumber);
                System.out.println("Gas Connection No: "+(i+101)+"\t\t\t"+"Customer Name: "+obj[i].name);
                System.out.println("Booking Date : "+sdf.format(obj[i].dt_1)+"\t\t"+"Customer mobile no: "+obj[i].mobile);
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Amount: "+obj[i].amount);
                System.out.println("Amount: "+obj[i].refund);
                System.out.println("Total Amount: "+(obj[i].amount - obj[i].refund));
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Delivery Parson name: "+obj[i].delPersonName+"\t\t"+"Delivery person mobile no: "+obj[i].DelMobileNo);
                System.out.println("Delivery Date: "+sdf.format(obj[i].dt_2));
                System.out.println("-----------------------------------------------------------------");
                System.out.println("\n\n");

            }
        }
    }

    public static void main(String[] args){
        System.out.println("*****************************************************************");
        System.out.println("                     Bharath Gas Agency                     ");
        System.out.println("*****************************************************************");
        Delivery[] deliveryObject = new Delivery[1];
        deliveryObject[0]=new Delivery("mouli","4th avenue", "marripalem", "530016","8476534849",1);

        for(Delivery delivery:deliveryObject){
            delivery.DelPersonDetails();
            delivery.getLastDate();
            delivery.getDates();
            delivery.validate();
            delivery.amountCalc();
            delivery.verifyOtp();
        }
        System.out.println();
        cylinderCount(deliveryObject);
        checkLateDel(deliveryObject);
        numOfSingleCylinders(deliveryObject);
        DeliveryDetails(deliveryObject);
        printReport(deliveryObject);
        printInvoice(deliveryObject);

    }
}