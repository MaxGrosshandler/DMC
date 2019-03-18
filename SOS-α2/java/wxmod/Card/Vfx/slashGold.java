package wxmod.Card.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ShineLinesEffect;

public class slashGold
  extends AbstractGameEffect
{
  @SuppressWarnings("unused")
  private static final float RAW_IMG_WIDTH = 32.0F;
  private static final float IMG_WIDTH = 32.0F * Settings.scale;
  private TextureAtlas.AtlasRegion img;
  private boolean isPickupable = false;
  public boolean pickedup = false;
  private float x;
  private float y;
  private float landingX;
  private float landingY;
  private boolean willBounce = false;
  private boolean hasBounced = true;
  private float bounceY;
  private float bounceX;
  private float vY = 6.0F;
  private float vX = 0.0F;
  private float gravity = -0.3F;
  private Hitbox hitbox;
  
  public slashGold(AbstractCreature m)
  {
    if (MathUtils.randomBoolean()) {
      this.img = ImageMaster.COPPER_COIN_1;
    } else {
      this.img = ImageMaster.COPPER_COIN_2;
    }
    this.willBounce = (MathUtils.random(3) != 0);
    if (this.willBounce)
    {
      this.hasBounced = false;
      this.bounceY = MathUtils.random(1.0F, 4.0F);
      this.bounceX = MathUtils.random(-3.0F, 3.0F);
    }
    this.y = (AbstractDungeon.floorY + 100.0F);
    this.x = (m.hb.cX - this.img.packedWidth / 2.0F);
    this.landingX = MathUtils.random(m.hb.cX - Settings.HEIGHT * 0.05F, m.hb.cX + Settings.HEIGHT * 0.08F);
    this.landingY = MathUtils.random(AbstractDungeon.floorY - Settings.HEIGHT * 0.05F, AbstractDungeon.floorY);
    
    this.rotation = MathUtils.random(360.0F);
    this.scale = Settings.scale;
  }
  
  public void update()
  {
    if (!this.isPickupable)
    {
      this.vX = ((this.landingX - this.x) / (Gdx.graphics.getDeltaTime() * 60.0F));
      this.x += this.vX * Gdx.graphics.getDeltaTime() * 60.0F;
      this.y += this.vY * Gdx.graphics.getDeltaTime() * 60.0F;
      this.vY += this.gravity;
      if (this.y < this.landingY) {
        if (this.hasBounced)
        {
          this.x = this.landingX;
          this.y = this.landingY;
          this.isPickupable = true;
          this.hitbox = new Hitbox(this.x - IMG_WIDTH * 2.0F, this.y - IMG_WIDTH * 2.0F, IMG_WIDTH * 4.0F, IMG_WIDTH * 4.0F);
        }
        else
        {
          if (MathUtils.random(1) == 0) {
            this.hasBounced = true;
          }
          this.x = this.landingX;
          this.y = this.landingY;
          this.vY = this.bounceY;
          this.vX = this.bounceX;
          this.bounceY *= 0.5F;
          this.bounceX *= 0.3F;
        }
      }
    }
    else if (!this.pickedup)
    {
      this.pickedup = true;
      this.isDone = true;
      playGainGoldSFX();
      
      AbstractDungeon.effectsQueue.add(new ShineLinesEffect(this.x, this.y));
    }
  }
  
  private void playGainGoldSFX()
  {
    int roll = MathUtils.random(2);
    switch (roll)
    {
    case 0: 
      CardCrawlGame.sound.play("GOLD_GAIN", 0.1F);
      break;
    case 1: 
      CardCrawlGame.sound.play("GOLD_GAIN_3", 0.1F);
      break;
    default: 
      CardCrawlGame.sound.play("GOLD_GAIN_5", 0.1F);
    }
  }
  
  public void render(SpriteBatch sb)
  {
    sb.setColor(Color.WHITE);
    
    sb.draw(this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
    if (this.hitbox != null) {
      this.hitbox.render(sb);
    }
  }

@Override
public void dispose() {
	// TODO 自动生成的方法存根
	
}
}