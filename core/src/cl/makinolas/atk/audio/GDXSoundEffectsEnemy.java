package cl.makinolas.atk.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GDXSoundEffectsEnemy {
	private Music soundExplotion=Gdx.audio.newMusic(Gdx.files.internal("Music/SFX_Explosion_01.wav"));
	private Music soundExplotionEnd=Gdx.audio.newMusic(Gdx.files.internal("Music/SFX_Explosion_03.wav"));
	private Music soundenemygetDmg=Gdx.audio.newMusic(Gdx.files.internal("Music/firered_00F1.wav"));
	public GDXSoundEffectsEnemy(){
	}
	public void PlayExplotion(){
		soundExplotion.play();
	}
	public void PlayExplotionEnd(){
		soundExplotionEnd.play();
	}
	public void playenemygetDmg() {
		if(soundenemygetDmg.isPlaying()){
			soundenemygetDmg.stop();
			soundenemygetDmg.play();
		}
		else{
			soundenemygetDmg.play();
		}
	}

	
}
