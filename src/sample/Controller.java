package sample;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.Characters.Character;
import sample.Characters.CharacterPrototypeFactory;
import sample.Characters.ICharacter;
import sample.Characters.Knight;
import sample.Utils.JSONFileManager;
import sample.Weapons.Weapon;
import sample.Weapons.WeaponFactory;
import sample.Weapons.WeaponRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;

public class Controller implements Initializable {


    //<editor-fold desc="Creation vars">
    public CharacterPrototypeFactory characterFactory;
    public WeaponRepository weaponRepository;
    public WeaponFactory weaponFactory;
    public int money;
    public CopyOnWriteArrayList<Character> characters;
    public int characterLevel;
    public String selectedCharacter;
    public String selectedWeapon;
    //</editor-fold>

    //<editor-fold desc="UI Vars">
    // UI Elements
    private Stage stage;
    private GraphicsContext g;
    private Image backgroundImage;

    // Drawing
    @FXML private Canvas drawingCanvas;

    // Controls
    @FXML private ComboBox cmbCharacters;
    @FXML private ComboBox cmbWeapons;
    @FXML private Button btnCreateCharaters;
    @FXML private TextField txtCharactersAmount;

    // Values for combo boxes
    ObservableList<String> character_list = FXCollections.observableArrayList("Knight" , "Archer" , "Wizard");
    ObservableList<String> knight_weapons = FXCollections.observableArrayList("Basic Sword" , "Big Sword" , "Mega Sword");

    // Display
    @FXML private Label lblMoney;
    @FXML private Label lblCharacterLevel;
    //</editor-fold>

    public Controller() {
        //System.out.println("Instantiating a Main Controller");
    }

    public ObservableList<String> getAvailableWeapons(String characterName){
        ObservableList<String> availableWeapons = FXCollections.observableArrayList();
        for (Weapon currentWeapon : weaponRepository.getWeapons()){
            if (currentWeapon.canBeUsedBy(characterName))
            {
                availableWeapons.add(currentWeapon.getName());
            }
        }
        return availableWeapons;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbCharacters.setItems(character_list);
    }

    public void setUpWeaponComboBox(){
        String selectedCharacter = cmbCharacters.getValue().toString().toLowerCase();
        ObservableList<String> weaponsForSelectedCharacter = getAvailableWeapons(selectedCharacter);
        cmbWeapons.setItems(weaponsForSelectedCharacter);
    }

    public void init(Stage stage) {
        //TODO: Add initialization here.
        this.stage = stage;

        drawingCanvas.setWidth(1000);
        drawingCanvas.setHeight(800);
        g = this.drawingCanvas.getGraphicsContext2D();

        characterFactory = new CharacterPrototypeFactory();
        weaponFactory = new WeaponFactory();
        weaponRepository = JSONFileManager.getInstance().getWeaponRepository();
        characters = new CopyOnWriteArrayList<>();

        characterLevel = 1;
        lblCharacterLevel.setText(" " + characterLevel);
        lblMoney.setText("1000");

    }

    public void reDraw(Image backgroundImage)
    {
        g.clearRect(0, 0, 900, 800);
        g.drawImage(backgroundImage , 0 , 0);
        //TODO: Add logic to keep drawing characters
        for (Character currentCharacter: characters){
            if (!currentCharacter.isMoving()){
                characters.remove(currentCharacter);
            }
            g.drawImage(currentCharacter.getCurrentImage() , currentCharacter.getX_position() , currentCharacter.getY_position() );
            Weapon charactersWeapon = currentCharacter.getWeapon();
            g.drawImage(charactersWeapon.getImage() , charactersWeapon.getX_position() , charactersWeapon.getY_position());
        }
    }

    // Character creation
    public void createCharacters(){
        int amountOfCharacters = Integer.parseInt(txtCharactersAmount.getText());
        selectedCharacter = cmbCharacters.getValue().toString().toLowerCase();
        selectedWeapon = cmbWeapons.getValue().toString();
        String characterSpecification = selectedCharacter + "_" + selectedWeapon + "_" + characterLevel;
        for (int i = 0 ; i < amountOfCharacters ; i++){
            Character newCharacter = (Character) characterFactory.getPrototype(characterSpecification);
            characters.add(newCharacter);
            weaponFactory.setUpWeaponPosition(newCharacter.getWeapon() , newCharacter);
            newCharacter.start();
        }
    }

    public void levelUpCharacter(){
        if (characterLevel == 3){
            characterLevel = 1;
            lblCharacterLevel.setText(" " + characterLevel);
        }
        else{
            characterLevel++;
            lblCharacterLevel.setText(" " + characterLevel);
        }
    }

}
