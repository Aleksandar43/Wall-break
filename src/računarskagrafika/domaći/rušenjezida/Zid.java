package računarskagrafika.domaći.rušenjezida;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class Zid extends PravougaoniSprite implements Odbijajući{
    public Zid(double x, double y, double širina, double visina, Paint boja) {
        super(x, y, širina, visina, boja);
    }

    public Zid(double x, double y, double širina, double visina) {
        super(x, y, širina, visina);
    }

    @Override
    public Shape getOblik() {
        return okvir;
    }
}
