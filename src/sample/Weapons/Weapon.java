package sample.Weapons;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import javafx.scene.image.Image;

public class Weapon implements IWeapon{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("movementMethod")
    @Expose
    private String movementMethod;
    @SerializedName("sprites")
    @Expose
    private List<String> sprites = null; //TODO: Define if this attributes is necessary
    @SerializedName("range")
    @Expose
    private Double range;
    @SerializedName("damage")
    @Expose
    private Double damage;
    @SerializedName("areaOfEffect")
    @Expose
    private List<Double> areaOfEffect = null;
    @SerializedName("canBeUsedBy")
    @Expose
    private List<String> canBeUsedBy = null;

    private int level;
    // Drawing attributes

    private Image image;
    double x_position;
    double y_position;
    int speed;


    public Weapon(String name) {
        this.name = name;
    }

    //<editor-fold desc="Getters and Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovementMethod() {
        return movementMethod;
    }

    public void setMovementMethod(String movementMethod) {
        this.movementMethod = movementMethod;
    }

    public List<String> getSprites() {
        return sprites;
    }

    public void setSprites(List<String> sprites) {
        this.sprites = sprites;
    }

    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }

    public Double getDamage() {
        return damage;
    }

    public void setDamage(Double damage) {
        this.damage = damage;
    }

    public List<Double> getAreaOfEffect() {
        return areaOfEffect;
    }

    public void setAreaOfEffect(List<Double> areaOfEffect) {
        this.areaOfEffect = areaOfEffect;
    }

    public List<String> getCanBeUsedBy() {
        return canBeUsedBy;
    }

    public void setCanBeUsedBy(List<String> canBeUsedBy) {
        this.canBeUsedBy = canBeUsedBy;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getX_position() {
        return x_position;
    }

    public void setX_position(double x_position) {
        this.x_position = x_position;
    }

    public double getY_position() {
        return y_position;
    }

    public void setY_position(double y_position) {
        this.y_position = y_position;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    //</editor-fold>

    @Override
    public void doAttack() {
        //TODO: Define each attack depending on the name  :(
    }

    public boolean canBeUsedBy(String characterName){
        for (String currentCharacter : this.getCanBeUsedBy()){
            if (currentCharacter.toLowerCase().equals(characterName)){
                return true;
            }
        }
        return false;
    }
}