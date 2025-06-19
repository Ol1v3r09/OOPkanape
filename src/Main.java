import javax.swing.*;
import java.awt.*;

public class Main {
    static JFrame frame;
    static Menu menu;
    static Kanape kanape;

    static Elolnezet elo;
    static Oldalnezet old;
    static Hatsonezet hat;
    static Felulnezet fel;

    public static void Init(){
        frame = new JFrame("Kanapé Tervező");
        frame.setSize(1300, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(2, 2));

        kanape = new Kanape();
        menu = new Menu(frame, kanape, Main::updateKanape, Main::randomKanape);
        frame.setJMenuBar(menu.GetMenuBar());
    }

    public static void main(String[] args) {
        Init();
        updateKanape();
        frame.setVisible(true);
    }

    public static void updateKanape(){
        kanape.MeretekBeallitasa();
        if (frame.getContentPane().getComponentCount() != 0){
            frame.remove(elo);
            frame.remove(old);
            frame.remove(hat);
            frame.remove(fel);
        }

        elo = new Elolnezet(kanape);
        old = new Oldalnezet(kanape);
        hat = new Hatsonezet(kanape);
        fel = new Felulnezet(kanape);

        frame.add(elo);
        frame.add(old);
        frame.add(hat);
        frame.add(fel);

        frame.revalidate();
        frame.repaint();

        menu.setKanape(kanape);
    }

    public static void randomKanape(){
        kanape = new Kanape();
        menu.setKanape(kanape);
        updateKanape();
    }
}
