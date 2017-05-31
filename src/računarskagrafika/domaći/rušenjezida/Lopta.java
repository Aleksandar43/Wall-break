package računarskagrafika.domaći.rušenjezida;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Lopta extends Sprite{
    private static final double BRZINA=2;
    private Circle oblik;
    private Vektor brzina, pozicija;
    private boolean pokrenuta=false;
    private Udarač udarač;
    
    public Lopta(double x, double y, double r, Paint boja){
        oblik=new Circle(r, boja);
        oblik.setStroke(Color.WHITE);
        oblik.setTranslateX(x);
        oblik.setTranslateY(y);
        getChildren().add(oblik);
    }
    
    public void pomeri(){
        if(pokrenuta){
            pozicija.saberi(brzina);
            oblik.setTranslateX(pozicija.getX());
            oblik.setTranslateY(pozicija.getY());
        } else{
            oblik.setTranslateX(udarač.getTranslateX()+udarač.getOkvir().getWidth()/2+udarač.getOkvir().getX());
        }
    }
    
    public void pokreni(){
        pozicija=new Vektor(oblik.getTranslateX(), oblik.getTranslateY());
        double ugao=(2*Math.PI)/360*(Math.random()*120+210);
        brzina=new Vektor(BRZINA*Math.cos(ugao), BRZINA*Math.sin(ugao));
        pokrenuta=true;
    }

    public Udarač getUdarač() {
        return udarač;
    }

    public void setUdarač(Udarač udarač) {
        this.udarač = udarač;
    }
}
