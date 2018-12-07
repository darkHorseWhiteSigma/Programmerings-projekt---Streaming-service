import java.io.*;
import java.util.*;
import java.nio.file.*;
public class User {
    protected String name;
    protected String password;
    protected boolean administrator;
    protected ArrayList<Media> favorites;
    protected boolean adult;

    public User(String name, String password, boolean adult){
        this.name = name;
        this.password = password;
        this.adult = adult;
    }

    public void addFavorite(Media favorite){
        favorites.add(favorite);
    }

    public void write(Media media) throws IOException {
        Path current = Paths.get(name + ".txt");
        String s = current.toAbsolutePath().toString();
        BufferedWriter bw = new BufferedWriter(new FileWriter(s, true));
        PrintWriter out = new PrintWriter(bw);
        out.print(media.getTitle() + ";");
        out.print(Integer.toString(media.getYear()) + ";");
        for(int i = 0; i < media.getGenre().size(); i++){
            if(i != media.getGenre().size() - 1){
            out.print(media.getGenre().get(i) + ",");
            }
            else{
                out.print(media.getGenre().get(i) + ";");
            }
        }
        out.println(Double.toString(media.getRating()).replace(".",",") + ";");
        bw.close();
    }

    public void setAdministrator(){
        administrator = true;
    }

    public boolean isAdministrator(){
        return administrator;
    }
}