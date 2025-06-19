import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicMenuItemUI;
import java.awt.*;
import java.io.*;

public class Menu {
    private final JMenuBar menuBar;
    private final JPanel formPanel;
    private final JPanel parnaPanel;

    private final JFrame frame;
    private Kanape kanape;
    private final Runnable updateKanape;
    private final Runnable randomKanape;

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


        JMenuItem kanapeDialogus = new JMenuItem("Kanapé Beállítások");
        kanapeDialogus.setUI(new BasicMenuItemUI());
        kanapeDialogus.setBorderPainted(false);
        kanapeDialogus.setFocusPainted(false);
        kanapeDialogus.setContentAreaFilled(false);
        kanapeDialogus.setOpaque(false);
        kanapeDialogus.setMaximumSize(new Dimension(120,20));
        kanapeDialogus.addActionListener(e -> showKanapeDialog());

        JMenuItem randomGeneral = new JMenuItem("Kanapé Generálás");
        randomGeneral.setUI(new BasicMenuItemUI());
        randomGeneral.setBorderPainted(false);
        randomGeneral.setFocusPainted(false);
        randomGeneral.setContentAreaFilled(false);
        randomGeneral.setOpaque(false);
        randomGeneral.setMaximumSize(new Dimension(120,20));
        randomGeneral.addActionListener(e -> this.randomKanape.run());

        JMenuItem sugo = new JMenuItem("Súgó");
        sugo.setUI(new BasicMenuItemUI());
        sugo.setBorderPainted(false);
        sugo.setFocusPainted(false);
        sugo.setContentAreaFilled(false);
        sugo.setOpaque(false);
        sugo.setMaximumSize(new Dimension(60,20));
        sugo.addActionListener(e -> sugoPdfMegnyitas());

        formPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        parnaPanel = new JPanel(new GridLayout(1, kanape.parnaSzam));


        fileMenu.add(mentes);
        fileMenu.add(betoltes);
        menuBar.add(fileMenu);
        menuBar.add(kanapeDialogus);
        menuBar.add(randomGeneral);
        menuBar.add(sugo);
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
        JTextField tfParnaSzam = new JTextField(String.format("%d", kanape.parnaSzam));
        tfParnaSzam.getDocument().addDocumentListener(new DocumentListener() {
            private void handleUpdate() {
                try {
                    int val = Integer.parseInt(tfParnaSzam.getText());
                    if (val >= Kanape.PARNA_MIN && val <= Kanape.PARNA_MAX) {
                        updateParnaPickers(val);
                    }
                    else if (val < Kanape.PARNA_MIN){
                        updateParnaPickers(Kanape.PARNA_MIN);
                    }
                    else {
                        updateParnaPickers(Kanape.PARNA_MAX);
                    }
                } catch (NumberFormatException ignored) {
                }
            }

            @Override public void insertUpdate(DocumentEvent e) { handleUpdate(); }
            @Override public void removeUpdate(DocumentEvent e) { handleUpdate(); }
            @Override public void changedUpdate(DocumentEvent e) { handleUpdate(); }
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

        formPanel.add(new JLabel(String.format("Szélesség (%d - %d):", Kanape.X_MIN, Kanape.X_MAX)));
        formPanel.add(tfSzelesseg);
        formPanel.add(new JLabel(String.format("Párnák száma (%d - %d):", Kanape.PARNA_MIN, Kanape.PARNA_MAX)));
        formPanel.add(tfParnaSzam);
        formPanel.add(new JLabel(String.format("Lábak száma (%d - %d):", Kanape.LAB_MIN, Kanape.LAB_MAX)));
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
                    tfLabSzam.setText(String.valueOf(lb));
                } catch (NumberFormatException ex) {
                    lb = Kanape.LAB_MIN;
                    tfLabSzam.setText(String.valueOf(lb));
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

        }
    }

    private void sugoPdfMegnyitas(){
        try {
            if (Desktop.isDesktopSupported()) {
                File pdfFile = new File("sugo.pdf");
                Desktop.getDesktop().open(pdfFile);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Nem sikerült megnyitni a súgófájlt", "Hiba", JOptionPane.ERROR_MESSAGE);
        }
    }
}
