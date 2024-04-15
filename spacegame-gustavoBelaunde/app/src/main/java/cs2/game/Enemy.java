package cs2.game;

import cs2.util.Vec2;
import javafx.scene.image.Image;

public class Enemy extends Sprite {
  private Image bulletPicture;

  /*
  //This constructor should initialize all fields
  //**Remember that some fields are inherited from Sprite
    */
  public Enemy(Image avatar, Image bulletPic, Vec2 p) { 
    super(avatar, p);
    this.bulletPicture = bulletPic;
  }


  /*
  // This method should create a new Bullet object and return it
  // The Bullet should be initialized with the bulletPicture, the
  // current position of the enemy, and a velocity going down the screen
    */
  public Bullet shoot() {

    Vec2 bulletPos = new Vec2(pos.getX() + img.getWidth() / 2, pos.getY());
  
    return new Bullet(bulletPicture, bulletPos, new Vec2(0, -5));
   }

}
