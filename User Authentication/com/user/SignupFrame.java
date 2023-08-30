package com.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupFrame extends JFrame {
    public Connection connection;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;

    public SignupFrame() {
        setTitle("Signup Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600); // Increased height for better layout
        setLocationRelativeTo(null); // Center the frame

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel formPanel = new JPanel(new GridLayout(5, 1, 10, 10)); // Increased rows to accommodate Login button
        JPanel inputPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel titleLabel = new JLabel("Create an Account");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel emailLabel = new JLabel("Email:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        emailField = new JTextField();

        usernameField.setPreferredSize(new Dimension(300, 100)); // Adjusted text field size
        passwordField.setPreferredSize(new Dimension(300, 100)); // Adjusted text field size
        emailField.setPreferredSize(new Dimension(300, 100)); // Adjusted text field size

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        formPanel.add(titleLabel);
        formPanel.add(inputPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton signupButton = new JButton("Signup");
        JButton logButton = new JButton("Login"); 
           CP.createConnection();
           connection = CP.con; 
        // Added Login button
        logButton.setBackground(new Color(0, 153, 204)); // Blue color
        logButton.setForeground(Color.WHITE);
        signupButton.setBackground(new Color(0, 153, 204)); // Blue color
        signupButton.setForeground(Color.WHITE);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();
             
                String insertQuery = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
                try{
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1,username);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, password);
                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) inserted.");
        
                    preparedStatement.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Action listener for Login button
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
            }
        });

        buttonPanel.add(signupButton);
        buttonPanel.add(logButton); // Add Login button to buttonPanel

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignupFrame());
    }
}
