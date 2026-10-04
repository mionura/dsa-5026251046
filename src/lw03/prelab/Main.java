package lw03.prelab;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    public static void problem1() {
        List<String> playlist = new ArrayList<>();

        // Membaca file relatif dari package/classpath
        try (InputStream is = Main.class.getResourceAsStream("playlist.txt");
             Scanner scanner = new Scanner(is)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");
                String command = parts[0];

                if (command.equals("ADD")) {
                    playlist.add(line.substring(4).trim());
                } else if (command.equals("INSERT")) {
                    int index = Integer.parseInt(parts[1]);
                    String song = line.substring(line.indexOf(parts[2])).trim();
                    playlist.add(index, song);
                } else if (command.equals("REMOVE")) {
                    playlist.remove(line.substring(7).trim());
                }
            }

            System.out.println("===== Problem 1 =====");
            System.out.println("Total songs: " + playlist.size());
            for (int i = 0; i < playlist.size(); i++) {
                System.out.println((i + 1) + ": " + playlist.get(i));
            }
        } catch (Exception e) {
            System.out.println("File playlist.txt not found.");
        }
    }



    public static void problem2() {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;

        try (InputStream is = Main.class.getResourceAsStream("participants.txt");
             Scanner scanner = new Scanner(is)) {

            while (scanner.hasNextLine()) {
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) continue;

                if (!participants.add(name)) {
                    duplicateCount++;
                }
            }

            System.out.println("\n===== Problem 2 =====");
            System.out.println("Unique participants: " + participants.size());
            int index = 1;
            for (String participant : participants) {
                System.out.println(index++ + ". " + participant);
            }
            System.out.println("Duplicate registrations: " + duplicateCount);
        } catch (Exception e) {
            System.out.println("File participants.txt not found.");
        }
    }


    
    public static void problem3() {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        try (InputStream is = Main.class.getResourceAsStream("inventory.txt");
             Scanner scanner = new Scanner(is)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");
                String type = parts[0];
                String product = parts[1];
                int quantity = Integer.parseInt(parts[2]);

                if (type.equals("ADD")) {
                    inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
                } else if (type.equals("SELL")) {
                    if (inventory.getOrDefault(product, 0) >= quantity) {
                        inventory.put(product, inventory.get(product) - quantity);
                    } else {
                        failedSales++;
                    }
                }
            }

            System.out.println("\n===== Problem 3 =====");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("Failed sales: " + failedSales);
        } catch (Exception e) {
            System.out.println("File inventory.txt not found.");
        }
    }
}