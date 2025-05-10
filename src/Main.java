import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main (String args[]) {
        JFrame frame = new JFrame();
        frame.setSize(1300, 800);

        Menu menu = new Menu();
        frame.setJMenuBar(menu.GetMenuBar());

        Container cp = frame.getContentPane();
        GridLayout grid =  new GridLayout(2,2);
        cp.setLayout(grid);

        Kanape kanape = new Kanape();

        Hatsonezet hat = new Hatsonezet(kanape);
        cp.add(hat);

        Oldalnezet old = new Oldalnezet(kanape);
        cp.add(old);

        Felulnezet fel = new Felulnezet(kanape);
        cp.add(fel);

        frame.setVisible(true);
    }
}