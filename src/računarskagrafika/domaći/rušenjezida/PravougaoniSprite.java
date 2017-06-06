package računarskagrafika.domaći.rušenjezida;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class PravougaoniSprite extends Sprite{
    protected Rectangle okvir;
    public PravougaoniSprite(double x, double y, double širina, double visina, Paint boja){
        okvir=new Rectangle(širina, visina);
        okvir.setTranslateX(x);
        okvir.setTranslateY(y);
        okvir.setFill(boja);
        getChildren().add(okvir);
    }
    
    public PravougaoniSprite(double x, double y, double širina, double visina){
        okvir=new Rectangle(širina, visina);
        okvir.setTranslateX(x);
        okvir.setTranslateY(y);
        getChildren().add(okvir);
    }

    public Rectangle getOkvir() {
        return okvir;
    }
}
