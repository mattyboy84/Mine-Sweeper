package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Tiles {

    Rectangle tile;
    int pos;
    String tileInfo;
    int currentNode;
    int recursion;
    Random rand = new Random();
    int temp;
    boolean newEntry = true;
    boolean inClosedSet = false;
    boolean replicate = false;
    int nodeToCheck;
    Boolean flagged;
    String previousText;

    public Tiles(int i, int j, Group group, Scene scene, ArrayList<Grid> grid, ArrayList<Mine> mine, ArrayList<Labels> labels, int WIDTH, int HEIGHT, int RES, int xLength, int yLength, int pos, ArrayList<Tiles> tiles, ArrayList<OpenSet> openSet, ArrayList<ClosedSet> closeSet, boolean gameOver) {
        this.tile = new Rectangle((WIDTH - (WIDTH - (RES * i))), (HEIGHT - (HEIGHT - (RES * j))), RES, RES);
        this.tile.setFill(Color.LIGHTGRAY);
        this.tile.setStroke(Color.BLACK);
        this.tile.setOpacity(1);
        group.getChildren().add(this.tile);
        this.pos = pos;
        this.tileInfo = grid.get(this.pos).getTileInfo();
        this.flagged = false;


        this.tile.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY:
                    System.out.println(gameOver);
                    if (!gameOver) {
                        if (!this.flagged) {

                            openSet.add(new OpenSet(currentNode, i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, this.pos, tiles, openSet, closeSet));
                            currentNode = this.pos;

                            tileSmasher(i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet,gameOver);
                        }
                    }
                    break;
                case SECONDARY:
                    //System.out.println(tiles.get(this.pos).getTileInfo() + " " + this.pos);
                    this.flagged = !this.flagged;
                    if (this.flagged) {
                        this.tile.setFill(Color.RED);

                    } else {
                        this.tile.setFill(Color.LIGHTGRAY);
                    }

                    break;
            }
        });

    }

    private void tileSmasher(int i, int j, Group group, Scene scene, ArrayList<Grid> grid, ArrayList<Mine> mine, ArrayList<Labels> labels, int WIDTH, int HEIGHT, int RES, int xLength, int yLength, int pos, ArrayList<Tiles> tiles, ArrayList<OpenSet> openSet, ArrayList<ClosedSet> closeSet,boolean gameOver) {

        switch (tiles.get(currentNode).getTileInfo()) {
            case "free":

                System.out.println("free tile removed");
                if (!tiles.get(currentNode).getFlagged()) {
                    group.getChildren().remove(tiles.get(currentNode).getTile());
                    tiles.get(currentNode).getTile().setVisible(false);
                }

                if ((currentNode < (xLength * yLength) - yLength)) {
                    openSet.add(new OpenSet(currentNode + yLength, i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet));//right
                }
                if (currentNode > yLength) {
                    openSet.add(new OpenSet(currentNode - yLength, i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet));//left
                }
                temp = currentNode;
                while (temp >= yLength) {
                    temp = temp - yLength;
                }
                if (!(temp == 0)) {
                    openSet.add(new OpenSet(currentNode - 1, i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet));//above
                }
                if (!(temp == 26)) {
                    openSet.add(new OpenSet(currentNode + 1, i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet));//below
                }
                if (currentNode < ((xLength * yLength) - yLength) && (!(temp == 26))) {
                    openSet.add(new OpenSet(currentNode + yLength + 1, i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet));//right below
                }

                if ((!(temp == 0)) && (currentNode < ((xLength * yLength) - yLength))) {
                    openSet.add(new OpenSet(currentNode + yLength - 1, i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet));//right above
                }
                if ((currentNode > yLength) && (!(temp == 26))) {
                    openSet.add(new OpenSet(currentNode - yLength + 1, i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet));//left below
                }

                if ((!(temp == 0)) && ((currentNode > yLength))) {
                    openSet.add(new OpenSet(currentNode - yLength - 1, i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet));//left above
                }

                for (int k = 0; k < openSet.size(); k++) {
                    if (openSet.get(k).getOpenPos() == 696969) {
                        openSet.remove(openSet.get(k));
                        k--;
                    }
                }
                for (int k = 0; k < openSet.size(); k++) {
                    if (currentNode == openSet.get(k).getOpenPos()) {
                        closeSet.add(new ClosedSet(openSet.get(k).getOpenPos(), i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet));
                        openSet.remove(openSet.get(k));
                        k--;
                    }
                }


                currentNode = openSet.get(1).getOpenPos();
                if (recursion < 1000) {
                    recursion++;
                    tileSmasher(i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet,gameOver);
                } else {
                    openSet.clear();
                }


                break;

            case "label":
                System.out.println("label tile removed");
                group.getChildren().remove(tiles.get(currentNode).getTile());
                tiles.get(currentNode).getTile().setVisible(false);

                for (int k = 0; k < openSet.size(); k++) {
                    if (currentNode == openSet.get(k).getOpenPos()) {
                        closeSet.add(new ClosedSet(openSet.get(k).getOpenPos(), i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet));
                        openSet.remove(openSet.get(k));
                        k--;
                    }
                }


                if (openSet.size() > 1) {
                    currentNode = openSet.get(1).getOpenPos();
                }
                if (recursion < 1000) {
                    recursion++;
                    tileSmasher(i, j, group, scene, grid, mine, labels, WIDTH, HEIGHT, RES, xLength, yLength, pos, tiles, openSet, closeSet,gameOver);
                } else {
                    openSet.clear();
                }

                break;
            case "mine":
                System.exit(6);
                System.out.println("mine hit");
                openSet.clear();
                break;

        }


    }
        /*
        if ((currentNode < (xLength * yLength) - yLength)) {
                    openSet.add(new OpenSet(currentNode + yLength));//right
                }
                if (currentNode > yLength) {
                    openSet.add(new OpenSet(currentNode - yLength));//left
                }
                temp = currentNode;
                while (temp >= yLength) {
                    temp = temp - yLength;
                }
                if (!(temp == 0)) {
                    openSet.add(new OpenSet(currentNode - 1));//above
                }
                if (!(temp == 26)) {
                    openSet.add(new OpenSet(currentNode + 1));//below
                }
                if (currentNode < ((xLength * yLength) - yLength) && (!(temp == 26))) {
                    openSet.add(new OpenSet(currentNode + yLength + 1));//right below
                }

                if ((!(temp == 0)) && (currentNode < ((xLength * yLength) - yLength))) {
                    openSet.add(new OpenSet(currentNode + yLength - 1));//right above
                }
                if ((currentNode > yLength) && (!(temp == 26))) {
                    openSet.add(new OpenSet(currentNode - yLength + 1));//left below
                }

                if ((!(temp == 0)) && ((currentNode > yLength))) {
                    openSet.add(new OpenSet(currentNode - yLength - 1));//left above
                }
         */

    public Rectangle getTile() {
        return tile;
    }

    public void setTile(Rectangle tile) {
        this.tile = tile;
    }

    public Boolean getFlagged() {
        return flagged;
    }

    public void setFlagged(Boolean flagged) {
        this.flagged = flagged;
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

    public int getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(int currentNode) {
        this.currentNode = currentNode;
    }
}
