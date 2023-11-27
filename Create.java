import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Create extends MyFrame implements ActionListener {

    ArrayList<String> voterIdList = new ArrayList<>();

    // Constructor
    public Create() {
        super();
        // For text field & button
        createTextField("Last Name: ", 30);
        createTextField("First Name: ", 60);
        createTextField("Age: ", 90);
        createTextField("Street Address: ", 120);
        createTextField("Barangay: ", 150);
        createTextField("City: ", 180);
        createTextField("Zip Code: ", 210);
        createButton("Submit", 240);

        // For document listener
        getLastNameField().getDocument().addDocumentListener(documentListener);
        getFirstNameField().getDocument().addDocumentListener(documentListener);
        getAgeField().getDocument().addDocumentListener(documentListener);
        getStreetAddressField().getDocument().addDocumentListener(documentListener);
        getBarangayField().getDocument().addDocumentListener(documentListener);
        getCityField().getDocument().addDocumentListener(documentListener);
        getZipCodeField().getDocument().addDocumentListener(documentListener);

        // For action listener
        getSubmitButton().addActionListener(this);

        // Add "Go Back" button in the Create class
        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirect to MainGUI.java
                MainGUI mainGUI = new MainGUI();
                mainGUI.setSize(500, 500);
                mainGUI.setLocationRelativeTo(null);
                mainGUI.setTitle("Voters Registration form");
                mainGUI.setVisible(true);
                dispose(); // Close the Create frame
            }
        });
        add(goBackButton, BorderLayout.SOUTH);
    }

    // Document listener
    DocumentListener documentListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            changed();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changed();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            changed();
        }

        public void changed() {
            int failedConditions = 0;

            // Check conditions for fields
            if (!checkLength(getLastNameField().getText(), 1, 30)) {
                failedConditions++;
            }

            if (!checkLength(getFirstNameField().getText(), 1, 30)) {
                failedConditions++;
            }

            if (!checkAge(getAgeField().getText())) {
                failedConditions++;
            }

            // Additional checks for new fields
            if (!checkLength(getStreetAddressField().getText(), 1, 100)) {
                failedConditions++;
            }

            if (!checkLength(getBarangayField().getText(), 1, 50)) {
                failedConditions++;
            }

            if (!checkLength(getCityField().getText(), 1, 50)) {
                failedConditions++;
            }

            if (!checkLength(getZipCodeField().getText(), 1, 10)) {
                failedConditions++;
            }

            // Check the number of failed conditions
            if (failedConditions == 0) {
                getSubmitButton().setEnabled(true);
            } else {
                getSubmitButton().setEnabled(false);
            }
        }

        private boolean checkAge(String text) {
            try {
                int age = Integer.parseInt(text);
                return (age >= 18 && age <= 65);
            } catch (NumberFormatException e) {
                return false;
            }
        }
    };

    // Action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getSubmitButton()) {
            try {
                // Generate unique voter id
                String generatedVoterId;
                do {
                    generatedVoterId = generateVoterId();
                } while (voterIdList.contains(generatedVoterId));
                voterIdList.add(generatedVoterId);

                // Create a new file with the generated voter's ID as the file name
                File file = new File(generatedVoterId + ".txt");
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                // Write user information to the file
                bufferedWriter.write("Voter ID: " + generatedVoterId + "\n");
                bufferedWriter.write("Last Name: " + getLastNameField().getText() + "\n");
                bufferedWriter.write("First Name: " + getFirstNameField().getText() + "\n");
                bufferedWriter.write("Age: " + getAgeField().getText() + "\n");
                bufferedWriter.write("Street Address: " + getStreetAddressField().getText() + "\n");
                bufferedWriter.write("Barangay: " + getBarangayField().getText() + "\n");
                bufferedWriter.write("City: " + getCityField().getText() + "\n");
                bufferedWriter.write("Zip Code: " + getZipCodeField().getText() + "\n");
                bufferedWriter.close();

                // Close the GUI
                dispose();

                // Display a success message in a new JFrame
                JFrame successFrame = new JFrame("Success");
                successFrame.setSize(300, 100);
                successFrame.setLocationRelativeTo(null);
                JLabel successLabel = new JLabel("Submission successful!");
                successLabel.setHorizontalAlignment(JLabel.CENTER);
                successFrame.add(successLabel);

                // Add a "Go Back" button in the success frame
                JButton goBackButton = new JButton("Go Back");
                goBackButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Redirect to Main.java
                        MainGUI mainGUI = new MainGUI();
                        mainGUI.setSize(500, 500);
                        mainGUI.setLocationRelativeTo(null);
                        mainGUI.setTitle("Voters Registration form");
                        mainGUI.setVisible(true);
                        successFrame.dispose(); // Close the success frame
                    }
                });
                successFrame.add(goBackButton, BorderLayout.SOUTH);

                successFrame.setVisible(true);
            } catch (IOException i) {
                i.printStackTrace();
                throw new RuntimeException(i);
            }
        }
    }

    private boolean checkLength(String text, int min, int max) {
        return (text.length() >= min && text.length() <= max);
    }

    private String generateVoterId() {
        int first = (int) (Math.floor(Math.random() * 9000) + 1000);
        int second = (int) (Math.floor(Math.random() * 90000) + 10000);
        return (first + "-" + second);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Create create = new Create();
            create.setVisible(true);
        });
    }
}