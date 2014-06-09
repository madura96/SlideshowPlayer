package com.humanbooster.slideshowplayer.main;

import com.humanbooster.slideshowplayer.controller.SlideshowController;
import com.humanbooster.slideshowplayer.model.*;
import com.humanbooster.slideshowplayer.view.console.ConsoleView;

import com.humanbooster.slideshowplayer.view.javafx.UIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by HumanBooster on 20/05/2014.
 */
public class Main extends Application {

    // create slideshow controller
    SlideshowController sc = new SlideshowController();

    @Override
    public void init() throws Exception {
        super.init();

        try {
            //  create a slideshow
            Slideshow ss = new Slideshow();

            for (int i = 0; i < 10; i++) { // add 10 slides
                Slide s = new Slide();
                // slide = titre + image
                TextSlideElement title = new TextSlideElement(0.1,0.4,0.8,0.2);
                title.setContent("Slide " + i);
                s.addSlideElement(title);
                ImageSlideElement image = new ImageSlideElement(0.1,0.4,0.8,0.2);
                title.setContent("image url");
                s.addSlideElement(image);
                ss.addSlide(s);
            }
            // give slideshow to controller
            sc.setSlideShow(ss);
            System.out.format("Slideshow of %d slides is created\n", 10);
           //launch(args);

            ConsoleView view = new ConsoleView();
            sc.addCurrentSlideChangedListener(view);
         }
        catch (Exception e) {
            e.printStackTrace();
            //System.err.println("Caught exception: " + e.getMessage());
        }
        finally {
            System.out.println("End Main.init()");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL location = getClass().getResource("/com/humanbooster/slideshowplayer/view/javafx/UIMain.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        // get the root node and create the scene
        Parent root = (Parent) fxmlLoader.load(location.openStream());
        Scene scene = new Scene(root, 1024, 700);

/*
        // code to have resizable contents in window
        Region contentRootRegion = (Region) fxmlLoader.load(location.openStream());
        //Set a default "standard" or "100%" resolution
        double origW = 960; //1366
        double origH = 540; //768

        //If the Region containing the GUI does not already have a preferred width and height, set it.
        //But, if it does, we can use that setting as the "standard" resolution.
        if ( contentRootRegion.getPrefWidth() == Region.USE_COMPUTED_SIZE )
            contentRootRegion.setPrefWidth( origW );
        else
            origW = contentRootRegion.getPrefWidth();

        if ( contentRootRegion.getPrefHeight() == Region.USE_COMPUTED_SIZE )
            contentRootRegion.setPrefHeight( origH );
        else
            origH = contentRootRegion.getPrefHeight();

        //Wrap the resizable content in a non-resizable container (Group)
        Group group = new Group( contentRootRegion );
        //Place the Group in a StackPane, which will keep it centered
        StackPane rootPane = new StackPane();
        rootPane.getChildren().add( group );

         //Create the scene initally at the "100%" size
        Scene scene = new Scene( rootPane, origW, origH );
        //Bind the scene's width and height to the scaling parameters on the group
        group.scaleXProperty().bind( scene.widthProperty().divide( origW ) );
        group.scaleYProperty().bind( scene.heightProperty().divide( origH ) );
*/

        primaryStage.setTitle("Sildeshow Player");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("UIMain.css").toExternalForm());

        UIController uic = fxmlLoader.getController();
        if (uic != null) {
            uic.setSc(sc);
        }

        primaryStage.show();

        System.out.println("End Main.start()");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        try {
            launch(args);
        }
        catch (Exception e) {
            e.printStackTrace();
            //System.err.println("Caught exception: " + e.getMessage());
        }
        finally {
            System.out.println("End Main.start()");
        }
    }



}



// using switch
/*
            while (true) {
                input = scanner.nextLine();

                EnumCommand cmd; // = EnumCommand.UNKNOWN;
                try {
                    cmd = EnumCommand.valueOf(input.toUpperCase());
                } catch (Exception e) {
                    cmd = EnumCommand.UNKNOWN;
                }
                Slide s;
                switch (cmd) {
                    case SHOW :
                        System.out.println("show slide");
                        s = sc.getCurrentSlide();
                     break;
                    case NEXT :
                        System.out.println("next slide");
                        s = sc.nextSlide();
                        System.out.println(s);
                        break;
                    case PREVIOUS :
                        System.out.println("previous slide");
                        s = sc.previousSlide();
                        System.out.println(s);
                        break;
                    case QUIT :
                        System.out.println("quit application");
                        break;
                    Default :
                    System.out.println("unknown keyword");
                 }
            }
*/
