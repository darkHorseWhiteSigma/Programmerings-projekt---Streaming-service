import java.io.*;
public class Main{
    public static void main(String[] args){
        Model model = new Model();
        try {
            model.read("film.txt");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        model.sort(model.films, "Drama");
        model.search(model.films, "The");
    }
}