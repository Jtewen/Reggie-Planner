package com.it326;

import java.io.*;
import java.util.*;

public class DatabaseHandler {
    
    static ArrayList<Account> accountList = new ArrayList<Account>();
    static ArrayList<Course> courseList = new ArrayList<Course>();
    static ArrayList<String> majorNames = new ArrayList<String>(); 
    static ArrayList<String> minorNames = new ArrayList<String>(); 
    static Account currentAccount;

    public static void loadAccounts() throws IOException, ClassNotFoundException{
        FileInputStream fi;
        fi = new FileInputStream(new File("src/main/java/com/it326/Data/Accounts.dat"));
        ObjectInputStream oi = new ObjectInputStream(fi);
        accountList = (ArrayList<Account>) oi.readObject();
        oi.close();
		fi.close();
        
    }

    public static void saveAccount(Account a) throws IOException{
        if(accountList.contains(a)){
            accountList.remove(a);
        }
        accountList.add(a);

        FileOutputStream f = new FileOutputStream(new File("src/main/java/com/it326/Data/Accounts.dat"));
		ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(accountList);
        f.close();
        o.close();
    }

    public static void saveAccount() throws IOException{
        FileOutputStream f = new FileOutputStream(new File("src/main/java/com/it326/Data/Accounts.dat"));
		ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(accountList);
        f.close();
        o.close();
    }

    public static Account verifyAccount(String usr, String pwd){
        for(Account a : accountList){
            if (usr.equals(a.getUsername()) && pwd.equals(a.getPassword())) {
                return a;
            }
        }
        return null;
    }

    public static Account registerAccount(String usr, String pwd) throws IOException{
        if (usr != "" && pwd != "" ) {
            for(Account a : accountList){
                if(usr.equals(a.getUsername()))
                    return null;
            }
            Account temp = new Account(usr, pwd);
            saveAccount(temp);
            return temp;
        }
        return null;
    }
}
