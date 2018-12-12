import java.util.*;
public abstract class Media{
    private String title;
    private int year;
    private ArrayList<String> genre;
    private double rating;
    private String picture;
    
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
    
    public int getYear(){
        return year;
    }
    
    public double getRating(){
        return rating;
    }
    
    public void setPicture(String picture){
        this.picture = picture;
    }
}