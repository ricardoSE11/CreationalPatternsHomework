package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application implements Runnable {


    Controller mainController;
    Thread mainThread;
    Image backgroundImage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = loader.load();

        mainController = loader.getController();
        mainController.init(primaryStage);

        primaryStage.setTitle("Clash of Creational Patterns");
        primaryStage.setScene(new Scene(root, 900,800));
        primaryStage.show();

        initComponents();
    }

    private void initComponents()
    {
        try
        {
            backgroundImage = new Image(new FileInputStream("src/Assets/General/green_background.jpg"));
            this.mainThread = new Thread(this);
            this.mainThread.start();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void run()
    {
        long start;
        long elapsed;
        long wait;
        int fps = 30;
        long time = 1000 / fps; // It seems that 1000 is way too fast or something

        while(true){
            try {
                start = System.nanoTime();
                elapsed = System.nanoTime() - start;
                wait = time - elapsed / 1000000; //1000000
                Thread.sleep(wait);
                mainController.reDraw(backgroundImage);
            }
            catch (InterruptedException ex) {}
        }
    }
}
