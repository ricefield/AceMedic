package edu.berkeley.cs160;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGSize;

import android.content.Intent;

import edu.berkeley.cs160.GameLayer;

public class GreetLayer extends CCLayer{
		
	public static CCScene scene()
	{
	    CCScene scene = CCScene.node();
	    CCLayer layer = new GreetLayer();
	    CGSize winSize = CCDirector.sharedDirector().displaySize();
	    //Greeting
	    CCSprite _Greeting =  CCSprite.sprite("greetingscene.png");
	    _Greeting.setPosition(winSize.width/2, winSize.height/2);
	    CCSprite scenario = CCSprite.sprite("scenario.png");
	    scenario.setPosition(winSize.width/2, winSize.height/2);
	    scenario.setOpacity(180);
	    scene.addChild(_Greeting);
	    scene.addChild(scenario);
	    scene.addChild(layer);
	 
	    return scene;
	}
	
	protected GreetLayer(){
		this.setIsTouchEnabled(true);
		CGSize winSize = CCDirector.sharedDirector().displaySize();
		//startButton
		CCMenuItemImage start = CCMenuItemImage.item("startbutton.png", "startbutton.png", this, "GameStart");
		CCMenuItemImage back = CCMenuItemImage.item("BackButton.png", "BackButton.png", this, "GameReturn");
		CCMenu menu = CCMenu.menu(start, back);
		menu.setPosition(winSize.width / 2.0f, 100);
		menu.alignItemsHorizontally(100);
		addChild(menu);	
	}

	
	public void GameStart(Object sender){
		CCDirector.sharedDirector().replaceScene(GameLayer.scene());	
	}
	
	public void GameReturn(Object sender){
		Intent task1 = new Intent(CCDirector.sharedDirector().getActivity(), MapActivity.class);
		//task1.putExtra("treatment", "anklefail");
    	CCDirector.sharedDirector().getActivity().startActivity(task1);
    	CCDirector.sharedDirector().getActivity().finish();
	}
}
