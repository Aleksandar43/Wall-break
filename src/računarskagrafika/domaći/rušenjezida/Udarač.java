package računarskagrafika.domaći.rušenjezida;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Udarač extends Sprite implements Odbijajući{
    private Rectangle oblik;
    private double početnoX;
    public Udarač(double x, double y, double širina, double visina, Paint boja) {
        oblik=new Rectangle(x, y, širina, visina);
        oblik.setFill(boja);
        getChildren().add(oblik);
        početnoX=x+širina/2;
    }

    public Udarač(double x, double y, double širina, double visina) {
        oblik=new Rectangle(x, y, širina, visina);
        getChildren().add(oblik);
        početnoX=x+širina/2;
    }

    @Override
    public void odbijanje() {
        
    }
    
    public void pomeri(MouseEvent d, double levaGranica, double desnaGranica){
        double novoX=d.getSceneX();
        if(novoX-oblik.getWidth()/2<levaGranica) novoX=levaGranica+oblik.getWidth()/2;
        if(novoX+oblik.getWidth()/2>desnaGranica) novoX=desnaGranica-oblik.getWidth()/2;
        setTranslateX(novoX-početnoX);
    }
}
