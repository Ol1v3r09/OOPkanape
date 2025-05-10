import javax.swing.*;
import java.awt.*;

public class Hatsonezet extends JPanel {
    Kanape kanape;
    public Hatsonezet(Kanape k){
        super();
        kanape = k;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g.create();

        Dimension size = getSize();
        int kanapeX = (int)((double) size.width * 0.05);
        int kanapeY = (int)((double) size.height * 0.10);


        //Háttámla
        gr.setColor(kanape.szin);
        gr.fillRect(
                kanapeX,
                kanapeY,
                kanape.meret.width,
                kanape.meret.height
        );

        //Lábak
        gr.setColor(kanape.labSzin);
        int labSzelesseg = 20;
        int labMagassag = 30;
        int behuzas = 50;
        double resz = (double) (kanape.meret.width - labSzelesseg - behuzas) / (kanape.labSzam - 1);

        for (int i = 0; i < kanape.labSzam; i++) {
            int x = kanapeX + (int)(i * resz) + (behuzas / 2);
            int y = kanapeY + kanape.meret.height;

            gr.fillRect(x, y, labSzelesseg, labMagassag);
        }

        gr.dispose();
    }
}
