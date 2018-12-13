import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class Controller implements ActionListener{

    protected View view;
    protected Model model;

    public Controller (Model model, View view){

        this.model = model;
        this.view = view;
        view.addController(this);
        view.makeFrame();
        view.loginView();
    }
   
    public void actionPerformed(ActionEvent e){

        String action = e.getActionCommand();
        
        switch(action){
            case "Login": loginButton(); break;
            case "Register" : registerButton(); break;
            case "Profile" : profileButton(); break;
            case "Media" : mediaButton(); break;
        }
    }
    
    public void loginButton(){
        view.mainView();
    }
    
    public void registerButton(){
    
        view.registerView();
    }
    
    public void profileButton(){
        
        view.profileView();
    }
    
    public void mediaButton(){
        try {
        view.mediaView();}
        catch (IOException e) {
        e.printStackTrace();
        }
    }
    
}