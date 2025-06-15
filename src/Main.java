import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
        menu = new Menu();
        frame.setJMenuBar(menu.GetMenuBar());
    }

    public static void main(String[] args) {
        Init();
        updateKanape();
        frame.setVisible(true);
    }

    public static void updateKanape(){
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
    }

    static class Menu {
        private JMenuBar menuBar;
        private JPanel formPanel;
        private JPanel parnaPanel;

        public Menu() {
            menuBar = new JMenuBar();

            JMenu fileMenu = new JMenu("Fájl");
            JMenu kanapeMenu = new JMenu("Kanapé");

            JMenuItem openSofaDialog = new JMenuItem("Beállítások megnyitása");
            openSofaDialog.addActionListener(e -> showKanapeDialog());

            formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
            parnaPanel = new JPanel(new GridLayout(1,kanape.parnaSzam));

            kanapeMenu.add(openSofaDialog);
            menuBar.add(fileMenu);
            menuBar.add(kanapeMenu);
        }

        public JMenuBar GetMenuBar() {
            return menuBar;
        }

        private void showKanapeDialog() {
            formPanel.removeAll();
            parnaPanel.removeAll();

            JDialog dialog = new JDialog(frame, "Kanapé beállítások", true);
            dialog.setSize(600, 400);
            dialog.setLayout(new BorderLayout());

            JTextField tfSzelesseg = new JTextField(String.format("%d", kanape.x));
            JTextField tfParnaSzam = new JTextField(String.format("%d", kanape.parnaSzam));
            tfParnaSzam.getDocument().addDocumentListener(new DocumentListener() {
                public void changedUpdate(DocumentEvent e) { updateParnaPickers(tfParnaSzam.getText()); }
                public void removeUpdate(DocumentEvent e) { updateParnaPickers(tfParnaSzam.getText()); }
                public void insertUpdate(DocumentEvent e) { updateParnaPickers(tfParnaSzam.getText()); }
            });
            JTextField tfLabSzam = new JTextField(String.format("%d", kanape.labSzam));

            JButton btnKanapeSzin = new JButton("Válassz");
            JButton btnLabSzin = new JButton("Válassz");
            JButton btnKarfaSzin = new JButton("Válassz");

            final Color[] kanapeSzin = {kanape.szin};
            btnKanapeSzin.setBackground(kanapeSzin[0]);
            final Color[] labSzin = {kanape.labSzin};
            btnLabSzin.setBackground(labSzin[0]);
            final Color[] karfaSzin = {kanape.karfaSzin};
            btnKarfaSzin.setBackground(karfaSzin[0]);

            btnKanapeSzin.addActionListener(e -> {
                Color c = JColorChooser.showDialog(dialog, "Kanapé színe", kanapeSzin[0]);
                if (c != null) {
                    kanapeSzin[0] = c;
                    btnKanapeSzin.setBackground(c);
                }
            });

            btnLabSzin.addActionListener(e -> {
                Color c = JColorChooser.showDialog(dialog, "Lábak színe", labSzin[0]);
                if (c != null) {
                    labSzin[0] = c;
                    btnLabSzin.setBackground(c);
                }
            });

            btnKarfaSzin.addActionListener(e -> {
                Color c = JColorChooser.showDialog(dialog, "Karfa színe", karfaSzin[0]);
                if (c != null) {
                    karfaSzin[0] = c;
                    btnKarfaSzin.setBackground(c);
                }
            });

            formPanel.add(new JLabel("Szélesség:"));
            formPanel.add(tfSzelesseg);
            formPanel.add(new JLabel("Párnák száma (max 5):"));
            formPanel.add(tfParnaSzam);
            formPanel.add(new JLabel("Lábak száma:"));
            formPanel.add(tfLabSzam);
            formPanel.add(new JLabel("Kanapé színe:"));
            formPanel.add(btnKanapeSzin);
            formPanel.add(new JLabel("Lábak színe:"));
            formPanel.add(btnLabSzin);
            formPanel.add(new JLabel("Karfa színe:"));
            formPanel.add(btnKarfaSzin);
            formPanel.add(new JLabel("Párnaszínek:"));

            updateParnaPickers(String.format("%d", kanape.parnaSzam));
            formPanel.add(parnaPanel, BorderLayout.WEST);

            dialog.add(formPanel, BorderLayout.CENTER);

            JButton btnCreate = new JButton("Kanapé létrehozása");
            btnCreate.addActionListener(e -> {
                try {
                    int sz = Integer.parseInt(tfSzelesseg.getText());
                    int pn = Integer.parseInt(tfParnaSzam.getText());
                    int lb = Integer.parseInt(tfLabSzam.getText());
                    Color[] pColors = new Color[pn];
                    for (int i = 0; i < pn; i++) {
                        pColors[i] = kanape.parnaSzinek[i] != null ? kanape.parnaSzinek[i] : kanapeSzin[0];
                    }
                    kanape = new Kanape(sz, pn, kanapeSzin[0], lb, labSzin[0], karfaSzin[0], pColors);

                    updateKanape();
                    dialog.dispose();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Hibás vagy hiányzó érték!", "Hiba", JOptionPane.ERROR_MESSAGE);
                }
            });

            dialog.add(btnCreate, BorderLayout.SOUTH);
            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
        }

        private void updateParnaPickers(String value) {
            try {
                int n = Math.min(Integer.parseInt(value), 5);
                parnaPanel.removeAll();
                parnaPanel.setLayout(new GridLayout(1, n));

                for (int i = 0; i < n; i++) {
                    int idx = i;
                    Color currentColor = (kanape.parnaSzinek != null && idx < kanape.parnaSzinek.length)
                            ? kanape.parnaSzinek[idx]
                            : Color.WHITE;

                    JButton btn = new JButton(String.valueOf(i + 1));
                    btn.setBackground(currentColor);

                    btn.addActionListener(ev -> {
                        Color c = JColorChooser.showDialog(frame, "Párna színe", currentColor);
                        if (c != null) {
                            kanape.parnaSzinek[idx] = c;
                            btn.setBackground(c);
                        }
                    });

                    parnaPanel.add(btn);
                }

                parnaPanel.revalidate();
                parnaPanel.repaint();
            } catch (NumberFormatException ex) {
                parnaPanel.removeAll();
                parnaPanel.revalidate();
                parnaPanel.repaint();
            }
        }


    }
}
