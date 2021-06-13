package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

//|||||||||||||||||||||||||||||||||||
public class Main extends Application {
    int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    int mineNum = 200;
    int temp = 0;
    Group group = new Group();
    Scene scene = new Scene(group, 9120, 1080);
    ArrayList<Grid> grid = new ArrayList<Grid>();
    ArrayList<Mine> mine = new ArrayList<Mine>();
    ArrayList<Labels> labels = new ArrayList<Labels>();
    ArrayList<Tiles> tiles = new ArrayList<Tiles>();
    ArrayList<OpenSet> openSet = new ArrayList<>();
    ArrayList<ClosedSet> closeSet = new ArrayList<>();
    boolean gameOver = false;
    int pos = 0;

    @Override
    public void start(Stage stage) throws Exception {

        int RES = 40;
        int xLength = WIDTH / RES;
        int yLength = HEIGHT / RES;
        System.out.println("y length " + yLength);
        System.out.println("x length " + xLength);
        for (int i = 0; i < xLength; i++) {//creates the rectangles seen in the window
            for (int j = 0; j < yLength; j++) {
                grid.add(new Grid(i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet));
                pos++;
            }
        }
        pos = 0;
        for (int i = 0; i < mineNum; i++) {//places mines
            mine.add(new Mine(i, mineNum, grid, mine, labels, group, scene, WIDTH, HEIGHT, RES, xLength, yLength, tiles, openSet, closeSet));
        }


        for (int i = 0; i < xLength; i++) {//creates the labels
            for (int j = 0; j < yLength; j++) {
                labels.add(new Labels(i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet));
                pos++;
            }
        }
        int mineToCheck;
        for (int i = 0; i < mine.size(); i++) {

            mineToCheck = mine.get(i).getLocation() + yLength;
            thing(mineToCheck);

            mineToCheck = mine.get(i).getLocation() - yLength;
            thing(mineToCheck);

            mineToCheck = mine.get(i).getLocation() - 1;
            thing(mineToCheck);

            mineToCheck = mine.get(i).getLocation() + 1;
            thing(mineToCheck);

            mineToCheck = mine.get(i).getLocation() + yLength + 1;
            thing(mineToCheck);

            mineToCheck = mine.get(i).getLocation() + yLength - 1;
            thing(mineToCheck);

            mineToCheck = mine.get(i).getLocation() - yLength + 1;
            thing(mineToCheck);

            mineToCheck = mine.get(i).getLocation() - yLength - 1;
            thing(mineToCheck);

        }

        for (int i = 0; i < labels.size(); i++) {
            Labels label = labels.get(i);
            if (label.getLabelText() == 0) {
                label.getLabel().setText("");
                grid.get(i).setTileInfo("free");
            }
        }

        for (Mine item : mine) {
            labels.get(item.getLocation()).setLabelText(0);
            grid.get(item.getLocation()).setTileInfo("mine");
        }
        pos = 0;
        for (int i = 0; i < xLength; i++) {//creates the overlay tiles
            for (int j = 0; j < yLength; j++) {
                tiles.add(new Tiles(i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet,gameOver));
                pos++;
            }
        }
        pos = 0;


        scene.setOnKeyPressed(event -> {//debug stuff
            switch (event.getCode()) {
                case D:
                    System.out.println("grid " + grid.size());
                    System.out.println("mine " + mine.size());
                    System.out.println("labels " + labels.size());
                    System.out.println("tiles " + tiles.size());

                    break;
                case F:
                    stage.setFullScreen(!stage.isFullScreen());

                    break;

                case E:
                    for (Tiles tile : tiles) {
                        group.getChildren().remove(tile.tile);
                    }
                    break;

                case R:
                    for (Tiles tile : tiles) {
                        group.getChildren().add(tile.tile);
                    }
                    break;
                case O:
                    for (int i = 0; i < openSet.size(); i++) {
                        System.out.println(openSet.get(i).getOpenPos());
                    }
                    System.out.println("--------------------");
                    break;
                case C:
                    for (int i = 0; i < closeSet.size(); i++) {
                        System.out.println(closeSet.get(i).getClosePos());
                    }

                    break;
                case G:
                    openSet.clear();
                    break;
            }
        });

        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);

    }

    private void thing(int mineToCheck) {
        try {
            labels.get(mineToCheck).setLabelText(labels.get(mineToCheck).getLabelText() + 1);
            switch (labels.get(mineToCheck).getLabelText()) {//updates colour of the labels according to No. of mine neighbours
                case 1:
                    labels.get(mineToCheck).getLabel().setTextFill(Color.BLUE);
                    break;
                case 2:
                    labels.get(mineToCheck).getLabel().setTextFill(Color.GREEN);
                    break;
                case 3:
                    labels.get(mineToCheck).getLabel().setTextFill(Color.RED);
                    break;
                case 4:
                    labels.get(mineToCheck).getLabel().setTextFill(Color.DARKBLUE);
                    break;
                case 5:
                    labels.get(mineToCheck).getLabel().setTextFill(Color.DARKRED);
                    break;
                case 6:
                    labels.get(mineToCheck).getLabel().setTextFill(Color.TEAL);
                    break;
                case 7:
                    labels.get(mineToCheck).getLabel().setTextFill(Color.BLACK);
                    break;
                case 8:
                    labels.get(mineToCheck).getLabel().setTextFill(Color.GRAY);
                    break;
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
