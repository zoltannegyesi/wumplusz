package hu.nye.progtech.wumplusz.service.map;

import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.wumplusz.model.MapVO;
import hu.nye.progtech.wumplusz.model.enums.Entity;
import hu.nye.progtech.wumplusz.service.game.GameStore;
import hu.nye.progtech.wumplusz.service.input.InputReader;
import hu.nye.progtech.wumplusz.service.input.UserInteractionHandler;
import hu.nye.progtech.wumplusz.service.util.InstructionOutputWriter;

/**
 * Komponens, amely a pálya módosítására használatos.
 */
public class MapEditor {

    private final InputReader inputReader;

    private final GameStore gameStore;

    private final UserInteractionHandler interactionHandler;

    public MapEditor(InputReader inputReader, GameStore gameStore, UserInteractionHandler interactionHandler) {
        this.inputReader = inputReader;
        this.gameStore = gameStore;
        this.interactionHandler = interactionHandler;
    }


    /**
     * Ez vezényeli le a pályaszerkesztést.
     */
    public void edit() {
        handleTableSize();
        MapVO mapVO = gameStore.getMapVO();
        gameStore.setAvailableEntities(createAvailableEntityList(mapVO.getSize()));
        Entity chosenEntity;
        Integer x;
        Integer y;
        do {
            chosenEntity = interactionHandler.getChosenEntity(gameStore.getAvailableEntities());
            x = interactionHandler.getCoordinate(mapVO, InstructionOutputWriter.X_COORDINATE);
            y = interactionHandler.getCoordinate(mapVO, InstructionOutputWriter.Y_COORDINATE);
            if (mapVO.isEntityPlaceable(chosenEntity, x, y)) {
                mapVO.addEntity(chosenEntity, x, y);
                if (!Entity.FAL.equals(chosenEntity)) {
                    gameStore.getAvailableEntities().remove(chosenEntity);
                }
            } else {
                System.out.println("Nem tehető ide ez az entitás, próbáld máshová!");
            }
            System.out.println(gameStore.getMapVO().toString());

        } while (gameStore.getAvailableEntities().size() > 1);
    }

    /**
     * Lekéri, beállítja a tábla méretét, majd létrehozza a MapVO-t.
     */
    private void handleTableSize() {
        System.out.print("A pálya méretét ");
        Integer tableSize = null;
        do {
            System.out.println("6-20-ig add meg!");
            tableSize = inputReader.readInteger();
        } while (!(tableSize >= 6 && tableSize <= 20));
        gameStore.setMapVO(new MapVO(tableSize));
        System.out.println(gameStore.getMapVO().toString());
    }

    /**
     * Létrehozza a letehető entitások listáját.
     */
    private List<Entity> createAvailableEntityList(Integer mapSize) {
        List<Entity> result = new ArrayList<>();
        Integer wumpusCount = 0;
        if (mapSize <= 8) {
            wumpusCount = 1;
        } else if (mapSize >= 9 && mapSize <= 14) {
            wumpusCount = 2;
        } else {
            wumpusCount = 3;
        }
        for (int i = 0; i < wumpusCount; i++) {
            result.add(Entity.WUMPUSZ);
        }
        result.add(Entity.ARANY);
        result.add(Entity.HOS);
        result.add(Entity.FAL);
        result.add(Entity.VEREM);
        return result;
    }
}
