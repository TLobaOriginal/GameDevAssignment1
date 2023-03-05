import util.GameObject;
import util.Point3f;

public class PowerUp extends GameObject {
    BoostableStats stat;
    int boost;
    PowerUp(String textureLocation, int width, int height, Point3f centre, int boost, BoostableStats stat){
        super(textureLocation, width, height, centre);
        this.stat = stat;
        this.boost = boost;
    }

    public void boostFighter(Fighter player){
        switch (stat){
            case ATTACK:
                player.boostAttack(boost);
                break;
            case DEFENCE:
                player.boostDefence(boost);
                break;
            default:
                System.out.println("Unknown stat type");
        }
    }
}
