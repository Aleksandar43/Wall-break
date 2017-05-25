/*
 *
 */
package računarskagrafika.domaći.rušenjezida;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class RušenjeZida extends Application {
    
    public static final int BROJ_BLOKOVA_U_REDU=13,
            BROJ_BLOKOVA_U_KOLONI=6;
    public static final double ŠIRINA_BLOKA=30,
            VISINA_BLOKA=20,
            ŠIRINA_PROZORA=480,
            VISINA_PROZORA=640;
    public static final Color[] BOJE={Color.GREY,Color.PURPLE, Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED};
    
    @Override
    public void start(Stage primaryStage) {
        Group glavnaGrupa=new Group();
        
        ImagePattern p=new ImagePattern(new Image("crteži/bg.png"), 0, 0, 40, 40, false);
        
        Zid leviZid=new Zid(0, 0, 10, VISINA_PROZORA, Color.GRAY),
                desniZid=new Zid(ŠIRINA_PROZORA-10, 0, 10, VISINA_PROZORA, Color.GRAY),
                gornjiZid=new Zid(0, 0, ŠIRINA_PROZORA, 10, Color.GRAY);
        glavnaGrupa.getChildren().addAll(leviZid, desniZid, gornjiZid);
        
        Blok[] blokovi = new Blok[BROJ_BLOKOVA_U_REDU*BROJ_BLOKOVA_U_KOLONI];
        for(int i=0;i<blokovi.length;i++){
            blokovi[i]=new Blok((i%BROJ_BLOKOVA_U_REDU)*ŠIRINA_BLOKA, (i/BROJ_BLOKOVA_U_REDU)*VISINA_BLOKA,
                    ŠIRINA_BLOKA, VISINA_BLOKA, BOJE[i/BROJ_BLOKOVA_U_REDU]);
            glavnaGrupa.getChildren().add(blokovi[i]);
        }
        
        Scene scene = new Scene(glavnaGrupa, ŠIRINA_PROZORA, VISINA_PROZORA);
        scene.setFill(p);
        
        primaryStage.setTitle("Rušenje zida");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
