import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Controller implements ActionListener{
    protected View view;
    protected Model model;

    public Controller (Model model, View view){

        this.model = model;
        this.view = view;
        model.addObserver(view);
        view.addController(this);
        view.makeFrame();
        view.loginView();
    }

    public void actionPerformed(ActionEvent e){

        String action = e.getActionCommand();

        switch(action){
            case "Login": loginButton(); break;
            case "RegisterView" : registerViewButton(); break;
            case "Register" : registerButton(); break;
            case "Profile" : profileButton(); break;
            case "Media" : mediaButton(); break;
            case "Films" : model.initialize("films"); break;
            case "Series" : model.initialize("series") ; break;
        }
    }
    
    public void registerButton() {
        model.addUser(view.getUserText().getText(), view.getPasswordText().getText(), true, false);
        view.loginView();
    }

    public void loginButton(){
        if(model.login(view.getUserText().getText(), view.getPasswordText().getText()) != null){
            model.initialize("films");
        }
    }

    public void registerViewButton(){

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