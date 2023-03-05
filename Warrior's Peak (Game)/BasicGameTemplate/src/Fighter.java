import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import util.GameObject;
import util.Point3f;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class Fighter extends GameObject {
    String resourcePackageLocation;
    String direction;
    Fighter opponent;
    BufferedImage currentImage;
    Rectangle playerHitBox;
    HealthBar healthBar;

    //States
    boolean grounded = false;
    boolean beingHit = false;
    boolean attacking = false;
    boolean comboActive = false;
    boolean stuck = false;
    boolean invulnerable = false;


    //Player's audioMaps
    private HashMap<String, Media> audioMap;

    //Stats
    int attackPower = 100;
    int defence = 100;

    public Fighter(String resourcePackageLocation, String textureLocation,
                   int width, int height, Point3f centre,
                   String direction, HealthBar healthBar){
        super(textureLocation, width, height, centre);
        this.direction = direction;
        this.resourcePackageLocation = resourcePackageLocation;
        this.healthBar = healthBar;
        audioMap = new HashMap<>();
        try {
            currentImage = ImageIO.read(new File(textureLocation));
        }catch (Exception ex){
            System.out.println("Trouble " + ex);
        }

        audioMap.put("punch1", new Media(new File("Warrior's Peak (Game)/BasicGameTemplate/res/audio/punch1.wav").toURI().toString()));
        audioMap.put("punch2", new Media(new File("Warrior's Peak (Game)/BasicGameTemplate/res/audio/punch2.wav").toURI().toString()));
        audioMap.put("punch3", new Media(new File("Warrior's Peak (Game)/BasicGameTemplate/res/audio/punch3.wav").toURI().toString()));
    }

    public void setPlayerHitBox(Rectangle playerHitBox) {
        this.playerHitBox = playerHitBox;
    }

    public void setOpponent(Fighter opponent) {
        this.opponent = opponent;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    //Player collision functions
    public void onCollisionWithAttack(String opponentAttackPose){
        if((!getTexture().contains("Attack")) || (opponentAttackPose.contains("Attack4") && !getTexture().contains("Attack4"))){
            beingHit(opponentAttackPose);
        } else {
            blowForBlow();
        }
    }

    private void blowForBlow() {
        System.out.println("BLOW FOR BLOW");
    }

    private void beingHit(String opponentAttackPose) {
        setBeingHit(true);
        if(opponentAttackPose.contains("Attack1")) {
            MediaPlayer mediaPlayer = new MediaPlayer(audioMap.get("punch1"));
            mediaPlayer.play();
            setTextureLocation(resourcePackageLocation + direction + "/Hurt1.png");
            Controller.getInstance().freezePlayerHasBeenHit(this, 200L);
            healthBar.reduceHp(((double)opponent.attackPower/defence) * 100);
        }
        else if(opponentAttackPose.contains("Attack2")) {
            setTextureLocation(resourcePackageLocation + direction + "/Hurt2.png");
            MediaPlayer mediaPlayer = new MediaPlayer(audioMap.get("punch2"));
            mediaPlayer.play();
            Controller.getInstance().freezePlayerHasBeenHit(this, 200L);
            healthBar.reduceHp(((double)opponent.attackPower/defence) * 100);
        }
        else if(opponentAttackPose.contains("Attack3")) {
            setTextureLocation(resourcePackageLocation + direction + "/Hurt1.png");
            MediaPlayer mediaPlayer = new MediaPlayer(audioMap.get("punch1"));
            mediaPlayer.play();
            Controller.getInstance().freezePlayerHasBeenHit(this, 200L);
            healthBar.reduceHp(((double)opponent.attackPower/defence) * 150);
        }
        else if(opponentAttackPose.contains("Attack4")) {
            MediaPlayer mediaPlayer = new MediaPlayer(audioMap.get("punch3"));
            mediaPlayer.play();
            setTextureLocation(resourcePackageLocation + direction + "/HurtLaunch.png");
            Controller.getInstance().freezePlayerHasBeenHit(this, 200L);
            healthBar.reduceHp(((double)opponent.attackPower/defence) * 200);
        }
        else if(opponentAttackPose.contains("JumpKick")) {
            MediaPlayer mediaPlayer = new MediaPlayer(audioMap.get("punch3"));
            mediaPlayer.play();
            setTextureLocation(resourcePackageLocation + direction + "/HurtLaunch.png");
            Controller.getInstance().freezePlayerHasBeenHit(this, 200L);
            healthBar.reduceHp(((double)opponent.attackPower/defence) * 150);
        }
        setBeingHit(false);
    }

    public void setBeingHit(boolean b) {
        beingHit = b;
    }

    //Winning condition function
    public boolean isDead(){
        return healthBar.getHealthPoints() <= 0.0;
    }

    public boolean checkGrounded(Rectangle ground){
        return playerHitBox.intersects(ground);
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    //statsFunction
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefence() {
        return defence;
    }

    //Stat boosting Functions
    public void boostAttack(int boost){
        attackPower += boost;
        new StatBoostTimeThread(this, boost, BoostableStats.ATTACK);
    }

    public void boostDefence(int boost){
        defence += boost;
        new StatBoostTimeThread(this, boost, BoostableStats.DEFENCE);
    }

    static class StatBoostTimeThread{
        StatBoostTimeThread(Fighter player, int boost, BoostableStats stat){
            new Thread(() ->{
                try{
                    Thread.sleep(10000L);
                    switch (stat){
                        case DEFENCE:
                            player.setDefence(player.getDefence() - boost);
                            break;
                        case ATTACK:
                            player.setAttackPower(player.getAttackPower() - boost);
                            break;
                    }
                }catch (Exception ex){
                    System.out.println("Failed threading");
                }
            }).start();
        }
    }
}
