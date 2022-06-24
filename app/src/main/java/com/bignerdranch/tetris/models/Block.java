package com.bignerdranch.tetris.models;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Point;

import androidx.annotation.NonNull;

import com.bignerdranch.tetris.constants.FieldConstants;
import com.google.android.material.shape.ShapeAppearanceModel;

import java.util.Random;

public class Block {
    private int shapeIndex;
    private int frameNumder;
    private BlockColor color;
    private Point position;


    private Block(int shapeIndex, BlockColor blockColor) {
        this.frameNumder = 0;
        this.shapeIndex = shapeIndex;
        this.color = blockColor;
        this.position = new Point( FieldConstants.COLUMN_COUNT.getValue()/2, 0);
    }

    public static Block createBlock() {
        Random random = new Random();
        int shapeIndex = random.nextInt(Shape.values().length);
        BlockColor blockColor = BlockColor.values()[random.nextInt(BlockColor.values().length)];
        Block block = new Block(shapeIndex,blockColor);
        block.position.x = block.position.x - Shape.values()[shapeIndex].getStartPosition();
        return block;
    }


    public enum BlockColor {
        PINK(Color.rgb(255, 105, 180), (byte) 2),
        GREEN(Color.rgb(0,128,0), (byte) 3),
        ORANGE(Color.rgb(255,140,0), (byte) 4),
        YELLOW(Color.rgb(255,255,0), (byte) 5),
        CYAN(Color.rgb(0,255,255), (byte) 6);

        BlockColor(int rgbValue, byte byteValue) {
                this.rgbValue = rgbValue;
                this.byteValue = byteValue;
        }

        private final int rgbValue;
        private final byte byteValue;
    }

    public static int getColor(byte value) {
        for(BlockColor color : BlockColor.values()) {
            if(value == color.byteValue) {
                return color.rgbValue;
            }
        }
        return -1;
    }

    public final void setState(int frame, Point position) {
        this.frameNumder = frame;
        this.position = position;
    }

    @NonNull
    public final byte[][] getShape(int frameNumder) {
        return Shape.values()[shapeIndex].getFrame(frameNumder).as2dByteArray();
    }

    public Point getPosition() {
        return this.position;
    }

    public final int getFrameCount() {
        return Shape.values()[shapeIndex].getFrameCount();
    }

    public int getFrameNumder(){
        return frameNumder;
    }

    public int getColor() {
        return color.rgbValue;
    }

    public byte getStaticValue() {
        return color.byteValue;
    }


}
