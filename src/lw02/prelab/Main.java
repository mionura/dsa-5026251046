import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactionList = new LinkedList<>();
        LinkedList<String[]> customerList = new LinkedList<>();

        InputStream inputStream = Main.class.getResourceAsStream("transactions.txt");
        if (inputStream == null) {
            System.out.println("File not found: transactions.txt");
            return;
        }

        try (Scanner reader = new Scanner(inputStream)) {
            while (reader.hasNext()) {
                String name = reader.next();
                String type = reader.next();
                String amount = reader.next();

                transactionList.add(new String[]{name, type, amount});

                boolean isAlreadyInCustomerList = false;
                for (String[] cust : customerList) {
                    if (cust[0].equals(name)) {
                        isAlreadyInCustomerList = true;
                        break;
                    }
                }

                if (!isAlreadyInCustomerList) {
                    customerList.add(new String[]{name, "0"});
                }
            }
        }

        Queue<String[]> transactionQueue = new LinkedList<>(transactionList);
        Stack<String[]> failedStack = new Stack<>();

        while (!transactionQueue.isEmpty()) {
            String[] item = transactionQueue.poll();
            String name = item[0];
            String type = item[1];
            int amount = Integer.parseInt(item[2]);

            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    int balance = Integer.parseInt(cust[1]);

                    if (type.equals("DEPOSIT")) {
                        balance += amount;
                        cust[1] = String.valueOf(balance); 
                    } else if (type.equals("WITHDRAW")) {
                        if (balance >= amount) {
                            balance -= amount;
                            cust[1] = String.valueOf(balance);
                        } else {
                            failedStack.push(item); 
                        }
                    }
                    break;
                }
            }
        }

        System.out.println("=== Final Balances ===");
        for (String[] cust : customerList) {
            System.out.println(cust[0] + " : " + cust[1]);
        }

        System.out.println();

        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] failed = failedStack.pop();
            System.out.println(failed[0] + " " + failed[1] + " " + failed[2]);
        }
    }
}