package računarskagrafika.domaći.rušenjezida;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Lopta extends Sprite{
    private static final double BRZINA=300;
    private Circle oblik;
    private Vektor brzina, pozicija;
    private boolean pokrenuta=false;
    private Udarač udarač;
    
    public Lopta(double x, double y, double r, Paint boja){
        oblik=new Circle(r, boja);
        //oblik.setStroke(Color.WHITE);
        oblik.setTranslateX(x);
        oblik.setTranslateY(y);
        getChildren().add(oblik);
    }
    
    public void pomeri(List<Odbijajući> odbijajućiObjekti, double protekloVreme, Group grupa){
        if(pokrenuta){
            pozicija.setX(pozicija.getX()+brzina.getX()*protekloVreme);
            pozicija.setY(pozicija.getY()+brzina.getY()*protekloVreme);
            boolean obrniBrzinuX=false,obrniBrzinuY=false;
            for(Odbijajući oo : new ArrayList<Odbijajući>(odbijajućiObjekti)){
                Shape o=oo.getOblik();
                Shape presek = Shape.intersect(o, oblik);
                if (presek.getBoundsInLocal().getWidth() != -1) {
                    Bounds granica = presek.getBoundsInParent();
                    if (presek.getBoundsInLocal().getWidth() < presek.getBoundsInLocal().getHeight()) {
                        obrniBrzinuX = true;
                        if(brzina.getX()>0){
                            pozicija.setX(2*(granica.getMinX()-oblik.getRadius())-pozicija.getX());
                        } else {
                            pozicija.setX(2*(granica.getMaxX()+oblik.getRadius())-pozicija.getX());
                        }
                    } else {
                        obrniBrzinuY = true;
                        if(brzina.getY()>0){
                            pozicija.setY(2*(granica.getMinY()-oblik.getRadius())-pozicija.getY());
                        } else {
                            pozicija.setY(2*(granica.getMaxY()+oblik.getRadius())-pozicija.getY());
                        }
                    }
                    if(oo instanceof Blok){
                        odbijajućiObjekti.remove(oo);
                        if(grupa!=null) grupa.getChildren().remove(oo);
                    }
                }
            }
            oblik.setTranslateX(pozicija.getX());
            oblik.setTranslateY(pozicija.getY());
            if(obrniBrzinuX) brzina.setX(-brzina.getX());
            if(obrniBrzinuY) brzina.setY(-brzina.getY());

            } else{
            oblik.setTranslateX(udarač.getOkvir().getTranslateX()+udarač.getOkvir().getWidth()/2);
        }
    }
    
    public void pokreni(){
        if (!pokrenuta) {
            pozicija = new Vektor(oblik.getTranslateX(), oblik.getTranslateY());
            double ugao = (2 * Math.PI) / 360 * (Math.random() * 120 + 210);
            brzina = new Vektor(BRZINA * Math.cos(ugao), BRZINA * Math.sin(ugao));
            pokrenuta = true;
        }
    }

    public Udarač getUdarač() {
        return udarač;
    }

    public void setUdarač(Udarač udarač) {
        this.udarač = udarač;
    }
}
