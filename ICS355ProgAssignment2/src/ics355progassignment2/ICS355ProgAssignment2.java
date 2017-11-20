/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ics355progassignment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Mike Serai
 * @assignment ICS 355: Progassignment 2
 * @date 11/17/17
 */
public class ICS355ProgAssignment2 {

    public static Integer SIZE;
    public static File file;
    public static Double cAmount;
    public static String cType;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Utilize the master account credentials for the database I created in 
            // netbeans named Accounts
            String host = "jdbc:derby://localhost:1527/Accounts";
            String userName = "serai";
            String userPass = "adminpass";
            // Make a connection to the database
            Connection con = DriverManager.getConnection(host, userName, userPass);
            Statement statement = con.createStatement();
            // Utilize SQL
            String SQL = "SELECT * FROM USERS";
            ResultSet result = statement.executeQuery(SQL);
            // Take the information in each column for each row by column header
            while (result.next()) {
                String usernameCollect = result.getString("USERNAME");
                String saltCollect = result.getString("SALT");
                String hashedpasswordsCollect = result.getString("HASHEDPASSWORDS");
                Boolean USDCollect = result.getBoolean("USD");
                Boolean EuroCollect = result.getBoolean("EURO");
                Boolean YenCollect = result.getBoolean("YEN");
                int BalanceCollect = result.getInt("BALANCE");
                // Take information, put it in a string and display it
                String info = usernameCollect + " " + saltCollect + " "
                        + hashedpasswordsCollect + " " + USDCollect + " "
                        + EuroCollect + " " + YenCollect + " " + BalanceCollect;
                System.out.println(info);
            }// while
        }// try
        catch (SQLException error) {
            System.out.println(error.getMessage());
        }// catch
        // Login or Create Process
        System.out.println("Welcome! Please enter (1) to Login"
                + " Or enter (2) to create an account ");
        Scanner scannerInitial = new Scanner(System.in);
        String userInitialChoice = scannerInitial.nextLine();
        System.out.println("You have entered " + userInitialChoice);
        // Login
        if (userInitialChoice.matches("1")) {
            System.out.println("You have chosen to login");
            // Get username
            System.out.println("Please enter your username");
            Scanner scannerUserName = new Scanner(System.in);
            String userNameScanner = scannerUserName.nextLine();
            System.out.println("You have entered: " + userNameScanner);
            String userNameScannerSearch = userNameScanner.toLowerCase();
            // Get password
            System.out.println("Please enter your password");
            Scanner scannerUserPass = new Scanner(System.in);
            String userPassScanner = scannerUserPass.nextLine();
            System.out.println("You have entered: " + userPassScanner);
            try {
            // Utilize the master account credentials for the database I created in 
            // netbeans named Accounts
            String host = "jdbc:derby://localhost:1527/Accounts";
            String userName = "serai";
            String userPass = "adminpass";
            // Make a connection to the database
            Connection con = DriverManager.getConnection(host, userName, userPass);
            Statement statement = con.createStatement();
            // Utilize SQL to identify
            String search = "SELECT * FROM USERS WHERE USERNAME=" + "'"+userNameScannerSearch+
                    "' AND HASHEDPASSWORDS=" + "'"+userPassScanner+"'";
            ResultSet result = statement.executeQuery(search);
            // Take the information in each column for each row by column header
            while (result.next()) {
                String usernameCollect = result.getString("USERNAME");
                String saltCollect = result.getString("SALT");
                String hashedpasswordsCollect = result.getString("HASHEDPASSWORDS");
                Boolean USDCollect = result.getBoolean("USD");
                Boolean EuroCollect = result.getBoolean("EURO");
                Boolean YenCollect = result.getBoolean("YEN");
                int BalanceCollect = result.getInt("BALANCE");
                // Take information, put it in a string and display it
                String info = usernameCollect + " " + saltCollect + " "
                        + hashedpasswordsCollect + " " + USDCollect + " "
                        + EuroCollect + " " + YenCollect + " " + BalanceCollect;
                System.out.println(info);
            }// while
        }// try
        catch (SQLException error) {
            System.out.println(error.getMessage());
        }// catch
        } else if (userInitialChoice.matches("2")) {
            System.out.println("You have chosen to create an account");
            // Create username
            System.out.println("Please enter a username.");
            Scanner scannerEnterID = new Scanner(System.in);
            String userEnterID = scannerEnterID.nextLine();
            System.out.println("You have chosen " + userEnterID + " as your username");
            // Create password
            System.out.println("Please enter a password.");
            Scanner scannerEnterPass = new Scanner(System.in);
            String userEnterPass = scannerEnterPass.nextLine();
            System.out.println("You have chosen " + userEnterPass + " as your password");
            SecureRandom random = new SecureRandom();
            double salt = Math.random();
            int hashedpassword = userEnterPass.hashCode();
            try{
            // Utilize the master account credentials for the database I created in 
            // netbeans named Accounts
            String host = "jdbc:derby://localhost:1527/Accounts";
            String userName = "serai";
            String userPass = "adminpass";
            // Make a connection to the database
            Connection con = DriverManager.getConnection(host, userName, userPass);
            Statement statement = con.createStatement();
            // Utilize SQL
            String SQL = "SELECT * FROM USERS";
            ResultSet result = statement.executeQuery(SQL);
            // Enter new information
            result.moveToInsertRow();
            result.updateString("USERNAME",userEnterID);
            result.updateDouble("SALT", salt);
            result.updateInt("HASHEDPASSWORDS", hashedpassword);
            result.updateBoolean("USD", true);
            result.updateBoolean("Euro", false);
            result.updateBoolean("Yen", false);
            // make those changes
            result.insertRow();
            // Take the information in each column for each row by column header
            while (result.next()) {
                String usernameCollect = result.getString("USERNAME");
                String saltCollect = result.getString("SALT");
                String hashedpasswordsCollect = result.getString("HASHEDPASSWORDS");
                Boolean USDCollect = result.getBoolean("USD");
                Boolean EuroCollect = result.getBoolean("EURO");
                Boolean YenCollect = result.getBoolean("YEN");
                int BalanceCollect = result.getInt("BALANCE");
                // Take information, put it in a string and display it
                String info = usernameCollect + " " + saltCollect + " "
                        + hashedpasswordsCollect + " " + USDCollect + " "
                        + EuroCollect + " " + YenCollect + " " + BalanceCollect;
                System.out.println(info);
            }// while
            statement.close();
            result.close();
        }// try
        catch (SQLException error) {
            System.out.println(error.getMessage());
        }// catch
        } else {
            System.out.println("You did not enter either 1 or 2");
            System.exit(1);
        }
        System.out.println("Welcome!Please Enter Your Name: ");
        Scanner scanner = new Scanner(System.in);
        String userID = scanner.nextLine();
        System.out.println("Your filename is " + userID
                + "\nYour Information will be stored in "
                + userID + ".txt");
        String user = userID + ".txt";
        File file = new File(user);
        try {
            if (file.createNewFile()) {
                System.out.println("File is created!");
            }//if (file.createNewFile())
            else {
                System.out.println("Welcome Back!");
                Currency[] currencyArray = readFromFile(file);
            }
        }//try
        catch (IOException e) {
            System.out.println("Sorry");
        }
        System.out.println("Would you like add/subtract from your account? Enter Yes or No: ");
        Scanner scannerYN = new Scanner(System.in);
        String answer = scannerYN.nextLine();
        System.out.println("You have entered: " + answer);
        String userYN = answer.toLowerCase();
        if (userYN.matches(".*\\byes\\b")) {
            System.out.println("How much would you like to add or subtract? Please include a - sign "
                    + "in front of the value(ie...-100 to subtract 100 USD from your account.");

            Scanner scannerAddNew = new Scanner(System.in);
            String answerToAdd2 = scannerAddNew.nextLine();
            String firstPart = answerToAdd2.substring(0, 1);
            String restOfAnswer = answerToAdd2.substring(1, answerToAdd2.length());
            //System.out.println("You have entered: " + answerToAdd2);
            //System.out.println("First character is: " + firstPart);
            //System.out.println("rest of answer" + restOfAnswer);
            Double newAmount = .0;
            try {
                newAmount = Double.parseDouble(answerToAdd2);
            } // Confirm the line stored is a double with try/catch
            catch (NumberFormatException exception) {
                System.out.println("Your input is not valid");
            }
            //System.out.println(newAmount);
            Double updatedAmount = .0;
            if (firstPart.matches("-")) {
                updatedAmount = 0.0 + newAmount;

            } else {
                updatedAmount = 0.0 + newAmount;
            }
            //System.out.println(updatedAmount);
            // Write to a file
            PrintWriter fileWriter = null;
            try {
                fileWriter = new PrintWriter(file);
            } catch (FileNotFoundException exception) {
                // Error message if the file is not found
                System.out.print("ERROR: File not found for \"");
                System.out.println(file + "\"");
            }
            if (fileWriter != null) {
                fileWriter.println("Amount Type");
                fileWriter.println(updatedAmount + " USD");
            }
            // Let the user know what is being written
            System.out.println("Wrote to file: " + file);
            // Close file to ensure it is written
            fileWriter.close();
        } else if (userYN.matches(".*\\bno\\b")) {
            System.out.println("Would you like your balance?");
            Scanner scannerYN1 = new Scanner(System.in);
            String answer2 = scannerYN1.nextLine();
            System.out.println("You have entered: " + answer2);
            String userYN1 = answer2.toLowerCase();
            if (userYN1.matches(".*\\byes\\b")) {
                System.out.println("Here it is:");
                try {
                    Currency[] currencyArray = readFromFile(file);
                    System.out.println("Would you like to see your account information in another currency? Enter yen or euro to see.");
                    Scanner scannerc = new Scanner(System.in);
                    String currencyC = scannerc.nextLine();
                    String userC = currencyC.toLowerCase();
                    if (userC.matches(".*\\byen\\b")) {
                        Double changeThis = currencyArray[0].getAmount();
                        Double nowYen = changeThis * 111.0;
                        System.out.println("You have " + nowYen + " yen");
                    } else if (userC.matches(".*\\beuro\\b")) {
                        Double changeThisE = currencyArray[0].getAmount();
                        Double nowEuro = changeThisE * 0.85;
                        System.out.println("You have " + nowEuro + " euros");
                    } else {
                        System.out.println("You entered a currency I cannot convert to");
                        System.exit(1);
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("You don't have a balance yet.");
                    // Write to a file
                    PrintWriter fileWriter = null;
                    try {
                        fileWriter = new PrintWriter(file);
                    } catch (FileNotFoundException exception) {
                        // Error message if the file is not found
                        System.out.print("ERROR: File not found for \"");
                        System.out.println(file + "\"");
                    }
                    if (fileWriter != null) {
                        fileWriter.println("Amount Type");
                        fileWriter.println("0 USD");
                    }
                    // Let the user know what is being written
                    System.out.println("Wrote to file: " + file);
                    // Close file to ensure it is written
                    fileWriter.close();
                }
            } else if (userYN1.matches(".*\\bno\\b")) {
                System.out.println("Take care, See you soon!");
                System.exit(1);
            }
        } else {
            System.out.println("I did not understand your answer. Please try again.");
        }

    }//main

    public static Currency[] readFromFile(File file) {
        final Integer SIZE = 1;
        //Create an array of our currency
        Currency[] currencyArray = new Currency[SIZE];
        int size = 0;
        Scanner fileConnect = null;
        // Connect with the file
        try {
            fileConnect = new Scanner(file);
        } catch (FileNotFoundException exception) {
            // Catch if file is not found
            System.out.print("File not found for: " + file);
        }
        // If the file is found
        if (fileConnect != null) {
            // Store the headers of the file
            String firstLineOfFile = fileConnect.nextLine();
            // Loop through the remaining lines of the file
            while (fileConnect.hasNextLine()) {
                // Store a line in the file
                String line = fileConnect.nextLine();
                // Seperate the line with commas
                Scanner lineInput = new Scanner(line).useDelimiter("\\s|\\n");
                //Store each section of the line that is seperated by spaces
                String cAmount1 = lineInput.next();
                //System.out.println(cAmount1);

                // Convert the String to a Double
                Double cAmount = .0;
                try {
                    cAmount = Double.parseDouble(cAmount1);
                    //System.out.println(cAmount);
                } // Confirm the line stored is a double with try/catch
                catch (NumberFormatException exception) {
                    System.out.println("not a double");
                }
                String cType = lineInput.next();
                //System.out.println(cType);
                // Initialize the array with an element 
                currencyArray[size] = new Currency(cAmount, cType);
                size++;
            }//while
            //Print the Array with the values from the file
            System.out.println("Here is your balance:" + "\n");
            System.out.println("Amount Type\n");
            for (int i = 0; i < SIZE; i++) {
                // Use toString to print
                System.out.printf("%s\n", currencyArray[i].toString());
            }

        }//if
        System.out.println("Would you like add/subtract from your account? Enter Yes or No: ");
        Scanner scannerYN = new Scanner(System.in);
        String answer = scannerYN.nextLine();
        System.out.println("You have entered: " + answer);
        String userYN = answer.toLowerCase();
        if (userYN.matches(".*\\byes\\b")) {
            double oldAmount = currencyArray[0].getAmount();
            System.out.println(oldAmount);
            System.out.println("How much would you like to add or subtract? Please include a - sign "
                    + "in front of the value(ie...-100 to subtract 100 USD from your account.");
            Scanner scannerAdd = new Scanner(System.in);
            String answerToAdd = scannerAdd.nextLine();
            String firstPart = answerToAdd.substring(0, 1);
            String restOfAnswer = answerToAdd.substring(1, answerToAdd.length());
            //System.out.println("You have entered: " + answerToAdd);
            //System.out.println("First character is: " + firstPart);
            //System.out.println("rest of answer" + restOfAnswer);
            Double newAmount = .0;
            try {
                newAmount = Double.parseDouble(answerToAdd);
            } // Confirm the line stored is a double with try/catch
            catch (NumberFormatException exception) {
                System.out.println("Your input is not valid");
            }
            //System.out.println(newAmount);
            Double updatedAmount = .0;
            if (firstPart.matches("-")) {
                updatedAmount = oldAmount + newAmount;

            } else {
                updatedAmount = oldAmount + newAmount;
            }
            //System.out.println(updatedAmount);
            //currencyArray[0].setAmount(updatedAmount);
            System.out.println("Your new balance is: " + currencyArray[0] + " USD");
            //Write to a file
            PrintWriter fileWriter = null;
            try {
                fileWriter = new PrintWriter(file);
            } catch (FileNotFoundException exception) {
                // Error message if the file is not found
                System.out.print("ERROR: File not found for \"");
                System.out.println(file + "\"");
            }
            if (fileWriter != null) {
                fileWriter.println("Amount Type");
                fileWriter.println(updatedAmount + " USD");
            }
            // Let the user know what is being written
            System.out.println("Wrote to file: " + file);
            // Close file to ensure it is written
            fileWriter.close();
            System.exit(1);
        } else if (userYN.matches(".*\\bno\\b")) {
            System.exit(1);

        } else {
            System.out.println("I did not understand your answer. Please try again.");
        }
        return currencyArray;
    }//readfromfile

}//public class progas1

