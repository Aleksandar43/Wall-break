/*
 *
 */
package računarskagrafika.domaći.rušenjezida;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
            VISINA_IGRE=480,
            VISINA_REZULTATA=100,
            VISINA_PROZORA=VISINA_REZULTATA+VISINA_IGRE,
            POLUPREČNIK_LOPTE=10;
    public static final Color[] BOJE={Color.GREY,Color.PURPLE, Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED};
    
    private Group glavnaGrupa,grupaBlokova,grupaOdbijajućih,grupaIgre;
    private Group grupaRezultata;
    private GridPane vBoxRezultata;
    private Zid leviZid,desniZid,gornjiZid;
    private Blok[] blokovi;
    private Udarač udarač;
    private Lopta lopta;
    private List<Odbijajući> odbijajućiObjekti;
    private Text tekstKonst, tekstVreme;
    
    private class Tajmer extends AnimationTimer{
        private long prethodno=0;
        @Override
        public void handle(long now) {
            lopta.pomeri(odbijajućiObjekti, (now-prethodno)/1e9f, grupaBlokova);
            prikazVremena(now);
            if(odbijajućiObjekti.size()<=4){
                tekstKonst.setText("Bravo!");
                tajmer.stop();
            }
            if(lopta.vanIgre()){
                tekstKonst.setText("Izgubili ste");
                tajmer.stop();
            }
            prethodno=now;
        }
    }
    
    private boolean pokrenuta=false;
    private long početnoVreme;
    private void prikazVremena(long now){
        if (pokrenuta) {
            long trenutnoVreme=now-početnoVreme;
            int sekundi=(int) (trenutnoVreme/1e9);
            int minuta=sekundi/60;
            sekundi=sekundi%60;
            int stotinki=(int) (trenutnoVreme/1e7) % 100;
            String s=minuta+":";
            if(sekundi<=9) s+="0"+sekundi;
            else s+=sekundi;
            s+=".";
            if(stotinki<=9) s+="0"+stotinki;
            else s+=stotinki;
            tekstVreme.setText(s);
        } else {
            početnoVreme=now;
        }
    }
    
    private Tajmer tajmer=new Tajmer();
    
    @Override
    public void start(Stage primaryStage) {
        glavnaGrupa=new Group();
        grupaIgre=new Group();
        grupaRezultata=new Group();
        
        ImagePattern p=new ImagePattern(new Image("crteži/bg.png"), 0, 0, 40, 40, false);
        
        Rectangle pozadina=new Rectangle(0, 0, ŠIRINA_PROZORA, VISINA_PROZORA);
        pozadina.setFill(p);
        glavnaGrupa.getChildren().add(pozadina);
        
        leviZid=new Zid(0, 0, ŠIRINA_ZIDA, VISINA_IGRE, Color.GRAY);
        desniZid=new Zid(ŠIRINA_PROZORA-ŠIRINA_ZIDA, 0, ŠIRINA_ZIDA, VISINA_IGRE, Color.GRAY);
        gornjiZid=new Zid(0, 0, ŠIRINA_PROZORA, ŠIRINA_ZIDA, Color.GRAY);
        
        grupaBlokova = new Group();
        blokovi = new Blok[BROJ_BLOKOVA_U_REDU*BROJ_BLOKOVA_U_KOLONI];
        for(int i=0;i<blokovi.length;i++){
            blokovi[i]=new Blok((i%BROJ_BLOKOVA_U_REDU)*ŠIRINA_BLOKA, (i/BROJ_BLOKOVA_U_REDU)*VISINA_BLOKA,
                    ŠIRINA_BLOKA, VISINA_BLOKA, BOJE[i/BROJ_BLOKOVA_U_REDU]);
            grupaBlokova.getChildren().add(blokovi[i]);
        }
        grupaBlokova.setTranslateY(VISINA_BLOKA*5);
        grupaBlokova.setTranslateX(ŠIRINA_ZIDA);
        
        udarač=new Udarač(ŠIRINA_PROZORA/2-50, VISINA_IGRE*0.9, 100, VISINA_BLOKA, Color.ORANGE);
        
        lopta=new Lopta(ŠIRINA_PROZORA/2, VISINA_IGRE*0.9-POLUPREČNIK_LOPTE-1, POLUPREČNIK_LOPTE, Color.BLACK);
        lopta.setUdarač(udarač);
        grupaIgre.getChildren().add(lopta);
        
        odbijajućiObjekti=new ArrayList<>();
        odbijajućiObjekti.add(leviZid);
        odbijajućiObjekti.add(desniZid);
        odbijajućiObjekti.add(gornjiZid);
        odbijajućiObjekti.add(udarač);
        odbijajućiObjekti.addAll(Arrays.asList(blokovi));
        grupaOdbijajućih=new Group();
        grupaOdbijajućih.getChildren().addAll(leviZid,desniZid,gornjiZid,udarač,grupaBlokova);
        grupaIgre.getChildren().add(grupaOdbijajućih);
        grupaIgre.setTranslateY(VISINA_REZULTATA);
        
        Rectangle pozadinaRezultata=new Rectangle(0, 0, ŠIRINA_PROZORA, VISINA_REZULTATA);
        pozadinaRezultata.setFill(Color.BLACK);
        grupaRezultata.getChildren().add(pozadinaRezultata);
        tekstKonst=new Text("Vreme");
        tekstVreme=new Text("0:00.00");
        tekstKonst.setFill(Color.WHITE);
        tekstVreme.setFill(Color.WHITE);
        vBoxRezultata=new GridPane();
        vBoxRezultata.setPrefSize(ŠIRINA_PROZORA, VISINA_REZULTATA);
        //vBoxRezultata.setMaxSize(ŠIRINA_PROZORA, VISINA_REZULTATA);
        vBoxRezultata.setAlignment(Pos.CENTER);
        vBoxRezultata.add(tekstKonst, 1, 0);
        vBoxRezultata.add(tekstVreme, 1, 1);
        GridPane.setHalignment(tekstKonst, HPos.CENTER);
        GridPane.setHalignment(tekstVreme, HPos.CENTER);
        grupaRezultata.getChildren().add(vBoxRezultata);
        
        glavnaGrupa.getChildren().addAll(grupaIgre,grupaRezultata);

        Scene scene = new Scene(glavnaGrupa, ŠIRINA_PROZORA, VISINA_PROZORA);
        
        glavnaGrupa.setOnMouseMoved(d -> udarač.pomeri(d, ŠIRINA_ZIDA, ŠIRINA_PROZORA-ŠIRINA_ZIDA));
        glavnaGrupa.setOnMouseClicked(d -> {lopta.pokreni(); pokrenuta=true;});
        
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
