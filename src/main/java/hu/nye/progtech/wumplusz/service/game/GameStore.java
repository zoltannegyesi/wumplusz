package hu.nye.progtech.wumplusz.service.game;

import java.util.List;

import hu.nye.progtech.wumplusz.model.MapVO;
import hu.nye.progtech.wumplusz.model.enums.Entity;

/**
 * Komponens, ami a játékkal kapcsolatos információkat tárolja.
 */

public class GameStore {

    private String userName;

    private MapVO mapVO;

    private List<Entity> availableEntities;


    /**
     * Visszaadja a MapVO-t.
     */
    public MapVO getMapVO() {
        return mapVO;
    }


    /**
     * Beállítja a MapVO-t.
     */
    public void setMapVO(MapVO mapVO) {
        this.mapVO = mapVO;
    }

    /**
     * Beállítja az elérhető entitásokat.
     */
    public void setAvailableEntities(List<Entity> entities) {
        this.availableEntities = entities;
    }

    /**
     * Visszaadja az elérhető entitásokat.
     */
    public List<Entity> getAvailableEntities() {
        return this.availableEntities;
    }

    /**
     * Beállítja a felhasználónevet.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Stringként visszaadja ezt az objektumot.
     */
    @Override
    public String toString() {
        return "GameStore{" +
                "userName='" + userName + '\'' +
                ", mapVO=" + mapVO +
                '}';
    }
}
