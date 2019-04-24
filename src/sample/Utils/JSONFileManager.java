package sample.Utils;

import com.google.gson.Gson;
import sample.Weapons.Weapon;
import sample.Weapons.WeaponRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONFileManager implements IFileManager{

    private static JSONFileManager fileManagerSingleton;
    private WeaponRepository weaponRepository;

    private JSONFileManager(){
        //System.out.println("Creating a new JSONFileManager");
        weaponRepository = new WeaponRepository();
    }

    public static JSONFileManager getInstance(){
        if (fileManagerSingleton == null){
            fileManagerSingleton = new JSONFileManager();
        }
        return fileManagerSingleton;
    }

    @Override
    public void readFile() {
        Gson gson = new Gson();
        BufferedReader br;

        try
        {
            br = new BufferedReader(new FileReader("src/Resources/weaponFile.json"));
            weaponRepository = gson.fromJson(br , WeaponRepository.class);
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public WeaponRepository getWeaponRepository() {
        return weaponRepository;
    }


}
