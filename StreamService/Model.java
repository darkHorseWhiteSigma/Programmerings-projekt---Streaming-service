import java.nio.file.*;
import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.lang.*;

public class Model extends Observable{
    protected ArrayList<Media> films;
    protected ArrayList<Media> series;
    protected ArrayList<User> users;

    public Model(){
        films = new ArrayList<Media>();
        series = new ArrayList<Media>();
        users = new ArrayList<User>();
        try {
            loadFilms();
            loadSeries();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        films = addPictures(films, "Films");
        series = addPictures(series, "Series");
    }

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
            double rating = Double.parseDouble(info[3].trim().replace(",",".")); // Replaces , with . so the rating can be stored in a double
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
            }
        }
        return match;
    }

    /*
     * Parameters: String name of user, String password of user, Boolean of whether the user is an adult
     * Creates a new user with the specified information and adds it to an ArrayList
     */
    public void addUser(String name, String password, boolean adult, boolean administrator){
        users.add(new User(name, password, adult, administrator));
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
            if(users.get(i).getName() == name && users.get(i).getPassword() == password){
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

    /*
     * Parameters: Name of text file to be edited, media to be removed
     * Removes a media from a text file
     */
    public void deleteMedia(String file, Media media) throws IOException{
        ArrayList<Media> medias = read(file); //Reads the text file into an arraylist of media
        for(int i = 0; i < medias.size(); i++){
            if(medias.get(i).getTitle().equals(media.getTitle()) && medias.get(i).getYear() == (media.getYear())){ // Checks if two medias ahve same titel and year
                medias.remove(i); // If a media in the arraylist is identical to the media to be removed, it is removed.
            }
        }
        Path current = Paths.get(file);
        String s = current.toAbsolutePath().toString(); // Creates access to text file
        BufferedWriter bw = new BufferedWriter(new FileWriter(s));
        PrintWriter out = new PrintWriter(bw); // out.print now writes to text file
        out.print(""); // Clears text file
        for(int i = 0; i < medias.size(); i++){
            writeMedia(file, medias.get(i)); // Rewrites text file with new arraylist.
        }
        bw.close();
    }

    /*
     * Parameters: Name of file to write in, media to add
     * Adds a media (film or series) to a text file as a new line in the proper format
     * If the text file of the specified name does not exist, a new one is made
     */
    public void writeMedia(String file, Media media) throws IOException {
        Path current = Paths.get(file);
        String s = current.toAbsolutePath().toString(); // Creates access to text file
        BufferedWriter bw = new BufferedWriter(new FileWriter(s, true));
        PrintWriter out = new PrintWriter(bw); // out.print now writes to text file
        out.print(media.getTitle() + ";"); // Writes title
        if(media instanceof Series){
            out.print(Integer.toString(media.getYear()) + "-" + Integer.toString(((Series)media).getEndYear()) + ";"); // Writes year and endyear
        }
        else{
            out.print(Integer.toString(media.getYear()) + ";"); // Writes only year
        }
        for(int i = 0; i < media.getGenre().size(); i++){
            if(i != media.getGenre().size() - 1){
                out.print(media.getGenre().get(i) + ","); // Writes genres separated by ,
            }
            else{
                out.print(media.getGenre().get(i) + ";"); // Writes last genre ending in ;
            }
        }
        out.print(Double.toString(media.getRating()).replace(".",",") + ";"); // Writes rating
        if(media instanceof Series){
            for(int i = 0; i < ((Series)media).getSeasons().size(); i++){
                if(i != ((Series)media).getSeasons().size() - 1){
                    out.print(((Series)media).getSeasons().get(i) + ","); // Writes seasons separated by ,
                }
                else{
                    out.print(((Series)media).getSeasons().get(i) + ";"); // Writes seasons ending with ;
                }
            }
        }
        out.println();
        bw.close();
    }

    /*
     * Parameters: User who owns the list, media to be added
     * Adds a media to a text file in the name of the user. Creates the text file if it does not exist
     */
    public void addFavourite(User user, Media media) throws IOException{
        writeMedia(user.getName() + ".txt", media);
    }

    public ArrayList<Media> sortRating(ArrayList<Media> medias){
        double[] ratings = new double[medias.size()];
        for(int i = 0; i < ratings.length; i++){
            ratings[i] = medias.get(i).getRating();
        }
        Arrays.sort(ratings);
        for(int i = 0; i < ratings.length; i++){
            System.out.println(ratings[i]);
        }
        return null;
    }

    /*
     * Parameters: An arraylist of medias to add pictures to, a folder containing the pictures
     * Returns: An arraylist of the same medias with the added pictures
     * The "pictures" are actually just the file path to the individual picture
     */
    public ArrayList<Media> addPictures(ArrayList<Media> medias, String folder){
        Path current = Paths.get(folder);
        String s = current.toAbsolutePath().toString();
        File f = new File(s);
        File[] fs = f.listFiles();
        for(int i = 0; i < medias.size(); i++){
            for(int j = 0; j < fs.length; j++){
                if(fs[j].toString().contains(medias.get(i).getTitle())){
                    medias.get(i).setPicture(fs[j].toString());
                }
            }
        }
        return medias;
    }
    /*
     * Parameter: The user to write to a text file
     * Writes a user to text file users.txt. If the file doesn't exist, it will create it
     */
    public void writeUser(User user) throws IOException{
        Path current = Paths.get("users.txt");
        String s = current.toAbsolutePath().toString(); // Creates access to text file
        BufferedWriter bw = new BufferedWriter(new FileWriter(s, true));
        PrintWriter out = new PrintWriter(bw); // out.print now writes to text file
        out.print(user.getName() + ";");
        out.print(user.getPassword() + ";");
        out.print(user.isAdministrator() + ";");
        out.println(user.isAdult());
        bw.close();
    }
    
    /*
     * Returns: An arraylist of users described in the text file users.txt
     */
    public ArrayList<User> readUsers() throws IOException{
        Path current = Paths.get("users.txt");
        String s = current.toAbsolutePath().toString();
        BufferedReader br = new BufferedReader(new FileReader(s));
        String st = br.readLine(); // Creates a string of the path to the file
        ArrayList<User> users = new ArrayList<User>();
        while (st != null){
            String[] info = st.split(";"); //Splits the string into an array of information from the string
            String name = info[0]; // Name is the first bit of information
            String password = info[1]; //Password is the second
            boolean administrator = Boolean.parseBoolean(info[2]); //Administrator is the third
            boolean adult = Boolean.parseBoolean(info[3]); //Adult is the fourth
            users.add(new User(name,password,adult,administrator));
            st = br.readLine();
        }
        br.close();
        return users;
    }

    /*
     * Parameters: User to be removed
     * Removes a user from a text file
     */
    public void deleteUser(User user) throws IOException{
        ArrayList<User> users = readUsers(); //Reads the text file of users into an arraylist of media
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getName().equals(user.getName()) && users.get(i).getPassword().equals(user.getPassword())){ // Checks if two users have the samme name and password
                users.remove(i); // If a media in the arraylist is identical to the media to be removed, it is removed.
            }
        }
        Path current = Paths.get("users.txt");
        String s = current.toAbsolutePath().toString(); // Creates access to text file
        BufferedWriter bw = new BufferedWriter(new FileWriter(s));
        PrintWriter out = new PrintWriter(bw); // out.print now writes to text file
        out.print(""); // Clears text file
        for(int i = 0; i < users.size(); i++){
            writeUser(users.get(i)); // Rewrites text file with new arraylist.
        }
        bw.close();
    }
}