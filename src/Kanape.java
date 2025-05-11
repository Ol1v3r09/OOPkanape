import java.awt.*;

public class Kanape {
    Color szin;
    Dimension parnaMeret;

    //Teljes méret
    Dimension meret;

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

    Parna[] parnak;
    int parnaSzam;

    public Kanape(){
        meret = new Dimension(400,150);
        parnaSzam = 4;


        hHattamlaMeret = new Dimension((int)(meret.width * 0.84), (int)(meret.height * 0.8));
        hKarfaMeret = new Dimension((int)(meret.width * 0.08), (int)(meret.height * 0.4));
        hLabMeret = new Dimension(20,(int)(meret.height * 0.2));

        oHattamlaMeret = new Dimension((int)(hHattamlaMeret.height * 0.5), (int)(hHattamlaMeret.height * 0.8));
        oAlsoTamaszMeret = new Dimension((int)(hHattamlaMeret.width * 0.5), (int)(hHattamlaMeret.height * 0.2));
        oKarfaMeret = new Dimension((int)(oAlsoTamaszMeret.width), hKarfaMeret.height);

        fHattamlaMeret = new Dimension(hHattamlaMeret.width, oHattamlaMeret.width);
        fAlsoTamaszMeret = new Dimension(hHattamlaMeret.width, oHattamlaMeret.height);

        int parnaSz = fAlsoTamaszMeret.width / parnaSzam;
        int parnaM = (int)(fAlsoTamaszMeret.height * 0.95);
        fParnaMeret = new Dimension(parnaSz, parnaM);
        fKarfaMeret = new Dimension(hKarfaMeret.width, (int)(fAlsoTamaszMeret.height * 0.95));


        szin = new Color(0,0,0);
        labSzam = 2;
        labSzin = new Color(176, 101, 0);
        karfaSzin = new Color(176, 101, 100);

        parnak = new Parna[parnaSzam];
        parnak[0] = new Parna();
        parnak[1] = new Parna();
        parnak[2] = new Parna();
        parnak[3] = new Parna();
        parnak[0].szin = new Color(255,0,0);
        parnak[1].szin = new Color(0,225,0);
        parnak[2].szin = new Color(0,0,255);
        parnak[3].szin = new Color(255,0,0);
    }
}
