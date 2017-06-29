package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Box extends Element implements Serializable  {
	//data members
private Image boxImage;
private boolean OnTarget;








public Image getBoxImage() {
	return boxImage;
}
public boolean isOnTarget() {
	return OnTarget;
}
public void setBoxImage(Image boxImage) {
	this.boxImage = boxImage;
}
public void setOnTarget(boolean onTarget) {
	this.OnTarget = onTarget;
}
	//default c'tor
	public Box() {
		this.pos.setX(0);
		this.pos.setY(0);
		this.OnTarget=false;
		try {
			boxImage= new Image(new FileInputStream("./resources/Jellyfish.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			}
	//c'tor with position
	public Box(Position pos){
		this.setPos(pos);
		this.OnTarget=false;
		try {
			boxImage= new Image(new FileInputStream("./resources/Jellyfish.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public String toString()
	{
		return "@";
	}
	//paints the box
	@Override
		public void draw(GraphicsContext gc, int i, int j, double cellWidth, double cellHeight) {
			gc.drawImage(boxImage, j*cellWidth, i*cellHeight,0.95*cellWidth,0.95*cellHeight);
		}
}
