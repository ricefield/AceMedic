package edu.berkeley.cs160;


import java.util.ArrayList;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCSpriteFrameCache;
import org.cocos2d.nodes.CCSpriteSheet;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor4B;

import edu.berkeley.cs160.CGRect;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;

public class RICE_gameLayer extends CCColorLayer {
	private static final String TAG = "Wasp";
	protected CCSprite BigMobile = CCSprite.sprite("bigmobile.png");
	protected CCSprite _AidBox =  CCSprite.sprite("firstaidbox.png");
	protected CCSprite _IcePack =  CCSprite.sprite("icepack.png");
	protected CCSprite status_IcePack =  CCSprite.sprite("icepack.png");
	protected CCSprite _Tape =  CCSprite.sprite("tape.png");
	protected CCSprite status_Tape =  CCSprite.sprite("tape.png");
	protected CCSprite _Stool =  CCSprite.sprite("stool.png");
	protected CCSprite status_Stool =  CCSprite.sprite("stool.png");
	protected CCSprite status_Mobile = CCSprite.sprite("mobile.png");
	protected CGSize winSize = CCDirector.sharedDirector().displaySize();
	//moving tools
	protected CCSprite m_IcePack =  CCSprite.sprite("m_icepack.png");
	protected CCSprite m_Bandage =  CCSprite.sprite("m_bandage.png");
	protected CCSprite m_stool = CCSprite.sprite("m_stool.png");
	protected CCSprite red = CCSprite.sprite("red-square.png");
	protected CCSprite smallred = CCSprite.sprite("small-red.png");
	protected CCSprite uparrow = CCSprite.sprite("uparrow.png");
	//hint
	protected CCSprite icing =  CCSprite.sprite("icing.png");
	protected CCSprite wrap = CCSprite.sprite("wrap.png");
	protected CCSprite elevate = CCSprite.sprite("elevate.png");
	//action
	protected int actionIndex = 0;
	protected int redIndex = 0;
	//foot icing scene
	protected CCSprite foot = CCSprite.sprite("foot.png");
	protected CCSprite foot1 = CCSprite.sprite("foot1.png");
	protected CCSprite coolfoot = CCSprite.sprite("foot2.png");
	//foot treatment scene
	protected CCSprite treatment1 = CCSprite.sprite("treatment1.png");
	protected CCSprite treatment2 = CCSprite.sprite("treatment2.png");
	protected CCSprite treatment3 = CCSprite.sprite("treatment3.png");
	protected CCSprite treatment4 = CCSprite.sprite("treatment4.png");
	protected CCSprite upstool = CCSprite.sprite("upstool.png");
	protected CCSprite done = CCSprite.sprite("done.png");
	//actionIndex
	//0:initial action
	//1:choose/move ice pack
	//2:choose/move bandage
	//3:treatment1 with smallred
	//4:treatment2
	//5:treatment3
	//6:treatment4 and done.
	//7:stool 
	
	//spriteSheet
	CCMenu dialButton;
	CCSpriteSheet spritesheet1 = CCSpriteSheet.spriteSheet("AnimTreatment1.png");
	CCSpriteSheet spritesheet2 = CCSpriteSheet.spriteSheet("AnimMobile.png");
	
	public static CCScene scene()
	{
	    CCScene scene = CCScene.node();
	    CCColorLayer layer = new RICE_gameLayer(ccColor4B.ccc4(255, 255, 255, 255));
	    scene.addChild(layer);	 
	    return scene;
	}
	
