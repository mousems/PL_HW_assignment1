
/*****************************************************************
    CS4001301 Programming Languages                   
    
    Programming Assignment #1
    
    Java programming using subtype, subclass, and exception handling
    
    To compile: %> javac Application.java
    
    To execute: %> java Application

******************************************************************/

import java.util.*;



public class Application {
    
    public static void main( String args []) {
        Account a;
        Date d;
        double ret;
        
        /*
        //test for CheckingAccount
        d = new Date(2015-1900,1-1,1);
        a = new CheckingAccount("John Smith", 1500.0, d, 0.12);
        
        
        try {
            d = new Date(2015-1900,1-1,2);
            a.withdraw(100.00, d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }

        
        try {
            d = new Date(2015-1900,1-1,3);
            ret = a.deposit(200.00, d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }

        try {
            d = new Date(2015-1900,1-1,11);
            ret = a.computeInterest(d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }


        try {
            d = new Date(2015-1900,1-1,20);
            ret = a.computeInterest(d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }

        try {
            d = new Date(2015-1900,1-1,22);
            ret = a.computeInterest(d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }

        d = new Date(2015-1900,1-1,23);
        a = new CheckingAccount("John Smith", 1500.0, d, 0.12);
        
        try {
            d = new Date(2015-1900,1-1,24);
            ret = a.withdraw(600.00, d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }
        //test for CheckingAccount
        */ 
    
        /*
        //test for SavingACcount
        d = new Date(2015-1900,1-1,1);
        a = new SavingAccount("John Smith", 1500.0, d, 0.12);
        
        
        try {
            d = new Date(2015-1900,1-1,2);
            a.withdraw(100.00, d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }

        
        try {
            d = new Date(2015-1900,1-1,3);
            a.withdraw(100.00, d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }

        try {
            d = new Date(2015-1900,1-1,4);
            a.withdraw(100.00, d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }


        try {
            d = new Date(2015-1900,1-1,22);
            ret = a.computeInterest(d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }

        try {
            d = new Date(2015-1900,1-1,5);
            a.withdraw(100.00, d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }

        try {
            d = new Date(2015-1900,1-1,22);
            ret = a.computeInterest(d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }

        try {
            d = new Date(2015-1900,2-1,5);
            a.withdraw(100.00, d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }


        try {
            d = new Date(2015-1900,2-1,8);
            a.withdraw(100.00, d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }


        try {
            d = new Date(2015-1900,2-1,9);
            a.withdraw(100.00, d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }


        try {
            d = new Date(2015-1900,2-1,10);
            a.withdraw(100.00, d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }

        try {
            d = new Date(2015-1900,2-1,22);
            ret = a.computeInterest(d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }


        try {
            d = new Date(2015-1900,5-1,22);
            ret = a.computeInterest(d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }
        //test for SavingACcount

        */

        d = new Date(2015-1900,1-1,1);
        a = new CDAccount("John Smith", 1500.0, d, 0.12 , 5);
        
        try {
            d = new Date(2015-1900,3-1,22);
            ret = a.deposit(100 ,d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }
        
        try {
            d = new Date(2015-1900,4-1,22);
            ret = a.deposit(100 ,d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }


        try {
            d = new Date(2015-1900,4-1,23);
            ret = a.withdraw(100 ,d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }


        try {
            d = new Date(2015-1900,4-1,23);
            ret = a.computeInterest(d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }
        
        try {
            d = new Date(2015-1900,5-1,22);
            ret = a.deposit(100 ,d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }
        
        try {
            d = new Date(2015-1900,6-1,22);
            ret = a.deposit(100 ,d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }


        try {
            d = new Date(2015-1900,7-1,22);
            ret = a.deposit(100 ,d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }



        try {
            d = new Date(2015-1900,4-1,23);
            ret = a.computeInterest(d);
            System.out.println ("Account <" + a.name() + "> now has $" + a.balance() + " balance\n");
        } catch (Exception e) {
           stdExceptionPrinting(e, a.balance());    
        }
        /* put your own tests here ....... */
        /* if your implementaion is correct, you can do the following with polymorphic array accountList
            public Account[] accountList;
            
            accountList = new Account[4];
            
            // buid 4 different accounts in the same array
            accountList[0] = new CheckingAccount("John Smith", 1500.0);
            accountList[1] = new SavingAccount("William Hurt", 1200.0);
            accountList[2] = new CDAccount("Woody Allison", 1000.0);
            accountList[3] = new LoanAccount("Judi Foster", -1500.0);
            
            // compute interest for all accounts
            for (int count = 0; count < accountList.length; count++) {
                double newBalance = accountList[count].computeInterest();
                System.out.println ("Account <" + a.name() + "> now has $" + newBalance + " balance\n");
            }
        */
    }

    static void stdExceptionPrinting(Exception e, double balance) {
        System.out.println("EXCEPTION: Banking system throws a " + e.getClass() +
                           " with message: \n\t" +
                           "MESSAGE: " + e.getMessage());
        System.out.println("\tAccount balance remains $" + balance + "\n");
    }
}          