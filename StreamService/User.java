import java.util.ArrayList;
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
    
    public void setAdministrator(){
        administrator = true;
    }
    
    public boolean isAdministrator(){
        return administrator;
    }
}