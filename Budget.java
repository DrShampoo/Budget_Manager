package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Budget {

    private double balance = 0;
    private Purchases purchases = new Purchases();

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance + this.balance;
        if (this.balance < 0) this.balance = 0;
    }

    public void addPurchase(Scanner scanner, int type) {
        System.out.println("Enter purchase name:");
        String name = scanner.nextLine();
        System.out.println("Enter its price:");
        double price = Double.parseDouble(scanner.nextLine());
        purchases.choosePurchasesList(type, name, price);
        balance -= price;
        System.out.println("Purchase was added!\n");
    }

    public void showListPurchase(Scanner scanner) {
        if (purchases.getAllPurchases().isEmpty()) {
            System.out.println("Purchase list is empty!\n");
            return;
        }
        boolean flag = true;
        while (flag) {
            printType("All");
            System.out.println("6) Back");
            int type = Integer.parseInt(scanner.nextLine());
            System.out.println();
            if (type == 6) {
                flag = false;
            } else {
                purchases.choosePurchasesList(type);
            }
        }
    }

    public void saveFile() {
        File file = new File("purchases.txt");
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(String.valueOf(balance) + "\n");
            fw.write("Food\n");
            purchases.writePurchases(fw, purchases.getFood());
            fw.write("Clothes\n");
            purchases.writePurchases(fw, purchases.getClothes());
            fw.write("Entertainment\n");
            purchases.writePurchases(fw, purchases.getEntertainment());
            fw.write("Other\n");
            purchases.writePurchases(fw, purchases.getOther());
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void loadFile() {
        File file = new File("purchases.txt");
        try (Scanner readScanner = new Scanner(file)) {
            balance = Double.parseDouble(readScanner.nextLine());
            String line = readScanner.nextLine();
            if (line.equals("Food")) {
                while (!(line = readScanner.nextLine()).equals("Clothes")) {
                    purchases.loadPurchases(line, purchases.getFood());
                }
            }
            if (line.equals("Clothes")) {
                while (!(line = readScanner.nextLine()).equals("Entertainment")) {
                    purchases.loadPurchases(line, purchases.getClothes());
                }
            }
            if (line.equals("Entertainment")) {
                while (!(line = readScanner.nextLine()).equals("Other")) {
                    purchases.loadPurchases(line, purchases.getEntertainment());
                }
            }
            if (line.equals("Other")) {
                while (readScanner.hasNext()) {
                    purchases.loadPurchases(readScanner.nextLine(), purchases.getOther());
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }

    public void addPurchaseType(Scanner scanner) {
        boolean flag = true;
        while (flag) {
            printType("Back");
            int type = Integer.parseInt(scanner.nextLine());
            System.out.println();
            if (type == 5) {
                flag = false;
            } else {
                addPurchase(scanner, type);
            }
        }
    }

    public void sort(Scanner scanner) {
        while (true) {
            System.out.println("How do you want to sort?");
            System.out.print("1) Sort all purchases\n" +
                    "2) Sort by type\n" +
                    "3) Sort certain type\n" +
                    "4) Back\n");
            int number = Integer.parseInt(scanner.nextLine());
            switch (number) {
                case 1:
                    System.out.println();
                    purchases.sortPurchases(purchases.getAllPurchases(), "All:");
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Types:");
                    purchases.sortByType();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Choose the type of purchase");
                    System.out.print("1) Food\n" +
                            "2) Clothes\n" +
                            "3) Entertainment\n" +
                            "4) Other\n");
                    int value = Integer.parseInt(scanner.nextLine());
                    switch (value) {
                        case 1:
                            purchases.sortPurchases(purchases.getFood(), "Food:");
                            break;
                        case 2:
                            purchases.sortPurchases(purchases.getClothes(), "Clothes:");
                            break;
                        case 3:
                            purchases.sortPurchases(purchases.getEntertainment(), "Entertainment:");
                            break;
                        case 4:
                            purchases.sortPurchases(purchases.getOther(), "Other:");
                            break;
                    }
                    break;
                case 4:
                    System.out.println();
                    return;
            }
        }
    }

    public void printType(String type) {
        System.out.println("Choose the type of purchases");
        System.out.print("1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) " + type + "\n");
    }
}
