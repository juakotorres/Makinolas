package cl.makinolas.atk.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GDXSoundEffectsEnemy {
	private Music soundExplotion=Gdx.audio.newMusic(Gdx.files.internal("Music/SFX_Explosion_01.wav"));
	private Music soundExplotionEnd=Gdx.audio.newMusic(Gdx.files.internal("Music/SFX_Explosion_03.wav"));
	
	public GDXSoundEffectsEnemy(){
	}
	public void PlayExplotion(){
		soundExplotion.play();
	}
	public void PlayExplotionEnd(){
		soundExplotionEnd.play();
	}

	
}
