package com.mbola.deminer.classes;

import android.content.Context;
import android.graphics.Color;

import com.mbola.deminer.R;
import com.mbola.deminer.shapes.RegularPolygonShape;
import com.mbola.deminer.views.PolygonImageView;

import services.Service;

public class Cell {

    private PolygonImageView polygonImageView;
    private Cell[] neighbours;
    private boolean isRevealed;
    private boolean hasBomb;
    private int neighbouringBombsNumber;

    public Cell(Context context, int viewId) {
        this.polygonImageView = new PolygonImageView(context);
        this.polygonImageView.setId(viewId+1);
        this.polygonImageView.setImageResource(R.drawable.cat07);
        //TODO make grey background
        this.polygonImageView.setPadding(0, 0, 0, 0);
        //this.polygonImageView.addBorder(5, android.R.color.black);
        this.polygonImageView.addBorder(5, Color.BLACK);
        this.polygonImageView.addShadow(10f, 0f, 0f, Color.BLACK);
        this.polygonImageView.setCornerRadius(0);
        this.polygonImageView.setVertices(6);
        this.polygonImageView.setX(0);
        this.polygonImageView.setY(0);
        this.polygonImageView.setPolygonShape(new RegularPolygonShape());
        //TODO do not forget to add to layout in Grid class
        this.neighbours = new Cell[] { null, null, null, null, null, null};
        this.isRevealed = false;
        this.neighbouringBombsNumber = 0;
    }

    public PolygonImageView getPolygonImageView() {
        return polygonImageView;
    }

    public void setPolygonImageView(PolygonImageView polygonImageView) {
        this.polygonImageView = polygonImageView;
    }

    public Cell[] getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Cell[] neighbours) {
        this.neighbours = neighbours;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isHasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public int getNeighbouringBombsNumber() {
        return neighbouringBombsNumber;
    }

    public void setNeighbouringBombsNumber(int neighbouringBombsNumber) {
        this.neighbouringBombsNumber = neighbouringBombsNumber;
    }

    public void setTopLeftNeighbour(Cell n) {
        this.neighbours[0] = n;
    }

    public void setTopNeighbour(Cell n) {
        this.neighbours[1] = n;
    }

    public void setTopRightNeighbour(Cell n) {
        this.neighbours[2] = n;
    }

    public void setBottomRightNeighbour(Cell n) {
        this.neighbours[3] = n;
    }

    public void setBottomNeighbour(Cell n) {
        this.neighbours[4] = n;
    }

    public void setBottomLeftNeighbour(Cell n) {
        this.neighbours[5] = n;
    }

    public Cell getTopLeftNeighbour() {
        return this.neighbours[0];
    }

    public Cell getTopNeighbour() {
        return this.neighbours[1];
    }

    public Cell getTopRightNeighbour() {
        return this.neighbours[2];
    }

    public Cell getBottomRightNeighbour() {
        return this.neighbours[3];
    }

    public Cell getBottomNeighbour() {
        return this.neighbours[4];
    }

    public Cell getBottomLeftNeighbour() {
        return this.neighbours[5];
    }

    public float getDiameter(Context context) {
        return Service.toPixel(
                context,
                Math.abs(this.polygonImageView.getPolygonShapeSpec().getDiameter())
                );
    }

    public void setX(float x) {
        this.polygonImageView.setX(x);
    }

    public void setY(float y) {
        this.polygonImageView.setY(y);
    }

    public float getX() {
        return this.polygonImageView.getX();
    }

    public float getY() {
        return this.polygonImageView.getY();
    }

    public void setText(String text) {
        this.polygonImageView.setText(text);
    }

    public String getText() {
        return this.polygonImageView.getText();
    }

    public void setColor(int color) {
        this.polygonImageView.setBorderColor(color);
    }

    public void revealEmptiesRecursively() {
        //System.out.println("BOOM"+this.isRevealed);
        if (!this.isRevealed) {
            this.setRevealed(true);
            if (this.neighbouringBombsNumber != 0) {
                this.setText(String.valueOf(this.neighbouringBombsNumber));
            }
            this.setColor(Color.GRAY);
            if (this.neighbouringBombsNumber == 0) {
                for (int i = 0; i<this.neighbours.length; i++) {
                    if (this.neighbours[i] != null) {
                        if (!this.neighbours[i].isHasBomb()) {
                            this.neighbours[i].revealEmptiesRecursively();
                        }
                    }
                }
            }
        }
    }


}