import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> jobs = new ArrayList<>();

        try (InputStream is = Main.class.getResourceAsStream("/jobs.txt");
             Scanner scanner = (is != null) ? new Scanner(is) : null) {

            if (scanner == null) {
                System.out.println("File jobs.txt not found.");
                return;
            }

            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();

                if (type.equals("MONO")) {
                    jobs.add(new MonoPrint(id, pages));
                } else if (type.equals("COLOUR")) {
                    jobs.add(new ColourPrint(id, pages));
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return;
        }

        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}