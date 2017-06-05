package računarskagrafika.domaći.rušenjezida;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Blok extends PravougaoniSprite implements Odbijajući{
    private Rectangle oblik;
    public Blok(double x, double y, double širina, double visina, Paint boja) {
        super(x, y, širina, visina, boja);
        okvir.setStroke(Color.BLACK);
    }

    public Blok(double x, double y, double širina, double visina) {
        super(x, y, širina, visina);
        okvir.setStroke(Color.BLACK);
    }

    private void napraviOblik(double x, double y, double širina, double visina, Paint boja){
        oblik=new Rectangle(x, y, širina, visina);
        oblik.setFill(boja);
        oblik.setStroke(Color.BLACK);
        getChildren().add(oblik);
    }

    @Override
    public Shape getOblik() {
        return okvir;
    }
}
