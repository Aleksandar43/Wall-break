package računarskagrafika.domaći.rušenjezida;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class Udarač extends PravougaoniSprite implements Odbijajući{
    private double početnoX;
    public Udarač(double x, double y, double širina, double visina, Paint boja) {
        super(x, y, širina, visina, boja);
        početnoX=x+širina/2;
    }

    public Udarač(double x, double y, double širina, double visina) {
        super(x, y, širina, visina);
        početnoX=x+širina/2;
    }

    public void pomeri(MouseEvent d, double levaGranica, double desnaGranica){
        double novoX=d.getSceneX()-okvir.getWidth()/2;
        if(novoX<levaGranica) novoX=levaGranica;
        if(novoX+okvir.getWidth()>desnaGranica) novoX=desnaGranica-okvir.getWidth();
        okvir.setTranslateX(novoX);
    }

    @Override
    public Shape getOblik() {
        return okvir;
    }
}
