
/*****************************************************************
    CS4001301 Programming Languages                   
    
    Programming Assignment #1
    
    Java programming using subtype, subclass, and exception handling
    
    To compile: %> javac Application.java
    
    To execute: %> java Application

******************************************************************/

import java.util.*;
import java.text.SimpleDateFormat;
class BankingException extends Exception {
    BankingException () { super(); }
    BankingException (String s) { super(s); }
} 

interface BasicAccount {
    String name();
    double balance();	
}

interface WithdrawableAccount extends BasicAccount {
    double withdraw(double amount) throws BankingException;	
}

interface DepositableAccount extends BasicAccount {
    double deposit(double amount) throws BankingException;	
}

interface InterestableAccount extends BasicAccount {
    double computeInterest() throws BankingException;	
}

interface FullFunctionalAccount extends WithdrawableAccount,
                                        DepositableAccount,
                                        InterestableAccount {
}

public abstract class Account {
	
    // protected variables to store commom attributes for every bank accounts	
    protected String accountName;
    protected double accountBalance;
    protected double accountInterestRate;
    protected Date openDate;
    protected Date lastInterestDate;
    
    // public methods for every bank accounts
    public String name() {
    	return(accountName);	
    }	
    
    public double balance() {
        return(accountBalance);
    }

    abstract double deposit(double amount, Date depositDate) throws BankingException;
    
    public double deposit(double amount) throws BankingException {
        Date depositDate = new Date();
        return(deposit(amount, depositDate));
    }
    
    
    abstract double withdraw(double amount, Date withdrawDate) throws BankingException;
    
    public double withdraw(double amount) throws BankingException {
        Date withdrawDate = new Date();
        return(withdraw(amount, withdrawDate));
    }
    
    abstract double computeInterest(Date interestDate) throws BankingException;
    
    public double computeInterest() throws BankingException {
        Date interestDate = new Date();
        return(computeInterest(interestDate));
    }
}

/*
 *  Derived class: CheckingAccount
 *
 *  Description:
 *      Interest is computed daily; there's no fee for
 *      withdraw; there is a minimum balance of $1000.
 */
                          
class CheckingAccount extends Account implements FullFunctionalAccount {

    CheckingAccount(String s, double firstDeposit, Date openDateIn, double interestRate) {
        accountName = s;
        accountBalance = firstDeposit;
        accountInterestRate = interestRate;
        openDate = openDateIn;
        lastInterestDate = openDate;	

        System.out.println( "[log] CheckingAccount <" + accountName + "> created! ...\n" +
                            "          balance : " + accountBalance + "\n" +
                            "          interest rate : " + accountInterestRate + "%\n" +
                            "          when : " + openDate.toString());
    }
    
    
    public double deposit(double amount, Date depositDate) throws BankingException {
        accountBalance += amount;   

        System.out.println ("[log] CheckingAccount <" + accountName + "> now depositing\n" +
                            "          amount : $" + amount + "\n" +
                            "          when : " + depositDate.toString());
        return(accountBalance);                                        
    }

    public double withdraw(double amount, Date withdrawDate) throws BankingException {
        if ((accountBalance  - amount) < 1000) {
            throw new BankingException ("Underfraft from checking account name:" +
                                         accountName);
        } else {
            accountBalance -= amount;

            System.out.println ("[log] CheckingAccount <" + accountName + "> now withdrawing\n" +
                                "          amount : $" + amount + "\n" +
                                "          when : " + withdrawDate.toString());
            return(accountBalance); 	
        }                                        	
    }
    
    public double computeInterest (Date interestDate) throws BankingException {
        if (interestDate.before(lastInterestDate)) {
            throw new BankingException ("Invalid date to compute interest for account name" +
                                        accountName);                            	
        }
        
        int numberOfDays = (int) ((interestDate.getTime() 
                                   - lastInterestDate.getTime())
                                   / 86400000.0);

        double interestEarned = (double) numberOfDays / 365.0 * accountInterestRate * accountBalance;

        System.out.println( "[log] CheckingAccount <" + accountName + "> computing interest ...\n" +
                            "          lasttime computing interest : " + lastInterestDate.toString() + "\n" +
                            "          Number of days since last interest : " + numberOfDays + "\n" +
                            "          Interest earned : " + interestEarned + "\n" +
                            "          when : " + interestDate.toString());

        lastInterestDate = interestDate;
        accountBalance += interestEarned;
        return(accountBalance);                            
    }  	
}           



/*
 *  Derived class: SavingAccount
 *
 *  Description:
 *      Interest is computed daily; there's no fee for
 *      withdraw; there is a minimum balance of $1000.
 */
                          
class SavingAccount extends Account implements FullFunctionalAccount {

    protected String[] withdrawDateLog;


    SavingAccount(String s, double firstDeposit, Date openDateIn, double interestRate) {
        accountName = s;
        accountBalance = firstDeposit;
        accountInterestRate = interestRate;
        openDate = openDateIn;
        lastInterestDate = openDate;    
        withdrawDateLog = new String[3];
        System.out.println( "[log] SavingAccount <" + accountName + "> created! ...\n" +
                            "          balance : " + accountBalance + "\n" +
                            "          interest rate : " + accountInterestRate + "%\n" +
                            "          when : " + openDate.toString());
    }
    
