import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//grosseur image

public class Main extends Application{
    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {

        //application
        primaryStage.setMaximized(true);
        BorderPane app = new BorderPane();

        //sliders
        Label bright = new Label("Luminosité");
        Slider luminosite = new Slider(-1, 1, 0);

        Label contrast = new Label("Contraste");
        Slider contraste = new Slider(-1, 1, 0);

        Label color = new Label("Teinte");
        Slider teinte = new Slider(-1,1,0);

        Label sat = new Label("Saturation");
        Slider saturation = new Slider(-1,1,0);

        VBox sliders = new VBox(bright, luminosite, contrast, contraste, color, teinte, sat, saturation);
        sliders.setSpacing(10);
        sliders.setAlignment(Pos.CENTER);

        //menu
        Menu fichier = new Menu("Fichier");
        Menu action = new Menu("Action");
        Menu image = new Menu("Charger une image");

        MenuItem image1 = new MenuItem("Image #1");
        MenuItem image2 = new MenuItem("Image #2");
        MenuItem image3 = new MenuItem("Image #3");
        MenuItem reinisialiser = new MenuItem("Réinisialiser");

        image.getItems().addAll(image1,image2,image3);
        fichier.getItems().addAll(image);
        action.getItems().addAll(reinisialiser);

        MenuBar menubar = new MenuBar(fichier, action);
        app.setTop(menubar);

        //texte gris
        Label label = new Label("Bienvenue dans le modificateur d'images!");
        HBox gris = new HBox(label);
        gris.setPadding(new Insets(5));
        app.setBottom(gris);
        gris.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));

        //images
        Image base = new Image("default.jpg");
        ImageView base2 = new ImageView(base);
        base2.setPreserveRatio(true);
        base2.setFitWidth(500);

        Image chat1 = new Image("image1.jpg");
        Image chat2 = new Image("image2.jpg");
        Image chat3 = new Image("image3.jpg");

        HBox content = new HBox(base2, sliders);
        content.setAlignment(Pos.CENTER);

        app.setCenter(content);

        ColorAdjust imageColors = new ColorAdjust();
        luminosite.valueProperty().bindBidirectional(imageColors.brightnessProperty());
        contraste.valueProperty().bindBidirectional(imageColors.contrastProperty());
        teinte.valueProperty().bindBidirectional(imageColors.hueProperty());
        saturation.valueProperty().bindBidirectional(imageColors.saturationProperty());
        base2.setEffect(imageColors);


        //actions
        image1.setOnAction(event -> {
            base2.setImage(chat1);
            label.setText("Image 1 chargée");
        });

        image2.setOnAction(event -> {
            base2.setImage(chat2);
            label.setText("Image 2 chargée");
        });

        image3.setOnAction(event -> {
            base2.setImage(chat3);
            label.setText("Image 3 chargée");
        });

        reinisialiser.setOnAction(event -> {
            label.setText("Réinitialisation des ajustements de couleurs");
            luminosite.setValue(0);
            contraste.setValue(0);
            teinte.setValue(0);
            saturation.setValue(0);
        });


        primaryStage.setScene(new Scene(app));
        primaryStage.show();
    }
}
