import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.*;

public class Menu {
    private JMenuBar menuBar;
    private JPanel formPanel;
    private JPanel parnaPanel;

    private JFrame frame;
    private Kanape kanape;
    private Runnable updateKanape;
    private Runnable randomKanape;

    public Menu(JFrame frame, Kanape kanape, Runnable updateKanape, Runnable randomKanape) {
        this.frame = frame;
        this.kanape = kanape;
        this.updateKanape = updateKanape;
        this.randomKanape = randomKanape;

        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Fájl");
        JMenuItem mentes = new JMenuItem("Mentés");
        mentes.addActionListener(e -> kanapeMentes());
        JMenuItem betoltes = new JMenuItem("Betöltés");
        betoltes.addActionListener(e -> kanapeBetoltes());

        JMenu kanapeMenu = new JMenu("Kanapé");
        JMenuItem randomGeneral = new JMenuItem("Generálás");
        randomGeneral.addActionListener(e -> this.randomKanape.run());
        JMenuItem openSofaDialog = new JMenuItem("Beállítások megnyitása");
        openSofaDialog.addActionListener(e -> showKanapeDialog());

        formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        parnaPanel = new JPanel(new GridLayout(1, kanape.parnaSzam));


        fileMenu.add(mentes);
        fileMenu.add(betoltes);
        kanapeMenu.add(openSofaDialog);
        kanapeMenu.add(randomGeneral);
        menuBar.add(fileMenu);
        menuBar.add(kanapeMenu);
    }

    public JMenuBar GetMenuBar() {
        return menuBar;
    }

