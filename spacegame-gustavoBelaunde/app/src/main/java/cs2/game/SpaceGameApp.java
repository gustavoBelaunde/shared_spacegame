package cs2.game;

import java.util.ArrayList;
import java.util.Iterator;

import cs2.util.Vec2;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SpaceGameApp extends Application {
  @Override
  public void start(Stage stage) {
    stage.setTitle("Invaders");
  
    //You can change the window size here if you want
    Canvas canvas = new Canvas(800,800);
    stage.setScene(new Scene(new StackPane(canvas)));
    stage.show();
    GraphicsContext g = canvas.getGraphicsContext2D();

     // Create instances of Player and EnemySwarm
        Image playerImage = new Image("file:users/gbelaund/Pictures/Player.jpg");
        Image enemyImage = new Image("file:users/gbelaund/Pictures/Enemy.jpg");
        Image bulletImage = new Image("file:users/gbelaund/Pictures/Bullet.jpg");

        Player player = new Player(playerImage,bulletImage,new Vec2(275,500));

        ArrayList<Bullet> bullets = new ArrayList<Bullet>();
        EnemySwarm enemySwarm = new EnemySwarm(5, 5, enemyImage, bulletImage);


        // KeyPressed event handler
        stage.getScene().setOnKeyPressed(e -> {
            KeyCode keyCode = e.getCode();
            if (keyCode == KeyCode.LEFT) {
                player.moveLeft();
            } else if (keyCode == KeyCode.RIGHT) {
                player.moveRight();
            } else if (keyCode == KeyCode.SPACE) {
                Bullet bullet = player.shoot();
                if (bullet != null) {
                    bullets.add(bullet);
                }
            }
        });

        // AnimationTimer to display everything and move bullets
        new AnimationTimer() {
            public void handle(long now) {
                g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                // Display player
                player.display(g);

                // Display enemies
                enemySwarm.display(g);

                // Move bullets
                for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext();) {
                    Bullet bullet = iterator.next();
                    bullet.display(g);
                    bullet.update();
                    // Remove bullets that have gone off-screen
                    if (bullet.pos.getY() < 0) {
                        iterator.remove();
                    }
                }

                // Make a random enemy shoot
                Bullet enemyBullet = enemySwarm.shoot();
                if (enemyBullet != null) {
                    bullets.add(enemyBullet);
                }
            }
        }.start();
    
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();


  }
}
