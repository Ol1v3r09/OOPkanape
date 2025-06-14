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
        frame = new JFrame();
        menu = new Menu();
        kanape = new Kanape();

        frame.setSize(1300, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setJMenuBar(menu.GetMenuBar());
        frame.setLayout(new GridLayout(2,2));

        elo = new Elolnezet(kanape);
        old = new Oldalnezet(kanape);
        hat = new Hatsonezet(kanape);
        fel = new Felulnezet(kanape);
    }

    public static void main (String args[]) {
        Init();
        frame.setVisible(true);

        frame.add(elo);
        frame.add(old);
        frame.add(hat);
        frame.add(fel);
    }

    static class Menu extends JMenuBar{
        JMenuBar mb;

        int szelesseg;
        int parnaszam;
        int labszam;
        Color kanapeszin;
        Color labszin;
        Color karfaszin;
        Color[] parnaszinek;

        JTextField tfSzelesseg;
        JTextField tfParnaSzam;
        JTextField tfLabSzam;

        Color cKanapeSzin;
        Color cLabSzin;
        Color cKarfaSzin;

        JPanel parnaDropdownPanel;
        boolean parnaDropdownVisible = false;

        public Menu(){
            super();
            setSize(900, 600);
            setLayout(new BorderLayout());

            mb = new JMenuBar();

            mb.add(new JLabel("Szélesség:"));
            tfSzelesseg = new JTextField();
            mb.add(tfSzelesseg);

            mb.add(new JLabel("Párnák száma:"));
            tfParnaSzam = new JTextField();
            mb.add(tfParnaSzam);

            mb.add(new JLabel("Lábak száma:"));
            tfLabSzam = new JTextField();
            mb.add(tfLabSzam);

            mb.add(new JLabel("Kanapé színe:"));
            JButton btnKanapeSzin = new JButton("Válassz színt");
            btnKanapeSzin.addActionListener(e -> {
                Color c = JColorChooser.showDialog(this, "Kanapé színe", cKanapeSzin);
                if (c != null) {
                    cKanapeSzin = c;
                    btnKanapeSzin.setBackground(c);
                }
            });
            mb.add(btnKanapeSzin);

            mb.add(new JLabel("Lábak színe:"));
            JButton btnLabSzin = new JButton("Válassz színt");
            btnLabSzin.addActionListener(e -> {
                Color c = JColorChooser.showDialog(this, "Lábak színe", cLabSzin);
                if (c != null) {
                    cLabSzin = c;
                    btnLabSzin.setBackground(c);
                }
            });
            mb.add(btnLabSzin);

            mb.add(new JLabel("Karfa színe:"));
            JButton btnKarfaSzin = new JButton("Válassz színt");
            btnKarfaSzin.addActionListener(e -> {
                Color c = JColorChooser.showDialog(this, "Karfa színe", cKarfaSzin);
                if (c != null) {
                    cKarfaSzin = c;
                    btnKarfaSzin.setBackground(c);
                }
            });
            mb.add(btnKarfaSzin);

            JButton btnParnaSzinek = new JButton("Párnaszínek kiválasztása");
            mb.add(btnParnaSzinek);

            parnaDropdownPanel = new JPanel();
            parnaDropdownPanel.setLayout(new GridLayout(0, 1));
            parnaDropdownPanel.setVisible(false);
            mb.add(parnaDropdownPanel);

            btnParnaSzinek.addActionListener(e -> {
                if (!parnaDropdownVisible) {
                    try {
                        int parnaSzam = Integer.parseInt(tfParnaSzam.getText());
                        parnaszinek = new Color[parnaSzam];
                        parnaDropdownPanel.removeAll();

                        for (int i = 0; i < parnaSzam; i++) {
                            final int index = i;
                            JButton btn = new JButton("Párna " + (i + 1));
                            btn.addActionListener(ev -> {
                                Color chosen = JColorChooser.showDialog(this, "Párna " + (index + 1) + " színe", Color.WHITE);
                                if (chosen != null) {
                                    parnaszinek[index] = chosen;
                                    btn.setBackground(chosen);
                                }
                            });
                            parnaDropdownPanel.add(btn);
                        }

                        parnaDropdownPanel.setVisible(true);
                        parnaDropdownVisible = true;

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Hibás szám a párnák számánál!", "Hiba", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    parnaDropdownPanel.setVisible(false);
                    parnaDropdownVisible = false;
                }

                parnaDropdownPanel.revalidate();
                parnaDropdownPanel.repaint();
            });

            JButton btnApply = new JButton("Kanapé létrehozása / frissítése");
            btnApply.addActionListener(e -> {
                updateKanape();
            });
            mb.add(btnApply);

            setVisible(true);
        }

        private void updateKanape() {
            try {
                szelesseg = Integer.parseInt(tfSzelesseg.getText());
                parnaszam = Integer.parseInt(tfParnaSzam.getText());
                labszam = Integer.parseInt(tfLabSzam.getText());

                if (parnaszinek == null || parnaszinek.length != parnaszam) {
                    parnaszinek = new Color[parnaszam];
                    for (int i = 0; i < parnaszam; i++) {
                        parnaszinek[i] = cKanapeSzin != null ? cKanapeSzin : Color.GRAY;
                    }
                }

                kanape = new Kanape(szelesseg, parnaszam, cKanapeSzin, labszam, cLabSzin, cKarfaSzin, parnaszinek);

                frame.remove(elo);
                frame.remove(old);
                frame.remove(hat);
                frame.remove(fel);

                elo = new Elolnezet(kanape);
                old = new Oldalnezet(kanape);
                hat = new Hatsonezet(kanape);
                fel = new Felulnezet(kanape);

                frame.add(elo);
                frame.add(old);
                frame.add(hat);
                frame.add(fel);

                this.revalidate();
                this.repaint();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Kérlek számokat adj meg!", "Hiba", JOptionPane.ERROR_MESSAGE);
            }
        }

        public JMenuBar GetMenuBar(){
            return mb;
        }
    }
}
