package sht;
import robocode.*;
import java.awt.Color; 
/**
 * RoboFighterClone - a robot by (your name here)
 */
public class ShtefRobot extends AdvancedRobot
{
    int moveDirection=1;
	double randInt = 1;
	double randHit = 1;
    public void run() {
		while(true){
			setColors(Color.white, Color.red, Color.black);
			setScanColor(Color.green);
        	setAdjustRadarForRobotTurn(true);
        	setAdjustGunForRobotTurn(true);
			if(getOthers() <= 2){
				setTurnRadarRight(360);
				setTurnLeft(-60-Math.random());
				setAhead(200);
				execute();
			}
        	turnRadarRightRadians(60);
		}
    }

    
    public void onScannedRobot(ScannedRobotEvent e) {
        double absolutBearing = e.getBearingRadians()+getHeadingRadians();
        double laterVelocity = e.getVelocity() * Math.sin(e.getHeadingRadians() - absolutBearing);
        double gunTurnAmt;
        setTurnRadarLeftRadians(getRadarTurnRemainingRadians());
        if(Math.random()>.12){
            setMaxVelocity((10 * Math.random()) + 10);
        }
        if (e.getDistance() > 150) {
            gunTurnAmt = robocode.util.Utils.normalRelativeAngle(absolutBearing - getGunHeadingRadians() + laterVelocity / 22);
            setTurnGunRightRadians(gunTurnAmt);
            setTurnRightRadians(robocode.util.Utils.normalRelativeAngle(absolutBearing - getHeadingRadians() + laterVelocity / getVelocity()));
            setAhead((e.getDistance() - 200) * moveDirection);
            setFire(3);
        }
        else{
            gunTurnAmt = robocode.util.Utils.normalRelativeAngle(absolutBearing - getGunHeadingRadians()+ laterVelocity / 15);
            setTurnGunRightRadians(gunTurnAmt);
            setTurnLeft(-90-e.getBearing());
            setAhead((e.getDistance() - 200) * moveDirection);
            setFire(3);
        }
    }
    public void onHitWall(HitWallEvent e){
        moveDirection=-moveDirection;
    }
	
	public void onHitByBullet(HitByBulletEvent e) {
		moveDirection = -moveDirection;
		randInt = Math.random() + .5;
		randHit = randInt * randInt * moveDirection;
	}
    /**
     * onWin:  Do a victory dance
     */
    public void onWin(WinEvent e) {
        for (int i = 0; i < 50; i++) {
            turnRight(9);
            ahead(10);
			turnLeft(9);
			back(10);
        }
    }
}