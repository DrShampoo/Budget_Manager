package budget;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Budget budget = new Budget();
        boolean flag = true;
        while (flag){
            System.out.println("Choose your action:");
            System.out.println("1) Add income");
            System.out.println("2) Add purchases");
            System.out.println("3) Show list of purchases");
            System.out.println("4) Balance");
            System.out.println("5) Save");
            System.out.println("6) Load");
            System.out.println("7) Analyze (Sort)");
            System.out.println("0) Exit");
            int action = Integer.parseInt(scanner.nextLine());
            System.out.println();

            switch (action) {
                case 1:
                    System.out.println("Enter income:");
                    double in = Double.parseDouble(scanner.nextLine());
                    budget.setBalance(in);
                    System.out.println("Income was added!\n");
                    break;
                case 2:
                    budget.addPurchaseType(scanner);
                    break;
                case 3:
                    budget.showListPurchase(scanner);
                    break;
                case 4:
                    System.out.printf("Balance: $%.2f\n%n", budget.getBalance());
                    break;
                case 5:
                    System.out.println("Purchases were saved!\n");
                    budget.saveFile();
                    break;
                case 6:
                    System.out.println("Purchases were loaded!\n");
                    budget.loadFile();
                    break;
                case 7:
                    budget.sort(scanner);
                    break;
                case 0:
                    System.out.println("Bye!");
                    flag = false;
                    scanner.close();
                    break;
                default:
                    System.out.println("Error!");
            }
        }
    }
}