    // private static String convertStringToDate(Date indate, String dateString){
    //     String dateString = null;
    //     SimpleDateFormat sdfr = new SimpleDateFormat(dateString);
    //     try{
    //         dateString = sdfr.format(indate);
    //     }catch (Exception ex ){
    //         System.out.println(ex);
    //     }
    //     return dateString;
    // }

    public double deposit(double amount, Date depositDate) throws BankingException {
        accountBalance += amount;   

        System.out.println ("[log] SavingAccount <" + accountName + "> now depositing\n" +
                            "          amount : $" + amount + "\n" +
                            "          when : " + depositDate.toString());
        return(accountBalance);                                        
    }

    public double withdraw(double amount, Date withdrawDate) throws BankingException {
        // checking if pass 3 withdraw Date Log are in same month.

        int withdrawFee = 1;


        SimpleDateFormat sdfr = new SimpleDateFormat("yyyyMM");

        for (int i=0; i<3; i++) {
            if (withdrawDateLog[i]==null) {
                withdrawFee = 0;
            }else{
                if(!sdfr.format(withdrawDate).equals(withdrawDateLog[i])) {
                    withdrawFee = 0;
                }
            }
        }



        if ((accountBalance  - amount - withdrawFee) < 0) {
            throw new BankingException ("Underfraft from checking account name:" +
                                         accountName);
        } else {
            accountBalance -= amount;
            accountBalance -= withdrawFee;
            
            withdrawDateLog[2] = withdrawDateLog[1] ;
            withdrawDateLog[1] = withdrawDateLog[0] ;
            withdrawDateLog[0] = sdfr.format(withdrawDate) ;

            System.out.println ("[log] SavingAccount <" + accountName + "> now withdrawing\n" +
                                "          amount : $" + amount + "\n" +
                                "          fee : $" + withdrawFee + "\n" +
                                "          when : " + withdrawDate.toString());
            return(accountBalance);     
        }                                           
    }
    
    public double computeInterest (Date interestDate) throws BankingException {
        if (interestDate.before(lastInterestDate)) {
            throw new BankingException ("Invalid date to compute interest for account name" +
                                        accountName);                               
        }
        SimpleDateFormat sdfryyyy = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfrMM = new SimpleDateFormat("MM");
        int numberOfMonth = (Integer.valueOf(sdfryyyy.format(interestDate))  - Integer.valueOf(sdfryyyy.format(lastInterestDate))) * 12;
        numberOfMonth += Integer.valueOf(sdfrMM.format(interestDate))  - Integer.valueOf(sdfrMM.format(lastInterestDate));

        double interestEarned = (double) numberOfMonth / 12 * accountInterestRate * accountBalance;

        System.out.println( "[log] SavingAccount <" + accountName + "> computing interest ...\n" +
                            "          lasttime computing interest : " + Integer.valueOf(sdfryyyy.format(lastInterestDate)) + "/" + Integer.valueOf(sdfrMM.format(lastInterestDate)) + "\n" +
                            "          Number of Month since last interest : " + numberOfMonth + "\n" +
                            "          Interest earned : " + interestEarned + "\n" +
                            "          when : " + interestDate.toString());

        lastInterestDate = interestDate;
        accountBalance += interestEarned;
        return(accountBalance);                            
    }   
}           




/*
 *  Derived class: CDAccount
 *
 *  Description:
 *      Interest is computed daily; there's no fee for
 *      withdraw; there is a minimum balance of $1000.
 */
                          
class CDAccount extends Account implements FullFunctionalAccount {
    protected int cdMonCount;

    CDAccount(String s, double firstDeposit, Date openDateIn, double interestRate, int cdMonCountin) {
        accountName = s;
        accountBalance = firstDeposit;
        accountInterestRate = interestRate;
        openDate = openDateIn;
        lastInterestDate = openDate;   
        cdMonCount = cdMonCountin; 

        System.out.println( "[log] CDAccount <" + accountName + "> created! ...\n" +
                            "          balance : " + accountBalance + "\n" +
                            "          interest rate : " + accountInterestRate + "%\n" +
                            "          CD Months : " + cdMonCount + "\n" +
                            "          when : " + openDate.toString());
    }
    
    
    public double deposit(double amount, Date depositDate) throws BankingException {

        SimpleDateFormat sdfryyyy = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfrMM = new SimpleDateFormat("MM");

        int numberOfMonth = (Integer.valueOf(sdfryyyy.format(depositDate))  - Integer.valueOf(sdfryyyy.format(openDate))) * 12;
        numberOfMonth += Integer.valueOf(sdfrMM.format(depositDate))  - Integer.valueOf(sdfrMM.format(openDate));

        if (numberOfMonth<cdMonCount) {
            throw new BankingException ("not yet , can't deposit:" +  accountName);            
        }
        accountBalance += amount;   

        System.out.println ("[log] CDAccount <" + accountName + "> now depositing\n" +
                            "          amount : $" + amount + "\n" +
                            "          when : " + depositDate.toString());
        return(accountBalance);                                        
    }

