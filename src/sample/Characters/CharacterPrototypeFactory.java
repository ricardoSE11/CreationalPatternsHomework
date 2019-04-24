package sample.Characters;

import sample.Weapons.Weapon;
import sample.Weapons.WeaponFactory;

import java.util.HashMap;

public class CharacterPrototypeFactory {

    private HashMap<String, ICharacterPrototype> characterPrototypes;
    private WeaponFactory weaponFactory;

    public CharacterPrototypeFactory()
    {
        this.characterPrototypes = new HashMap<>();
        weaponFactory = new WeaponFactory();
    }

    //FIXME: This needs validation
    public ICharacterPrototype getPrototype(String character_Weapon_level)
    {
        ICharacterPrototype currentCharacter = characterPrototypes.get(character_Weapon_level);
        if (currentCharacter != null){
            return characterPrototypes.get(character_Weapon_level).deepClone();
        }
        else{
            return createCharacter(character_Weapon_level);
        }
    }

    public void addPrototype(String character_Weapon_level, ICharacterPrototype character)
    {
        characterPrototypes.put(character_Weapon_level, character);
    }

    public ICharacterPrototype createCharacter(String character_Weapon_level){
        String [] characterAttributes = character_Weapon_level.split("_");
        String choosenCharacter = characterAttributes[0];
        String choosenWeapon = characterAttributes[1];
        int level = Integer.parseInt(characterAttributes[2]);

        switch (choosenCharacter)
        {
            case "knight":{
                Weapon weapon = weaponFactory.createWeapon(choosenWeapon);
                ICharacterPrototype newCharacter = new Knight(weapon , level);
                weapon.setLevel(((Knight) newCharacter).getLevel());
                addPrototype(character_Weapon_level , newCharacter);
                return newCharacter;
            }

            case "archer":{
                Weapon weapon = weaponFactory.createWeapon(choosenWeapon);
                ICharacterPrototype newCharacter = new Archer(weapon , level);
                weapon.setLevel(((Archer) newCharacter).getLevel());
                addPrototype(character_Weapon_level , newCharacter);
                return newCharacter;
            }

            case "wizard":{
                Weapon weapon = weaponFactory.createWeapon(choosenWeapon);
                ICharacterPrototype newCharacter = new Wizard(weapon , level);
                weapon.setLevel(((Wizard) newCharacter).getLevel());
                addPrototype(character_Weapon_level , newCharacter);
                return newCharacter;
            }
        }

        return null;
    }
}
