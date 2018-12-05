import java.util.*;
public abstract class Media{
    protected String titel;
    protected int year;
    protected ArrayList<String> genre;
    protected double rating;
    
    public Media(String titel, int year, ArrayList<String> genre, double rating){
        this.titel = titel;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }
}