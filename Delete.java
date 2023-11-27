import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Delete extends JFrame implements ActionListener {

    private JTextField textField;
    private JComboBox<String> reasonDropdown;
    private JButton submitButton;

    // Constructor
    public Delete() {
        super("Delete Voter");
        setLayout(null);

        // For text field & button
        createTextField("Enter Voter ID: ", 30);
        createReasonDropdown("Reason for Deletion: ", 60);
        createButton("Submit", 90);

        // For action listener
        getSubmitButton().addActionListener(this);

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getSubmitButton()) {
            String voterIdToDelete = getTextField().getText();
            String deletionReason = getReasonDropdown().getSelectedItem().toString();

            // Validate voter ID format 
            if (!voterIdToDelete.matches("\\d{4}-\\d{5}")) {
                JOptionPane.showMessageDialog(null, "Invalid Voter ID format. Please enter a valid ID.");
                return;
            }

            // Confirm deletion
            int confirmDialogResult = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete the voter with ID: " + voterIdToDelete +
                            "\nReason for deletion: " + deletionReason,
                    "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (confirmDialogResult == JOptionPane.YES_OPTION) {
                if (deleteVoterFile(voterIdToDelete)) {
                    JOptionPane.showMessageDialog(null, "Voter with ID: " + voterIdToDelete + " deleted successfully.");
                    dispose(); // Close the Delete frame after deletion
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete the voter file. Please try again.");
                }
            }
        }
    }

    private boolean deleteVoterFile(String voterId) {
        File fileToDelete = new File(voterId + ".txt");

        if (fileToDelete.exists()) {
            return fileToDelete.delete();
        } else {
            JOptionPane.showMessageDialog(null, "File not found for Voter ID: " + voterId);
            return false;
        }
    }

    private void createReasonDropdown(String label, int yPos) {
        JLabel reasonLabel = new JLabel(label);
        reasonLabel.setBounds(10, yPos, 150, 20);
        add(reasonLabel);

        String[] reasons = {"Duplicate Entry", "Invalid Information", "Other"};
        JComboBox<String> reasonDropdown = new JComboBox<>(reasons);
        reasonDropdown.setBounds(160, yPos, 200, 20);
        add(reasonDropdown);
        setReasonDropdown(reasonDropdown);
    }

    private void createTextField(String label, int yPos) {
        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setBounds(10, yPos, 150, 20);
        add(fieldLabel);

        JTextField textField = new JTextField();
        textField.setBounds(160, yPos, 200, 20);
        add(textField);
        setTextField(textField);
    }

    private void createButton(String buttonName, int yPos) {
        JButton button = new JButton(buttonName);
        button.setBounds(160, yPos, 200, 30);
        add(button);
        setSubmitButton(button);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Delete();
        });
    }

    // Getter and setter methods
    public JTextField getTextField() {
        return textField;
    }

    public JComboBox<String> getReasonDropdown() {
        return reasonDropdown;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    private void setTextField(JTextField textField) {
        this.textField = textField;
    }

    private void setReasonDropdown(JComboBox<String> reasonDropdown) {
        this.reasonDropdown = reasonDropdown;
    }

    private void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
    }
}
