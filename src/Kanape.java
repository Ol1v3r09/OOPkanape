import java.awt.*;
import java.util.Random;

public class Kanape {
    //Korlátozó parméterek
    static final int X_MIN = 300;
    static final int X_MAX = 550;
    static final int PARNA_MIN = 1;
    static final int PARNA_MAX = 5;
    static final int LAB_MIN = 2;
    static final int LAB_MAX = 6;


    public Color szin;
    public int x;
    int y;
    int z;

    //Minden oldal mérete
    //Hátulról
    Dimension hHattamlaMeret;
    Dimension hKarfaMeret;
    Dimension hLabMeret;

    //Oldalról
    Dimension oHattamlaMeret;
    Dimension oAlsoTamaszMeret;
    Dimension oKarfaMeret;

    //Felülről
    Dimension fHattamlaMeret;
    Dimension fAlsoTamaszMeret;
    Dimension fParnaMeret;
    Dimension fKarfaMeret;

    //Előről
    Dimension eAlsoTamaszMeret;
    Dimension eHattamlaMeret;
    Dimension eParnaMeret;
    Dimension eKarfaMeret;

    public int labSzam;
    public Color labSzin;

    public Color karfaSzin;

    public Color[] parnaSzinek;
    public int parnaSzam;

    public Kanape(){
        Random rnd =  new Random();
        x = rnd.nextInt(X_MIN, X_MAX);
        y = x  / 4;
        z = (int)(x * 0.3);

        parnaSzam = rnd.nextInt(PARNA_MIN,PARNA_MAX + 1);
        MeretekBeallitasa();

        szin = new Color(
                rnd.nextInt(0,256),
                rnd.nextInt(0, 256),
                rnd.nextInt(0,256));

        labSzam = rnd.nextInt(LAB_MIN,LAB_MAX + 1);
        labSzin = new Color(
                rnd.nextInt(0,256),
                rnd.nextInt(0, 256),
                rnd.nextInt(0,256));
        karfaSzin = new Color(
                rnd.nextInt(0,256),
                rnd.nextInt(0, 256),
                rnd.nextInt(0,256));

        parnaSzinek = new Color[parnaSzam];
        for (int i = 0; i < parnaSzam; i++){
            parnaSzinek[i] = new Color(
                    rnd.nextInt(0,256),
                    rnd.nextInt(0, 256),
                    rnd.nextInt(0,256));
        }
    }

    public void MeretekBeallitasa(){
        y = x  / 4;
        z = (int)(x * 0.3);
        //Hátsó méretek
        hHattamlaMeret = new Dimension((int)(x * 0.84), (int)(y * 0.8));
        hKarfaMeret = new Dimension((int)(x * 0.08), (int)(y * 0.4));
        hLabMeret = new Dimension(20,(int)(y * 0.2));

        //Oldalsó méretek
        oHattamlaMeret = new Dimension((int)(hHattamlaMeret.height * 0.5), (int)(hHattamlaMeret.height * 0.8));
        oAlsoTamaszMeret = new Dimension(z, (int)(hHattamlaMeret.height * 0.2));
        oKarfaMeret = new Dimension(oAlsoTamaszMeret.width, hKarfaMeret.height);

        //Felső méretek
        fHattamlaMeret = new Dimension(hHattamlaMeret.width, oHattamlaMeret.width);
        fAlsoTamaszMeret = new Dimension(hHattamlaMeret.width, z);

        int parnaSz = fAlsoTamaszMeret.width / parnaSzam;
        int parnaM = fAlsoTamaszMeret.height - fHattamlaMeret.height;
        fParnaMeret = new Dimension(parnaSz, parnaM);
        fKarfaMeret = new Dimension(hKarfaMeret.width, fAlsoTamaszMeret.height - fHattamlaMeret.height);

        //Elsőnézetes méretek
        eHattamlaMeret = new Dimension((int)(x * 0.84), (int)(y * 0.8));
        eAlsoTamaszMeret = new Dimension((int)(x * 0.84), (int)(hHattamlaMeret.height * 0.2));
        eKarfaMeret = new Dimension((int)(x * 0.08), (int)(y * 0.4));
        eParnaMeret = new Dimension(parnaSz,(int)(y * 0.15));
    }
}