	protected RICE_gameLayer(ccColor4B color){
		super(color);
		this.setIsTouchEnabled(true);
		
		//icons
		CCMenuItemImage Quit = CCMenuItemImage.item("exit.png", "exit.png", this, "quit");
		CCMenuItemImage AidBox = CCMenuItemImage.item("firstaidbox.png", "firstaidbox.png", this, "icon1");
		CCMenuItemImage IcePack = CCMenuItemImage.item("icepack.png", "icepack_click.png", this, "icon2");
		CCMenuItemImage Tape = CCMenuItemImage.item("tape.png", "tape_clicked.png", this, "icon3");
		CCMenuItemImage Stool = CCMenuItemImage.item("stool.png", "stool_clicked.png", this, "icon4");
		CCMenuItemImage Mobile = CCMenuItemImage.item("mobile.png", "mobile.png", this, "icon5");
		
		CCMenu quitmenu = CCMenu.menu(Quit);
		CCMenu menu = CCMenu.menu(AidBox, IcePack, Tape, Stool, Mobile);
		menu.setPosition(CGPoint.ccp(_Tape.getContentSize().width / 2.0f, winSize.height / 2.0f - 20));
		menu.alignItemsVertically(40);
		
		quitmenu.setPosition(CGPoint.ccp(_Tape.getContentSize().width / 2.0f + 50, winSize.height - 50));
		icing.setPosition(850, 80);
		
		CCSprite iconlabel =  CCSprite.sprite("icon button.png");
		iconlabel.setPosition(winSize.width - 2*(iconlabel.getContentSize().width / 2.0f), winSize.height- 2*(iconlabel.getContentSize().height / 2.0f));
		addChild(iconlabel);
		//footscene
		foot.setPosition(winSize.width/2.0f, winSize.height/2.0f);
    
		CGSize s = CCDirector.sharedDirector().winSize();
		
        CCSpriteFrameCache.sharedSpriteFrameCache().addSpriteFrames("AnimDigit.plist");
        
        
        CCSprite first = CCSprite.sprite("digit9.png", true);
        CCSprite second = CCSprite.sprite("digit9.png", true);
        first.setPosition(s.width/2, s.height - 2*first.getScaleY() - 50); 
        second.setPosition(s.width/2 - 40, s.height - 2*first.getScaleY() - 50); 

        CCSpriteSheet spritesheet = CCSpriteSheet.spriteSheet("AnimDigit.png");
        addChild(spritesheet);
        
        ArrayList<CCSpriteFrame> animFrames = new ArrayList<CCSpriteFrame>();
        ArrayList<CCSpriteFrame> animFrames2 = new ArrayList<CCSpriteFrame>();

      	for(int i = 5; i >= 0; i--){
        	animFrames2.add(CCSpriteFrameCache.sharedSpriteFrameCache().spriteFrameByName("digit" + i + ".png"));
        }        
      	for(int i = 9; i >= 0; i--){
        	animFrames.add(CCSpriteFrameCache.sharedSpriteFrameCache().spriteFrameByName("digit" + i + ".png"));
        }
        
        CCAnimation animation = CCAnimation.animation("Digit", 1.0f, animFrames);
        CCAnimation animation2 = CCAnimation.animation("Digit", 10.0f, animFrames2);
        CCAction action = CCRepeatForever.action(CCAnimate.action(animation, false));
        CCAction action2 = CCAnimate.action(animation2, false);
        first.runAction(action);
        second.runAction(action2);
        spritesheet.addChild(second);
        spritesheet.addChild(first);
        
	    
		addChild(foot);
		addChild(icing);
		addChild(menu);
		addChild(quitmenu);
		
		//update
		this.schedule("update");
		this.runAction(CCSequence.actions(CCDelayTime.action(60.0f), CCCallFunc.action(this, "timeout")));
	}
	
	
	
	public void quit(Object sender){
		Intent task1 = new Intent(CCDirector.sharedDirector().getActivity(), MapActivity.class);
		//task1.putExtra("treatment", "ankledone");
    	CCDirector.sharedDirector().getActivity().startActivity(task1);
       	CCDirector.sharedDirector().getActivity().finish();
	}
	
