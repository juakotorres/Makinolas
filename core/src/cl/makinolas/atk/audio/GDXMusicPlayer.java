package cl.makinolas.atk.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import cl.makinolas.atk.stages.OptionsStage;

public class GDXMusicPlayer {
	private static Music music;
	private static GDXMusicPlayer instance;

	private GDXMusicPlayer(){
	}
	
	public static GDXMusicPlayer getInstance() {
		if (instance == null)
			instance = new GDXMusicPlayer();
		return instance;
	}
	
	public void PlayLooped(String s){
		if(music == null){
			 music = Gdx.audio.newMusic(Gdx.files.internal(s)); 
		}
		else{
		 music.stop();
		 music = Gdx.audio.newMusic(Gdx.files.internal(s)); 
		
		}
		 music.setVolume(OptionsStage.getMusicVolume());
		 music.play();
		 music.setLooping(true);  
		
	}

	public void StopMusic(){
		if(music!=null){
			music.stop();
		}
	}
	public void SetVolume(float f){
		music.setVolume(f);
	}
}
