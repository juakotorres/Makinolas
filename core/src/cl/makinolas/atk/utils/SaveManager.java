package cl.makinolas.atk.utils;

import cl.makinolas.atk.stages.Levels;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.friend.FriendDescriptor;
import cl.makinolas.atk.start.GameText;

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

    public void loadData(String path) throws SaveDoesNotExistException{
        FileHandle file = Gdx.files.local(path);
        if(!file.exists()) throw new SaveDoesNotExistException();
        String encData = file.readString();
        Json base = new Json();
        String data = cryptor.decrypt(encData);
        //System.out.println("Loaded:\n"+data);
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
    
    public void startGameSave(Friend friend, String myName, boolean mySex){
      SaveInstance saveInstance = new SaveInstance();
      FriendDescriptor fd = new FriendDescriptor();
      fd.name = friend.getName();
      fd.level = 5;
      saveInstance.friends = new FriendDescriptor[]{fd};
      saveInstance.name = myName;
      saveInstance.sex = mySex;
      boolean[] unlockedStages = new boolean[Levels.values().length];
      for(int i = 0; i < Levels.values().length; i++) unlockedStages[i] = false;
      unlockedStages[0] = true;
      saveInstance.levelsUnlocked = unlockedStages;
      
      //System.out.println(GameText.savePath);
      SaveManager.getInstance().saveData(saveInstance, GameText.savePath);
    }

    public void saveState() {
        Hero hero = Hero.getInstance();
        SaveInstance save2 = new SaveInstance();
        save2.friends = hero.saveMyFriends();
        save2.items = hero.getInventory().createDescriptors();
        save2.money = hero.getInventory().getMoney();
        save2.levelsUnlocked = hero.getLevelsUnlocked();
        save2.name = save.name;
        save2.sex = save.sex;
        //System.out.println(GameText.savePath);
        SaveManager.getInstance().saveData(save2,GameText.savePath);
    }

    public int getHighscore(){
        Preferences pref = Gdx.app.getPreferences("ATK");
        return pref.getInteger("highscore",0);
    }

    public void setHighscore(int k){
        Preferences pref = Gdx.app.getPreferences("ATK");
        pref.putInteger("highscore",k);
        pref.flush();
    }
}
