import javax.swing.*;
import java.awt.*;


public class Oldalnezet extends JPanel {
    Kanape kanape;
    public Oldalnezet(Kanape k){
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


        // Háttámla oldalról
        gr.setColor(kanape.szin);
        gr.fillRoundRect(
                kanapeX,
                kanapeY,
                kanape.meret.width / 7,
                kanape.meret.height + 10,
                11,11
        );


        // Alsó támasz
        gr.setColor(kanape.szin);
        int tamaszMagassag = kanape.meret.height / 5;
        int tamaszSzelesseg = kanape.meret.width / 2;
        gr.fillRoundRect(
                kanapeX,
                kanapeY + kanape.meret.height,
                tamaszSzelesseg,
                tamaszMagassag,
                10,10
        );


        // Hátsó lab
        gr.setColor(kanape.labSzin);
        int labSzelesseg = 20;
        int labMagassag = 30;
        gr.fillRect(
                kanapeX + 20,
                kanapeY + kanape.meret.height + tamaszMagassag,
                labSzelesseg,
                labMagassag
        );


        // Első láb
        gr.setColor(kanape.labSzin);
        gr.fillRect(
                kanapeX + tamaszSzelesseg - 40,
                kanapeY + kanape.meret.height + tamaszMagassag,
                labSzelesseg,
                labMagassag
        );


        // Karfa oldalról
        gr.setColor(kanape.karfaSzin);
        int karfaMagassag = kanape.meret.height / 2;
        gr.fillRoundRect(
                kanapeX + 5,
                kanapeY + (kanape.meret.height / 2 )+ 10,
                (kanape.meret.width / 2) - 8,
                karfaMagassag,
                40,
                20
        );
    }
}

