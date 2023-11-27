import javax.swing.*;
import java.awt.Dimension;

public class MyFrame extends JFrame {

    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField ageField;
    private JTextField streetAddressField;
    private JTextField barangayField;
    private JTextField cityField;
    private JTextField zipCodeField;
    private JButton submitButton;

    // Constructor
    public MyFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    // Template
    protected void createTextField(String labelName, int y) {
        JLabel fieldLabel = new JLabel(labelName);
        fieldLabel.setBounds(100, y, 250, 20);
        add(fieldLabel);

        JTextField textField = new JTextField();
        textField.setBounds(200, y, 250, 20);
        add(textField);

        // Assign the text field to the corresponding field in the class
        assignTextField(labelName, textField);
    }

    protected void createButton(String buttonName, int y) {
        JButton button = new JButton(buttonName);
        button.setBounds(100, y, 250, 20);
        button.setPreferredSize(new Dimension(250, 40));
        button.setEnabled(false);
        add(button);

        // Assign the submit button
        if (buttonName.contains("Submit")) {
            submitButton = button;
        }
    }

    // Assign text field to corresponding field in the class
    private void assignTextField(String labelName, JTextField textField) {
        switch (labelName) {
            case "Last Name: ":
                lastNameField = textField;
                break;
            case "First Name: ":
                firstNameField = textField;
                break;
            case "Age: ":
                ageField = textField;
                break;
            case "Street Address: ":
                streetAddressField = textField;
                break;
            case "Barangay: ":
                barangayField = textField;
                break;
            case "City: ":
                cityField = textField;
                break;
            case "Zip Code: ":
                zipCodeField = textField;
                break;
        }
    }

    // Getters
    public JTextField getLastNameField() {
        return lastNameField;
    }

    public JTextField getFirstNameField() {
        return firstNameField;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public JTextField getStreetAddressField() {
        return streetAddressField;
    }

    public JTextField getBarangayField() {
        return barangayField;
    }

    public JTextField getCityField() {
        return cityField;
    }

    public JTextField getZipCodeField() {
        return zipCodeField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }
}