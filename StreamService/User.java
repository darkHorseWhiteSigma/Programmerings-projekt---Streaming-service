import java.io.*;
import java.util.*;
import java.nio.file.*;
public class User {
    protected String name;
    protected String password;
    protected boolean administrator;
    protected boolean adult;

    public User(String name, String password, boolean adult){
        this.name = name;
        this.password = password;
        this.adult = adult;
    }

    public void addFavorite(Media media) throws IOException {
        Path current = Paths.get(name + ".txt");
        String s = current.toAbsolutePath().toString();
        BufferedWriter bw = new BufferedWriter(new FileWriter(s, true));
        PrintWriter out = new PrintWriter(bw);
        out.print(media.getTitle() + ";");
        if(media instanceof Series){
            out.print(Integer.toString(media.getYear()) + "-" + Integer.toString(((Series)media).getEndYear()) + ";");
        }
        else{
            out.print(Integer.toString(media.getYear()) + ";");
        }
        for(int i = 0; i < media.getGenre().size(); i++){
            if(i != media.getGenre().size() - 1){
                out.print(media.getGenre().get(i) + ",");
            }
            else{
                out.print(media.getGenre().get(i) + ";");
            }
        }
        out.print(Double.toString(media.getRating()).replace(".",",") + ";");
        if(media instanceof Series){
            for(int i = 0; i < ((Series)media).getSeasons().size(); i++){
                if(i != ((Series)media).getSeasons().size() - 1){
                    out.print(((Series)media).getSeasons().get(i) + ",");
                }
                else{
                    out.println(((Series)media).getSeasons().get(i) + ";");
                }
            }
        }
        bw.close();
    }

    public void setAdministrator(){
        administrator = true;
    }

    public boolean isAdministrator(){
        return administrator;
    }
}