import javax.swing.*;

class Menu extends JMenuBar{
    JMenuBar mb;
    JMenuItem miSzin;
    JMenuItem miMeret;
    JMenuItem miSzelesseg, miMagassag;

    public Menu(){
        super();
        setSize(1300,150);
        mb=new JMenuBar();
        mb.add(miSzin=new JMenu("Szín"));
        mb.add(miMeret=new JMenu("Méret"));
        miMeret.add(miSzelesseg = new JMenuItem("Szélesség"));
        miMeret.add(miMagassag = new JMenuItem("Magasság"));
        setVisible(true);
    }

    public JMenuBar GetMenuBar(){
        return mb;
    }
}
