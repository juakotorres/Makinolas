package cl.makinolas.atk.utils;

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

    public void saveData(SaveInstance saved, String path){
        Json base = new Json(JsonWriter.OutputType.javascript);
        String jstr = base.toJson(saved);
        Gdx.files.local(path).writeString(cryptor.encrypt(jstr),false);
        System.out.println("Saved");
    }

    public boolean hasSaveInstance(){
        return save != null;
    }


}
