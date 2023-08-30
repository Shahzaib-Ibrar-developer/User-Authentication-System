package com.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    public Connection connection;
    private JTextField usernameFields;
    private JPasswordField passwordFields;

    public Login() {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null); // Center the frame

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240)); // Set background color

        JPanel formPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        formPanel.setBackground(new Color(240, 240, 240)); // Set background color

        JLabel titleLabel = new JLabel("Welcome Back!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font size
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Center align the label
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Add some spacing

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBackground(new Color(240, 240, 240)); // Set background color
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameFields = new JTextField();
        passwordFields = new JPasswordField();
        usernameFields.setPreferredSize(new Dimension(250, 30)); // Adjust field size
        passwordFields.setPreferredSize(new Dimension(250, 30));

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameFields);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordFields);

        formPanel.add(titleLabel);
        formPanel.add(inputPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 240, 240)); // Set background color
        JButton loginButton = new JButton("Login");
        JButton signButton = new JButton("Sign Up");
        loginButton.setBackground(new Color(0, 153, 204)); // Blue color
        loginButton.setForeground(Color.WHITE);
        signButton.setBackground(new Color(0, 153, 204)); // Blue color
        signButton.setForeground(Color.WHITE);

        CP.createConnection();
        connection = CP.con;

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameFields.getText();
                String password = new String(passwordFields.getPassword());

                String selectQuery = "SELECT username, password FROM users WHERE username = ?";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                    preparedStatement.setString(1, username);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        String retrievedUsername = resultSet.getString("username");
                        String retrievedPassword = resultSet.getString("password");
                        if (username.equals(retrievedUsername) && password.equals(retrievedPassword)) {
                            JOptionPane.showMessageDialog(null, "Successfully logged in");
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid username or password");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "User not found");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignupFrame frame = new SignupFrame();
            }
        });

        buttonPanel.add(signButton);
        buttonPanel.add(loginButton);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}
