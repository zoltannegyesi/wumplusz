package hu.nye.progtech.wumplusz.service.input;


import java.util.InputMismatchException;
import java.util.List;

import hu.nye.progtech.wumplusz.model.MapVO;
import hu.nye.progtech.wumplusz.model.enums.Entity;
import hu.nye.progtech.wumplusz.service.util.InstructionOutputWriter;

/**
 * Komponens, ami kezeli a felhaszálói interakciót.
 */
public class UserInteractionHandler {

    private final InputReader inputReader;

    public UserInteractionHandler(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    /**
     * Lekéri a felhasználó nevét.
     */
    public String getUsername() {
        System.out.print("Add meg a neved: ");
        return inputReader.readString();
    }

    /**
     * Lekéri a felhasználótól a menüpontot.
     */
    public Integer getMenuPoint() {
        InstructionOutputWriter.printMenu();
        try {
            return inputReader.readInteger();
        } catch (InputMismatchException e) {
            System.out.println("Kérlek egy számot adj meg 1-6 között.");
            return getMenuPoint();
        }
    }

    /**
     * Lekéri az entitást a fasználótól.
     */
    public Entity getChosenEntity(List<Entity> availableEntities) {
        InstructionOutputWriter.printEntity(availableEntities);
        String entityChoice = inputReader.readString();
        Entity result = null;
        for (Entity entity : Entity.values()) {
            if (entity.name().equals(entityChoice)) {
                result = Entity.valueOf(entityChoice);
            }
        }
        if (result == null) {
            System.out.println("Nem létező entitás, próbáld újra!");
            return getChosenEntity(availableEntities);
        }
        return result;
    }

    /**
     * Lekéri a koordinátát a felhasználótól.
     */
    public Integer getCoordinate(MapVO mapVO, String coordinateType) {
        Integer coordinate;
        do {
            InstructionOutputWriter.printCoordinate(coordinateType);
            coordinate = inputReader.readInteger();
            System.out.println(coordinate);
        } while (!mapVO.isInsideMap(coordinate));
        return coordinate;
    }
}
