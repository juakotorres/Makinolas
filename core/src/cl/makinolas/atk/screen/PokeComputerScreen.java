package cl.makinolas.atk.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.KeyHandable;
import cl.makinolas.atk.actors.SimpleImageActor;
import cl.makinolas.atk.actors.SimpleInputController;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.items.Inventory;
import cl.makinolas.atk.actors.ui.TeamFriendImage;
import cl.makinolas.atk.utils.SaveManager;

public class PokeComputerScreen extends SimpleScreen implements KeyHandable {

	private BitmapFont large = new BitmapFont(Gdx.files.internal("Fonts/large.fnt"),Gdx.files.internal("Fonts/large.png"),false);
    private Label currentItem;
    private Hero hero;
    private int index_team;
    private int index_backup;
    
    public PokeComputerScreen(Game g) {
		super(g, new Stage(new FitViewport(640,480)));
		//CAMBIAR POR UNA IMAGEN DE UN pokemon computer
		stage.addActor(new Background("Background/PokeCenter.jpg",stage.getCamera()));
		//quizas esto es innecesario, o poner una foto de un computador
        //stage.addActor(new SimpleImageActor("Humans/LadyCenter.gif",440,60));
        hero = Hero.getInstance();
        index_team = 0;
        index_backup = 0;
        showAllies();
        
        Skin uskin = new Skin(Gdx.files.internal("Data/uiskin.json"));
        TextButton exitButton = new TextButton("Exit PokeComputer", uskin);
        exitButton.setPosition(20, 20);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitComputer();
            }
        });
        stage.addActor(exitButton);
        
        TextButton swapButton = new TextButton("Swap Pokemon", uskin);
        swapButton.setPosition(500, 20);
        swapButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                swapPokemon();
            }
        });
        stage.addActor(swapButton);
        
        
        
        //SOLUCION PARCHE 
        TextButton leftTeamButton = new TextButton("<", uskin);
        leftTeamButton.setPosition(500, 200);
        leftTeamButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                index_team--;
                if (index_team < 0){
                	index_team = 3;
                }
            }
        });
        stage.addActor(leftTeamButton);
        
        TextButton rightTeamButton = new TextButton(">", uskin);
        rightTeamButton.setPosition(520, 200);
        rightTeamButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                index_team++;
                if (index_team > 3){
                	index_team = 0;
                }
            }
        });
        stage.addActor(rightTeamButton);
        
        TextButton leftBackupButton = new TextButton("<", uskin);
        leftBackupButton.setPosition(500, 100);
        leftBackupButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                index_backup--;
                if (index_backup < 0){
                	index_backup = hero.getBackupAllies().size - 1;
                }
            }
        });
        stage.addActor(leftBackupButton);
        
        TextButton rightBackupButton = new TextButton(">", uskin);
        rightBackupButton.setPosition(520, 100);
        rightBackupButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                index_backup++;
                if (index_backup > hero.getBackupAllies().size - 1){
                	index_backup = 0;
                }
            }
        });
        stage.addActor(rightBackupButton);
        
        
        
        currentItem = new Label("",uskin);
        currentItem.setPosition(440,60);
        stage.addActor(currentItem);
        stage.addListener(new SimpleInputController(this,null));
	}

//importante
    private void updateAllies(){
    	for (int i = 0; i < hero.getAllies().size; i++) {
            TeamFriendImage tfimg = new TeamFriendImage(hero.getAllies().get(i), true);
            tfimg.setPosition(60 + 60 * i,350);
            tfimg.setScale(1.5f);
            //stage.addActor(tfimg);
            stage.getActors().set(i+1, tfimg);
    	}
    	for (int i = 0; i < hero.getBackupAllies().size; i++) {
            TeamFriendImage tfimg = new TeamFriendImage(hero.getBackupAllies().get(i), true);
            tfimg.setPosition(60 + 60 * (i % 6),250 - 70 * (i / 6));
            tfimg.setScale(1.5f);
            //stage.addActor(tfimg);
            stage.getActors().set(i+5, tfimg);
        }
    }
    
    private void showAllies(){
    	for (int i = 0; i < hero.getAllies().size; i++) {
            TeamFriendImage tfimg = new TeamFriendImage(hero.getAllies().get(i), true);
            tfimg.setPosition(60 + 60 * i,350);
            tfimg.setScale(1.5f);
            stage.addActor(tfimg);
    	}
    	for (int i = 0; i < hero.getBackupAllies().size; i++) {
            TeamFriendImage tfimg = new TeamFriendImage(hero.getBackupAllies().get(i), true);
            tfimg.setPosition(60 + 60 * (i % 6),250 - 70 * (i / 6));
            tfimg.setScale(1.5f);
            stage.addActor(tfimg);
        }
    }

    private void exitComputer() {
    	SaveManager.getInstance().saveState();
        myGame.setScreen(new MapScreen(myGame));
    }
    
    public void swapPokemon(){
    	hero.swapTeamAllies(index_team, index_backup);
    	showAllies();
    }

    /*
    public void healTeam(){
        Inventory inventory = Hero.getInstance().getInventory();
        if(inventory.payMoney(25*hero.getAllies().size)){
            for (int i = 0; i < hero.getAllies().size; i++) {
                Friend f = hero.getAllies().get(i);
                f.setDead(false);
                f.setHealth(f.getMaxHealth());
                f.setMagic(f.getMaxMagic());
            }
            SaveManager.getInstance().saveState();
            currentMoney.setText("$"+inventory.getMoney());
        }
    }
    */

    @Override
    public void render(float delta) {
        super.render(delta);
        Batch batch = stage.getBatch();
        batch.begin();
        large.draw(batch,"PokeComputer",228,460);
        large.draw(batch,"Pokemon Team:",60,420);
        large.draw(batch,"Pokemon Backup:",60,330);
        large.draw(batch,"Team Index:",440,260);
        large.draw(batch,"Backup Index:",440,160);
        batch.end();
    }

    @Override
    public void handleKey(int keycode) {
        switch (keycode){
            case Input.Keys.X:
                exitComputer();
                break;
            case Input.Keys.Z:
                swapPokemon();
                break;
        }
    }


}