    public double withdraw(double amount, Date withdrawDate) throws BankingException {
        int withdrawFee = 250;

        SimpleDateFormat sdfryyyy = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfrMM = new SimpleDateFormat("MM");

        int numberOfMonth = (Integer.valueOf(sdfryyyy.format(withdrawDate))  - Integer.valueOf(sdfryyyy.format(openDate))) * 12;
        numberOfMonth += Integer.valueOf(sdfrMM.format(withdrawDate))  - Integer.valueOf(sdfrMM.format(openDate));

        if (numberOfMonth>=cdMonCount) {
            withdrawFee = 0;
        }
        if ((accountBalance  - amount - withdrawFee) < 0) {
            throw new BankingException ("Underfraft from checking account name:" +
                                         accountName);
        } else {
            accountBalance -= amount;
            accountBalance -= withdrawFee;

            System.out.println ("[log] CDAccount <" + accountName + "> now withdrawing\n" +
                                "          amount : $" + amount + "\n" +
                                "          fee : $" + withdrawFee + "\n" +
                                "          when : " + withdrawDate.toString());
            return(accountBalance);     
        }                                           
    }
    
    public double computeInterest (Date interestDate) throws BankingException {
        if (interestDate.before(lastInterestDate)) {
            throw new BankingException ("Invalid date to compute interest for account name" +
                                        accountName);                               
        }
        SimpleDateFormat sdfryyyy = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfrMM = new SimpleDateFormat("MM");
        int numberOfMonth = (Integer.valueOf(sdfryyyy.format(interestDate))  - Integer.valueOf(sdfryyyy.format(lastInterestDate))) * 12;
        numberOfMonth += Integer.valueOf(sdfrMM.format(interestDate))  - Integer.valueOf(sdfrMM.format(lastInterestDate));

        double interestEarned = (double) numberOfMonth / 12 * accountInterestRate * accountBalance;

        System.out.println( "[log] CDAccount <" + accountName + "> computing interest ...\n" +
                            "          lasttime computing interest : " + Integer.valueOf(sdfryyyy.format(lastInterestDate)) + "/" + Integer.valueOf(sdfrMM.format(lastInterestDate)) + "\n" +
                            "          Number of Month since last interest : " + numberOfMonth + "\n" +
                            "          Interest earned : " + interestEarned + "\n" +
                            "          when : " + interestDate.toString());

        lastInterestDate = interestDate;
        accountBalance += interestEarned;
        return(accountBalance);                            
    }   
}           





/*
 *  Derived class: LoanAccount
 *
 *  Description:
 *      Interest is computed daily; there's no fee for
 *      withdraw; there is a minimum balance of $1000.
 */
                          
class LoanAccount extends Account implements FullFunctionalAccount {

    LoanAccount(String s, double firstDeposit, Date openDateIn, double interestRate) {
        accountName = s;
        accountBalance = firstDeposit;
        accountInterestRate = interestRate;
        openDate = openDateIn;
        lastInterestDate = openDate;    

        System.out.println( "[log] LoanAccount <" + accountName + "> created! ...\n" +
                            "          balance : " + accountBalance + "\n" +
                            "          interest rate : " + accountInterestRate + "%\n" +
                            "          when : " + openDate.toString());
    }
    
    
    public double deposit(double amount, Date depositDate) throws BankingException {
        accountBalance += amount;   

        System.out.println ("[log] LoanAccount <" + accountName + "> now depositing\n" +
                            "          amount : $" + amount + "\n" +
                            "          when : " + depositDate.toString());
        return(accountBalance);                                        
    }

    public double withdraw(double amount, Date withdrawDate) throws BankingException {
        if ((accountBalance  - amount) < 1000) {
            throw new BankingException ("Underfraft from checking account name:" +
                                         accountName);
        } else {
            accountBalance -= amount;

            System.out.println ("[log] LoanAccount <" + accountName + "> now withdrawing\n" +
                                "          amount : $" + amount + "\n" +
                                "          when : " + withdrawDate.toString());
            return(accountBalance);     
        }                                           
    }
    
    public double computeInterest (Date interestDate) throws BankingException {
        if (interestDate.before(lastInterestDate)) {
            throw new BankingException ("Invalid date to compute interest for account name" +
                                        accountName);                               
        }
        
        int numberOfDays = (int) ((interestDate.getTime() 
                                   - lastInterestDate.getTime())
                                   / 86400000.0);

        double interestEarned = (double) numberOfDays / 365.0 * accountInterestRate * accountBalance;

        System.out.println( "[log] LoanAccount <" + accountName + "> computing interest ...\n" +
                            "          lasttime computing interest : " + lastInterestDate.toString() + "\n" +
                            "          Number of days since last interest : " + numberOfDays + "\n" +
                            "          Interest earned : " + interestEarned + "\n" +
                            "          when : " + interestDate.toString());

        lastInterestDate = interestDate;
        accountBalance += interestEarned;
        return(accountBalance);                            
    }   
}           