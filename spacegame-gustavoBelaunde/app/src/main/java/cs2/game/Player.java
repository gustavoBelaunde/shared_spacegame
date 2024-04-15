package cs2.game;

import cs2.util.Vec2;
import javafx.scene.image.Image;

public class Player extends Sprite {
  private Image bulletPicture;

  //public Image image = new Image("/users/gbelaund/Pictures/Player.png");

  /*
  //This constructor should initialize all fields
  //**Remember that some fields are inherited from Sprite
  */
  public Player(Image avatar, Image bullPic, Vec2 p) {
    super(avatar, p);
    this.bulletPicture = bullPic;
   }
  

  /*
  // This method should create a new Bullet object and return it
  // The Bullet should be initialized with the bulletPicture, the
  // current position of the player, and a velocity going up the screen
    */
  public Bullet shoot() { 

  Vec2 bulletPos = new Vec2(pos.getX() + img.getWidth() / 2, pos.getY());
  
  return new Bullet(bulletPicture, bulletPos, new Vec2(0, -5));
}



  /*
  // This method should move the player left by some amount
  */
  public void moveLeft() { 
    pos = new Vec2(pos.getX() - 5, pos.getY());
  }

  /*
  // This method should move the player right by some amount
  */
  public void moveRight() { 
     pos = new Vec2(pos.getX() - 5, pos.getY());
  }
  

}
