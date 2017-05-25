package računarskagrafika.domaći.rušenjezida;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Blok extends Sprite implements Odbijajući{
    private Rectangle oblik;
    public Blok(double x, double y, double širina, double visina, Paint boja) {
        napraviOblik(x, y, širina, visina, boja);
    }

    public Blok(double x, double y, double širina, double visina) {
        napraviOblik(x, y, širina, visina, Color.GRAY);
    }

    private void napraviOblik(double x, double y, double širina, double visina, Paint boja){
        oblik=new Rectangle(x, y, širina, visina);
        oblik.setFill(boja);
        oblik.setStroke(Color.BLACK);
        getChildren().add(oblik);
    }
    
    @Override
    public void odbijanje() {
        //blok treba da nestane
    }
}
