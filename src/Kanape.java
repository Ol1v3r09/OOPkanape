import java.awt.*;

public class Kanape {
    Color szin;
    int x;
    int y;
    int z;

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

    int labSzam;
    Color labSzin;

    Color karfaSzin;

    Color[] parnaSzinek;
    int parnaSzam;

    public Kanape(){
        x = 400;
        y = x  / 4;
        z = (int)(x * 0.3);
        parnaSzam = 3;
        MeretekBeallitasa();

        szin = new Color(150,50,0);
        labSzam = 3;
        labSzin = new Color(176, 101, 0);
        karfaSzin = new Color(176, 101, 100);

        parnaSzinek = new Color[parnaSzam];
        parnaSzinek[0] = new Color(255,0,0);
        parnaSzinek[1] = new Color(0,225,0);
        parnaSzinek[2] = new Color(0,0,255);
    }

    public Kanape(int szelesseg, int parnaszam, Color kanapeszin, int labszam, Color labszin, Color karfaszin, Color[] parnaszinek){
        x = szelesseg;
        y = x  / 4;
        z = (int)(x * 0.3);
        parnaSzam = parnaszam;
        MeretekBeallitasa();

        szin = kanapeszin;
        labSzam = labszam;
        labSzin = labszin;
        karfaSzin = karfaszin;

        parnaSzinek = new Color[parnaSzam];
        for (int i = 0; i < parnaSzam; i++){
            parnaSzinek[i] = parnaszinek[i];
        }
    }

    public void MeretekBeallitasa(){
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
