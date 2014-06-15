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
    private SlideshowController sc = null;

    @Override
    public void init() throws Exception {
        super.init();

        try {
            sc = new SlideshowController(); // create slideshow controller
            sc.setTransitionTimeBetweenSlides(1000);
            //sc.setSlideshow(dummySlideshowFactory()); // create sildeshow and give it to its controller
            sc.setSlideshow(defaultSlideshowFactory());
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

        primaryStage.setTitle("Sildeshow Player");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/com/humanbooster/slideshowplayer/view/javafx/UIMain.css").toExternalForm());
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(300);

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


    private Slideshow dummySlideshowFactory() {
        //  create a slideshow
        Slideshow slideshow = new Slideshow();

        for (int i = 0; i < 10; i++) { // add 10 slides
            Slide s = new Slide();

            TextSlideElement title = new TextSlideElement(0.1,0.1,0.8,0.2);
            title.setContent("slide " + i);
            s.addSlideElement(title);

            ImageSlideElement image = new ImageSlideElement(0.1,0.4,0.8,0.5);

            image.setContent("http://placehold.it/" + ((i+1)*100) + "x" + ((i+1)*20));
            s.addSlideElement(image);

            slideshow.addSlide(s);
        }
        System.out.format("Slideshow of %d slides is created\n", 10);
        return slideshow;
    }

    private Slideshow defaultSlideshowFactory() {
        Slideshow slideshow = new Slideshow();
        slideshow.addSlide(this.slideFactory("Bromo", "/resources/images/bromo.jpg"));
        slideshow.addSlide(this.slideFactory("Malaysia", "/resources/images/malaysia.jpg"));
        slideshow.addSlide(this.slideFactory("Mercantour", "/resources/images/mercantour.jpg"));
        slideshow.addSlide(this.slideFactory("Seychelles", "/resources/images/seychelles.jpg"));
        slideshow.addSlide(this.slideFactory("Vietnam", "/resources/images/vietnam.jpg"));
        slideshow.addSlide(this.slideFactory("Brazil", "/resources/images/brazil.jpg"));
        return slideshow;
    }
    private Slide slideFactory(String title, String imageURL) {
        Slide s = new Slide();

        TextSlideElement textSlideElement = new TextSlideElement(0.025,0.025, 0.95, 0.05);  // TextSlideElement
        textSlideElement.setContent(title);

        ImageSlideElement imageSlideElement = new ImageSlideElement(0.025,0.1, 0.95, 0.875); // ImageSlideElement
        imageSlideElement.setContent(imageURL);

        s.addSlideElement(textSlideElement);
        s.addSlideElement(imageSlideElement);

        return s;
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
