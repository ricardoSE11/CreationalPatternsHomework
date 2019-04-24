package sample.Characters;

import javafx.scene.image.Image;
import sample.Weapons.IWeapon;
import sample.MovementMethod;
import sample.Weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class Character extends Thread implements ICharacter, ICharacterPrototype {

    String name; //TODO: Decide if this attribute is necessary
    MovementMethod movementMethod;
    Weapon weapon;
    ArrayList<Image> sprites;
    Image currentImage;
    double healthPoints;
    double damagePerSecond;
    int level;
    List<Integer> volume;
    int requiredLevel;
    int cost;

    // Drawing attributes;
    boolean isMoving;
    double x_position;
    double y_position;
    int speed;

    public abstract Image getCurrentImage();

    //<editor-fold desc="Getters and Setters">
    public double getX_position() {
        return x_position;
    }

    public double getY_position() {
        return y_position;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getLevel() {
        return level;
    }

    //</editor-fold>

    @Override
    public abstract ICharacterPrototype clone();

    @Override
    public abstract ICharacterPrototype deepClone();

    @Override
    public abstract void doAction();


}