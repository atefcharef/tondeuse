package com.mowItNow.tondeuse.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
        private int x;
        private int y;
        private Orientation orientation;


@Override
public String toString() {
        return x + " " + y + " " + orientation;
        }
}