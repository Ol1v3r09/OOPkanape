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

        int kanapeX = (getWidth() - kanape.meret.width) / 2;
        int kanapeY = (getHeight() - kanape.meret.height) / 2;


        //Háttámla
        gr.setColor(kanape.szin);
        gr.fillRoundRect(
                kanapeX,
                kanapeY,
                kanape.hHattamlaMeret.width,
                kanape.hHattamlaMeret.height,
                21,
                21
        );

        //Lábak
        gr.setColor(kanape.labSzin);
        int behuzas = 50;
        double resz = (double) (kanape.hHattamlaMeret.width - kanape.hLabMeret.width - behuzas) / (kanape.labSzam - 1);

        for (int i = 0; i < kanape.labSzam; i++) {
            int x = kanapeX + (int)(i * resz) + (behuzas / 2);
            int y = kanapeY + kanape.hHattamlaMeret.height;

            gr.fillRect(x, y, kanape.hLabMeret.width, kanape.hLabMeret.height);
        }

        //Karfák
        gr.setColor(kanape.karfaSzin);
        for (int i = 0; i < 2; i++) {
            /*int x = i == 0 ? kanapeX - karfaSzelesseg / 2 : kanapeX + kanape.meret.width - (karfaSzelesseg / 2);
            int y = (int) (kanapeY + kanape.meret.height * 0.4);*/
            if (i==0){
                int x = kanapeX - kanape.hKarfaMeret.width;
                int y = (int) (kanapeY + kanape.hHattamlaMeret.height * 0.4);
                gr.fillRoundRect(x, y, kanape.hKarfaMeret.width, kanape.hKarfaMeret.height, 20 ,20);
            }
            else{
                int x = kanapeX + kanape.hHattamlaMeret.width;
                int y = (int) (kanapeY + kanape.hHattamlaMeret.height * 0.4);
                gr.fillRoundRect(x, y, kanape.hKarfaMeret.width, kanape.hKarfaMeret.height, 20 ,20);
            }

        }

        gr.dispose();
    }
}
