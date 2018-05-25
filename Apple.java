public class Apple{

	int x=0;
	int y=200;
	int old=0;
	boolean hit1=false, hit2=false, hit3=false;

	public Apple(int aX, int aY){
		x=aX;
		old=x;
		y=aY;
	}

	public int getX(){
		return x;
	}

	public void setX(){
		x=-100;
	}

	public void resetX(){
		x=old;
	}

	public int getY(){
		return y;
	}

	public void setY(int num){
		y=num;
	}

	public void setHit1(){
		hit1=true;
	}

	public void setHit2(){
		hit2=true;
	}

	public void setHit3(){
		hit3=true;
	}

	public void resetHit1(){
		hit1=false;
	}

	public void resetHit2(){
		hit2=false;
	}

	public void resetHit3(){
		hit3=false;
	}

	public boolean checkHit1(){
		return hit1;
	}

	public boolean checkHit2(){
		return hit2;
	}

	public boolean checkHit3(){
		return hit3;
	}

}