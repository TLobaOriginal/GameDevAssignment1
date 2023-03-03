import util.GameObject;
import util.Point3f;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

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

    public Fighter(String resourcePackageLocation, String textureLocation,
                   int width, int height, Point3f centre,
                   String direction, HealthBar healthBar){
        super(textureLocation, width, height, centre);
        this.direction = direction;
        this.resourcePackageLocation = resourcePackageLocation;
        this.healthBar = healthBar;
        try {
            currentImage = ImageIO.read(new File(textureLocation));
        }catch (Exception ex){
            System.out.println("Trouble " + ex);
        }
    }

    public void setPlayerHitBox(Rectangle playerHitBox) {
        this.playerHitBox = playerHitBox;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

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
        if(opponentAttackPose.contains("Attack1"))
            setTextureLocation(resourcePackageLocation + direction + "/Hurt1.png");
        else if(opponentAttackPose.contains("Attack2"))
            setTextureLocation(resourcePackageLocation + direction + "/Hurt2.png");
        else if(opponentAttackPose.contains("Attack3"))
            setTextureLocation(resourcePackageLocation + direction + "/Hurt1.png");
        else if(opponentAttackPose.contains("Attack4"))
            setTextureLocation(resourcePackageLocation + direction + "/HurtLaunch.png");
        else if(opponentAttackPose.contains("JumpKick"))
            setTextureLocation(resourcePackageLocation + direction + "/HurtLaunch.png");
    }

    private void setBeingHit(boolean b) {
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
}
