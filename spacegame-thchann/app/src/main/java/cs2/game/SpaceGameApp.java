package cs2.game;

import cs2.util.Vec2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class SpaceGameApp extends Application {

  protected static final int FRAME_RATE = 16;
  Image bullet = new Image("file:laser_beam.png", 100, 100, true, false);
  Image battleship = new Image("file:rocket.png", 10, 80, true, false);
  Image enemy_ship = new Image("file:enemy_ship.png", 30,30,true,false);

  public void start(Stage stage) {
    stage.setTitle("PROXY WARS");
    stage.show();
    //You can change the window size here if you want
    Canvas canvas = new Canvas(800,800);
    stage.setScene(new Scene(new StackPane(canvas)));
    GraphicsContext g = canvas.getGraphicsContext2D();

    Player player = new Player(battleship, bullet, new Vec2(100,600));
    Enemy enemy = new Enemy(enemy_ship, bullet, new Vec2(100,200));
    EnemySwarm enemyswarm = new EnemySwarm(3, 3, enemy_ship, bullet);
    ArrayList<Bullet> bullets = new ArrayList<>();

    AnimationTimer timer = new AnimationTimer() {
      private long lastUpdateTime = 0;

      public void handle(long now){
        long elapsedTime = now - lastUpdateTime;

        if (elapsedTime >= FRAME_RATE * 100_000_000) {
          lastUpdateTime = now;
          elapsedTime = 0;
          bullets.add(enemy.shoot());
          bullets.add(enemyswarm.shoot());
        }
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, 800, 800);
        player.display(g);
        enemy.display(g);
        enemy.move();
        enemyswarm.display(g);

          canvas.setOnKeyPressed((KeyEvent k) -> {
            canvas.requestFocus();
            if(k.getCode().equals(KeyCode.LEFT)){
              player.moveLeft();
            } 
            if (k.getCode().equals(KeyCode.RIGHT)){
              player.moveRight();
            }
            if (k.getCode().equals(KeyCode.UP)){
              player.moveUp();
            } 
            if (k.getCode().equals(KeyCode.DOWN)){
              player.moveDown();
            } 
            if (k.getCode().equals(KeyCode.SPACE)){
              bullets.add(player.shoot());
            }
          });

          for (Bullet b : bullets){
           
            b.update();
            b.display(g);
            /*if(b.pos.getX() < 0 || b.pos.getX() > canvas.getWidth() || b.pos.getY() <0 || b.pos.getY() > canvas.getHeight()) {
              bullets.remove(b);
            }*/
          }
        //}
      }
    };
    timer.start();
    canvas.requestFocus();

  }
}
