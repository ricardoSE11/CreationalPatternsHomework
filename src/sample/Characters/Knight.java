package sample.Characters;

import javafx.scene.image.Image;
import sample.Weapons.IWeapon;
import sample.MovementMethod;
import sample.Weapons.Weapon;
import sample.Weapons.WeaponFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Knight extends Character{

    public Knight(Weapon weapon, int level)
    {
        // Pre-established attributes
        this.movementMethod = MovementMethod.TERRESTRIAL;
        this.healthPoints = 10;
        this.damagePerSecond = 2;
        this.requiredLevel = 1;
        this.cost = 100;
        this.sprites = new ArrayList<>();

        this.x_position = 0;
        this.y_position = (Math.random() * ((480 - 100) + 1) ) + 17;
        this.speed = 45;
        this.isMoving = true;

        // Constructor-defined attributes
        this.weapon = weapon;
        this.level = level;

        // Setting up the images according to the level
        setUpSprites(level);

    }

    public void setCurrentImage(Image image){
        this.currentImage = image;
    }


    public void setUpSprites(int level){

        switch (level) {
            case 1:{
                try {
                    for (int i = 0 ; i < 10 ; i++){
                        sprites.add(new Image(new FileInputStream("src/Assets/Knight/LevelOne/" + i + ".png")));
                    }
                    //System.out.println("Succesfully loaded Knight sprites");
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            break;

            case 2:{
                try {
                    for (int i = 0 ; i < 10 ; i++){
                        sprites.add(new Image(new FileInputStream("src/Assets/Knight/LevelTwo/" + i + ".png")));
                    }
                    //System.out.println("Succesfully loaded LEVEL TWO Knight sprites");
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            break;

            case 3:{
                try {
                    for (int i = 0 ; i < 10 ; i++){
                        sprites.add(new Image(new FileInputStream("src/Assets/Knight/LevelThree/" + i + ".png")));
                    }
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            break;
        }
    }

    @Override
    public void run() {
        setCurrentImage(sprites.get(0));
        int currentImageIndex = 0;
        while(isMoving){
            if (x_position > 750){
                isMoving = false;
            }

            else{
                x_position += 5;
                this.weapon.setX_position(this.weapon.getX_position() + 5);
                if (currentImageIndex == 9){
                    currentImageIndex = 0;
                    setCurrentImage(sprites.get(currentImageIndex));
                }
                else{
                    setCurrentImage(sprites.get(currentImageIndex));
                }
                currentImageIndex++;


                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public ICharacterPrototype clone() {
        return null;
    }

    //FIXME: Not using IWeapon.
    @Override
    public ICharacterPrototype deepClone() {
        WeaponFactory wp = new WeaponFactory();
        Weapon weapon = wp.createWeapon(this.weapon.getName());
        ICharacterPrototype newKnight = new Knight(weapon , this.level);
        return newKnight;
    }

    @Override
    public void doAction() {

    }

    @Override
    public Image getCurrentImage() {
        return currentImage;
    }
}
