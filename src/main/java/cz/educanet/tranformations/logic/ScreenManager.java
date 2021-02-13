package cz.educanet.tranformations.logic;

import cz.educanet.tranformations.logic.models.Coordinate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.stream;

public class ScreenManager {

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