class Currency {

    protected Double amount;
    protected String type;
    public static final Double DCONVERT = 1.00;
    public static final Double ECONVERT = 0.85;
    public static final Double YCONVERT = 111.00;

    /*The Constructor intializes the data fields
   *
   *@param cAmount is the amount
   *@param Type is the type of currency
     */
    public Currency(Double cAmount, String cType) {
        amount = cAmount;
        type = cType;
    }

    /**
     * Gets the Currency amount
     *
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Gets the Currency type
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the amount
     *
     * @param cAmount is the currency amount
     */
    public void setAmount(Double cAmount) {
        amount = cAmount;
    }

    /**
     * Sets the type
     *
     * @param cType is the currency type
     */
    public void setType(String cType) {
        type = cType;
    }

    /**
     * Converts the currency to dollar
     */
    public void dollarAmount() {
        amount = amount * DCONVERT;
    }

    /**
     * Converts the currency to euro
     */
    public void euroAmount() {
        amount = amount * ECONVERT;
    }

    /**
     * Converts the currency to yen
     */
    public void yenAmount() {
        amount = amount * YCONVERT;
    }

    /**
     * Changes the type to lowercase
     *
     */
    public void lowerType() {
        type = type.toLowerCase();
    }

    /**
     * The toString() method returns a String with the two data fields
     *
     * @return the output of the initialized array
     */
    public String toString() {
        String output = String.format("%.2f %s\n", amount, type);
        return output;
    }
}//class Currency
