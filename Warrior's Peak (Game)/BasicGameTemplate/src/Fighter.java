import util.GameObject;
import util.Point3f;
import util.Vector3f;

import javafx.scene.media.Media;

public class Fighter extends GameObject {
    String direction;
    Fighter opponent;
    boolean grounded = false;
    public Fighter(String textureLocation, int width, int height, Point3f centre, String direction){
        super(textureLocation, width, height, centre);
        this.direction = direction;
    }

    public void setOpponent(Fighter opponent) {
        this.opponent = opponent;
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
