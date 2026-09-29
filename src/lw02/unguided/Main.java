import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foods = new LinkedList<>();
        LinkedList<String[]> drinks = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        Scanner sc = new Scanner(Main.class.getResourceAsStream("orders.txt"));
        while (sc.hasNext()){
            String[] order = new String[4];
            order[0] = sc.next();
            order[1] = sc.next();
            order[2] = sc.next();
            order[3] = sc.next();
            orders.add(order);
        }
        sc.close();

        queue.addAll(orders);
        LinkedList<String[]> successes = new LinkedList<>();

        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String food = order[1];
            String drink = order[2];
            String targetFood = null;
            String targetDrink = null;
            boolean isAvailable = false;

            String[] foodItem = null;
            String[] drinkItem = null;
            boolean foodAvailable = food.equals("-");
            boolean drinkAvailable = drink.equals("-");

            if (!food.equals("-")) {
                for (String[] f : foods) {
                    if (f[0].equals(food)) {
                        targetFood = f[0];
                        foodItem = f;
                        int stock = Integer.parseInt(f[1]);
                        if (stock > 0) {
                            foodAvailable = true;
                        }
                        break;
                    }
                }
            }

            if (!drink.equals("-")) {
                for (String[] d : drinks) {
                    if (d[0].equals(drink)) {
                        targetDrink = d[0];
                        drinkItem = d;
                        int stock = Integer.parseInt(d[1]);
                        if (stock > 0) {
                            drinkAvailable = true;
                        }
                        break;
                    }
                }
            }

            isAvailable = foodAvailable && drinkAvailable;

            if (isAvailable) {
                if (foodItem != null) {
                    foodItem[1] = String.valueOf(Integer.parseInt(foodItem[1]) - 1);
                }
                if (drinkItem != null) {
                    drinkItem[1] = String.valueOf(Integer.parseInt(drinkItem[1]) - 1);
                }
                successes.add(order);
            } else {
                failed.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] success : successes) {
            System.out.println(success[0] + " " + success[1] + " " + success[2] + " " + success[3]);
        }

        System.out.println("=== Remaining Food Stock ===");
        for (String[] f : foods) {
            System.out.println(f[0] + " : " + f[1]);
        }

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] d : drinks) {
            System.out.println(d[0] + " : " + d[1]);
        }

        System.out.println("=== Failed Orders ===");
        while (!failed.isEmpty()) {
            String[] fail = failed.pop();
            System.out.println(fail[0] + " " + fail[1] + " " + fail[2] + " " + fail[3]);
        }
    }
}