import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LetrehozasForm extends JFrame {

    // KANAPÉ ADATOK
    int szelesseg;
    int parnaszam;
    int labszam;
    Color kanapeszin;
    Color labszin;
    Color karfaszin;
    Color[] parnaszinek;

    // FORM
    JLabel lSzelesseg;
    JTextField tfSzelesseg;
    JLabel lParnaSzam;
    JTextField tfParnaSzam;
    JLabel lLabSzam;
    JTextField tfLabSzam;
    JLabel lKanapeSzin;
    Color cKanapeSzin;
    JLabel lLabSzin;
    Color cLabSzin;
    JLabel lKarfaSzin;
    Color cKarfaSzin;

    JButton buttonParnaSzinek;
    JPanel parnaColorPanel;

    public LetrehozasForm() {
        super("Kanapé létrehozás");
        setSize(400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridLayout grid = new GridLayout(0, 1);
        setLayout(grid);

        // Szélesség
        lSzelesseg = new JLabel("Szélesség");
        lSzelesseg.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lSzelesseg);

        tfSzelesseg = new JTextField();
        tfSzelesseg.setFont(new Font("Arial", Font.PLAIN, 15));
        add(tfSzelesseg);

        // Párnák száma
        lParnaSzam = new JLabel("Párnák száma");
        lParnaSzam.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lParnaSzam);

        tfParnaSzam = new JTextField();
        tfParnaSzam.setFont(new Font("Arial", Font.PLAIN, 15));
        add(tfParnaSzam);

        // Lábak száma
        lLabSzam = new JLabel("Lábak száma");
        lLabSzam.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lLabSzam);

        tfLabSzam = new JTextField();
        tfLabSzam.setFont(new Font("Arial", Font.PLAIN, 15));
        add(tfLabSzam);

        // Kanapé színe
        lKanapeSzin = new JLabel("Kanapé színe");
        lKanapeSzin.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lKanapeSzin);

        JButton buttonKanape = new JButton("Válassz színt");
        buttonKanape.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(
                    LetrehozasForm.this,
                    "Válassz egy színt",
                    cKanapeSzin
            );
            if (selectedColor != null) {
                cKanapeSzin = selectedColor;
                buttonKanape.setBackground(cKanapeSzin);
            }
        });
        add(buttonKanape);

        // Láb színe
        lLabSzin = new JLabel("Lábak színe");
        lLabSzin.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lLabSzin);

        JButton buttonLab = new JButton("Válassz színt");
        buttonLab.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(
                    LetrehozasForm.this,
                    "Válassz egy színt",
                    cLabSzin
            );
            if (selectedColor != null) {
                cLabSzin = selectedColor;
                buttonLab.setBackground(cLabSzin);
            }
        });
        add(buttonLab);

        // Karfa színe
        lKarfaSzin = new JLabel("Karfa színe");
        lKarfaSzin.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lKarfaSzin);

        JButton buttonKarfa = new JButton("Válassz színt");
        buttonKarfa.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(
                    LetrehozasForm.this,
                    "Válassz egy színt",
                    cKarfaSzin
            );
            if (selectedColor != null) {
                cKarfaSzin = selectedColor;
                buttonKarfa.setBackground(cKarfaSzin);
            }
        });
        add(buttonKarfa);

        // Párnaszín kiválasztó gomb
        buttonParnaSzinek = new JButton("Párnaszínek kiválasztása");
        buttonParnaSzinek.addActionListener(e -> {
            try {
                parnaszam = Integer.parseInt(tfParnaSzam.getText());
                parnaszinek = new Color[parnaszam];
                parnaColorPanel.removeAll();

                for (int i = 0; i < parnaszam; i++) {
                    final int index = i;
                    JButton colorButton = new JButton("Párna " + (i + 1) + " színe");
                    colorButton.addActionListener(ev -> {
                        Color selectedColor = JColorChooser.showDialog(
                                LetrehozasForm.this,
                                "Válassz színt a(z) " + (index + 1) + ". párnához",
                                Color.WHITE
                        );
                        if (selectedColor != null) {
                            parnaszinek[index] = selectedColor;
                            colorButton.setBackground(selectedColor);
                        }
                    });
                    parnaColorPanel.add(colorButton);
                }

                parnaColorPanel.revalidate();
                parnaColorPanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(LetrehozasForm.this, "Adj meg érvényes számot a párnák számához!", "Hiba", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(buttonParnaSzinek);

        // Panel, ahol a párnaszínek gombjai jelennek meg
        parnaColorPanel = new JPanel();
        parnaColorPanel.setLayout(new GridLayout(0, 1));
        add(parnaColorPanel);

        // Submit gomb
        JButton submit = new JButton("Kanapé Létrehozása");
        submit.addActionListener(e -> kanapeLetrehoz());
        add(submit);

        setVisible(true);
    }

    // Kanapé létrehozó metódus
    public Kanape kanapeLetrehoz() {
        try {
            szelesseg = Integer.parseInt(tfSzelesseg.getText());
            parnaszam = Integer.parseInt(tfParnaSzam.getText());
            labszam = Integer.parseInt(tfLabSzam.getText());

            kanapeszin = cKanapeSzin;
            labszin = cLabSzin;
            karfaszin = cKarfaSzin;

            // Ha nem választott párnaszíneket, állítsuk be alapértelmezésként a kanapé színét
            if (parnaszinek == null || parnaszinek.length != parnaszam) {
                parnaszinek = new Color[parnaszam];
                for (int i = 0; i < parnaszam; i++) {
                    parnaszinek[i] = kanapeszin;
                }
            }

            Kanape kanape = new Kanape(szelesseg, parnaszam, kanapeszin, labszam, labszin, karfaszin, parnaszinek);
            JOptionPane.showMessageDialog(this, "Kanapé létrehozva sikeresen!");
            return kanape;

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Kérlek számokat adj meg a méretekhez!", "Hiba", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
