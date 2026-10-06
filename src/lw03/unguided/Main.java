package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> registrations = new LinkedHashSet<>();
 
        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("registrations.txt"));

        while(sc1.hasNextLine()) {
            String student_id = sc1.nextLine();
            registrations.add(student_id);
        }
        sc1.close();
       
        List<String> checkins = new ArrayList<>();
        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
  
        int rejectedAttempts = 0;
        while(sc2.hasNextLine()) {
            String student_id = sc2.nextLine();
            checkins.add(student_id);

            if(!checkins.contains(student_id) && !registrations.contains(student_id)) {
                checkins.add(student_id);
            }else if(!registrations.contains(student_id)) {
                rejectedAttempts++;
            }else if(!checkins.contains(student_id)) {
                checkins.add(student_id);
            }
        }   
        sc2.close();

        System.out.println("===== Events Check-in Results =====");

        for (String student_id : checkins) {
            if (registrations.contains(student_id)) {
                System.out.println(student_id + " - Checked In");
            } else {
                System.out.println(student_id + " - Rejected");
            }
        }

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registrations.size());
        System.out.println("Successful check-ins: " + checkins.size());
        System.out.println("Absent students: " + (registrations.size() - checkins.size()));
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
}
