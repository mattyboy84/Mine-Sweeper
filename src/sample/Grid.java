package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Objects;

public class Grid {
    Rectangle grid;
    int pos;
    String tileInfo;

    public Grid(int i, int j, Group group, Scene scene, ArrayList<Grid> grid, ArrayList<Mine> mine, ArrayList<Labels> labels, int width, int height, int RES, int WIDTH, int HEIGHT, int pos, ArrayList<Tiles> tiles, ArrayList<OpenSet> openSet, ArrayList<ClosedSet> closeSet) {
        this.grid = new Rectangle((WIDTH - (WIDTH - (RES * i))), (HEIGHT - (HEIGHT - (RES * j))), RES, RES);
        this.grid.setFill(Color.GRAY);
        this.grid.setStroke(Color.BLACK);
        //this.grid.setStrokeWidth(1);
        group.getChildren().add(this.grid);
        this.pos=pos;
        this.tileInfo="free";
        this.grid.setOnMouseClicked(event -> {
            System.out.println(this.pos);
            System.out.println("tile info " + this.tileInfo);
        });
    }

    public Rectangle getGrid() {
        return grid;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getTileInfo() {
        return tileInfo;
    }

    public void setTileInfo(String tileInfo) {
        this.tileInfo = tileInfo;
    }

    public void setGrid(Rectangle grid) {
        this.grid = grid;
    }
}
