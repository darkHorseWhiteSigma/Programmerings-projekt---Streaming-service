import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
public class ImageComponent extends JComponent{
    private BufferedImage img;
    
    public ImageComponent(BufferedImage img){
        this.img = img;
    }
    
    public void paint(Graphics g){
        g.drawImage(img,0,0,null);
    }
    
    public Dimension getPreferredSize(){
        if(img == null){
            return new Dimension(100,100);
        }
        else{
            return new Dimension(img.getWidth(),img.getHeight());
        }
    }
}