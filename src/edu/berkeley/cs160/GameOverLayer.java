package edu.berkeley.cs160;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.ccColor4B;

import android.content.Intent;
import android.view.MotionEvent;

public class GameOverLayer extends CCColorLayer
{
	protected CCLabel _label;
	
	public static CCScene scene(String message)
	{
		CCScene scene = CCScene.node();
		GameOverLayer layer = new GameOverLayer(ccColor4B.ccc4(255, 255, 255, 255));
		
		layer.getLabel().setString(message);
		
		scene.addChild(layer);
		
		return scene;
	}
	
	public CCLabel getLabel()
	{
		return _label;
	}
	
	protected GameOverLayer(ccColor4B color)
	{
		super(color);
		
		this.setIsTouchEnabled(true);
		
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		
		_label = CCLabel.makeLabel("Won't See Me", "DroidSans", 32);
		_label.setColor(ccColor3B.ccRED);
		_label.setScale(2);
		_label.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
		
		CCMenuItemImage back = CCMenuItemImage.item("exit.png", "exit.png", this, "GameReturn");
		CCMenu menu = CCMenu.menu(back);
		menu.setPosition(winSize.width / 2.0f, 100);
		
		
		
		addChild(menu);		
		addChild(_label);
	}
	
	@Override
	public boolean ccTouchesEnded(MotionEvent event)
	{
		 return super.ccTouchesEnded(event);
	}
	
	public void GameReturn(Object sender){
		Intent task1 = new Intent(CCDirector.sharedDirector().getActivity(), MapActivity.class);
		//task1.putExtra("treatment", "anklefail");
    	CCDirector.sharedDirector().getActivity().startActivity(task1);
    	CCDirector.sharedDirector().getActivity().finish();
	}
}
