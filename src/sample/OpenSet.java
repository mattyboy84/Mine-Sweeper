package sample;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.util.ArrayList;

public class OpenSet {
    int openPos;
    boolean repeat;

    public OpenSet(int pos1, int i, int j, Group group, Scene scene, ArrayList<Grid> grid, ArrayList<Mine> mine, ArrayList<Labels> labels, int WIDTH, int HEIGHT, int RES, int xLength, int yLength, int pos, ArrayList<Tiles> tiles, ArrayList<OpenSet> openSet, ArrayList<ClosedSet> closeSet) {


        for (int k = 0; k < openSet.size(); k++) {
            if (pos1 == openSet.get(k).getOpenPos()) {
                repeat = true;
            }
        }


        for (int k = 0; k <closeSet.size() ; k++) {
            if (pos1==closeSet.get(k).getClosePos()){
                repeat=true;
            }
        }


        if (!repeat) {
            this.openPos = pos1;
        }else {
            this.openPos=696969;
        }
    }

    public int getOpenPos() {
        return openPos;
    }

    public void setOpenPos(int openPos) {
        this.openPos = openPos;
    }
}
