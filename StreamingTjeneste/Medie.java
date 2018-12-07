import java.util.*;

public class Medie{

    protected String title;
    protected int year;
    protected ArrayList<String> genre;
    protected double rating;
    //protected int aldersgrænse;

    public Medie(String title, int year, double rating){
        this.title = title;
        this.year = year;
        genre = new ArrayList<String>();
        genre.add("Drama");
        this.rating = rating;
        
    }
    
    public String getTitle(){
    
        return title;
    }
    
    public int getYear(){
    
        return year;
    }
    
    public ArrayList<String> getGenre(){
    
        return genre;
    }
    
    public double getRating(){
        return rating;
    }
}