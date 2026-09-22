package lw01.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("washes.txt"));
        int washCount = scanner.nextInt();
        WashService[] washList = new WashService[washCount];

        for (int i = 0; i < washCount; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();

            if (type.equals("MOTORCYCLE")) {
                washList[i] = new MotorcycleWash(id, days);
            } else if (type.equals("CAR")) {
                washList[i] = new CarWash(id, days);
            }
        }

        scanner.close();

        for (int i = 0; i < washCount; i++) {
            System.out.println(washList[i].summary());
        }
    }
}

