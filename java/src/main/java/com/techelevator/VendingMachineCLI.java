package com.techelevator;

import com.techelevator.view.*;

import java.io.*;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

    private Menu menu;

    public List<Item> itemList = new ArrayList<>(); // stores vending machine items as array list
    private Scanner userInput = new Scanner(System.in);
    private Wallet newWallet = new Wallet();

    public VendingMachineCLI() {
    }

    public void getChange() {
        newWallet.giveChange(newWallet.getBalance());  // calls change method from Wallet class and give our customer change
    }

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {

        try {
            File vendingMachineFile = new File("C:\\Users\\Student\\workspace\\mod1-wk4-pairs-green-t8\\java\\vendingmachine.csv");

            Scanner vendingMachineScanner = new Scanner(vendingMachineFile); // read in from file with method, parse apart at delimiters
            while (vendingMachineScanner.hasNextLine()) {
                String lineOfData = vendingMachineScanner.nextLine(); // lineOfData = line from csv
                String[] arr = lineOfData.split("\\|"); // parse apart at delimiters
                if (arr[3].equals("Chip")) {            // if statement if type.equals("Chip")itemList.add(new Chips()) -- saving items in memory as object
                    Chips chips = new Chips(arr[0], arr[1], Double.parseDouble(arr[2]), arr[3]);
                    itemList.add(chips);
                } else if (arr[3].equals("Candy")) {
                    Candy candy = new Candy(arr[0], arr[1], Double.parseDouble(arr[2]), arr[3]);
                    itemList.add(candy);
                } else if (arr[3].equals("Drink")) {
                    Drink drink = new Drink(arr[0], arr[1], Double.parseDouble(arr[2]), arr[3]);
                    itemList.add(drink);
                } else if (arr[3].equals("Gum")) {
                    Gum gum = new Gum(arr[0], arr[1], Double.parseDouble(arr[2]), arr[3]);
                    itemList.add(gum);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {  // displays all available items line by line to customer
                for (int i = 0; i < itemList.size(); i++) {
                    System.out.println(itemList.get(i));
                }
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {    // When the customer selects "(2) Purchase", they are guided through the purchasing process menu
                purchaseMenu();
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                System.out.println("Have a great day!");
                break;
            }
        }
    }


        public void purchaseMenu() {

            while (true) {
                String choice2 = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

                if (choice2.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {  // Feed Money - allows the customer to repeatedly feed money into the machine whole dollar(1, 2, 5, 10)
                    System.out.println("Please enter 1, 2, 5, or 10: ");
                    String input = userInput.nextLine();
                    try {
                        insertFeedMoney(input);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Current Money Provided: " + newWallet.getBalance());
                    Logger.writeLog("Feeding Money " + input);

                } else if (choice2.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                    for (int i = 0; i < itemList.size(); i++) {     //Show the list of products in stock
                        System.out.println(itemList.get(i));
                    }
                    System.out.println(); // this is here to add a space between the product list and balance display
                    System.out.println("Current Money Provided: " + newWallet.getBalance());    // show remaining balance to customer
                    System.out.print("Please enter the code of the item you wish to purchase: ");// prompt user for item selection & check entered string against string in list
                    String input = userInput.nextLine();

                    boolean itemPick = false;
                    // for loop made with if statement to see if list of products equals itemNumber
                    for (Item snacks : itemList) {
                        if (input.equals((snacks.getItemNumber()))) {
                            itemPick = true;
                            if (snacks.isOutOfStock()) {
                                System.out.println("Item is out of stock. Please choose another item.");
                                return;
                            }
                            if (newWallet.getBalance() < snacks.getPrice()) { // checking to see if balance is enough money, if not it will read the message below
                                System.out.println("Balance is not enough to purchase product, please get a job."); // created this if statement within the main if statement (above), because if input matches itemNumber, we have more actions to take.
                                return;
                            }
                            newWallet.purchases(snacks.getPrice());             // this is getting the purchases, and getting the price of the item
                            snacks.setQuantity(snacks.getQuantity() - 1);   // subtracting the quantity of items until it goes out of stock
                            System.out.println(snacks.getItemName() + " " + snacks.getPrice() + " " + "Remaining balance" + " " + newWallet.getBalance()); // printing out the item name with a price, and getting the remaining balance
                            snacks.sound(); // this allows us to display our sounds after someone makes a purchase on a snack
                            Logger.writeLog("Purchasing: " + snacks.getItemName() + " Price: " + snacks.getPrice() + " Balance is: " + newWallet.getBalance());
                        }
                    }
                    if (!itemPick) { // if item number does not exist, this is what will be printed to user (i.e ! = false)
                        System.out.println("Item number does not exist, please try harder.");
                    }

                } else if (choice2.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
                    getChange(); // dispense change in form of quarters, dimes, and nickels -- call method from Wallet class
                    break;
                }
            }
        }

            public void insertFeedMoney (String money) throws FileNotFoundException {
                    if (money.equals("1")) {
                        newWallet.addMoney(1);
                    }
                    if (money.equals("2")) {
                        newWallet.addMoney(2);
                    }
                    if (money.equals("5")) {
                        newWallet.addMoney(5);
                    }
                    if (money.equals("10")) {
                        newWallet.addMoney(10);
                    }
                }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = null;
        cli = new VendingMachineCLI(menu);
        cli.run();
    }
}