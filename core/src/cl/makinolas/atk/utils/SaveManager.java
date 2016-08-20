package cl.makinolas.atk.utils;

import cl.makinolas.atk.actors.Hero;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

public class SaveManager {

    private Cryptor cryptor;
    private SaveInstance save;

    private static SaveManager instance = new SaveManager();

    private SaveManager(){
        cryptor = new PhraseCryptor("ATK");
        //cryptor = new IdentityCryptor();
    }

    public static SaveManager getInstance() {
        return instance;
    }

    public SaveInstance getSaveInstance() {
        return save;
    }

    public void loadData(String path){
        FileHandle file = Gdx.files.local(path);
        if(!file.exists()) return;
        String encData = file.readString();
        Json base = new Json();
        String data = cryptor.decrypt(encData);
        System.out.println("Loaded:\n"+data);
        save = base.fromJson(SaveInstance.class,data);
    }

    private void saveData(SaveInstance saved, String path){
        Json base = new Json(JsonWriter.OutputType.javascript);
        String jstr = base.toJson(saved);
        Gdx.files.local(path).writeString(cryptor.encrypt(jstr),false);
        System.out.println(jstr);
    }

    public boolean hasSaveInstance(){
        return save != null;
    }


    public void saveState() {
        Hero hero = Hero.getInstance();
        SaveInstance save = new SaveInstance();
        save.heroX = hero.getBody().getPosition().x;
        save.heroY = hero.getBody().getPosition().y;
        save.friends = hero.saveMyFriends();
        save.items = hero.getInventory().createDescriptors();
        save.money = hero.getInventory().getMoney();
        SaveManager.getInstance().saveData(save,"ATK.sav");
    }
}
