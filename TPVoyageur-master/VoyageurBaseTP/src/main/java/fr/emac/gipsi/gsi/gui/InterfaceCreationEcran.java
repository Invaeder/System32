package fr.emac.gipsi.gsi.gui;

import fr.emac.gipsi.gsi.couleurs.EchantillonCouleur;
import fr.emac.gipsi.gsi.screen.Screen;
import fr.emac.gipsi.gsi.screen.VisualRectangle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class InterfaceCreationEcran extends JFrame {

    private JPanel contentPane;
    private JTextField txtCouleurchoisi;
    private JPanel palette;
    private Screen ecran = new Screen();
    private JPanel Ecran;
    private JPanel contenuPalette;
    private String nomCouleurEnCours = "AQUA";
    private JTextField txtName;
    private JTextField txtLigne;
    private JTextField textColonne;
    private JTextArea textArea;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InterfaceCreationEcran frame = new InterfaceCreationEcran();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public InterfaceCreationEcran() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 761, 551);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setBounds(5, 5, 494, 450);
        contentPane.add(tabbedPane);

        Ecran = new JPanel();
        tabbedPane.addTab("ecran", null, Ecran, null);
        Ecran.setLayout(null);

        JPanel source = new JPanel();
        tabbedPane.addTab("source", null, source, null);
        source.setLayout(null);

        textArea = new JTextArea();
        textArea.setBounds(6, 5, 436, 418);
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(new Rectangle(6, 5, 436, 418));
        source.add(scrollPane);

        InitEcran();
        contentPane.setLayout(null);

        palette = new JPanel();
        palette.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
        palette.setBounds(501, 5, 259, 450);
        palette.setLayout(new BorderLayout(0, 0));
        contenuPalette = new JPanel();

        palette.add(contenuPalette);
        contenuPalette.setLayout(new FlowLayout(FlowLayout.CENTER));
        txtCouleurchoisi = new JTextField();
        JPanel contenuPaletteSouth = new JPanel();
        contenuPaletteSouth.add(txtCouleurchoisi);
        palette.add(contenuPaletteSouth, BorderLayout.SOUTH);
        txtCouleurchoisi.setColumns(10);

        JButton btnAll = new JButton("all");
        btnAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                allSameCouleur();
            }

        });

        contenuPaletteSouth.add(btnAll);

        creationCaseCouleur();
        contentPane.add(palette);

        JPanel action = new JPanel();
        action.setBounds(5, 459, 755, 64);
        contentPane.add(action);
        action.setLayout(null);

        txtName = new JTextField();
        txtName.setBounds(474, 4, 134, 28);
        txtName.setText("name");

        txtName.setColumns(10);
        action.add(txtName);

        JButton btnSectcolor = new JButton("generate");
        btnSectcolor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                generateTextEcran();
            }

        });
        btnSectcolor.setBounds(620, 5, 115, 29);
        action.add(btnSectcolor);

        JButton btnLoad = new JButton("Load");
        btnLoad.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoadEcran();
            }
        });
        btnLoad.setBounds(620, 34, 115, 29);
        action.add(btnLoad);

        JButton btnSave = new JButton("change");
        btnSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeEcran();
            }

        });
        btnSave.setBounds(218, 5, 75, 29);
        action.add(btnSave);

        txtLigne = new JTextField();
        txtLigne.setText("lig");
        txtLigne.setBounds(72, 4, 134, 28);
        action.add(txtLigne);
        txtLigne.setColumns(10);

        textColonne = new JTextField();
        textColonne.setText("col");
        textColonne.setBounds(72, 30, 134, 28);
        action.add(textColonne);
        textColonne.setColumns(10);
    }

    private void InitEcran() {
        ecran.setColMax(8);
        ecran.setLigMax(8);
        for (int lig = 0; lig < 9; lig++) {
            for (int col = 0; col < 9; col++) {
                updatePanel(lig, col, "AQUA");
            }
        }

    }

    private void updatePanel(int lig, int col, String nomCouleur) {
        int total = lig * ecran.getColMax() + col;
        if (ecran.getRectangles().size() > total) {
            ecran.updateColorByXY(lig, col, new VisualRectangle(lig, col, nomCouleur));
        } else {
            creationEcran(nomCouleur);
        }
    }

    private void creationEcran(String nomCouleur) {
        for (int lig = 0; lig < ecran.getLigMax() + 1; lig++) {
            for (int col = 0; col < ecran.getColMax() + 1; col++) {
                ecran.getRectangles().add(new VisualRectangle(lig, col, nomCouleur));
                Ecran.add(creation1CaseCouleur(lig, col, nomCouleur));
            }
        }
    }

    private void creationCaseCouleur() {
        for (int i = 0; i < EchantillonCouleur.getListeCouleurs().size(); i++) {
            this.contenuPalette.add(creation1CaseCouleur(i));
        }

    }

    private JPanel creation1CaseCouleur(int indice) {

        JPanel p = new JPanel();
        p.setName(EchantillonCouleur.getListeCouleurs().get(indice).getName());
        ((FlowLayout) p.getLayout()).setVgap(10);
        ((FlowLayout) p.getLayout()).setHgap(10);
        p.setBackground(EchantillonCouleur.getListeCouleurs().get(indice));

        p.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                String NomCouleurClick = ((JPanel) arg0.getSource()).getName();
                txtCouleurchoisi.setText(NomCouleurClick);
                nomCouleurEnCours = NomCouleurClick;
            }
        });

        return p;

    }

    private JPanel creation1CaseCouleur(int lig, int col, String nomCouleur) {
        int h = (400 - ecran.getLigMax()) / (ecran.getLigMax() + 1);
        int w = (400 - ecran.getColMax()) / (ecran.getColMax() + 1);

        JPanel p = new JPanel();
        p.setName(lig + "_" + col);
        p.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        p.setBounds(col * w, lig * h, w, h);
        p.setBackground(EchantillonCouleur.getColorByName(nomCouleur));
        p.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                String[] coord = ((JPanel) arg0.getSource()).getName().split("_");
                updatePanel(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]), nomCouleurEnCours);
                ((JPanel) arg0.getSource()).setBackground(EchantillonCouleur.getColorByName(nomCouleurEnCours));
            }
        });

        return p;

    }

    private void allSameCouleur() {
        for (Component p : Ecran.getComponents()) {
            if (p instanceof JPanel) {
                String[] coord = p.getName().split("_");
                ((JPanel) p).setBackground(EchantillonCouleur.getColorByName(nomCouleurEnCours));
                updatePanel(Integer.parseInt(coord[0]), Integer.parseInt(coord[1]), nomCouleurEnCours);
            }
        }

    }

    private void generateTextEcran() {

        this.textArea.setText("");
        String debutLigne = txtName.getText() + ".getRectangles().add(new VisualRectangle(";
        String finLigne = "\"));\n";

        String output = "public static Screen " + txtName.getText().replaceAll(" ", "") + "(){\n";
        output = output + "Screen " + txtName.getText().replaceAll(" ", "") + " = new Screen(\"" + txtName.getText()
                + "\");\n";
        output = output + txtName.getText() + ".setColMax(" + ecran.getColMax() + ");\n";
        output = output + txtName.getText() + ".setLigMax(" + ecran.getLigMax() + ");\n";

        for (VisualRectangle r : ecran.getRectangles()) {
            output = output + debutLigne + r.getIndiceLigne() + "," + r.getIndiceColonne() + ",\"" + r.getNomCouleur()
                    + finLigne;
        }
        output = output + "return " + txtName.getText().replaceAll(" ", "") + ";\n}";

        this.textArea.setText(output);

    }

    protected void LoadEcran() {

        ArrayList<ArrayList<String>> cases = new ArrayList<ArrayList<String>>();
        int colMax = 0;
        int ligMax = 0;
        if (!this.textArea.getText().equals("")) {
            String[] lignes = textArea.getText().split("\n");
            if (lignes.length > 4) {
                colMax = recupNum(lignes[2]);
                ligMax = recupNum(lignes[3]);
                for (int i = 4; i < lignes.length; i++) {
                    ArrayList<String> contenuLigne = recupcontenuLigne(lignes[i]);
                    if (contenuLigne.size() > 0)
                        cases.add(recupcontenuLigne(lignes[i]));
                }
            }
        }

        if (cases.size() > 0) {
            changeEcran(ligMax, colMax, cases);
        }

    }

    private int recupNum(String num) {
        String[] res = num.split("\\(");
        int resu = 0;
        for (String s : res) {
            if (!s.contains("Max")) {
                resu = Integer.parseInt(s.substring(0, s.lastIndexOf(")")));
            }
        }
        return resu;
    }

    public static ArrayList<String> recupcontenuLigne(String ligne) {
        ArrayList<String> res = new ArrayList<String>();
        String charSequence = "new VisualRectangle";
        for (String lig : ligne.split(";")) {
            if (lig.contains(charSequence)) {
                String stringToParse = lig.split(charSequence)[1].replace("(", "").replace(")", "");
                String[] valeurs = stringToParse.split(",");
                for (int i = 0; i < valeurs.length; i++) {
                    valeurs[i] = valeurs[i].trim();
                }
                res.add("" + Integer.parseInt(valeurs[0]));
                res.add("" + Integer.parseInt(valeurs[1]));
                res.add(valeurs[2].replace("\"",""));
            }
        }
        return res;
    }

    private void changeEcran(int ligMax, int colMax, ArrayList<ArrayList<String>> cases) {

        Ecran.removeAll();
        ecran.getRectangles().clear();
        ecran.setColMax(colMax);
        ecran.setLigMax(ligMax);
        ecran.setRectangles(new ArrayList<VisualRectangle>());

        for (ArrayList<String> c : cases) {
            updatePanel(Integer.parseInt(c.get(0)), Integer.parseInt(c.get(1)), c.get(2));
			for (Component p : Ecran.getComponents()) {
				if (p instanceof JPanel) {
					String[] coord = p.getName().split("_");
					if (c.get(0).equals(coord[0]) && c.get(1).equals(coord[1])) {
						((JPanel) p).setBackground(EchantillonCouleur.getColorByName(c.get(2)));
					}
				}
			}
        }

    }

    private void changeEcran() {

        Ecran.removeAll();
        ecran.getRectangles().clear();
        ecran.setColMax(Integer.parseInt((this.textColonne.getText())) - 1);
        ecran.setLigMax(Integer.parseInt((this.txtLigne.getText())) - 1);
        ecran.setRectangles(new ArrayList<VisualRectangle>());
        for (int lig = 0; lig < ecran.getLigMax() + 1; lig++) {
            for (int col = 0; col < ecran.getLigMax() + 1; col++) {
                updatePanel(lig, col, this.nomCouleurEnCours);
            }
        }
    }
}