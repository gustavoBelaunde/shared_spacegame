package cs2.game;

import cs2.util.Vec2;
import javafx.scene.image.Image;

public class Enemy extends Sprite {
  public boolean isRight = true, isUp = true;
  private Image bulletPicture;
  public Double x;

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
    return new Bullet(bulletPicture, new Vec2(pos.getX(),pos.getY()), new Vec2(0,10));
   }

  public void move() {
    /*if (pos.getX() <= 140){
      pos = new Vec2(pos.getX(), pos.getY());
    } else if (pos.getX() >= 700){
      pos = new Vec2(-pos.getX(), pos.getY());
    }
    if (pos.getY() >= 700){
      pos = new Vec2(pos.getX(), pos.getY());
    } else if (pos.getY() <= 100){
      pos = new Vec2(pos.getX(), pos.getY() + 100);
      System.out.println(pos.getY());
    }*/

    if(pos.getX() > 700){
      isRight = false;
    } else if (pos.getX() < 50){
      isRight = true;
    }

    if(pos.getY() < 50){
      isUp = false;
      //pos = new Vec2(pos.getX(), pos.getY() + 100);
    } else if (pos.getY() > 500){
      isUp = true;
      //pos = new Vec2(pos.getX(), pos.getY() - 5);
    }

    if(isRight && isUp){
      pos = new Vec2(pos.getX() + 3, pos.getY() - 1);
    } else if (!isRight && !isUp) {
      pos = new Vec2(pos.getX() - 3, pos.getY() + 1);
    } else if (!isRight && isUp){
      pos = new Vec2(pos.getX() - 3, pos.getY() - 1);
    } else {
      pos = new Vec2(pos.getX() + 3, pos.getY() + 1);
    }



   }


}

