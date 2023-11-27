import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Read extends JFrame {
    private JButton showAllButton;
    private JButton searchByIdButton;
    private JButton backButton;

    public Read() {
        setTitle("Read Information");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        showAllButton = new JButton("Show All");
        searchByIdButton = new JButton("Search by ID");
        backButton = new JButton("Back");

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(showAllButton);
        buttonPanel.add(searchByIdButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllInformation(displayArea);
            }
        });

        searchByIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByID(displayArea);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current frame and go back to the MainGUI class
                dispose();
                MainGUI mainGUI = new MainGUI();
                mainGUI.setSize(500, 500);
                mainGUI.setLocationRelativeTo(null);
                mainGUI.setTitle("Voters Registration form");
                mainGUI.setVisible(true);
            }
        });
    }

    private void displayAllInformation(JTextArea displayArea) {
        File folder = new File(".");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            displayArea.append(line + "\n");
                        }
                        displayArea.append("\n"); // Add a newline to separate information from different files
                        reader.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private void searchByID(JTextArea displayArea) {
        String id = JOptionPane.showInputDialog("Enter ID:");
        if (id != null) {
            try {
                File folder = new File(".");
                File[] listOfFiles = folder.listFiles();

                if (listOfFiles != null) {
                    for (File file : listOfFiles) {
                        if (file.isFile() && file.getName().endsWith(".txt")) {
                            BufferedReader reader = new BufferedReader(new FileReader(file));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                if (line.contains("Voter ID: " + id)) {
                                    displayArea.append(line + "\n");
                                    for (int i = 0; i < 7; i++) { // Assuming each record has 7 lines
                                        displayArea.append(reader.readLine() + "\n");
                                    }
                                    break;
                                }
                            }
                            reader.close();
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Read read = new Read();
            read.setVisible(true);
        });
    }
}