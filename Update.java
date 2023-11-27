import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Update extends JFrame implements ActionListener {
    private JTextField voterIdField;
    private JButton fetchDataButton;
    private JButton submitButton;
    private JButton goBackButton;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField ageField;
    private JTextField streetAddressField;
    private JTextField barangayField;
    private JTextField cityField;
    private JTextField zipCodeField;

    private String fetchedLastName;
    private String fetchedFirstName;
    private String fetchedAge;
    private String fetchedStreetAddress;
    private String fetchedBarangay;
    private String fetchedCity;
    private String fetchedZipCode;

    public Update() {
        setTitle("Update Information");

        voterIdField = new JTextField(20);
        fetchDataButton = new JButton("Fetch Data");
        submitButton = new JButton("Submit");
        goBackButton = new JButton("Go Back");
        lastNameField = new JTextField(20);
        firstNameField = new JTextField(20);
        ageField = new JTextField(20);
        streetAddressField = new JTextField(20);
        barangayField = new JTextField(20);
        cityField = new JTextField(20);
        zipCodeField = new JTextField(20);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Enter Voter ID: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(voterIdField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(fetchDataButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Last Name: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("First Name: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Age: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(ageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Street Address: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(streetAddressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Barangay: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(barangayField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("City: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        add(cityField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Zip Code: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        add(zipCodeField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        add(submitButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 8;
        add(goBackButton, gbc);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        fetchDataButton.addActionListener(this);
        submitButton.addActionListener(this);
        goBackButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fetchDataButton) {
            String voterId = voterIdField.getText().trim();
            if (!voterId.isEmpty()) {
                loadExistingData(voterId);
                updateTextFields();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a Voter ID.");
            }
        } else if (e.getSource() == submitButton) {
            saveData();
        } else if (e.getSource() == goBackButton) {
            SwingUtilities.invokeLater(() -> {
                MainGUI mainGUI = new MainGUI();
                mainGUI.setSize(500, 500);
                mainGUI.setLocationRelativeTo(null);
                mainGUI.setTitle("Voters Registration form");
                mainGUI.setVisible(true);
            });
            dispose();
        }
    }

    private void loadExistingData(String voterId) {
        try {
            File file = new File(voterId + ".txt");
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                fetchedLastName = getValueForLabel(reader, "Last Name: ");
                fetchedFirstName = getValueForLabel(reader, "First Name: ");
                fetchedAge = getValueForLabel(reader, "Age: ");
                fetchedStreetAddress = getValueForLabel(reader, "Street Address: ");
                fetchedBarangay = getValueForLabel(reader, "Barangay: ");
                fetchedCity = getValueForLabel(reader, "City: ");
                fetchedZipCode = getValueForLabel(reader, "Zip Code: ");
                reader.close();
            } else {
                JOptionPane.showMessageDialog(this, "Voter ID not found.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    private String getValueForLabel(BufferedReader reader, String label) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith(label)) {
                return line.substring(label.length()).trim();
            }
        }
        return "";
    }

    private void updateTextFields() {
        lastNameField.setText(fetchedLastName);
        firstNameField.setText(fetchedFirstName);
        ageField.setText(fetchedAge);
        streetAddressField.setText(fetchedStreetAddress);
        barangayField.setText(fetchedBarangay);
        cityField.setText(fetchedCity);
        zipCodeField.setText(fetchedZipCode);
    }

    private void saveData() {
        String voterId = voterIdField.getText().trim();
        try {
            File file = new File(voterId + ".txt");
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Voter ID: " + voterId + "\n");
            bufferedWriter.write("Last Name: " + lastNameField.getText() + "\n");
            bufferedWriter.write("First Name: " + firstNameField.getText() + "\n");
            bufferedWriter.write("Age: " + ageField.getText() + "\n");
            bufferedWriter.write("Street Address: " + streetAddressField.getText() + "\n");
            bufferedWriter.write("Barangay: " + barangayField.getText() + "\n");
            bufferedWriter.write("City: " + cityField.getText() + "\n");
            bufferedWriter.write("Zip Code: " + zipCodeField.getText() + "\n");

            bufferedWriter.close();
            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving data.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Update update = new Update();
            update.setVisible(true);
        });
    }
}