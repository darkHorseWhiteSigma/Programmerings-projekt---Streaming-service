import java.io.*;
import java.util.*;

public class FileSaver{

    private String fileName;

    public FileSaver(String fileName) {
        this.fileName = fileName;

    }

    public boolean write(ArrayList<Medie> medias) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        boolean success = write(bw, medias);
        bw.close();
        return success;

    }

    private boolean write(BufferedWriter bw, ArrayList<Medie> medias) {
        try{

            if (medias != null && !medias.isEmpty()) {
                for(Medie p : medias){
                    bw.write("Title: " + p.getTitle() + ",");
                    bw.write(" Year: " + Integer.toString(p.getYear()) + ",");
                    for(int i = 0; i < p.getGenre().size(); i++){
                        bw.write(" Genres: " + p.getGenre().get(i) + ",");
                    }
                    bw.write(" Rating: " + Double.toString(p.getRating()) + ",");
                    
                    bw.newLine();
                }
            }
            }catch (IOException e){

            return false;}
        return true;
        }}