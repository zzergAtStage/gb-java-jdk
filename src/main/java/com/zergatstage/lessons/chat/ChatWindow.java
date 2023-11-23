package com.zergatstage.lessons.chat;

import com.zergatstage.lessons.chat.model.Chat;
import com.zergatstage.lessons.chat.model.ChatTransport;
import com.zergatstage.lessons.chat.model.Message;
import com.zergatstage.lessons.chat.model.User;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.stream.Collectors;

public class ChatWindow extends JFrame implements ChatTransport {
    public static final int WINDOW_HIGH = 555;
    public static final int WINDOW_WIDTH = 507;
    public static final int WINDOW_POSX = 800;
    public static final int WINDOW_POSY = 100;
    private final JTextArea textBox = new JTextArea();
    private  JMenuItem loginItem;
    private  JMenuItem userLogOff;
    private  JMenuItem exitProgram;
    JMenuBar menuBar;
    JMenu jMenu;
    JScrollPane paneUserList;
    JPanel windowContent;
    JList<String> listUser;
    JPanel chatHistoryPane;
    JPanel messagePane;

    Chat chatEntity;
    User currentUser;
    public static void main(String[] args) {
        new ChatWindow();
    }

    public ChatWindow(){
        chatEntity = new Chat();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WINDOW_POSX,WINDOW_POSY,WINDOW_WIDTH,WINDOW_HIGH);
        windowContent = new JPanel();
        GridBagLayout gb = new GridBagLayout();
        windowContent.setLayout(gb);

        listUser = getUserWindowList(gb);
        GridBagConstraints constr;

        listUser.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) System.out.println(listUser.getSelectedValue());
        });

        //chat history box
        constr = getGridBagConstraints(3,0, 3, 2,
                GridBagConstraints.FIRST_LINE_END, GridBagConstraints.BOTH );
        gb.setConstraints(textBox,constr);
        windowContent.add(textBox);
        textBox.setEditable(false);

        //MessageField
        JTextField messageBox = new JTextField();
        constr = getGridBagConstraints(2,2, 2, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        gb.setConstraints(messageBox,constr);
        windowContent.add(messageBox);
        messageBox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Handle the logic for sending the message
                    sendMessage(messageBox.getText());
                    // Clear the messageBox after sending the message (optional)
                    messageBox.setText("");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        //send button
        JButton sentBtn = new JButton("Send");
        constr = getGridBagConstraints(5,2, 1, 1,
                GridBagConstraints.LAST_LINE_END,GridBagConstraints.NONE);
        gb.setConstraints(sentBtn,constr);
        windowContent.add(sentBtn);
        setContentPane(windowContent);
        addMenu();


        setVisible(true);
    }

    private JList<String> getUserWindowList(GridBagLayout gb) {
        GridBagConstraints constr = getGridBagConstraints(0,0 , 2, 1 ,
                GridBagConstraints.FIRST_LINE_START, GridBagConstraints.NONE);
        JList<String> listUser = new JList<>();

        //listUser.setListData(new String[]{"User1", "User2", "User3", "User4", "User5"});
        listUser.setListData(chatEntity.getActiveUsers().stream()
                .map(user -> user.getUserName() + " - " + user.getUserId())
                .toArray(String[]::new));

        paneUserList = new JScrollPane(listUser);
        gb.setConstraints(paneUserList,constr);
        windowContent.add(paneUserList);
        return listUser;
    }

    private static GridBagConstraints getGridBagConstraints(int gridX, int gridY, int gridWidth, int gridHeight, int anchor, int fill) {
        GridBagConstraints constr = new GridBagConstraints();
        constr.insets = new Insets(10,10,10,10);
        constr.gridx=gridX;
        constr.gridy=gridY;
        constr.gridwidth=gridWidth;
        constr.gridheight=gridHeight;
        constr.fill = fill;
        constr.weightx = (fill == GridBagConstraints.BOTH) ? 1.0 : 0.0;
        constr.weighty = (fill == GridBagConstraints.BOTH) ? 1.0 : 0.0;;
        constr.anchor = anchor;
        return constr;
    }

    private void addMenu() {
        //This setting moves the menu into Apple Screen menu bar
        //System.setProperty("apple.laf.useScreenMenuBar", "true");
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        jMenu = new JMenu("File");
        loginItem = new JMenuItem("Login");
        loginItem.addActionListener(e -> {
            System.out.println("A login point");
            userLogin(this);
            chatEntity.addUserToChat(currentUser);
            System.out.println(chatEntity.getActiveUsers());
            repaint();
        });
        jMenu.add(loginItem);
        userLogOff = new JMenuItem("User log off");
        userLogOff.addActionListener(e -> System.out.println("A logout method called..."));
        jMenu.add(userLogOff);
        jMenu.addSeparator();
        exitProgram = new JMenuItem("Exit");
        exitProgram.addActionListener(e -> System.exit(0));
        jMenu.add(exitProgram);
        menuBar.add(jMenu);
    }

    private void userLogin(ChatWindow c){
        LoginWindow loginWindow = new LoginWindow(c);
        //TODO replaceStub
        currentUser = new User("Some", "Somevich", (int) Math.random());
    }

    @Override
    public void sendMessage(String messageBody) {
        if (messageBody.isBlank()) return;
        Message message = new Message();
        textBox.append("\n" + message);
        repaint();
    }

    @Override
    public Chat readChatHistory() {
        return null;
    }
}
