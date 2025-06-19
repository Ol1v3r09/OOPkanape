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

        int kanapeX = (getWidth() / 2) - (kanape.x / 2);
        int kanapeY = (getHeight()) / 2 - (kanape.y / 2);

        Font font = new Font(Font.DIALOG, Font.BOLD, 20);
        gr.setFont(font);
        gr.drawString("Felülnézet", (getSize().width / 2) - 80, getSize().height / 4);

        //Alsó Támasz felülről
        gr.setColor(kanape.szin);
        gr.fillRoundRect(
                kanapeX,
                kanapeY,
                kanape.fAlsoTamaszMeret.width,
                kanape.fAlsoTamaszMeret.height,
                5,5
        );

        gr.setColor(Color.BLACK);
        gr.drawRoundRect(
                kanapeX,
                kanapeY,
                kanape.fAlsoTamaszMeret.width,
                kanape.fAlsoTamaszMeret.height,
                5, 5
        );

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

        gr.setColor(Color.BLACK);
        gr.drawRoundRect(
                kanapeX,
                kanapeY,
                kanape.fHattamlaMeret.width,
                kanape.fHattamlaMeret.height,
                20,
                20
        );

        //Párnák
        for (int i = 0; i < kanape.parnaSzam; i++){
            gr.setColor(kanape.parnaSzinek[i]);
            gr.fillRoundRect(
                    kanapeX + (kanape.fParnaMeret.width * i),
                    kanapeY + kanape.fHattamlaMeret.height,
                    kanape.fParnaMeret.width,
                    kanape.fParnaMeret.height,
                    30,
                    13
            );

            gr.setColor(Color.black);
            gr.drawRoundRect(
                    kanapeX + (kanape.fParnaMeret.width * i),
                    kanapeY + kanape.fHattamlaMeret.height,
                    kanape.fParnaMeret.width,
                    kanape.fParnaMeret.height,
                    30,
                    13
            );

        }

        //Karfák
        for (int i = 0; i < 2; i++) {
            gr.setColor(kanape.karfaSzin);
            /*int x = i == 0 ? kanapeX - karfaSzelesseg / 2 : kanapeX + kanape.meret.width - (karfaSzelesseg / 2);
            int y = (int) (kanapeY + kanape.meret.height * 0.4);*/
            if (i==0){
                int x = kanapeX - kanape.fKarfaMeret.width;
                int y = kanapeY + kanape.fHattamlaMeret.height;
                gr.fillRoundRect(x, y, kanape.fKarfaMeret.width, kanape.fKarfaMeret.height, 10 ,10);
                gr.setColor(Color.BLACK);
                gr.drawRoundRect(x, y, kanape.fKarfaMeret.width, kanape.fKarfaMeret.height, 10 ,10);

            }
            else{
                int x = kanapeX + kanape.fAlsoTamaszMeret.width;
                int y = kanapeY + kanape.fHattamlaMeret.height;
                gr.fillRoundRect(x, y, kanape.fKarfaMeret.width, kanape.fKarfaMeret.height, 10 ,10);
                gr.setColor(Color.BLACK);
                gr.drawRoundRect(x, y, kanape.fKarfaMeret.width, kanape.fKarfaMeret.height, 10 ,10);
            }

        }

        gr.dispose();




    }
}