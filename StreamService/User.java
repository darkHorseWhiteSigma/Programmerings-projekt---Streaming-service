import java.io.*;
import java.util.*;
import java.nio.file.*;
public final class User {
    protected String name;
    protected String password;
    protected boolean administrator;
    protected boolean adult;

    public User(String name, String password, boolean adult){
        this.name = name;
        this.password = password;
        this.adult = adult;
    }

    public void setAdministrator(){
        administrator = true;
    }

    public boolean isAdministrator(){
        return administrator;
    }
    
    public String getName(){
        return name;
    }
}