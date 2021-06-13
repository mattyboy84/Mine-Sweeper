package sample;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.ArrayList;

public class ClosedSet {
    int closePos;

    public ClosedSet(int openPos, int i, int j, Group group, Scene scene, ArrayList<Grid> grid, ArrayList<Mine> mine, ArrayList<Labels> labels, int WIDTH, int HEIGHT, int RES, int xLength, int yLength, int closePos, ArrayList<Tiles> tiles, ArrayList<OpenSet> openSet, ArrayList<ClosedSet> closeSet) {
        this.closePos = closePos;
    }

    public int getClosePos() {
        return closePos;
    }

    public void setClosePos(int closePos) {
        this.closePos = closePos;
    }
}