	public void icon1(){
		
	}
	//icepack
	public void icon2(Object sender){
		removeChild(dialButton, true);
		removeChild(spritesheet2, true);
		removeChild(m_IcePack, true);
		removeChild(m_Bandage, true);
		removeChild(m_stool, true);
		removeChild(BigMobile, true);
		removeChild(status_Mobile, true);
		removeChild(status_IcePack, true);
		removeChild(status_Tape, true);
		removeChild(status_Stool, true);
		removeChild(smallred,true);
		removeChild(red, true);
		removeChild(uparrow, true);
		if(actionIndex == 0){
			removeChild(foot, true);
		}
		status_IcePack.setPosition(winSize.width - 2*(_IcePack.getContentSize().width / 2.0f), winSize.height- 2*(_IcePack.getContentSize().height / 2.0f));
		m_IcePack.setPosition(CGPoint.ccp(_IcePack.getContentSize().width * 2.0f + 100, _IcePack.getContentSize().height / 2.0f + 100));
		//foot
		red.setPosition(winSize.width/2.0f - 20, winSize.height/2.0f - 50);
		red.setOpacity(50);
		foot1.setPosition(winSize.width/2.0f, winSize.height/2.0f);
		if(actionIndex == 0){
		addChild(foot1);
		addChild(red);
		}
		addChild(status_IcePack);
		addChild(m_IcePack);
		actionIndex = 1;
		}
	
	
	public void icon3(Object sender){
		removeChild(dialButton, true);
		removeChild(spritesheet2, true);
		removeChild(m_IcePack, true);
		removeChild(m_Bandage, true);
		removeChild(m_stool, true);
		removeChild(BigMobile, true);
		removeChild(status_Mobile, true);
		removeChild(status_IcePack, true);
		removeChild(status_Tape, true);
		removeChild(status_Stool, true);
		removeChild(smallred,true);
		removeChild(red, true);
		removeChild(uparrow, true);
		m_Bandage.setPosition(CGPoint.ccp(_IcePack.getContentSize().width * 2.0f + 100, _IcePack.getContentSize().height / 2.0f + 100));
		status_Tape.setPosition(winSize.width - 2*(_IcePack.getContentSize().width / 2.0f), winSize.height- 2*(_IcePack.getContentSize().height / 2.0f));
		red.setPosition(winSize.width/2.0f - 20, winSize.height/2.0f + 50);
		red.setOpacity(50);
		addChild(red);
		addChild(status_Tape);
		addChild(m_Bandage);
		actionIndex = 2;
	}
	
	public void icon4(Object sender){
		removeChild(dialButton, true);
		removeChild(spritesheet2, true);
		removeChild(m_IcePack, true);
		removeChild(m_Bandage, true);
		removeChild(m_stool, true);
		removeChild(BigMobile, true);
		removeChild(status_Mobile, true);
		removeChild(status_IcePack, true);
		removeChild(status_Tape, true);
		removeChild(status_Stool, true);
		removeChild(smallred,true);
		removeChild(red, true);
		removeChild(uparrow, true);
		uparrow.setPosition(winSize.width/2.0f - 20, winSize.height/2.0f -250);
		status_Stool.setPosition(winSize.width - 2*(_IcePack.getContentSize().width / 2.0f), winSize.height- 2*(_IcePack.getContentSize().height / 2.0f));
		smallred.setPosition(winSize.width/2.0f - 20, winSize.height/2.0f -200);
		smallred.setOpacity(0);
		m_stool.setPosition(CGPoint.ccp(_IcePack.getContentSize().width * 2.0f + 100, _IcePack.getContentSize().height / 2.0f + 100));
		addChild(uparrow);
		addChild(smallred);
		addChild(status_Stool);
		addChild(m_stool);
		actionIndex = 7;
	}
	
