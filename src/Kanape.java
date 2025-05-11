import java.awt.*;

public class Kanape {
    Dimension meret;
    Color szin;
    Dimension parnaMeret;

    //Fix meret
    Dimension hattamlaMeret;
    Dimension oldalrolHattamlaMeret;
    Dimension oldalrolTamaszMeret;

    int labSzam;
    Color labSzin;

    Color karfaSzin;

    Parna[] parnak;

    public Kanape(){
        meret = new Dimension(400,130);
        szin = new Color(0,0,0);
        labSzam = 2;
        labSzin = new Color(176, 101, 0);
        karfaSzin = new Color(176, 101, 0);
    }
}
