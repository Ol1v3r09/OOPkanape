import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main (String args[]) {
        JFrame frame = new JFrame();
        frame.setSize(1300, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Menu menu = new Menu();
        frame.setJMenuBar(menu.GetMenuBar());

        GridLayout grid =  new GridLayout(2,2);
        frame.setLayout(grid);

        Kanape kanape = new Kanape();

        Hatsonezet hat = new Hatsonezet(kanape);
        frame.add(hat);

        Oldalnezet old = new Oldalnezet(kanape);
        frame.add(old);

        Felulnezet fel = new Felulnezet(kanape);
        frame.add(fel);

        Elolnezet elo = new Elolnezet(kanape);
        frame.add(elo);

        frame.setVisible(true);
    }
}