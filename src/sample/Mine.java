package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

public class Mine {

    Random rand = new Random();
    Circle circle;
    int location;

    public Mine(int i, int mineNum, ArrayList<Grid> grid, ArrayList<Mine> mines, ArrayList<Labels> labels, Group group, Scene scene, int WIDTH, int HEIGHT, int RES, int xLength, int yLength, ArrayList<Tiles> tiles, ArrayList<OpenSet> openSet, ArrayList<ClosedSet> closeSet) {
        boolean replicate = true;
        int locationTEMP = rand.nextInt(grid.size());
        int tempTemp = 0;

        while (replicate) {
            locationTEMP = rand.nextInt(grid.size());
            tempTemp = locationTEMP;

            replicate = false;
            for (int j = 0; j < mines.size(); j++) {
                if (locationTEMP == mines.get(j).getLocation()) {
                    replicate = true;
                }
            }


            if (locationTEMP < yLength || locationTEMP > ((xLength * yLength) - yLength)) {
                replicate = true;
            }


            while (tempTemp >= yLength) {
                tempTemp = tempTemp - yLength;
            }
            if (tempTemp == 26 || tempTemp == 0) {
                replicate = true;
            }
        }
        this.location = locationTEMP;

        grid.get(this.location).setTileInfo("mine");
        this.circle = new Circle(grid.get(locationTEMP).getGrid().getX() + ((int) (RES / 2)), grid.get(locationTEMP).getGrid().getY() + ((int) (RES / 2)), ((int) (RES / 2) - (int) ((RES / 2) / 10)));
        //this.circle.setFill();
        group.getChildren().add(this.circle);
        this.circle.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY:
                    System.out.println(this.location);
                    //System.out.println("bomb ");
                    break;
            }
        });

    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
