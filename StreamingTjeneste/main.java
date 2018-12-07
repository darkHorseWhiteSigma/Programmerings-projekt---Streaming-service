import java.util.*;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.Path;

public class main{

    public static void main(String args[]){

        Medie Model = new Medie("Kage", 1912, 2.2);
        Path current = Paths.get("example.txt");
        String s = current.toAbsolutePath().toString();
        System.out.println(s);
        FileSaver pw = new FileSaver(s);
        FileSaver pr = new FileSaver(s + "/test123.txt");
        ArrayList<Medie> medias = new ArrayList<Medie>();
        medias.add(Model);

        try{
            if (medias != null && !medias.isEmpty()){
                for(int i = 0; i < medias.size() ; i++){
                    System.out.println("Number of medie: " + i + 1);
                    if (pw.write(medias)) {
                        System.out.println("Done!");
                    } else {
                        System.out.println("Fail!");
                    }
                }
            }} catch (IOException e) {
            System.out.println(e.getMessage());

        }

    }
}