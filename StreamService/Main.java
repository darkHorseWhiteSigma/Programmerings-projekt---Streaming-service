import java.io.*;
public class Main{
    public static void main(String[] args){
        Reader Model = new Reader();
        try {
            Model.readFilms();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}