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

        int hattamlaMagassag = kanape.meret.width / 7;

        gr.setColor(kanape.szin);
        gr.fillRect(
                kanapeX,
                kanapeY + hattamlaMagassag,
                kanape.fAlsoTamaszMeret.width,
                kanape.fAlsoTamaszMeret.height
        );

        //Párnák
        for (int i = 0; i < kanape.parnaSzam; i++){
            gr.setColor(kanape.parnak[i].szin);
            gr.fillRoundRect(
                    kanapeX + (kanape.fParnaMeret.width * i),
                    kanapeY + kanape.fHattamlaMeret.height,
                    kanape.fParnaMeret.width,
                    kanape.fParnaMeret.height,
                    20,
                    20
            );
        }

        // Háttámla felűlről
        gr.setColor(kanape.szin);
        gr.fillRoundRect(
                kanapeX,
                kanapeY,
                kanape.fHattamlaMeret.width,
                kanape.fHattamlaMeret.height,
                20,
                20
        );

        //Karfák
        gr.setColor(kanape.karfaSzin);
        for (int i = 0; i < 2; i++) {
            /*int x = i == 0 ? kanapeX - karfaSzelesseg / 2 : kanapeX + kanape.meret.width - (karfaSzelesseg / 2);
            int y = (int) (kanapeY + kanape.meret.height * 0.4);*/
            if (i==0){
                int x = kanapeX - kanape.fKarfaMeret.width;
                int y = kanapeY + kanape.fHattamlaMeret.height;
                gr.fillRoundRect(x, y, kanape.fKarfaMeret.width, kanape.fKarfaMeret.height, 20 ,20);
            }
            else{
                int x = kanapeX + kanape.fAlsoTamaszMeret.width;
                int y = kanapeY + kanape.fHattamlaMeret.height;
                gr.fillRoundRect(x, y, kanape.fKarfaMeret.width, kanape.fKarfaMeret.height, 20 ,20);
            }

        }

        gr.dispose();




    }
}