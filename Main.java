import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI mainGUI = new MainGUI();
            mainGUI.setSize(500, 500);
            mainGUI.setLocationRelativeTo(null);
            mainGUI.setTitle("Voters Registration form");
            mainGUI.setVisible(true);
        });

        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("1. Create \n2. Read \n3. Update \n4. Delete \n5. Exit");

        option = scanner.nextInt();

        switch (option) {
            case 1:
                // Create
                break;
            case 2:
                // Read
                break;
            case 3:
                // Update
                // Handle update operation logic
                break;
            case 4:
                // Delete
                break;
            case 5:
                System.exit(0);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }
    }

    public void setVisible(boolean b) {
    }
}