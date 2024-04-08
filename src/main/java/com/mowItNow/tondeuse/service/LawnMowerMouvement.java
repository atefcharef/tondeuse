package com.mowItNow.tondeuse.service;


import com.mowItNow.tondeuse.model.Commande;
import com.mowItNow.tondeuse.model.Movable;
import com.mowItNow.tondeuse.model.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class LawnMowerMouvement implements Movable {

    Position position;
    int maxX;
    int maxY;


    @Override
    public void move(Commande instruction) {
        switch (instruction) {
            case D:
                position.setOrientation(position.getOrientation().rotateRight());
                break;
            case G:
                position.setOrientation(position.getOrientation().rotateLeft());
                break;
            case A:
                moveForward();
                break;
        }
    }

    private void moveForward() {
        switch (position.getOrientation()) {
            case N:
                if (position.getY() < maxY)
                    position.setY(position.getY()+1);
                break;
            case E:
                if (position.getX() < maxX)
                    position.setX(position.getX()+ 1);
                break;
            case S:
                if (position.getY() > 0)
                    position.setY(position.getY()-1);
                break;
            case W:
                if (position.getX() > 0)
                    position.setX(position.getX()-1);
                break;
        }
    }

}
