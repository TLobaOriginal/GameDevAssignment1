import util.Point3f;

public class HealthBar {
    private final double healthPoints;
    private final double totalHealthPoints;
    private final String resourcePath = "Warrior's Peak (Game)/BasicGameTemplate/res/";
    private Point3f centre;
    String direction;
    private String texture;

    HealthBar(double healthPoints, String direction, Point3f centre){
        this.healthPoints = healthPoints;
        totalHealthPoints = healthPoints;
        this.direction = direction;
        this.centre = centre;
        this.texture = resourcePath + "healthBarHigh" + this.direction + ".png";
    }

    public void updateHealthBarTexture(){
        if(healthPoints < (totalHealthPoints * 0.33)){
            this.texture = resourcePath + "healthBarLow" + direction + ".png";
        }
        else if(healthPoints < (totalHealthPoints * 0.66)){
            this.texture = resourcePath + "healthBarMedium" + direction + ".png";
        }
        else{
            this.texture = resourcePath + "healthBarHigh" + direction + ".png";
        }
    }

    public Point3f getCentre() {
        return centre;
    }

    public String getTexture() {
        return texture;
    }

    public double getHealthPoints() {
        return healthPoints;
    }
}
