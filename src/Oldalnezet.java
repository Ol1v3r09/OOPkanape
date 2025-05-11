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

        int kanapeX = (getWidth() - kanape.oAlsoTamaszMeret.width) / 2;
        int kanapeY = (getHeight() - kanape.meret.height) / 2;


        // Háttámla oldalról
        gr.setColor(kanape.szin);
        gr.fillRoundRect(
                kanapeX,
                kanapeY,
                kanape.oHattamlaMeret.width,
                kanape.oHattamlaMeret.height,
                10,10
        );


        // Alsó támasz
        gr.setColor(kanape.szin);
        gr.fillRoundRect(
                kanapeX,
                kanapeY + kanape.oHattamlaMeret.height,
                kanape.oAlsoTamaszMeret.width,
                kanape.oAlsoTamaszMeret.height,
                10,10
        );


        // Hátsó lab
        gr.setColor(kanape.labSzin);
        gr.fillRect(
                kanapeX + 25,
                kanapeY + kanape.oHattamlaMeret.height + kanape.oAlsoTamaszMeret.height,
                kanape.hLabMeret.width,
                kanape.hLabMeret.height
        );


        // Első láb
        gr.setColor(kanape.labSzin);
        gr.fillRect(
                kanapeX + kanape.oAlsoTamaszMeret.width - 50,
                kanapeY + kanape.oHattamlaMeret.height + kanape.oAlsoTamaszMeret.height,
                kanape.hLabMeret.width,
                kanape.hLabMeret.height
        );


        // Karfa oldalról
        gr.setColor(kanape.karfaSzin);
        gr.fillRoundRect(
                kanapeX,
                kanapeY + (kanape.oHattamlaMeret.height / 2),
                kanape.oKarfaMeret.width,
                kanape.oKarfaMeret.height,
                30,
                15
        );
        gr.dispose();
    }
}

