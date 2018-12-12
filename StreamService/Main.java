import java.io.*;
public class Main{
    public static void main(String[] args){
        Model model = new Model();
        model.addUser("bob","bob",false,false);
        model.addUser("pete","pete",true,false);
        try{
            model.writeUser(model.users.get(0));
            model.writeUser(model.users.get(1));
            System.out.println(model.readUsers().get(0).getName());
            System.out.println(model.readUsers().get(1).getName());
            model.deleteUser(model.users.get(0));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}