	public void icon5(Object sender){
		removeChild(dialButton, true);
		removeChild(spritesheet2, true);
		removeChild(m_IcePack, true);
		removeChild(m_Bandage, true);
		removeChild(m_stool, true);
		removeChild(BigMobile, true);
		removeChild(status_Mobile, true);
		removeChild(status_IcePack, true);
		removeChild(status_Tape, true);
		removeChild(status_Stool, true);
		removeChild(smallred,true);
		removeChild(red, true);
		removeChild(uparrow, true);
		//BigMobile.setPosition(winSize.width/2, winSize.height/2);
		status_Mobile.setPosition(winSize.width - 2*(_IcePack.getContentSize().width / 2.0f), winSize.height- 2*(_IcePack.getContentSize().height / 2.0f));
		
		CCMenuItemImage Dial = CCMenuItemImage.item("small-red.png", "small-red.png", this, "dial");
		dialButton = CCMenu.menu(Dial);
		dialButton.setPosition(winSize.width/2, winSize.height/2 - 200);
		dialButton.setOpacity(0);
		
		CGSize s = CCDirector.sharedDirector().winSize();
		
        CCSpriteFrameCache.sharedSpriteFrameCache().addSpriteFrames("AnimMobile.plist");
        
        CCSprite mobile = CCSprite.sprite("digit9.png", true);
        mobile.setPosition(s.width/2, s.height/2); 

        spritesheet2 = CCSpriteSheet.spriteSheet("AnimMobile.png");
        addChild(spritesheet2);
        
       
        ArrayList<CCSpriteFrame> animFrames = new ArrayList<CCSpriteFrame>();

      	for(int i = 1; i <= 3; i++){
        	animFrames.add(CCSpriteFrameCache.sharedSpriteFrameCache().spriteFrameByName("mobile" + i + ".png"));
        }        
        CCAnimation animation = CCAnimation.animation("Mobile", 0.1f, animFrames);
        CCAction action = CCRepeatForever.action(CCAnimate.action(animation, false));
        mobile.runAction(action);
        spritesheet2.addChild(mobile);
        
        addChild(dialButton);
		addChild(status_Mobile);
	}
	
	public void dail(Object sender){
		removeChild(dialButton, true);
		removeChild(spritesheet2, true);
		
		
	}
	
	@Override
    public boolean ccTouchesBegan(MotionEvent event) {
		return super.ccTouchesBegan(event);
	}
	
