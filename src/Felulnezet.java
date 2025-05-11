import javax.swing.*;
import java.awt.*;


public class Felulnezet extends JPanel {
    Kanape kanape;

    public Felulnezet(Kanape k) {
        super();
        kanape = k;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g.create();

        int kanapeX = (getWidth() - kanape.meret.width) / 2;
        int kanapeY = (getHeight() - kanape.meret.height) / 2;

        // Háttámla felűlről
        gr.setColor(kanape.szin);
        gr.fillRoundRect(
                kanapeX,
                kanapeY,
                kanape.meret.width,
                kanape.meret.height/ 5,
                21,
                21
        );

        gr.dispose();




    }
}