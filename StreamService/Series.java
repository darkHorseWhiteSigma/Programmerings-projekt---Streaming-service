import java.util.*;
public class Series extends Media{
    protected int endYear;
    protected ArrayList<String> seasons;
    
    public Series(String titel, int year, ArrayList<String> genre, double rating, int endYear, ArrayList<String> seasons){
        super(titel,year,genre,rating);
        this.endYear = endYear;
        this.seasons = seasons;
    }
    
}