	@Override
    public boolean ccTouchesMoved(MotionEvent event) { 
		CGRect icePackRect = CGRect.make(0,0,0,0);
    	CGRect bandageRect = CGRect.make(0,0,0,0);
    	CGRect stoolRect = CGRect.make(0,0,0,0);
    	CCSprite size = CCSprite.sprite("firstaidbox.png");
		int pointerIndex = event.getPointerId(event.getActionIndex());
		CGPoint loc =  CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(pointerIndex),event.getY(pointerIndex)));
    	Log.d(TAG, String.format("touch %d=(%f,%f)", event.getActionIndex(), loc.x,loc.y));
    	
    	//touch rectangle
    	CGRect touchRect = CGRect.make(loc.x - (m_IcePack.getContentSize().width / 2.0f) + 50,
    			loc.y - (m_IcePack.getContentSize().height / 2.0f + 50),
                m_IcePack.getContentSize().width - 50,
                m_IcePack.getContentSize().height - 50);
    	
    	//icepack rectangle
    	if(actionIndex == 0 ||actionIndex == 1){
    		icePackRect = CGRect.make(m_IcePack.getPosition().x - (m_IcePack.getContentSize().width / 2.0f),
    		m_IcePack.getPosition().y - (m_IcePack.getContentSize().height / 2.0f),
    		m_IcePack.getContentSize().width,
    		m_IcePack.getContentSize().height);    		
    	}
    	
    	if(actionIndex == 2 || actionIndex == 3 || actionIndex == 4 || actionIndex == 5 || actionIndex == 6){
    		bandageRect = CGRect.make(m_Bandage.getPosition().x - (m_Bandage.getContentSize().width / 2.0f),
    		m_Bandage.getPosition().y - (m_Bandage.getContentSize().height / 2.0f),
    		m_Bandage.getContentSize().width,
    		m_Bandage.getContentSize().height);    		
    	}
    	
    	if(actionIndex == 7){
    		stoolRect = CGRect.make(m_stool.getPosition().x - (m_stool.getContentSize().width / 2.0f),
    		m_stool.getPosition().y - (m_stool.getContentSize().height / 2.0f),
    		m_stool.getContentSize().width,
    		m_stool.getContentSize().height);	
    	}
    	 if(CGRect.intersects(icePackRect, touchRect) || CGRect.intersects(bandageRect, touchRect) ||  CGRect.intersects(stoolRect, touchRect)){
    		 if (loc.x <= 2*(size.getContentSize().width) - 30){
    			 return super.ccTouchesMoved(event);
    		 }else{
    			 int pointCnt = event.getPointerCount();
       			for (int i = 0; i < pointCnt; i++) {
       				CGPoint location = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(i),event.getY(i)));
       				Log.d(TAG, String.format("move %d=(%f,%f)", i, location.x, location.y));
           
       				switch (actionIndex) {
       				case 0:
       					m_IcePack.setPosition(location);
       					break;
       				case 1:
       					m_IcePack.setPosition(location);
       					break;	
       				case 2:
       					m_Bandage.setPosition(location);
       				case 3:
       					m_Bandage.setPosition(location);
       				case 4:
       					m_Bandage.setPosition(location);
       				case 5:
       					m_Bandage.setPosition(location);
       				case 6:
       					m_Bandage.setPosition(location);
       				case 7:
       					m_stool.setPosition(location);
       				default:
       					break;
       				}
       			}
    		 }
    	 }
    	 return super.ccTouchesMoved(event); 	
	}
    
	public boolean ccTouchesEnded(MotionEvent event) {
		 return super.ccTouchesEnded(event);
	}
	
	public void update(float dt){
		CGRect icePackRect = CGRect.make(0,0,0,0);
		CGRect redRect  = CGRect.make(0,0,0,0);
		CGRect smallredRect  = CGRect.make(0,0,0,0);
		CGRect bandageRect = CGRect.make(0,0,0,0);
    	CGRect stoolRect = CGRect.make(0,0,0,0);
		
		if(actionIndex == 1){
    		icePackRect = CGRect.make(m_IcePack.getPosition().x - (m_IcePack.getContentSize().width / 2.0f),
    		m_IcePack.getPosition().y - (m_IcePack.getContentSize().height / 2.0f),
    		m_IcePack.getContentSize().width,
    		m_IcePack.getContentSize().height);  
   
    		redRect = CGRect.make(red.getPosition().x - (red.getContentSize().width / 2.0f),
    		red.getPosition().y - (red.getContentSize().height / 2.0f),
    		red.getContentSize().width,
    		red.getContentSize().height); 
    	}
		
		if(actionIndex == 2){
			bandageRect = CGRect.make(m_Bandage.getPosition().x - (m_Bandage.getContentSize().width / 2.0f),
		    		m_Bandage.getPosition().y - (m_Bandage.getContentSize().height / 2.0f),
		    		m_Bandage.getContentSize().width,
		    		m_Bandage.getContentSize().height);
			
			redRect = CGRect.make(red.getPosition().x - (red.getContentSize().width / 2.0f),
		    		red.getPosition().y - (red.getContentSize().height / 2.0f),
		    		red.getContentSize().width,
		    		red.getContentSize().height); 
		}
		
		if(actionIndex == 3 || actionIndex == 4 || actionIndex == 5 || actionIndex == 6){
			bandageRect = CGRect.make(m_Bandage.getPosition().x - (m_Bandage.getContentSize().width / 2.0f),
		    		m_Bandage.getPosition().y - (m_Bandage.getContentSize().height / 2.0f),
		    		m_Bandage.getContentSize().width,
		    		m_Bandage.getContentSize().height);
			
			smallredRect = CGRect.make(smallred.getPosition().x - (smallred.getContentSize().width / 2.0f),
		    		smallred.getPosition().y - (smallred.getContentSize().height / 2.0f),
		    		smallred.getContentSize().width,
		    		smallred.getContentSize().height); 
		}
		
		if(actionIndex == 7){
			stoolRect = CGRect.make(m_stool.getPosition().x - (m_stool.getContentSize().width / 2.0f),
					m_stool.getPosition().y - (m_stool.getContentSize().height / 2.0f),
					m_stool.getContentSize().width,
					m_stool.getContentSize().height);
			
			smallredRect = CGRect.make(smallred.getPosition().x - (smallred.getContentSize().width / 2.0f),
		    		smallred.getPosition().y - (smallred.getContentSize().height / 2.0f),
		    		smallred.getContentSize().width,
		    		smallred.getContentSize().height); 
		}
		
		
		if(CGRect.intersects(redRect, icePackRect) && actionIndex == 1){
			if(redIndex == 0){
				this.runAction(CCSequence.actions(CCDelayTime.action(1.5f), CCCallFunc.action(this, "coolfoot")));
			}
			redIndex = 1;
		}
		
		if(CGRect.intersects(redRect, bandageRect) && actionIndex == 2){
			if(redIndex == 1){
				this.runAction(CCSequence.actions(CCDelayTime.action(1.0f), CCCallFunc.action(this, "treatment1")));
			}
			redIndex = 2;
		}
		
		if(CGRect.intersects(smallredRect, bandageRect) && actionIndex == 3){
			if(redIndex == 2){
				this.runAction(CCSequence.actions(CCDelayTime.action(1.0f), CCCallFunc.action(this, "treatment2")));
			}
			redIndex = 3;
		}		
		
		if(CGRect.intersects(smallredRect, bandageRect) && actionIndex == 4){
			if(redIndex == 3){
				this.runAction(CCSequence.actions(CCDelayTime.action(1.0f), CCCallFunc.action(this, "treatment3")));
			}
			redIndex = 4;
		}
		
		if(CGRect.intersects(smallredRect, bandageRect) && actionIndex == 5){
			if(redIndex == 4){
				this.runAction(CCSequence.actions(CCDelayTime.action(1.0f), CCCallFunc.action(this, "treatment4")));
			}
			redIndex = 5;
		}
		
		if(CGRect.intersects(smallredRect, stoolRect) && actionIndex == 7){
			if(redIndex == 5){
				this.runAction(CCSequence.actions(CCDelayTime.action(1.0f), CCCallFunc.action(this, "upstool")));
			}
			redIndex = 6;
		}
	}
	
	public void coolfoot(){
    	removeChild(foot1, true);
    	removeChild(red, true);
    	removeChild(m_IcePack, true);
    	removeChild(icing, true);
    	wrap.setPosition(850, 80);
    	coolfoot.setPosition(winSize.width/2.0f, winSize.height/2.0f);
    	m_IcePack.setPosition(CGPoint.ccp(_IcePack.getContentSize().width * 2.0f - 40, _IcePack.getContentSize().height / 2.0f + 25));
    	addChild(wrap);
    	addChild(coolfoot);
    	addChild(m_IcePack);
    	
    }
	
	public void treatment1(){
		removeChild(foot1, true);
		removeChild(coolfoot, true);
		removeChild(red, true);
		removeChild(m_Bandage, true);
		smallred.setPosition(winSize.width / 2.0f + 170, winSize.height / 2.0f);
		smallred.setOpacity(0);
		//treatment1.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
		m_Bandage.setPosition(CGPoint.ccp(_IcePack.getContentSize().width * 2.0f - 40, _IcePack.getContentSize().height / 2.0f + 25));
		//addChild(treatment1);
		
		
		CGSize s = CCDirector.sharedDirector().winSize();
		
        CCSpriteFrameCache.sharedSpriteFrameCache().addSpriteFrames("AnimTreatment1.plist");
        
        CCSprite arrow = CCSprite.sprite("digit9.png", true);
        arrow.setPosition(s.width/2, s.height/2); 

        spritesheet1 = CCSpriteSheet.spriteSheet("AnimTreatment1.png");
        addChild(spritesheet1);
        
       
        ArrayList<CCSpriteFrame> animFrames = new ArrayList<CCSpriteFrame>();

      	for(int i = 1; i <= 7; i++){
        	animFrames.add(CCSpriteFrameCache.sharedSpriteFrameCache().spriteFrameByName("firsttreat" + i + ".png"));
        }        
        CCAnimation animation = CCAnimation.animation("Treat", 0.1f, animFrames);
        CCAction action = CCRepeatForever.action(CCAnimate.action(animation, false));
        arrow.runAction(action);
        spritesheet1.addChild(arrow);
       
        
		addChild(m_Bandage);
        addChild(smallred);
        actionIndex = 3;
	}
	
	public void treatment2(){
		removeChild(spritesheet1, true);
		removeChild(foot1, true);
		removeChild(coolfoot, true);
		removeChild(smallred, true);
		removeChild(m_Bandage, true);
		removeChild(treatment1, true);
		smallred.setPosition(winSize.width / 2.0f + 50, winSize.height / 2.0f - 100);
		smallred.setOpacity(0);
		treatment2.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
		m_Bandage.setPosition(CGPoint.ccp(_IcePack.getContentSize().width * 2.0f - 40, _IcePack.getContentSize().height / 2.0f + 25));
		addChild(treatment2);
		addChild(m_Bandage);
		addChild(smallred);
		actionIndex = 4;
	}
	
	public void treatment3(){
		removeChild(treatment1, true);
		removeChild(foot1, true);
		removeChild(coolfoot, true);
		removeChild(smallred, true);
		removeChild(m_Bandage, true);
		removeChild(treatment2, true);
		smallred.setPosition(winSize.width / 2.0f + 80, winSize.height / 2.0f - 100);
		smallred.setOpacity(0);
		treatment3.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
		m_Bandage.setPosition(CGPoint.ccp(_IcePack.getContentSize().width * 2.0f - 40, _IcePack.getContentSize().height / 2.0f + 25));
		addChild(treatment3);
		addChild(m_Bandage);
		addChild(smallred);
		actionIndex = 5;
	}
	
	public void treatment4(){
		removeChild(smallred, true);
		removeChild(m_Bandage, true);
		removeChild(treatment3, true);
		removeChild(wrap,true);
		elevate.setPosition(850, 80);
		treatment4.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
		m_Bandage.setPosition(CGPoint.ccp(_IcePack.getContentSize().width * 2.0f - 40, _IcePack.getContentSize().height / 2.0f + 25));
		addChild(treatment4);
		addChild(elevate);
		addChild(m_Bandage);
		actionIndex = 6;
	}
	
	public void upstool(){
		removeChild(m_Bandage, true);
		removeChild(treatment3, true);
		removeChild(treatment1, true);
		removeChild(treatment2, true);
		removeChild(smallred, true);
		removeChild(m_stool, true);
		removeChild(treatment4, true);
		removeChild(uparrow, true);
		CCMenuItemImage done = CCMenuItemImage.item("done.png", "done.png", this, "finished");
		CCMenu donebutton = CCMenu.menu(done);
		donebutton.setPosition(winSize.width/2, 100);
		upstool.setPosition(winSize.width / 2.0f, winSize.height / 2.0f);
		addChild(donebutton);
		addChild(upstool);
	}

	public void finished(Object sender){
		Intent task1 = new Intent(CCDirector.sharedDirector().getActivity(), MapActivity.class);
    	CCDirector.sharedDirector().getActivity().startActivity(task1);
       	CCDirector.sharedDirector().getActivity().finish();
    	
	}
	
	public void timeout(){
		CCDirector.sharedDirector().replaceScene(GameOverLayer.scene("Timeout"));
	}
	
}
