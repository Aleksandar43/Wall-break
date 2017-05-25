package računarskagrafika.domaći.rušenjezida;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class PravougaoniSprite extends Sprite{
    private Rectangle okvir;
    public PravougaoniSprite(double x, double y, double širina, double visina, Paint boja){
        okvir=new Rectangle(x, y, širina, visina);
        okvir.setFill(boja);
        getChildren().add(okvir);
    }
    
    public PravougaoniSprite(double x, double y, double širina, double visina){
        okvir=new Rectangle(x, y, širina, visina);
        getChildren().add(okvir);
    }
}
