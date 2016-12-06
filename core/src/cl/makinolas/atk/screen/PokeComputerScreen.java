package cl.makinolas.atk.screen;

import java.util.ArrayList;

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
import cl.makinolas.atk.actors.SimpleInputController;
import cl.makinolas.atk.actors.ui.TeamFriendImage;
import cl.makinolas.atk.audio.GDXSoundEffectsHero;
import cl.makinolas.atk.audio.GDXSoundEffectsPlayer;
import cl.makinolas.atk.utils.SaveManager;

public class PokeComputerScreen extends SimpleScreen implements KeyHandable {

	private BitmapFont large = new BitmapFont(Gdx.files.internal("Fonts/large.fnt"),Gdx.files.internal("Fonts/large.png"),false);
    private Label currentItem;
    private Hero hero;
    private int index_team;
    private int index_backup;
    private ArrayList<TeamFriendImage> backupImages;
    private ArrayList<TeamFriendImage> alliesImages;
    private int index_page;
    private int page_limit;
    private int team_size;
    private int backup_size;
    private GDXSoundEffectsPlayer mplayer = GDXSoundEffectsHero.getInstance();
    public PokeComputerScreen(Game g) {
		super(g, new Stage(new FitViewport(640,480)));
		alliesImages = new ArrayList<TeamFriendImage>();
		backupImages = new ArrayList<TeamFriendImage>();
		stage.addActor(new Background("Background/PokeCenter.jpg",stage.getCamera()));
        hero = Hero.getInstance();
        index_team = 0;
        index_backup = 0;
        index_page = 0;
        team_size = hero.getAllies().size;
        backup_size = hero.getBackupAllies().size;
        page_limit = backup_size % 18 == 0 && backup_size != 0? (backup_size / 18) : (backup_size / 18) + 1 ;//backup_size < 18 || 
        showAllies();
        
        Skin uskin = new Skin(Gdx.files.internal("Data/uiskin.json"));
        TextButton exitButton = new TextButton("Exit PokeComputer", uskin);
        exitButton.setPosition(20, 20);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitComputer();
                mplayer.PlayPressButton();
            }
        });
        stage.addActor(exitButton);
        
        TextButton swapButton = new TextButton("Swap Pokemon", uskin);
        swapButton.setPosition(500, 20);
        swapButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                swapPokemon();
                mplayer.PlayPressButton();
            }
        });
        stage.addActor(swapButton);
        
        
        
        //SOLUCION PARCHE 
        TextButton leftTeamButton = new TextButton("<", uskin);
        leftTeamButton.setPosition(500, 200);
        leftTeamButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            	index_team = index_team - 1 < 0 ? team_size - 1 : index_team - 1;
            	mplayer.PlayPressButton();

            }
        });
        stage.addActor(leftTeamButton);
        
        TextButton rightTeamButton = new TextButton(">", uskin);
        rightTeamButton.setPosition(520, 200);
        rightTeamButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	 mplayer.PlayPressButton();
            	index_team = index_team + 1 > team_size - 1 ? 0 : index_team + 1;
            }
        });
        stage.addActor(rightTeamButton);
        
        TextButton leftBackupButton = new TextButton("<", uskin);
        leftBackupButton.setPosition(500, 100);
        leftBackupButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	 mplayer.PlayPressButton();
            	indexBackupHandler(-1);

            }
        });
        stage.addActor(leftBackupButton);
        
        TextButton rightBackupButton = new TextButton(">", uskin);
        rightBackupButton.setPosition(520, 100);
        rightBackupButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	 mplayer.PlayPressButton();
            	indexBackupHandler(1);
            	
            }
        });
        stage.addActor(rightBackupButton);
        
        TextButton pageRightBackupButton = new TextButton("Next Page", uskin);
        pageRightBackupButton.setPosition(400, 20);
        pageRightBackupButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	changePage(1);
            }
        });
        stage.addActor(pageRightBackupButton);
        
        TextButton pageLeftBackupButton = new TextButton("Prev Page", uskin);
        pageLeftBackupButton.setPosition(300, 20);
        pageLeftBackupButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	changePage(-1);
            }
        });
        stage.addActor(pageLeftBackupButton);
        
        currentItem = new Label("",uskin);
        currentItem.setPosition(440,60);
        stage.addActor(currentItem);
        stage.addListener(new SimpleInputController(this,null));
	}
    
    /*
     * Inicializa las imagenes de los Pokemon en la pantalla, solo debe ser utilizado en el inicio
     * de la pantalla del PC.
     * */
    private void showAllies(){
    	for (int i = 0; i < team_size ; i++) {
            TeamFriendImage tfimg = new TeamFriendImage(hero.getAllies().get(i), true);
            alliesImages.add(tfimg);
            tfimg.setPosition(60 + 60 * i,350);
            tfimg.setScale(1.5f);
            stage.addActor(tfimg);
    	}    	
    	for (int j = 0; j < page_limit; j++){
    		for (int i = 0; i < 18 && i + j * 18 < backup_size ; i++) {//i < hero.getBackupAllies().size && 
                TeamFriendImage tfimg = new TeamFriendImage(hero.getBackupAllies().get(i + j * 18), true);
                backupImages.add(tfimg);
                tfimg.setPosition(60 + 60 * (i % 6),250 - 70 * (i / 6));
                tfimg.setScale(1.5f);
                tfimg.setVisible(j == 0? true : false);
                stage.addActor(tfimg);
            }
    	}
    }
    
    /*
     * Metodo utilizado para cambiar las imagenes de los Pokemon disponibles en el PC segun la pagina 
     * actual, debe obtener entre 1 y 18 elementos del arreglo backupImages, modificar los atributos 
     * de los anteriores Pokemon a no visibles y poner los nuevos elementos en estado visible. 
     * */
    private void changePage(int change){
    	//modificar las anteriores
    	for (int i = 0; i < 18 && i + index_page * 18 < backup_size ; i++) {
    		TeamFriendImage tfimg = backupImages.get(i + index_page * 18);
    		tfimg.setVisible(false);
    		stage.addActor(tfimg);
        }
    	
    	//cambiar el numero de pagina
    	changePageNumber(change);
    	/*debug
    	System.out.println("alliesImages size: " + alliesImages.size());
    	System.out.println("backupImages size: " + backupImages.size());
    	System.out.println("page limit: "+ page_limit);
    	System.out.println("page index: "+index_page);
    	System.out.println("backup index: " + index_backup +" team index: "+ index_team);
    	*/
    	//modificar las nuevas imagenes
    	for (int i = 0; i < 18 && i + index_page * 18 < backup_size ; i++) {
    		TeamFriendImage tfimg = backupImages.get(i + index_page * 18);
    		tfimg.setVisible(true);
    		stage.addActor(tfimg);
        }
    	
    }
    
    /*
     * Produce el cambio del indice de pagina considerando los casos bordes.
     * */
    private void changePageNumber(int change){
    	if (change > 0){
    		index_page = index_page + 1 == page_limit ? 0 : index_page + 1 ;
        	index_backup = index_page * 18;
    	}
    	else{
    		index_page = index_page - 1 < 0 ? page_limit - 1 : index_page - 1 ;
        	index_backup = index_page * 18;
    	}
    }
    
    /* el indice que apuntan en el arreglo de los Backup Pokemon debe estar entre los limites
     * definidos de la pagina actual, esto significa que el limite inferior de la variable
     * index_backup es (index_page * 18), es decir 0, 18, 36, ... 
     * Luego el limite superior de index_backup es el minimo entre el tamaño del arreglo de
     * Backup Pokemon y el limite superior de la pagina (((index_page + 1) * 18) - 1). 
     * */
    private void indexBackupHandler(int i){
    	int aux = index_backup + i;
    	int min_limit = index_page * 18;
    	int max_limit = Math.min(((index_page + 1) * 18) - 1, Math.max(0, backup_size - 1));
    	index_backup = aux < min_limit? max_limit : (aux > max_limit? min_limit : aux);
    }

    private void exitComputer() {
    	SaveManager.getInstance().saveState();
        myGame.setScreen(new MapScreen(myGame));
    }
    
    public void swapPokemon(){
    	if (backupImages.size() != 0 ){
	    	hero.swapTeamAllies(index_team, index_backup);
	    	TeamFriendImage ally = alliesImages.get(index_team);
	    	TeamFriendImage backup = backupImages.get(index_backup);
	    	float auxX = ally.getX();
	    	float auxY = ally.getY();
	    	ally.setPosition(backup.getX(), backup.getY());
	    	backup.setPosition(auxX, auxY);
	    	stage.addActor(ally);
	    	stage.addActor(backup);
	    	alliesImages.set(index_team, backup);
	    	backupImages.set(index_backup, ally);
    	}
    }


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

