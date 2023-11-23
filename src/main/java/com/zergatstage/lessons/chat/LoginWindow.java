package com.zergatstage.lessons.chat;

import com.zergatstage.lessons.chat.model.User;
import com.zergatstage.lessons.chat.model.UserCredentials;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class LoginWindow{

    User currentUser;
    public LoginWindow(ChatWindow c){
        JFrame frame = new JFrame("Logon");
        frame.setBounds(0,0,200,250);
        frame.setLocationRelativeTo(c);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(4,2,5,5));

        JLabel serverIpLbl = new JLabel("Server");
        JTextField serverIP = new JTextField("1.1.1.1");
        JTextField userId = new JTextField("User id");
        JPasswordField userPassword = new JPasswordField("User password");
        pane.add(serverIpLbl);
        pane.add(serverIP);
        pane.add(new JLabel("User:"));
        pane.add(userId);
        pane.add(new JLabel("Password:"));
        pane.add(userPassword);
        JButton proceedLogin = new JButton("Login");
        JButton cancelLogin = new JButton("Cancel");
        proceedLogin.addActionListener( e -> {
            String message = loginUser(serverIP.getText(), userId.getText(),userPassword.getPassword());
            if (!message.isBlank()) frame.dispose();
            JOptionPane.showMessageDialog(frame, message, "Login Failed", JOptionPane.ERROR_MESSAGE);

        });
        cancelLogin.addActionListener( e -> frame.dispose());
        pane.add(proceedLogin);
        pane.add(cancelLogin);
        frame.add(pane);
        frame.setContentPane(pane);
        frame.pack();
        frame.setVisible(true);


    }

    private String loginUser(String serverId, String userId, char[] password) {
        //TODO create interface to work with password (hash) and server answer
        try{
            UserCredentials user = new UserCredentials(Integer.parseInt(userId), Arrays.hashCode(password));
            return ServerAuthorizationStub.checkUserAuthority(user);
        } catch (NumberFormatException e) {
            return "Wrong input format. Try again!";
        }
    }


}
