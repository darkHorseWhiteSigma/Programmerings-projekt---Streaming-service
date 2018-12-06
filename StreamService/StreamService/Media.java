import java.util.*;
public abstract class Media{
    protected String title;
    protected int year;
    protected ArrayList<String> genre;
    protected double rating;
    
    public Media(String title, int year, ArrayList<String> genre, double rating){
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }
    
    public ArrayList<String> getGenre(){
        return genre;
    }
    
    public String getTitle(){
        return title;
    }
}