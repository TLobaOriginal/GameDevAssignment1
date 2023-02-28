import util.GameObject;
import util.Point3f;
import util.Vector3f;

import javafx.scene.media.Media;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Fighter extends GameObject {
    String resourcePackageLocation;
    String direction;
    Fighter opponent;
    BufferedImage currentImage;

    //States
    boolean grounded = false;
    boolean beingHit = false;

    public Fighter(String resourcePackageLocation, String textureLocation,
                   int width, int height, Point3f centre,
                   String direction){
        super(textureLocation, width, height, centre);
        this.direction = direction;
        this.resourcePackageLocation = resourcePackageLocation;
        try {
            currentImage = ImageIO.read(new File(textureLocation));
        }catch (Exception ex){
            System.out.println("Trouble " + ex);
        }
    }

    public void setOpponent(Fighter opponent) {
        this.opponent = opponent;
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
            setTextureLocation(resourcePackageLocation + direction + "/Hurt3.png");
        else if(opponentAttackPose.contains("Attack4"))
            setTextureLocation(resourcePackageLocation + direction + "/HurtLaunch.png");
        else if(opponentAttackPose.contains("JumpKick"))
            setTextureLocation(resourcePackageLocation + direction + "/HurtLaunch.png");
    }

    private void setBeingHit(boolean b) {
    }

    public boolean checkGrounded(GameObject ground){
        /*Vector3f rayCastBegin = new Vector3f(getCentre());
        float innerPlayerWidth = getWidth() * 0.6f;
        rayCastBegin.PlusPoint(new Point3f(-1*(innerPlayerWidth/2.0f), 0.0f, 0.0f));
        float yValue = getHeight()/2.0f;
        Vector3f rayCastEnd = new Vector3f(rayCastBegin).PlusVector(new Vector3f(0.0f, yValue, 0.0f));

        RayCastInfo*/

        if ((ground.getCentre().getY() + 300 + (getHeight() / 2)) == getCentre().getY()){
            //System.out.println("I AM Touching the ground!!!");
            return true;
        }
        return false;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setGrounded(boolean grounded) {
        this.grounded = grounded;
    }
}
