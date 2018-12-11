import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.JScrollPane;
import java.nio.file.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI{

    public GUI(){

        //ActionListener ActionListener = new ActionListener();    

    }

    public void loginPage(){
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,200);
        JPanel panel = new JPanel();

        //placement(panel);
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(31);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(31);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(180, 80, 80, 25);
        panel.add(registerButton);

        JButton getUsernameButton = new JButton("Forgot Username");
        getUsernameButton.setBounds(10, 120, 140, 25);
        panel.add(getUsernameButton);

        JButton getPasswordButton = new JButton("Forgot Password");
        getPasswordButton.setBounds(180, 120, 140, 25);
        panel.add(getPasswordButton);

        frame.add(panel);
        frame.setVisible(true);
        frame.validate();

        registerButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    register();
                }});

        loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    mix();
                }});
    }

    public void register(){

        JFrame frame = new JFrame("Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,150);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Enter Username");
        usernameLabel.setBounds(10, 10, 140, 25);
        panel.add(usernameLabel);

        JTextField usernameText = new JTextField(31);
        usernameText.setBounds(120, 10, 160, 25);
        panel.add(usernameText);

        JLabel passwordLabel = new JLabel("Enter Password");
        passwordLabel.setBounds(10, 40, 140, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(31);
        passwordText.setBounds(120, 40, 160, 25);
        panel.add(passwordText);

        JLabel ageLabel = new JLabel("Enter Age");
        ageLabel.setBounds(10, 70, 140, 25);
        panel.add(ageLabel);

        JTextField ageText = new JTextField(31);
        ageText.setBounds(120, 70, 160, 25);
        panel.add(ageText);

        frame.add(panel);
        frame.setVisible(true);
        frame.validate();

    }

    public void chose(){

        JFrame frame = new JFrame("Chose");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,200);
        JPanel panel = new JPanel(new GridLayout(1,4,5,5));

        JButton filmsButton = new JButton("Films");
        filmsButton.setPreferredSize( new Dimension(100, 100));
        panel.add(filmsButton);

        filmsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    films();
                }});

        JButton seriesButton = new JButton("Series");
        seriesButton.setPreferredSize( new Dimension(100, 100));
        panel.add(seriesButton);

        seriesButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    series();
                }});

        JButton catagoriesButton = new JButton("Catagories");
        catagoriesButton.setPreferredSize( new Dimension(100, 100));
        panel.add(catagoriesButton);

        JButton favoritesButton = new JButton("Favorites");
        favoritesButton.setPreferredSize( new Dimension(100, 100));
        panel.add(favoritesButton);

        frame.add(panel);
        frame.setVisible(true);
        frame.validate();

    }

    /*private static void placement(JPanel panel){
    panel.setLayout(null);

    JLabel userLabel = new JLabel("User");
    userLabel.setBounds(10, 10, 80, 25);
    panel.add(userLabel);

    JTextField userText = new JTextField(31);
    userText.setBounds(100, 10, 160, 25);
    panel.add(userText);

    JLabel passwordLabel = new JLabel("Password");
    passwordLabel.setBounds(10, 40, 80, 25);
    panel.add(passwordLabel);

    JPasswordField passwordText = new JPasswordField(31);
    passwordText.setBounds(100, 40, 160, 25);
    panel.add(passwordText);

    JButton loginButton = new JButton("Login");
    loginButton.setBounds(10, 80, 80, 25);
    panel.add(loginButton);

    JButton registerButton = new JButton("Register");
    registerButton.setBounds(180, 80, 80, 25);
    panel.add(registerButton);

    }*/

    public void mix(){
        JFrame frame = new JFrame("StreamingService");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        JPanel panel = new JPanel(new GridLayout(0,4,5,5));
        panel.setBackground(Color.BLACK);
        Path current = Paths.get("");
        String a = (current.toAbsolutePath().toString() + "/Mix");
        //System.out.println(a);

        File f = new File(a);
        File[] fa = f.listFiles();
        JButton buttons[] = new JButton[(fa.length)];

        //for (int i = 0; i< fa.length; i++){
        //    System.out.println(fa[i]);
        //}
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        for (int i = 0; i < fa.length; i++){
            buttons[i] = new JButton(new ImageIcon(fa[i].toString()));
            buttons[i].setPreferredSize( new Dimension(140, 209));
            buttons[i].setBorder(null);
            panel.add(buttons[i]);
        }

        JPanel panel2 = new JPanel(new GridLayout(2,3,5,5));
        panel2.setBackground(Color.BLACK);

        JButton filmsButton = new JButton("Films");
        filmsButton.setPreferredSize( new Dimension(50, 50));
        panel2.add(filmsButton);

        filmsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    films();
                }});

        JButton seriesButton = new JButton("Series");
        seriesButton.setPreferredSize( new Dimension(50, 50));
        panel2.add(seriesButton);

        seriesButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    series();
                }});

        JButton favoritesButton = new JButton("Favorites");
        favoritesButton.setPreferredSize( new Dimension(50, 50));
        panel2.add(favoritesButton);

        JTextField searchField = new JTextField(15);
        searchField.setText(" Search for Genre or Title");
        searchField.setPreferredSize( new Dimension(50, 50));
        searchField.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                if(searchField.getText().isEmpty()) {
                    searchField.setText(" Search for Genre or Title");
                }
            }
            
            public void focusGained(FocusEvent e) {
                if(searchField.getText().equals(" Search for Genre or Title")) {
                    searchField.setText("");
                }
            }
        });
        panel2.add(searchField);
        
        JButton profileButton = new JButton("Profile");
        profileButton.setPreferredSize( new Dimension(50, 50));
        panel2.add(profileButton);
        
        JButton logOutButton = new JButton("Log Out");
        logOutButton.setPreferredSize( new Dimension(50, 50));
        panel2.add(logOutButton);
        
        logOutButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    loginPage();
                }});
        
        /*
        JMenuBar searchBar = new JMenuBar();
        panel2.add(searchBar);
        JMenu searchMenu = new JMenu("Search");
        searchBar.add(searchMenu);
        searchBar.setPreferredSize( new Dimension(40, 40));
        JMenuItem genreItem = new JMenuItem("Genre");
        searchMenu.add(genreItem);
        JTextField g = new JTextField(31);
        String genre[]={"Drama","Action","Love","Si-fi","Comedy"};  
        JComboBox g = new JComboBox(genre);
        JMenuItem gsItem = new JMenuItem();
        searchMenu.add(gsItem);
        gsItem.add(g);
        JMenuItem searchItem = new JMenuItem("Search Title");
        searchMenu.add(searchItem);
        JMenu subMenu = new JMenu("test");
        subMenu.add("test1");
        subMenu.add("test2");
        searchMenu.add(subMenu);*/

  

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setColumnHeaderView(panel2);
        scrollPane.setViewportView(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        //scrollPane.getViewport().setBackground(Color.black);
        frame.add(scrollPane);
        frame.validate();

    }

    public void films(){
        JFrame frame = new JFrame("StreamingService");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        JPanel panel = new JPanel(new GridLayout(0,4,5,5));

        Path current = Paths.get("");
        String a = (current.toAbsolutePath().toString() + "/Films");
        //System.out.println(a);

        File f = new File(a);
        File[] fa = f.listFiles();
        JButton buttons[] = new JButton[(fa.length-1)];

        //for (int i = 0; i< fa.length; i++){
        //    System.out.println(fa[i]);
        //}
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        for (int i = 0; i < fa.length-1; i++){
            buttons[i] = new JButton(new ImageIcon(fa[i+1].toString()));
            buttons[i].setPreferredSize( new Dimension(140, 209));
            buttons[i].setBorder(null);
            panel.add(buttons[i]);
        }
        JPanel panel2 = new JPanel();
        JMenuBar menubar = new JMenuBar();
        panel2.add(menubar);
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem); 

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setColumnHeaderView(panel2);
        scrollPane.setViewportView(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        frame.add(scrollPane);
        frame.validate();

    }

    public void series(){
        JFrame frame = new JFrame("StreamingService");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        JPanel panel = new JPanel(new GridLayout(0,4,5,5));

        Path current = Paths.get("");
        String a = (current.toAbsolutePath().toString() + "/Series");
        //System.out.println(a);

        File f = new File(a);
        File[] fa = f.listFiles();
        JButton buttons[] = new JButton[(fa.length)];

        //for (int i = 0; i< fa.length; i++){
        //    System.out.println(fa[i]);
        //}
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        for (int i = 0; i < fa.length; i++){
            buttons[i] = new JButton(new ImageIcon(fa[i].toString()));
            buttons[i].setPreferredSize( new Dimension(140, 209));
            buttons[i].setBorder(null);
            panel.add(buttons[i]);
        }

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        frame.add(scrollPane);
        frame.validate();

    }
}