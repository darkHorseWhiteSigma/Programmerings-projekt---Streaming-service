import java.io.*;
public class Main{
    public static void main(String[] args){
        Reader Model = new Reader();
        try {
            Model.read("film.txt");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Model.sort(Model.films, "Drama");
        Model.search(Model.films, "The");
    }
}