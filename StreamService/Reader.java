import java.nio.file.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader{
    protected ArrayList<Media> films;
    protected ArrayList<Media> series;
    protected ArrayList<User> users;

    public Reader(){
        films = new ArrayList<Media>();
        series = new ArrayList<Media>();
        users = new ArrayList<User>();
    }

    public void readFilms() throws IOException {
        Path current = Paths.get("film.txt");
        String s = current.toAbsolutePath().toString();
        BufferedReader br = new BufferedReader(new FileReader(s));
        String st = br.readLine();
        while (st != null){                    
            String[] info = st.split(";");
            String titel = info[0];
            int year = Integer.parseInt(info[1].trim());
            String[] cat = info[2].split(",");
            ArrayList<String> genre = new ArrayList<String>();
            for(int i = 0; i < cat.length; i++){
                genre.add(cat[i].trim());
            }
            double rating = Double.parseDouble(info[3].trim().replace(",","."));
            Film f = new Film(titel,year,genre,rating);
            films.add(f);
            st = br.readLine();
        }
        br.close();
    }

    public void readSeries() throws IOException {
        Path current = Paths.get("serie.txt");
        String s = current.toAbsolutePath().toString();
        BufferedReader br = new BufferedReader(new FileReader(s));
        String st = br.readLine();
        while (st != null){                    
            String[] info = st.split(";");
            String titel = info[0];
            String[] yearSplit = info[1].split("-");
            int year = Integer.parseInt(yearSplit[0].trim());
            int endYear;
            if(yearSplit.length == 2){
                if (yearSplit[1].trim().length() == 0){
                    endYear = -1; // series is ongoing
                }
                else{
                    endYear = Integer.parseInt(yearSplit[1].trim()); // series has ended
                }
            }
            else{
                endYear = 0; // series ran for a year only
            }
            String[] cat = info[2].split(",");
            ArrayList<String> genre = new ArrayList<String>();
            for(int i = 0; i < cat.length; i++){
                genre.add(cat[i].trim());
            }
            double rating = Double.parseDouble(info[3].trim().replace(",","."));
            String[] sea = info[4].split(",");
            ArrayList<String> seasons = new ArrayList<String>();
            for(int i = 0; i < sea.length; i++){
                seasons.add(sea[i].trim());
            }
            Series f = new Series(titel,year,genre,rating,endYear,seasons);
            series.add(f);
            st = br.readLine();
        }
        br.close();
    }
    
    public ArrayList<Media> sort(ArrayList<Media> medias, String genre){
        ArrayList<Media> sorted = new ArrayList<Media>();
        for(int i = 0; i < medias.size(); i++){
            for(int j = 0; j < medias.get(i).getGenre().size(); j++){
                if(medias.get(i).getGenre().get(j).contains(genre)){
                    sorted.add(medias.get(i));
                    System.out.println(medias.get(i).getTitle());
                    break;
                }
            }
        }
        return sorted;
    }
    
    public ArrayList<Media> search(ArrayList<Media> medias, String title){
        ArrayList<Media> match = new ArrayList<Media>();
        for(int i = 0; i < medias.size(); i++){
            if(medias.get(i).getTitle().toLowerCase().contains(title.toLowerCase())){
                match.add(medias.get(i));
                System.out.println(medias.get(i).getTitle());
                
            }
        }
    
        return match;
    }
    
    public void addUser(String name, String password, boolean adult){
        users.add(new User(name, password, adult));
        if(users.size() == 1){
            users.get(0).setAdministrator();
        }
    }
    
    public User login(String name, String password){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).name == name && users.get(i).password == password){
                return users.get(i);
            }
        }
        System.out.println("Wrong username or password");
        return null;
    }
    
    public void newAdministrator(User administrator, User notAdministrator){
        if(administrator.isAdministrator() == true){
            notAdministrator.setAdministrator();
        }
        else{
            System.out.println("You must be an administrator to promote others to administrator");
        }
        
    }
}