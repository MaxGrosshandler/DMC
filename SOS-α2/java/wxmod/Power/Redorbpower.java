package wxmod.Power;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import wxmod.Card.Vfx.slashGold;


public class Redorbpower extends AbstractPower
 {
  public static final String POWER_ID = "Redorbpower";
  private static final PowerStrings powerStrings;
  public static final String NAME;
  public static final String[] DESCRIPTIONS;
  private int number;

   
   public Redorbpower(AbstractCreature owner, int amt)
   {
    this.name = NAME;
    this.ID = POWER_ID;
    this.owner = owner;
    this.amount = -1;
    this.img = ImageMaster.loadImage("img/powers/Mark.png");
   updateDescription();
   loadRegion("Redorbpower");
 }

  
   public void updateDescription() {
		 this.description = DESCRIPTIONS[0];}
 
   
   public void onDeath()
   {this.number = (int)Math.round(Math.random()*(50-1)+1);
    AbstractDungeon.player.gold += this.number;
    for(int i = 0;i < this.number; i++) {
    AbstractDungeon.effectsQueue.add(new slashGold(AbstractDungeon.player));
    }
   }
   
   static {
       powerStrings = CardCrawlGame.languagePack.getPowerStrings("Redorbpower");
       NAME = Redorbpower.powerStrings.NAME;
       DESCRIPTIONS = Redorbpower.powerStrings.DESCRIPTIONS;
   }
}
   
   
  
 
 