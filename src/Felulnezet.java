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

        Dimension size = getSize();
        int kanapeX = (int) ((double) size.width * 0.05);
        int kanapeY = (int) ((double) size.height * 0.10);

        // Háttámla felűlről
        gr.setColor(kanape.szin);
        gr.fillRoundRect(
                kanapeX,
                kanapeY,
                kanape.meret.width,
                kanape.meret.height/ 5,
                20,
                20
        );






    }
}