/*
 *
 */
package računarskagrafika.domaći.rušenjezida;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RušenjeZida extends Application {
    
    public static final int BROJ_BLOKOVA_U_REDU=13,
            BROJ_BLOKOVA_U_KOLONI=6;
    public static final double ŠIRINA_BLOKA=30,
            VISINA_BLOKA=15,
            /*ŠIRINA_PROZORA=480,
            VISINA_PROZORA=640,
            ŠIRINA_ZIDA=(ŠIRINA_PROZORA-ŠIRINA_BLOKA*BROJ_BLOKOVA_U_REDU)/2;*/
            ŠIRINA_ZIDA=20,
            ŠIRINA_PROZORA=ŠIRINA_ZIDA*2+ŠIRINA_BLOKA*BROJ_BLOKOVA_U_REDU,
            VISINA_PROZORA=480,
            POLUPREČNIK_LOPTE=10;
    public static final Color[] BOJE={Color.GREY,Color.PURPLE, Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED};
    
    private Group glavnaGrupa,grupaBlokova;
    private Zid leviZid,desniZid,gornjiZid;
    private Blok[] blokovi;
    private Udarač udarač;
    private Lopta lopta;
    private List<Odbijajući> odbijajućiObjekti;
    
    private class Tajmer extends AnimationTimer{
        private long prethodno=0;
        @Override
        public void handle(long now) {
            lopta.pomeri(odbijajućiObjekti, (now-prethodno)/1e9f);
            prethodno=now;
        }
    }
    
    private Tajmer tajmer=new Tajmer();
    
    @Override
    public void start(Stage primaryStage) {
        glavnaGrupa=new Group();
        
        ImagePattern p=new ImagePattern(new Image("crteži/bg.png"), 0, 0, 40, 40, false);
        
        Rectangle pozadina=new Rectangle(0, 0, ŠIRINA_PROZORA, VISINA_PROZORA);
        pozadina.setFill(p);
        glavnaGrupa.getChildren().add(pozadina);
        
        leviZid=new Zid(0, 0, ŠIRINA_ZIDA, VISINA_PROZORA, Color.GRAY);
        desniZid=new Zid(ŠIRINA_PROZORA-ŠIRINA_ZIDA, 0, ŠIRINA_ZIDA, VISINA_PROZORA, Color.GRAY);
        gornjiZid=new Zid(0, 0, ŠIRINA_PROZORA, ŠIRINA_ZIDA, Color.GRAY);
        glavnaGrupa.getChildren().addAll(leviZid, desniZid, gornjiZid);
        
        grupaBlokova = new Group();
        blokovi = new Blok[BROJ_BLOKOVA_U_REDU*BROJ_BLOKOVA_U_KOLONI];
        for(int i=0;i<blokovi.length;i++){
            blokovi[i]=new Blok((i%BROJ_BLOKOVA_U_REDU)*ŠIRINA_BLOKA, (i/BROJ_BLOKOVA_U_REDU)*VISINA_BLOKA,
                    ŠIRINA_BLOKA, VISINA_BLOKA, BOJE[i/BROJ_BLOKOVA_U_REDU]);
            grupaBlokova.getChildren().add(blokovi[i]);
        }
        glavnaGrupa.getChildren().add(grupaBlokova);
        grupaBlokova.setTranslateY(VISINA_BLOKA*5);
        grupaBlokova.setTranslateX(ŠIRINA_ZIDA);
        
        udarač=new Udarač(ŠIRINA_PROZORA/2-50, VISINA_PROZORA*0.9, 100, VISINA_BLOKA, Color.ORANGE);
        glavnaGrupa.getChildren().add(udarač);
        
        lopta=new Lopta(ŠIRINA_PROZORA/2, VISINA_PROZORA*0.9-POLUPREČNIK_LOPTE, POLUPREČNIK_LOPTE, Color.BLACK);
        lopta.setUdarač(udarač);
        glavnaGrupa.getChildren().add(lopta);
        
        odbijajućiObjekti=new ArrayList<>();
        odbijajućiObjekti.add(leviZid);
        odbijajućiObjekti.add(desniZid);
        odbijajućiObjekti.add(gornjiZid);
        odbijajućiObjekti.add(udarač);
        odbijajućiObjekti.addAll(Arrays.asList(blokovi));
        
        Scene scene = new Scene(glavnaGrupa, ŠIRINA_PROZORA, VISINA_PROZORA);
        
        glavnaGrupa.setOnMouseMoved(d -> udarač.pomeri(d, ŠIRINA_ZIDA, ŠIRINA_PROZORA-ŠIRINA_ZIDA));
        glavnaGrupa.setOnMouseClicked(d -> lopta.pokreni());
        
        primaryStage.setTitle("Rušenje zida");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.show();
        
        tajmer.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
