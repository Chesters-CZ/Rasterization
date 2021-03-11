package cz.educanet.tranformations.logic;

import cz.educanet.tranformations.logic.models.Coordinate;
import cz.educanet.tranformations.presentation.canvas.Canvas;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.stream;

public class ScreenManager {

    public static shape desiredShape = shape.RECTANGLE;

    private Set<Coordinate> selectedPoints = new HashSet<>();

    public void select(Coordinate coordinate) {
        selectedPoints.add(coordinate);
    }

    public void unselect(Coordinate coordinate) {
        selectedPoints.remove(coordinate);
    }

    public boolean isSelected(Coordinate coordinate) {
        return selectedPoints.contains(coordinate);
    }

    public Set<Coordinate> getSelectedPoints() {
        return selectedPoints;
    }

    public boolean isFilledIn(Coordinate coordinate) { // TODO: Implement this
        if (desiredShape == shape.RECTANGLE) {
            if (coordinate.getY() > 0 && coordinate.getX() > 0
                    && coordinate.getY() < Canvas.getScreenHeight() - 1 && coordinate.getX() < Canvas.getScreenWidth() - 1)
                return true;
            else return false;
        }

        // if triangle desired
        else {
            int[][] bodytrojuhelniku = new int[3][2];
            int i = 0;
            for (Coordinate vyfluscoordinate : getSelectedPoints()) {
                bodytrojuhelniku[i][0] = vyfluscoordinate.getX();
                bodytrojuhelniku[i][1] = vyfluscoordinate.getY();
                i++;
            }

            return (
                    /* strana 0 */(double) (coordinate.getX() - bodytrojuhelniku[1][0]) * (bodytrojuhelniku[2][1] - bodytrojuhelniku[1][1]) - (coordinate.getY() - bodytrojuhelniku[1][1]) * (bodytrojuhelniku[2][0] - bodytrojuhelniku[1][0]) < 0
                    &&
                    /* strana 1 */(double) (coordinate.getX() - bodytrojuhelniku[2][0]) * (bodytrojuhelniku[0][1] - bodytrojuhelniku[2][1]) - (coordinate.getY() - bodytrojuhelniku[2][1]) * (bodytrojuhelniku[0][0] - bodytrojuhelniku[2][0]) < 0
                    &&
                    /* strana 2 */(double) (coordinate.getX() - bodytrojuhelniku[0][0]) * (bodytrojuhelniku[1][1] - bodytrojuhelniku[0][1]) - (coordinate.getY() - bodytrojuhelniku[0][1]) * (bodytrojuhelniku[1][0] - bodytrojuhelniku[0][0]) < 0
            );
        }
    }

    public enum shape {TRIANGLE, RECTANGLE}
}
