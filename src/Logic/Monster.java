package Logic;

import App.GameImage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Monster extends LifeForm{
	
	private Hero target;
	private GameImage hitBox;
	private String rightSide;
	private String leftSide;
	private boolean isImageRight;
	private int score;
	
	public Monster(Vector2D pos,Hero h) {
		super(pos,new Vector2D(0,0),new Vector2D(60,90),new GameImage(new Image(ClassLoader.getSystemResource("Images/Hero1.png").toString())),1);
		target = h;
		this.getGameImage().updatePosition(this.getPosition());
		this.hitBox = new GameImage(new Image(ClassLoader.getSystemResource("Images/HitBox.png").toString()));
		this.hitBox.updatePosition(new Vector2D(-100,-100));
		this.score = 1;
	}
	
	public double findHero() {
		double x = target.getPosition().getX() - this.getPosition().getX();
		double y = target.getPosition().getY() - this.getPosition().getY();
		
		double rad = Math.atan2(y, x);
		return rad;
		
	}
	@Override
	public boolean isCollide(Entity other) {
		if(this.hitBox.getBoundsInParent() == null || other.getGameImage().getBoundsInParent() == null) {
			return false;
		}
		if(this.hitBox == null) {
			return false;
		}
		return this.hitBox.getBoundsInParent().intersects(other.getGameImage().getBoundsInParent());
	}
	@Override
	public boolean isCollide(ImageView other) {
		if(this.hitBox.getBoundsInParent() == null || other.getBoundsInParent() == null) {
			return false;
		}
		if(this.hitBox == null) {
			return false;
		}
		return this.hitBox.getBoundsInParent().intersects(other.getBoundsInParent());
	}
	
	public void updateSide() {
		if(this.isRight && !this.isImageRight) {
			this.getGameImage().setImage(new Image(ClassLoader.getSystemResource(rightSide).toString()));
			this.isImageRight = true;
		}
		if(!this.isRight && this.isImageRight) {
			this.getGameImage().setImage(new Image(ClassLoader.getSystemResource(leftSide).toString()));
			this.isImageRight = false;
		}
	}
	
	public abstract Vector2D direct();
	
	public Hero getTarget() {
		return target;
	}
	public void setTarget(Hero target) {
		this.target = target;
	}
	public GameImage getHitBox() {
		return hitBox;
	}
	public void setHitBox(GameImage hitBox) {
		this.hitBox = hitBox;
	}
	public boolean isImageRight() {
		return isImageRight;
	}
	public void setImageRight(boolean isImageRight) {
		this.isImageRight = isImageRight;
	}
	public String getRightSide() {
		return rightSide;
	}
	public void setRightSide(String rightSide) {
		this.rightSide = rightSide;
	}
	public String getLeftSide() {
		return leftSide;
	}
	public void setLeftSide(String leftSide) {
		this.leftSide = leftSide;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
