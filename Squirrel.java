
public class Squirrel{

	int x;
	int y=400;
	int imgCount=0;
	int counter=0;
	int jCounter=0;

	public Squirrel(int ax, int ay){
		x=ax;
		y=ay;
	}

	public void addJCounter(){
		jCounter++;
	}

	public void subJCounter(){
		jCounter--;
	}

	public void setJCounter(){
		jCounter=0;
	}

	public int getJCounter(){
		return jCounter;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public int getImgCount(){
		return imgCount;
	}

	public void addX(){
		x++;
	}

	public void addY(){
		y+=4;
	}

	public void subY(){
		y-=4;
	}

	public void setY(){
		y=450;
	}

	public void setX(int in){
		x=in;
	}

	public void addImgCount(){
		imgCount++;
	}

	public void subImgCount(){
		imgCount--;
	}

	public void setImgCount(int g){
		imgCount=g;
	}

	public int getCounter(){
		return counter;
	}

	public void addCounter(){
		counter++;
	}
	public void setCounter(){
		counter=0;
	}

}