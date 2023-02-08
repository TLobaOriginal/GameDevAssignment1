import util.GameObject;
import util.Point3f;

public class Fighter extends GameObject {
    public Fighter(String textureLocation, int width, int height, Point3f centre){
        super(textureLocation, width, height, centre);
    }

    public boolean checkGrounded(GameObject ground){
        if ((ground.getCentre().getY() + 300 + (getHeight() / 50)) == getCentre().getY()){
            System.out.println("I AM Touching the ground!!!");
            return true;
        }
        return false;
    }

}
