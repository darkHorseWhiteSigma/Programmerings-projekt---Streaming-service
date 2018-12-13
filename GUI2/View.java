import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.JScrollPane;
import java.nio.file.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class View extends JFrame  {
    protected ActionListener actionListener;
    protected JPanel loginPanel, registerPanel, profilePanel, mediaPanel;
    protected JScrollPane mainScrollPane;
   
    
    public View (){
    loginPanel = new JPanel();
    mainScrollPane = new JScrollPane();
    }
    
    
    public void makeFrame(){
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setVisible(true);
        this.validate();
        //this.repaint();
        
    }
    
    public void updateFrame(String display){
        
        this.setTitle(display);
        this.getContentPane().removeAll();
        switch(display){
            case "Login": this.add(loginPanel); break;
            case "Silverstream" : this.add(mainScrollPane); break;
            case "Register" : this.add(registerPanel); break;
            case "Profile" : this.add(profilePanel); break;
            case "Media" : this.add(mediaPanel); break;
        }
        //this.pack();
        this.revalidate();
        this.repaint();
    }
    
    public void loginView(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        //placement(panel);

        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome friend :D");
        welcomeLabel.setForeground(Color.white);
        welcomeLabel.setBounds(100, 1, 160, 50);
        panel.add(welcomeLabel);

        JLabel userLabel = new JLabel("User");
        userLabel.setForeground(Color.white);
        userLabel.setBounds(10, 40, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(31);
        userText.setBounds(100, 40, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.white);
        passwordLabel.setBounds(10, 70, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(31);
        passwordText.setBounds(100, 70, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 110, 80, 25);
        loginButton.setActionCommand("Login");
        loginButton.addActionListener(actionListener);
        panel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(180, 110, 80, 25);
        panel.add(registerButton);

        registerButton.setActionCommand("Register");
        registerButton.addActionListener(actionListener);

        JButton getUsernameButton = new JButton("Forgot Username");
        getUsernameButton.setBounds(10, 150, 140, 25);
        panel.add(getUsernameButton);

        JButton getPasswordButton = new JButton("Forgot Password");
        getPasswordButton.setBounds(180, 150, 140, 25);
        panel.add(getPasswordButton);
        
        loginPanel = panel;
        
        updateFrame("Login");
    }
    
    public void mainView(){
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

        /*filmsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    films();
                }});
                */
        JButton seriesButton = new JButton("Series");
        seriesButton.setPreferredSize( new Dimension(50, 50));
        panel2.add(seriesButton);
        
        /*seriesButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    series();
                }});
                */
        JButton favoritesButton = new JButton("Favorites");
        favoritesButton.setPreferredSize( new Dimension(50, 50));
        favoritesButton.setActionCommand("Media");
        favoritesButton.addActionListener(actionListener);
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
        profileButton.setActionCommand("Profile");
        profileButton.addActionListener(actionListener);
        panel2.add(profileButton);
        
        /*profileButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    profile();
                }});
                */
        JButton logOutButton = new JButton("Log Out");
        logOutButton.setPreferredSize( new Dimension(50, 50));
        panel2.add(logOutButton);
        
        /*logOutButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    frame.dispose();
                    loginPage();
                }});
                */
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
        
        mainScrollPane = scrollPane;
        updateFrame("Silverstream");
    
    }
    
    
    public void registerView(){
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

        JLabel ageLabel = new JLabel("Adult or Child?");
        ageLabel.setBounds(10, 70, 140, 25);
        panel.add(ageLabel);

        String genre[]={"Adult", "Child"};  
        JComboBox g = new JComboBox(genre);
        g.setBounds(120, 70, 160, 25);
        panel.add(g);
        
        registerPanel = panel;
        updateFrame("Register");
        
    }
    
    public void profileView(){
    
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(10, 10, 140, 25);
        //usernameLabel.setPreferredSize( new Dimension(50, 50));
        panel.add(usernameLabel);

        JTextField usernameText = new JTextField(31);
        usernameText.setBounds(120, 10, 160, 25);
        //usernameText.setPreferredSize( new Dimension(50, 50));
        panel.add(usernameText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 140, 25);
        //passwordLabel.setPreferredSize( new Dimension(50, 50));
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(31);
        passwordText.setBounds(120, 40, 160, 25);
        //passwordText.setPreferredSize( new Dimension(50, 50));
        panel.add(passwordText);

        JLabel ageLabel = new JLabel("Age");
        ageLabel.setBounds(10, 70, 140, 25);
        //ageLabel.setPreferredSize( new Dimension(50, 50));
        panel.add(ageLabel);

        JTextField ageText = new JTextField(31);
        ageText.setBounds(120, 70, 160, 25);
        //ageText.setPreferredSize( new Dimension(50, 50));
        panel.add(ageText);
        
        profilePanel = panel;
        updateFrame("Profile");

        
    }
    
    public void mediaView() throws IOException{
        
        JPanel panel = new JPanel();
       

        Path current = Paths.get("");
        String a = (current.toAbsolutePath().toString() + "/12 Angry Men.jpg");
        BufferedImage mediaPicture = ImageIO.read(new File(a));
        JLabel pictureLabel = new JLabel(new ImageIcon(mediaPicture));
        pictureLabel.setPreferredSize( new Dimension(140, 209));
        panel.add(pictureLabel);
        
        mediaPanel = panel;
        updateFrame("Media");
        
    
    }
    
    public void addController(ActionListener actionListener){
        this.actionListener = actionListener;
    }
    
    
}
