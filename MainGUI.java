import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI extends JFrame {

    public MainGUI() {
        setSize(200, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(173, 216, 230));

        JButton createButton = createMenuButton("Create", Create.class);
        JButton readButton = createMenuButton("Read", Read.class);
        JButton updateButton = createMenuButton("Update", Update.class);
        JButton deleteButton = createMenuButton("Delete", Delete.class);
        JButton exitButton = createMenuButton("Exit", MainGUI.class);

        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setBackground(new Color(230, 230, 245));

        setButtonBounds(createButton, 0);
        setButtonBounds(readButton, 1);
        setButtonBounds(updateButton, 2);
        setButtonBounds(deleteButton, 3);
        setButtonBounds(exitButton, 4);

        buttonPanel.add(createButton);
        buttonPanel.add(readButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);

        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void setButtonBounds(JButton button, int index) {
        int buttonWidth = 130;
        int buttonHeight = 45;
        int verticalGap = 35;

        int x = 180;
        int y = index * (buttonHeight + verticalGap) + 10;

        button.setBounds(x, y, buttonWidth, buttonHeight);
    }

    private JButton createMenuButton(String buttonText, Class<?> targetClass) {
        JButton button = new JButton(buttonText);
        button.setBackground(new Color(176, 224, 230)); // Powder Blue
        button.setForeground(Color.BLACK);
        button.setPreferredSize(new Dimension(80, 20));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(173, 216, 230)); // Light Steel Blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(176, 224, 230)); // Powder Blue again
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Dispose of the current frame
                    dispose();

                    // If the button is "Exit", exit the program
                    if (targetClass == MainGUI.class) {
                        System.exit(0);
                    } else {
                        // Create the new frame
                        SwingUtilities.invokeLater(() -> {
                            try {
                                JFrame frame = (JFrame) targetClass.getDeclaredConstructor().newInstance();
                                frame.setSize(500, 300);
                                frame.setLocationRelativeTo(null);
                                frame.setTitle(buttonText);
                                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                frame.setVisible(true);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        });
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}