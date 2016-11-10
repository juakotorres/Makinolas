package cl.makinolas.atk.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GDXMusicPlayer {
	private static Music music;
	

	public GDXMusicPlayer(){
	
	}
	
	public void PlayLooped(String s){
		if(music==null){
			 music = Gdx.audio.newMusic(Gdx.files.internal(s)); 
		}
		else{
		 music.stop();
		 music = Gdx.audio.newMusic(Gdx.files.internal(s)); 
		
		}
		 music.setVolume(0.4f);
		 music.play();
		 music.setLooping(true);  
		
	}


	public void StopMusic(){
		if(music!=null){
			music.stop();
		}
	}
}
