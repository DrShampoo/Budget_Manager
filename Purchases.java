package budget;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Purchases {

    private Map<String, Double> allPurchases = new HashMap<>();
    private Map<String, Double> food = new HashMap<>();
    private Map<String, Double> clothes = new HashMap<>();
    private Map<String, Double> entertainment = new HashMap<>();
    private Map<String, Double> other = new HashMap<>();

    public void setAllPurchases(Map<String, Double> allPurchases) {
        this.allPurchases = allPurchases;
    }

    public Map<String, Double> getFood() {
        return food;
    }

    public void setFood(Map<String, Double> food) {
        this.food = food;
    }

    public Map<String, Double> getClothes() {
        return clothes;
    }

    public void setClothes(Map<String, Double> clothes) {
        this.clothes = clothes;
    }

    public Map<String, Double> getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(Map<String, Double> entertainment) {
        this.entertainment = entertainment;
    }

    public Map<String, Double> getOther() {
        return other;
    }

    public void setOther(Map<String, Double> other) {
        this.other = other;
    }

    public Map<String, Double> getAllPurchases() {
        return allPurchases;
    }

    public double sumOfPurchases(Map<String, Double> products) {
        double count = 0;
        for (Double price : products.values()) {
            count += price;
        }
        return count;
    }

    public void loadPurchases(String line, Map<String, Double> map) {
        String[] array = line.split(":");
        double price = Double.parseDouble(array[1]);
        map.put(array[0], price);
        allPurchases.put(array[0], price);
    }

    public void writePurchases(FileWriter fileWriter, Map<String, Double> map) throws IOException {
        for (var entry : map.entrySet()) {
            fileWriter.write(entry.getKey() + ":" + entry.getValue() + "\n");
        }
    }

    public void choosePurchasesList (int type, String purchase, double price) {
        allPurchases.put(purchase, price);
        switch (type) {
            case 1:
                food.put(purchase, price);
                break;
            case 2:
                clothes.put(purchase, price);
                break;
            case 3:
                entertainment.put(purchase, price);
                break;
            case 4:
                other.put(purchase, price);
                break;
            default:
                System.out.println("Error!");
        }
    }


    public void choosePurchasesList(int type) {
        switch (type) {
            case 1:
                printPurchases(food, "Food:");
                break;
            case 2:
                printPurchases(clothes, "Clothes");
                break;
            case 3:
                printPurchases(entertainment, "Entertainment:");
                break;
            case 4:
                printPurchases(other, "Other:");
                break;
            case 5:
                printPurchases(allPurchases, "All:");
                break;
            default:
                System.out.println("Error!");
        }
    }

    public void sortPurchases(Map<String, Double> products, String type) {
        System.out.println();
        if (products.isEmpty()) {
            System.out.println("Purchase list is empty\n");
            return;
        }
        System.out.println(type);
        products.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("%s $%.2f%n", entry.getKey(), entry.getValue()));

        System.out.printf("Total: $%.2f%n", sumOfPurchases(products));
        System.out.println();
    }

    public void sortByType() {
        Map<String, Double> map = new HashMap<>();
        map.put("Food - $", food.isEmpty() ? 0 : sumOfPurchases(food));
        map.put("Entertainment - $", entertainment.isEmpty() ? 0 : sumOfPurchases(entertainment));
        map.put("Clothes - $", clothes.isEmpty() ? 0 : sumOfPurchases(clothes));
        map.put("Other - $", other.isEmpty() ? 0 : sumOfPurchases(other));

        map.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("%s%.2f%n", entry.getKey(), entry.getValue()));
        System.out.printf("Total sum: $%.2f%n", sumOfPurchases(allPurchases));
        System.out.println();
    }


    public void printPurchases(Map<String, Double> products, String type) {
        if (products.isEmpty()) {
            System.out.println(type);
            System.out.println("Purchase list is empty\n");
        } else {
            System.out.println(type);
            for (var entry : products.entrySet()) {
                System.out.printf("%s $%.2f\n", entry.getKey(), entry.getValue());
            }
            System.out.printf("Total sum: $%.2f\n%n", sumOfPurchases(products));
        }
    }
}
