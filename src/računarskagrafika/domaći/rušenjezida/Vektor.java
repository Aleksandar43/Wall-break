package računarskagrafika.domaći.rušenjezida;

public class Vektor {
    private double x,y;

    public Vektor(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public Vektor saberi(Vektor v){
        x+=v.x;
        y+=v.y;
        return this;
    }
    
    public Vektor pomnoži(double a){
        x=a*x;
        y=a*y;
        return this;
    }
    
    public static Vektor sabiranje(Vektor v1, Vektor v2){
        return new Vektor(v1.x+v2.x, v1.y+v2.y);
    }
}
