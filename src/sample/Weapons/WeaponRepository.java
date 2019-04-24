package sample.Weapons;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeaponRepository {

    public WeaponRepository(){
        //System.out.println("Creating a new Weapon Repository");
    }

    @SerializedName("weapons")
    @Expose
    private ArrayList<Weapon> weapons = null;

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

}