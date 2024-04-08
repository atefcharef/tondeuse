package com.mowItNow.tondeuse.service;

import com.mowItNow.tondeuse.model.Commande;
import com.mowItNow.tondeuse.model.Orientation;
import com.mowItNow.tondeuse.model.Position;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LawnMowerService {

    public List<Position> lawn(String filename) {
        List<Position> finalPositions = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            if (!scanner.hasNextInt()) return finalPositions;
            int maxX = scanner.nextInt();
            int maxY = scanner.nextInt();

            while (scanner.hasNextInt()) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                Orientation orientation = Orientation.valueOf(scanner.next());

                List<Commande> commandes = new ArrayList<>();
                if (scanner.hasNext()) {
                    for (char instruction : scanner.next().toCharArray()) {
                        commandes.add(Commande.valueOf(String.valueOf(instruction)));
                    }
                }

                LawnMowerMouvement lawnMowerMouvement = LawnMowerMouvement.builder().position(new Position(x, y, orientation)).maxX(maxX).maxY(maxY).build();
                commandes.forEach(lawnMowerMouvement::move);

                finalPositions.add(lawnMowerMouvement.getPosition());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        }

        return finalPositions;
    }
    }
