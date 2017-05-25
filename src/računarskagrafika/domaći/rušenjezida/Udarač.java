package računarskagrafika.domaći.rušenjezida;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Udarač extends Sprite implements Odbijajući{
    private Rectangle oblik;
    public Udarač(double x, double y, double širina, double visina, Paint boja) {
        oblik=new Rectangle(x, y, širina, visina);
        oblik.setFill(boja);
        getChildren().add(oblik);
    }

    public Udarač(double x, double y, double širina, double visina) {
        oblik=new Rectangle(x, y, širina, visina);
        getChildren().add(oblik);
    }

    @Override
    public void odbijanje() {
        
    }
}
