package com.mowItNow.tondeuse;

import com.mowItNow.tondeuse.model.Orientation;
import com.mowItNow.tondeuse.model.Commande;
import com.mowItNow.tondeuse.model.Position;
import com.mowItNow.tondeuse.service.LawnMowerMouvement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LawnMowerMouvementTest {

    private LawnMowerMouvement lawnMowerMouvement;

    @BeforeEach
    void setUp() {
        Position initialPosition = new Position(1, 2, Orientation.N);
        lawnMowerMouvement = LawnMowerMouvement.builder().position(initialPosition).maxX(5).maxY(5).build();
    }

    @Test
    void shouldMoveForwardWhenCommandIsAdvanceAndOrientationIsNorth() {
        lawnMowerMouvement.move(Commande.A);
        assertEquals(new Position(1, 3, Orientation.N), lawnMowerMouvement.getPosition());
    }

    @Test
    void shouldNotMoveWhenAtNorthEdgeAndCommandIsAdvance() {
        lawnMowerMouvement.getPosition().setY(5);
        lawnMowerMouvement.move(Commande.A);
        assertEquals(new Position(1, 5, Orientation.N), lawnMowerMouvement.getPosition());
    }

    @Test
    void shouldRotateRightWhenCommandIsRight() {
        lawnMowerMouvement.move(Commande.D);
        assertEquals(new Position(1, 2, Orientation.E), lawnMowerMouvement.getPosition());
    }

    @Test
    void shouldRotateLeftWhenCommandIsLeft() {
        lawnMowerMouvement.move(Commande.G);
        assertEquals(new Position(1, 2, Orientation.W), lawnMowerMouvement.getPosition());
    }
}