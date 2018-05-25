
public class acornn{

	int x;
	int y;
	int oldX;
	boolean isOn=true;

	public acornn(int ax, int ay){
		x=ax;
		y=ay;
		oldX=ax;
	}

	public int getX(){
		return x;
	}

	public boolean getIsOn(){
		return isOn;
	}

	public void setIsOn(boolean b){
		isOn=b;
	}

	public int getY(){
		return y;
	}

	public void subX(){
		x-=5;
	}

	public void setX(){
		x=oldX;
	}

	public void changeX(){
		x=-10000;
	}

}