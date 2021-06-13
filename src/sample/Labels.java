package sample;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import jdk.nashorn.internal.runtime.ECMAException;

import java.util.ArrayList;

public class Labels {

    Label label;
    int labelText=0;
    int pos;
    int localMines;

    public Labels(int i, int j, Group group, Scene scene, ArrayList<Grid> grid, ArrayList<Mine> mine, ArrayList<Labels> labels, int WIDTH, int HEIGHT, int RES, int xLength, int yLength, int pos, ArrayList<Tiles> tiles, ArrayList<OpenSet> openSet, ArrayList<ClosedSet> closeSet) {
        this.label = new Label(String.valueOf(labelText));
//        label.setFont(javafx.scene.text.Font.font("Upheaval", FontWeight.BOLD, fontSize));//font and such
        this.label.setFont(Font.font("Upheaval", FontWeight.BOLD,22));
        Text text = new Text(this.label.getText());
        text.setFont(Font.font("Upheaval", FontWeight.BOLD,22));
        this.pos = pos;
        grid.get(this.pos).setTileInfo("label");
        //System.out.println(this.pos);
        this.label.relocate(
                grid.get(this.pos).grid.getX() + (RES / 2) - (text.getBoundsInParent().getWidth() / 2)
                ,
                grid.get(this.pos).grid.getY() + (RES / 2) - (text.getBoundsInParent().getHeight() / 2)
        );

        this.label.setTextFill(Color.RED);


        group.getChildren().add(this.label);

        this.label.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY:
                    System.out.println(this.pos);
                    break;
            }
        });
    }



    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public int getLabelText() {
        return labelText;
    }

    public void setLabelText(int labelText) {
        if (labelText!=0) {
            this.labelText = labelText;
            this.label.setText(String.valueOf(this.labelText));
        }else {
            this.labelText = 0;
            this.label.setText("");
        }
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
