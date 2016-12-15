package cl.makinolas.atk.actors.ui;

import cl.makinolas.atk.actors.friend.Friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FriendInfo extends Actor {

	private Friend friend;

	private Sprite face;
	private ShapeRenderer infoBox;
	private BitmapFont font;

	public FriendInfo(Friend f) {

		friend = f;
		infoBox = new ShapeRenderer();
		face = new Sprite(friend.getFriendFaceSprite());
		font = new BitmapFont(Gdx.files.internal("Fonts/normal.fnt"), Gdx.files.internal("Fonts/normal.png"), false);
		font.setColor(Color.WHITE);
	}

	public void setFriend(Friend f) {
		friend = f;
		face = new Sprite(friend.getFriendFaceSprite());

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		float x = getX();
		float y = getY();

		batch.end();
		infoBox.begin(ShapeRenderer.ShapeType.Filled);
		infoBox.setTransformMatrix(batch.getTransformMatrix());
		infoBox.setProjectionMatrix(batch.getProjectionMatrix());
		infoBox.setColor(Color.GRAY);
		infoBox.rect(x - 10, y - 65, 145, 100);
		infoBox.end();
		batch.begin();

		font.draw(batch, friend.getName(), x + 30, y + 26);
		font.draw(batch, "Level:  " + String.valueOf(friend.getLevel()), x, y);
		font.draw(batch, "Health: " + String.valueOf(friend.getHealth()) + "/" + String.valueOf(friend.getMaxHealth()),
				x, y - 15);
		font.draw(batch, "Magic:  " + String.valueOf(friend.getMagic()) + "/" + String.valueOf(friend.getMaxMagic()), x,
				y - 30);
		font.draw(batch, "Exp:    " + String.valueOf((int) friend.thisLevelExp()), x, y - 45);

		face.setPosition(x - 15, y + 2);
		if (friend.getDead()) {
			face.setColor(1, 1, 1, 0.4f);
		} else {
			face.setColor(1, 1, 1, 1);
		}
		face.draw(batch, parentAlpha);

	}
}
