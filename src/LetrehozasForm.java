import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LetrehozasForm extends JFrame{
    int szelesseg;
    int parnaszam;
    int labszam;
    Color kanapeszin;
    Color labszin;
    Color karfaszin;
    Color[] parnaszinek;

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


    public LetrehozasForm() {
        super();
        setSize(400, 600);

        GridLayout grid = new GridLayout(14,1);
        setLayout(grid);

        //Szelesseg

        lSzelesseg = new JLabel("Szélesség");
        lSzelesseg.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lSzelesseg);

        tfSzelesseg = new JTextField();
        tfSzelesseg.setFont(new Font("Arial", Font.PLAIN, 15));
        add(tfSzelesseg);

        //ParnaSzam

        lParnaSzam = new JLabel("Párnák száma");
        lParnaSzam.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lParnaSzam);

        tfParnaSzam = new JTextField();
        tfParnaSzam.setFont(new Font("Arial", Font.PLAIN, 15));
        add(tfParnaSzam);

        //LabSzam

        lLabSzam = new JLabel("Lábak száma");
        lLabSzam.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lLabSzam);

        tfLabSzam = new JTextField();
        tfLabSzam.setFont(new Font("Arial", Font.PLAIN, 15));
        add(tfLabSzam);

        //KanapeSzin

        lKanapeSzin = new JLabel("Kanapé színe");
        lKanapeSzin.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lKanapeSzin);

        JButton buttonKanape = new JButton("Válassz színt");
        buttonKanape.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(
                        LetrehozasForm.this,
                        "Válassz egy színt",
                        cKanapeSzin
                );

                if (selectedColor != null) {
                    cKanapeSzin = selectedColor;
                    buttonKanape.setBackground(cKanapeSzin);
                }
            }
        });
        add(buttonKanape);

        //LabSzin

        lLabSzin = new JLabel("Lábak színe");
        lLabSzin.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lLabSzin);

        JButton buttonLab = new JButton("Válassz színt");
        buttonLab.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(
                        LetrehozasForm.this,
                        "Válassz egy színt",
                        cLabSzin
                );

                if (selectedColor != null) {
                    cLabSzin = selectedColor;
                    buttonLab.setBackground(cLabSzin);
                }
            }
        });
        add(buttonLab);

        //KarfaSzin

        lKarfaSzin = new JLabel("Karfa színe");
        lKarfaSzin.setFont(new Font("Arial", Font.PLAIN, 20));
        add(lKarfaSzin);

        JButton buttonKarfa = new JButton("Válassz színt");
        buttonKarfa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(
                        LetrehozasForm.this,
                        "Válassz egy színt",
                        cKarfaSzin
                );

                if (selectedColor != null) {
                    cKarfaSzin = selectedColor;
                    buttonKarfa.setBackground(cKarfaSzin);
                }
            }
        });
        add(buttonKarfa);


        setVisible(true);
    }
}
