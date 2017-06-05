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
        double novoX=d.getSceneX();
        if(novoX-okvir.getWidth()/2<levaGranica) novoX=levaGranica+okvir.getWidth()/2;
        if(novoX+okvir.getWidth()/2>desnaGranica) novoX=desnaGranica-okvir.getWidth()/2;
        setTranslateX(novoX-početnoX);
    }

    @Override
    public Shape getOblik() {
        return okvir;
    }
}