    public void setKanape(Kanape k){
        kanape = k;
    }
    private void showKanapeDialog() {
        formPanel.removeAll();
        parnaPanel.removeAll();

        JDialog dialog = new JDialog(frame, "Kanapé beállítások", true);
        dialog.setSize(600, 400);
        dialog.setLayout(new BorderLayout());

        JTextField tfSzelesseg = new JTextField(String.format("%d", kanape.x));
        tfSzelesseg.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                try {
                    int pn = Integer.parseInt(tfSzelesseg.getText());
                    pn = Math.max(Kanape.X_MIN, Math.min(Kanape.X_MAX, pn));
                    tfSzelesseg.setText(String.valueOf(pn));
                } catch (NumberFormatException ex) {
                    tfSzelesseg.setText("200");
                }
            }
        });
        tfSzelesseg.getDocument().addDocumentListener(new DocumentListener() {
            private void checkLength() {
                if (tfSzelesseg.getText().length() == 3) {
                    tfSzelesseg.transferFocus();
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkLength();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                checkLength();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkLength();
            }
        });

        JTextField tfParnaSzam = new JTextField(String.format("%d", kanape.parnaSzam));
        tfParnaSzam.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                try {
                    int pn = Integer.parseInt(tfParnaSzam.getText());
                    pn = Math.max(Kanape.PARNA_MIN, Math.min(Kanape.PARNA_MAX, pn));
                    tfParnaSzam.setText(String.valueOf(pn));

                    updateParnaPickers(pn);
                } catch (NumberFormatException ex) {
                    tfParnaSzam.setText(String.format("%d", Kanape.PARNA_MIN));
                    updateParnaPickers(Kanape.PARNA_MIN);
                }
            }
        });
        tfParnaSzam.getDocument().addDocumentListener(new DocumentListener() {
            private void checkLength() {
                if (!tfParnaSzam.getText().isEmpty()) {
                    tfParnaSzam.transferFocus();
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkLength();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                checkLength();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkLength();
            }
        });


        JTextField tfLabSzam = new JTextField(String.format("%d", kanape.labSzam));
        tfLabSzam.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                try {
                    int lb = Integer.parseInt(tfLabSzam.getText());
                    lb = Math.max(Kanape.LAB_MIN, Math.min(Kanape.LAB_MAX, lb));
                    tfLabSzam.setText(String.valueOf(lb));
                } catch (NumberFormatException ex) {
                    tfLabSzam.setText(String.format("%d", Kanape.LAB_MIN));
                }
            }
        });
        tfLabSzam.getDocument().addDocumentListener(new DocumentListener() {
            private void checkLength() {
                if (!tfLabSzam.getText().isEmpty()) {
                    tfLabSzam.transferFocus();
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkLength();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                checkLength();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                checkLength();
            }
        });

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

        updateParnaPickers(kanape.parnaSzam);
        formPanel.add(parnaPanel, BorderLayout.WEST);

        dialog.add(formPanel, BorderLayout.CENTER);

        JButton btnCreate = new JButton("Kanapé létrehozása");
        btnCreate.addActionListener(e -> {
            try {
                int sz;
                try {
                    sz = Integer.parseInt(tfSzelesseg.getText());
                    if (sz < Kanape.X_MIN) sz = Kanape.X_MIN;
                    if (sz > Kanape.X_MAX) sz = Kanape.X_MAX;
                    tfSzelesseg.setText(String.valueOf(sz));
                } catch (NumberFormatException ex) {
                    sz = Kanape.X_MIN;
                    tfSzelesseg.setText(String.valueOf(sz));
                }

                int pn;
                try {
                    pn = Integer.parseInt(tfParnaSzam.getText());
                    if (pn < Kanape.PARNA_MIN) pn = Kanape.PARNA_MIN;
                    if (pn > Kanape.PARNA_MAX) pn = Kanape.PARNA_MAX;
                    tfParnaSzam.setText(String.valueOf(pn));
                } catch (NumberFormatException ex) {
                    pn = Kanape.PARNA_MIN;
                    tfParnaSzam.setText(String.valueOf(pn));
                }

                int lb;
                try {
                    lb = Integer.parseInt(tfLabSzam.getText());
                    if (lb < Kanape.LAB_MIN) lb = Kanape.LAB_MIN;
                    if (lb > Kanape.LAB_MAX) lb = Kanape.LAB_MAX;
                    tfLabSzam.setText(String.valueOf(pn));
                } catch (NumberFormatException ex) {
                    lb = Kanape.LAB_MIN;
                    tfLabSzam.setText(String.valueOf(pn));
                }
                kanape.x = sz;
                kanape.parnaSzam = pn;
                kanape.szin = kanapeSzin[0];
                kanape.labSzam = lb;
                kanape.labSzin = labSzin[0];
                kanape.karfaSzin = karfaSzin[0];

                updateKanape.run();
                dialog.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Hibás vagy hiányzó érték!", "Hiba", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(btnCreate, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    private void updateParnaPickers(int value) {
        try {
            int n = Math.min(value, Kanape.PARNA_MAX);
            parnaPanel.removeAll();
            parnaPanel.setLayout(new GridLayout(1, n));

            if (kanape.parnaSzinek == null || kanape.parnaSzinek.length != n) {
                Color[] newColors = new Color[n];
                for (int i = 0; i < n; i++) {
                    newColors[i] = (i < kanape.parnaSzinek.length) ? kanape.parnaSzinek[i] : Color.WHITE;
                }
                kanape.parnaSzinek = newColors;
            }

            for (int i = 0; i < n; i++) {
                int idx = i;
                if (kanape.parnaSzinek[idx] == null) {
                    kanape.parnaSzinek[idx] = Color.WHITE;
                }

                JButton btn = new JButton(String.format("%d", (i + 1)));
                btn.setBackground(kanape.parnaSzinek[idx]);

                btn.addActionListener(ev -> {
                    Color c = JColorChooser.showDialog(frame, "Párna színe", kanape.parnaSzinek[idx]);
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

    private void kanapeMentes(){
        FileDialog fd = new FileDialog(frame, "Kanapé Mentés", FileDialog.SAVE);
        fd.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".sofa");
            }
        });
        fd.setFile("ujkanape.sofa");
        fd.setVisible(true);

        String filename = fd.getDirectory() + fd.getFile();
        if (!filename.endsWith(".sofa")) {
            filename += ".sofa";
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            StringBuilder sb = new StringBuilder();

            sb.append(kanape.x).append(",");
            sb.append(kanape.parnaSzam).append(",");
            sb.append(kanape.szin.getRed()).append(",");
            sb.append(kanape.szin.getGreen()).append(",");
            sb.append(kanape.szin.getBlue()).append(",");
            sb.append(kanape.labSzam).append(",");
            sb.append(kanape.labSzin.getRed()).append(",");
            sb.append(kanape.labSzin.getGreen()).append(",");
            sb.append(kanape.labSzin.getBlue()).append(",");
            sb.append(kanape.karfaSzin.getRed()).append(",");
            sb.append(kanape.karfaSzin.getGreen()).append(",");
            sb.append(kanape.karfaSzin.getBlue()).append(",");

            for (Color c : kanape.parnaSzinek) {
                sb.append(c.getRed()).append(",");
                sb.append(c.getGreen()).append(",");
                sb.append(c.getBlue()).append(",");
            }

            sb.setLength(sb.length() - 1);

            writer.write(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void kanapeBetoltes(){
        FileDialog fd = new FileDialog(frame, "Kanapé Betöltés", FileDialog.LOAD);
        fd.setFilenameFilter(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".sofa");
            }
        });
        fd.setVisible(true);
        String filename = fd.getDirectory() + fd.getFile();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            line = reader.readLine();

            String[] adatok = line.split(",");
            kanape.x = Integer.parseInt(adatok[0]);
            int psz = Integer.parseInt(adatok[1]);
            kanape.parnaSzam = psz;
            kanape.szin = new Color(Integer.parseInt(adatok[2]), Integer.parseInt(adatok[3]), Integer.parseInt(adatok[4]));
            kanape.labSzam = Integer.parseInt(adatok[5]);
            kanape.labSzin = new Color(Integer.parseInt(adatok[6]), Integer.parseInt(adatok[7]), Integer.parseInt(adatok[8]));
            kanape.karfaSzin = new Color(Integer.parseInt(adatok[9]), Integer.parseInt(adatok[10]), Integer.parseInt(adatok[11]));
            Color[] parnaSzinek = new Color[psz];
            int idx = 0;
            for (int i = 12; i < 12 + (psz * 3); i += 3) {
                parnaSzinek[idx++] = new Color(
                        Integer.parseInt(adatok[i]),
                        Integer.parseInt(adatok[i + 1]),
                        Integer.parseInt(adatok[i + 2])
                );
            }
            kanape.parnaSzinek = parnaSzinek;
            updateKanape.run();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
