import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

public class ImageViewer extends JFrame{
    private JFrame frame;

    public ImageViewer(){
        BufferedImage img = null;
        JPanel panel = new JPanel();
        setVisible(true);
        try{
            img = ImageIO.read(new File("C:\\Users\\Niels\\OneDrive\\Desktop\\Projects\\StreamService\\12 Angry Men.jpg"));
            ImageComponent ic = new ImageComponent(img);
            panel.add(ic);
            add(panel, BorderLayout.CENTER);
            //revalidate();
            //repaint();
        } catch(IOException e){
            
        }
        /*setSize(1000,900);
        setTitle("Hello World");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        JLabel helloWorld = new JLabel("helloWorld");
        ImageIcon image = new ImageIcon("C:\\Users\\Niels\\OneDrive\\Desktop\\Projects\\StreamService\\12 Angry Men.jpg");
        getContentPane().add(helloWorld);


        JLabel label = new JLabel("", image, JLabel.CENTER);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add( label, BorderLayout.CENTER );*/

        //makeFrame();
    }

    private void makeFrame(){
        frame = new JFrame("ImageViewer");
        Container contentPane = frame.getContentPane();

        JLabel label = new JLabel("I am a label. I can display some text");
        contentPane.add(label);

        JLabel label2 = new JLabel();

        frame.pack();
        frame.setVisible(true);
    }
}