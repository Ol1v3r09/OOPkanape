import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;

public class Elolnezet extends JPanel {
    Kanape kanape;

    public Elolnezet(Kanape k) {
        super();
        kanape = k;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gr = (Graphics2D) g.create();

        int kanapeX = (getWidth() - kanape.x) / 2;
        int kanapeY = (getHeight() - kanape.y) / 2;

        Font font = new Font(Font.DIALOG, Font.BOLD, 20);
        gr.setFont(font);
        gr.drawString("Elölnézet", kanapeX * 2, kanapeY / 2);

        //Háttámla előről
        gr.setColor(kanape.szin);
        gr.fillRoundRect(
                kanapeX,
                kanapeY,
                kanape.eHattamlaMeret.width,
                kanape.eHattamlaMeret.height,
                21,
                21
        );

        gr.setColor(Color.BLACK);
        gr.drawRoundRect(
                kanapeX,
                kanapeY,
                kanape.eHattamlaMeret.width,
                kanape.eHattamlaMeret.height,
                21,
                21
        );

        //Alsótámasz előlről
        gr.setColor(kanape.szin);
        gr.fillRoundRect(
                kanapeX,
                kanapeY + kanape.eHattamlaMeret.height - kanape.eAlsoTamaszMeret.height,
                kanape.eAlsoTamaszMeret.width,
                kanape.eAlsoTamaszMeret.height,
                10,10
        );

        gr.setColor(Color.BLACK);
        gr.drawRoundRect(
                kanapeX,
                kanapeY + kanape.eHattamlaMeret.height - kanape.eAlsoTamaszMeret.height,
                kanape.eAlsoTamaszMeret.width,
                kanape.eAlsoTamaszMeret.height,
                10,10
        );

        //Karfa előlről
        for (int i = 0; i < 2; i++) {
            /*int x = i == 0 ? kanapeX - karfaSzelesseg / 2 : kanapeX + kanape.meret.width - (karfaSzelesseg / 2);
            int y = (int) (kanapeY + kanape.meret.height * 0.4);*/
            gr.setColor(kanape.karfaSzin);
            if (i==0){
                int x = kanapeX - kanape.eKarfaMeret.width;
                int y = (int) (kanapeY + kanape.eHattamlaMeret.height * 0.4);
                gr.fillRoundRect(x, y, kanape.eKarfaMeret.width, kanape.eKarfaMeret.height, 10 ,10);
                gr.setColor(Color.BLACK);
                gr.drawRoundRect(x, y, kanape.eKarfaMeret.width, kanape.eKarfaMeret.height, 10 ,10);
            }
            else{
                int x = kanapeX + kanape.eHattamlaMeret.width;
                int y = (int) (kanapeY + kanape.eHattamlaMeret.height * 0.4);
                gr.fillRoundRect(x, y, kanape.eKarfaMeret.width, kanape.eKarfaMeret.height, 10 ,10);
                gr.setColor(Color.BLACK);
                gr.drawRoundRect(x, y, kanape.eKarfaMeret.width, kanape.eKarfaMeret.height, 10 ,10);
            }

        }

        //Lábak előlről
        int behuzas = 50;
        double resz = (double) (kanape.hHattamlaMeret.width - kanape.hLabMeret.width - behuzas) / (kanape.labSzam - 1);

        for (int i = 0; i < kanape.labSzam; i++) {
            gr.setColor(kanape.labSzin);
            int x = kanapeX + (int)(i * resz) + (behuzas / 2);
            int y = kanapeY + kanape.hHattamlaMeret.height;

            gr.fillRect(x, y, kanape.hLabMeret.width, kanape.hLabMeret.height);
            gr.setColor(Color.black);
            gr.drawRect(x, y, kanape.hLabMeret.width, kanape.hLabMeret.height);
        }

        //Párnák előlről
        for (int i = 0; i < kanape.parnaSzam; i++){
            gr.setColor(kanape.parnak[i].szin);
            gr.fillRoundRect(
                    kanapeX + (kanape.eParnaMeret.width * i),
                    kanapeY + kanape.eHattamlaMeret.height - kanape.eParnaMeret.height - kanape.eAlsoTamaszMeret.height,
                    kanape.eParnaMeret.width,
                    kanape.eParnaMeret.height,
                    10,
                    10
            );

            gr.setColor(Color.black);
            gr.drawRoundRect(
                    kanapeX + (kanape.eParnaMeret.width * i),
                    kanapeY + kanape.eHattamlaMeret.height - kanape.eParnaMeret.height - kanape.eAlsoTamaszMeret.height,
                    kanape.eParnaMeret.width,
                    kanape.eParnaMeret.height,
                    10,
                    10
            );
        }

    }
}