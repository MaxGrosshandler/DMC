  package wxmod.Power;
  
  import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
  import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
  import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
  import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
 
 public class Flex2 extends AbstractPower
 {
   public static final String POWER_ID = "Flex2";
   private static final PowerStrings powerStrings;
   public static final String NAME;
   public static final String[] DESCRIPTIONS;


   
   public Flex2(AbstractCreature owner, int newAmount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = newAmount;
     this.img = ImageMaster.loadImage("img/powers/Exercise1.png");
     updateDescription();
     loadRegion("Flex2");
   }
   
   public void updateDescription() {	   
	   this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	             }
   
   public void atEndOfTurn(boolean isPlayer)
 {
	   if(AbstractDungeon.player.hasPower("Skystarpower")) {}
       else { 
             flash();
             AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, "Strength", this.amount));
             AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "Flex2"));
       }	 
     } 
   
   static {
	     powerStrings = CardCrawlGame.languagePack.getPowerStrings("Flex2");
	     NAME = Flex2.powerStrings.NAME;
	     DESCRIPTIONS = Flex2.powerStrings.DESCRIPTIONS;
	 }
   
   }
  


