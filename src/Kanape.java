import java.awt.*;

public class Kanape {
    Dimension meret;
    Color szin;
    Dimension parnaMeret;

    int labSzam;
    Color labSzin;
    Karfa[] karfak;
    Parna[] parnak;

    public Kanape(){
        meret = new Dimension(400,130);
        szin = new Color(0,0,0);
        labSzam = 2;
        labSzin = new Color(176, 101, 0);
    }
}
