package sample.Weapons;

import javafx.scene.image.Image;
import sample.Characters.Character;
import sample.Utils.IFileManager;
import sample.Utils.JSONFileManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WeaponFactory {

    IFileManager jsonFileManager;
    WeaponRepository weaponRepository;

    public WeaponFactory() {
        //TODO: Keep in mind that will be initialized only when the file has been read
        jsonFileManager = JSONFileManager.getInstance();
        jsonFileManager.readFile(); // Used to initialize the data in the weapon repository;
        weaponRepository = jsonFileManager.getWeaponRepository();
    }

    public Weapon getWeapon(String name){
        for (Weapon currentWeapon : weaponRepository.getWeapons()){
            if (currentWeapon.getName().equals(name)){
                return currentWeapon;
            }
        }
        return null;
    }

    public void setUpImageForWeapon(Weapon weapon){
        try {
            Image weaponImage = new Image(new FileInputStream("src/Assets/Weapons/" + weapon.getName() + ".png"));
            weapon.setImage(weaponImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setUpWeaponPosition(Weapon weapon , Character character){
        weapon.setX_position(character.getX_position() + 10);
        weapon.setY_position(character.getY_position() + 60);
    }

    public Weapon createWeapon(String name){
        Weapon choosenWeapon = getWeapon(name);
        if (choosenWeapon != null){
            Weapon newWeapon = new Weapon(name);
            newWeapon.setMovementMethod(choosenWeapon.getMovementMethod());
            newWeapon.setRange(choosenWeapon.getRange());
            newWeapon.setDamage(choosenWeapon.getDamage());
            newWeapon.setCanBeUsedBy(choosenWeapon.getCanBeUsedBy());
            setUpImageForWeapon(newWeapon);
            return newWeapon;
        }
        return null;
    }
}


