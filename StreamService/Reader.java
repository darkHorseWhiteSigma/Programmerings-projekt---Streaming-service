import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Reader{
    protected ArrayList<Media> films;
    protected ArrayList<Media> series;
    protected ArrayList<User> users;

    public Reader(){
        films = new ArrayList<Media>();
        series = new ArrayList<Media>();
        users = new ArrayList<User>();
    }

    /*public void readFilms(String file) throws IOException {
    Path current = Paths.get(file);
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
    }*/
    /*
     * Parameter: Name of file to be read from
     * Returns: ArrayList of all films or series described in the file
     */
    public ArrayList<Media> read(String file) throws IOException {
        Path current = Paths.get(file);
        String s = current.toAbsolutePath().toString();
        BufferedReader br = new BufferedReader(new FileReader(s));
        String st = br.readLine(); // Creates a string of the path to the file
        ArrayList<Media> medias = new ArrayList<Media>();
        while (st != null){
            Media f;
            String[] info = st.split(";"); //Splits the string into an array of information from the string
            String titel = info[0]; // Title is the first bit of information
            String[] cat = info[2].split(","); // Genres need to be further split
            ArrayList<String> genre = new ArrayList<String>();
            for(int i = 0; i < cat.length; i++){
                genre.add(cat[i].trim()); // Puts genres in an ArrayList
            }
            double rating = Double.parseDouble(info[3].trim().replace(",",".")); // Replaces , with . so the rating can be stored ina  double
            if(st.matches(".*\\d-.*")){ // Regular expression which matches with all strings containing a digit followed by -
                String[] yearSplit = info[1].split("-"); // Splits data into beginning and end year
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
                String[] sea = info[4].split(","); 
                ArrayList<String> seasons = new ArrayList<String>();
                for(int i = 0; i < sea.length; i++){
                    seasons.add(sea[i].trim()); //Adds seasons into ArrayList
                }
                f = new Series(titel,year,genre,rating,endYear,seasons);
            }
            else{
                int year = Integer.parseInt(info[1].trim());
                f = new Film(titel,year,genre,rating);
            }
            medias.add(f);
            st = br.readLine();
        }
        br.close();
        return medias;
    }

    /*
     * Parameters: ArrayList of Media to be sorted, String of genre to be sorted after
     * Returns: ArrayList of all media containing the genre
     */
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

    /*
     * Parameters: ArrayList of Media to be searched from, String to be searched after
     * Returns: ArrayList of all Media whose title containins the string
     */
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

    /*
     * Parameters: String name of user, String password of user, Boolean of whether the user is an adult
     * Creates a new user with the specified information and adds it to an ArrayList
     */
    public void addUser(String name, String password, boolean adult){
        users.add(new User(name, password, adult));
        if(users.size() == 1){
            users.get(0).setAdministrator();
        }
    }

    /*
     * Parameters: Username and login password
     * Returns: if Username and password matches a user, the user is returned. If they don't, null is returned
     */
    public User login(String name, String password){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).name == name && users.get(i).password == password){
                return users.get(i);
            }
        }
        System.out.println("Wrong username or password");
        return null;
    }

    /*
     * Parameters: A user who is supposed to be an administrator and a user who is to be promoted to one
     * If the first user is an administrator, the second user is promoted to one
     * If not, an error message is printed
     */
    public void newAdministrator(User administrator, User notAdministrator){
        if(administrator.isAdministrator() == true){
            notAdministrator.setAdministrator();
        }
        else{
            System.out.println("You must be an administrator to promote others to administrator");
        }

    }

    /*
     * Loads all films from the film.txt file to ArrayList films
     */
    public void loadFilms() throws IOException{
        films = read("film.txt");
    }

    /*
     * Loads all series from the serie.txt file to ArrayList series
     */
    public void loadSeries() throws IOException{
        series = read("serie.txt");
    }

    public ArrayList<Media> deleteMedia(String file, Media media) throws IOException{
        ArrayList<Media> medias = read(file);
        //medias.remove(medias.get(0));
        for(Media e : medias){
            if(e == media){
                medias.remove(e);
            }
        }
        return medias;
        /*Path current = Paths.get(file);
        String s = current.toAbsolutePath().toString();
        BufferedWriter bw = new BufferedWriter(new FileWriter(s));
        PrintWriter out = new PrintWriter(bw);
        out.print("");
        for(int i = 0; i < medias.size(); i++){
        writeMedia(file, medias.get(i));
        }
        bw.close();*/
    }

    public void writeMedia(String file, Media media) throws IOException {
        Path current = Paths.get(file);
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
                    out.print(((Series)media).getSeasons().get(i) + ";");
                }
            }
        }
        out.println();
        bw.close();
    }
}