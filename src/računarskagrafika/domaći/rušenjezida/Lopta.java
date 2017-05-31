package računarskagrafika.domaći.rušenjezida;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Lopta extends Sprite{
    private Circle oblik;
    private Vektor brzina, pozicija;
    private boolean pokrenuta=false;
    
    public Lopta(double x, double y, double r, Paint boja){
        oblik=new Circle(r, boja);
        oblik.setTranslateX(x);
        oblik.setTranslateY(y);
        getChildren().add(oblik);
    }
    
    public void pomeri(){
        if(pokrenuta){
            
        } else{
            
        }
    }
}
