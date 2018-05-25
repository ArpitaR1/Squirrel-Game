import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.awt.image.*;
import java.applet.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import java.util.*;
import sun.audio.*;

public class GameTemplate extends JPanel implements KeyListener,Runnable,MouseListener
{
	private float angle;
	private int x;
	private int y;
	private JFrame frame;
	Thread t;
	private boolean gameOn;
	BufferedImage guy;
	BufferedImage[] guys=new BufferedImage[8];
	BufferedImage[] hit=new BufferedImage[8];
	Image back1=null,back2=null,rock=null,apple=null,finish=null,gameOver=null,start=null,acorn=null;
	int rx=120,ry=0,rx2=0,ry2=0,h=0,imgCount=0,counter=0,check=0,imgCount2=0,count=0,score=0;
	boolean clicked=false,jump1=false,jump2=false,jump3=false,right=false,restart=false,hits=false,end=false,instructions=false;
	Rectangle squirrel=null;

	ArrayList<Squirrel> squirrels = new ArrayList<Squirrel>();
	ArrayList<Apple> apples = new ArrayList<Apple>();
	ArrayList<Rectangle> appleHitBox = new ArrayList<Rectangle>();
	ArrayList<Integer> rocks = new ArrayList<Integer>();
	ArrayList<Rectangle> rockHitBox = new ArrayList<Rectangle>();
	ArrayList<Rectangle> squirrelHitBox = new ArrayList<Rectangle>();
	ArrayList<acornn> acorns = new ArrayList<acornn>();
	ArrayList<Rectangle> acornHitBox = new ArrayList<Rectangle>();

	public GameTemplate()
	{
		repaint();
		frame=new JFrame();
		x=5;
		y=450;
		gameOn=false;

		squirrels.add(new Squirrel(5,450));
		squirrels.add(new Squirrel(105,450));
		squirrels.add(new Squirrel(205,450));

		acorns.add(0,new acornn(1000,140));
		acorns.add(1,new acornn(3000,140));
		acorns.add(2,new acornn(6000,140));
		acorns.add(3,new acornn(9000,140));
		acorns.add(4,new acornn(12000,140));
		acorns.add(5,new acornn(15000,140));
		acorns.add(6,new acornn(18000,140));


		for (int i=0;i<acorns.size();i++){
			acornHitBox.add(i,new Rectangle(acorns.get(i).getX()+30,acorns.get(i).getY()+10,40,60));
		}

		squirrelHitBox.add(new Rectangle(squirrels.get(0).getX(),squirrels.get(0).getY()+20,100,32));
		squirrelHitBox.add(new Rectangle(squirrels.get(1).getX(),squirrels.get(1).getY()+20,88,32));
		squirrelHitBox.add(new Rectangle(squirrels.get(2).getX(),squirrels.get(2).getY()+20,88,32));


		int first =(int) ( Math.random() * 500 + 800);
		int firstApple =(int) ( Math.random() * 500 + 200);
		int size = (int)(Math.random()*8+20);
		int size2 = (int)(Math.random()*10+8);
		int size3 = (int)(Math.random()*10+20);
		rocks.add(0,first);
		apples.add(0,new Apple(firstApple,250));

		for (int i=1;i<size;i++){
			int rand = (int)(Math.random() * 700)+400;
			rocks.add(i,rocks.get(i-1)+rand);
		}

		for (int i=1;i<size3;i++){
			int rand = (int)(Math.random() * 400)+200;
			apples.add(i,new Apple(apples.get(i-1).getX()+rand,250));
			appleHitBox.add(new Rectangle(-x*4+apples.get(i).getX()+3,305,35,35));
		}

		for (int i=0;i<rocks.size();i++){
			if (-x*4+rocks.get(i)+25 <= 7700){
				rockHitBox.add(new Rectangle(-x*4+rocks.get(i)+20,460,60,60));
				rockHitBox.add(new Rectangle(-x*4+rocks.get(i)+35,410,35,60));
			}
		}

		try {

			guy = ImageIO.read(new File("squirrel.png"));
			back1 = Toolkit.getDefaultToolkit().createImage("back1.png");
			back2 = Toolkit.getDefaultToolkit().createImage("back2.png");
			rock = Toolkit.getDefaultToolkit().createImage("rock.png");
			apple = Toolkit.getDefaultToolkit().createImage("apple.png");
			finish = Toolkit.getDefaultToolkit().createImage("finish.png");
			gameOver = Toolkit.getDefaultToolkit().createImage("GameOver.png");
			start = Toolkit.getDefaultToolkit().createImage("start.png");
			acorn = Toolkit.getDefaultToolkit().createImage("acorn.png");
			rock=rock.getScaledInstance(100, 150, Image.SCALE_DEFAULT);

			guys[0]=guy.getSubimage(25,410,120,80);
			guys[0] = resize(guys[0],90,60);
			guys[1]=guy.getSubimage(155,410,120,80);
			guys[1] = resize(guys[1],90,60);
			guys[2]=guy.getSubimage(290,410,120,80);
			guys[2] = resize(guys[2],90,60);;
			guys[3]=guy.getSubimage(415,410,120,80);
			guys[3] = resize(guys[3],90,60);
			guys[4]=guy.getSubimage(535,410,120,80);
			guys[4] = resize(guys[4],90,60);
			guys[5]=guy.getSubimage(660,410,120,80);
			guys[5] = resize(guys[5],90,60);
			guys[6]=guy.getSubimage(780,410,120,80);
			guys[6] = resize(guys[6],90,60);
			guys[7]=guy.getSubimage(900,410,120,80);
			guys[7] = resize(guys[7],90,60);

			hit[0]=guy.getSubimage(70,280,80,130);
			hit[0] = resize(hit[0],60,90);
			hit[1]=guy.getSubimage(160,280,80,130);
			hit[1] = resize(hit[1],60,90);
			hit[2]=guy.getSubimage(250,280,80,130);
			hit[2] = resize(hit[2],60,90);
			hit[3]=guy.getSubimage(340,280,80,130);
			hit[3] = resize(hit[3],60,90);
			hit[4]=guy.getSubimage(430,280,80,130);
			hit[4] = resize(hit[4],60,90);
			hit[5]=guy.getSubimage(500,280,80,130);
			hit[5] = resize(hit[5],60,90);



		}
		catch (IOException e) {
		}

		frame.addKeyListener(this);
		frame.addMouseListener(this);
		frame.add(this);
		frame.setSize(1000,700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		t=new Thread(this);
		t.start();
	}

	public void run()
	{
		while(true)
		{
			if(gameOn)
			{
				//Math happens here!
				if (x>=2100){
					x=2100;
				}

				//System.out.println(MouseInfo.getPointerInfo().getLocation().getX()+"");

				squirrelHitBox.set(0,new Rectangle(squirrels.get(0).getX(),squirrels.get(0).getY()+20,88,32));
				squirrelHitBox.set(1,new Rectangle(squirrels.get(1).getX(),squirrels.get(1).getY()+20,88,32));
				squirrelHitBox.set(2,new Rectangle(squirrels.get(2).getX(),squirrels.get(2).getY()+20,88,32));

				for (int i=0;i<acorns.size();i++){
					acorns.get(i).subX();
					if (i<acornHitBox.size()){
						acornHitBox.set(i,new Rectangle(acorns.get(i).getX()+30,acorns.get(i).getY()+10,40,60));
					}
				}

				for (int i=0;i<rocks.size()-1;i++){
					if (-x*4+rocks.get(i) <= -x*4+7700){
						rockHitBox.set(i,new Rectangle(-x*4+rocks.get(i)+20,460,60,60));
					}
					if (i+1<=rockHitBox.size()-1  &&  (-x*4+rocks.get(i+1) <= -x*4+7700)){
						rockHitBox.set(i+1,new Rectangle(-x*4+rocks.get(i+1)+35,410,35,60));
					}
				}

				for (int i=0;i<appleHitBox.size();i++){
					appleHitBox.set(i,new Rectangle(-x*4+apples.get(i).getX()+3,305,35,35));
				}

				for (int y=0;y<squirrelHitBox.size();y++){
					for (int x=0;x<rockHitBox.size();x++){
						if(squirrelHitBox.get(y).intersects(rockHitBox.get(x))){
							hits=true;
						}
					}
				}

				for (int y=0;y<squirrelHitBox.size();y++){
					for (int x=0;x<acornHitBox.size();x++){
						if(squirrelHitBox.get(y).intersects(acornHitBox.get(x))){
							score+=5;
							try{
								playSound2();
							}catch(Exception e){}
							acorns.get(x).changeX();
						}
					}
				}

				for (int y=0;y<squirrelHitBox.size();y++){
					for (int i=0;i<appleHitBox.size();i++){
						if(squirrelHitBox.get(y).intersects(appleHitBox.get(i))){
							if (apples.get(i).checkHit1()==false && y==2){
								apples.get(i).setHit1();
							}
							else if (apples.get(i).checkHit2()==false && apples.get(i).checkHit1()==true && y==1){
								apples.get(i).setHit2();
							}
							else if (apples.get(i).checkHit3()==false && apples.get(i).checkHit2()==true && apples.get(i).checkHit1()==true && y==0){
								apples.get(i).setHit3();
								score++;
								//System.out.println(score);
								try{
								playSound();
								}catch(Exception e){
									e.printStackTrace();
								}
								apples.get(i).setX();
							}
						}
					}
				}

				move();

				repaint();
			}
			else if (gameOn=false){
				repaint();
			}
			if(restart)
			{
				restart=false;
				gameOn=true;
			}
			try
			{
				t.sleep(9);
			}catch(InterruptedException e)
			{
			}
		}

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		//all painting happens here!


		if (gameOn==false){
			g2d.drawImage(start,0,0,null);
			g2d.setColor(new Color(106, 145, 242));
			g2d.fillRect(0,0,1000,1000);
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Helvetica", Font.BOLD, 30));
			g2d.drawString("Press anywhere to",355,200);
			g2d.setFont(new Font("Helvetica", Font.BOLD, 100));
			g2d.drawString("START",325,330);
			g2d.setFont(new Font("Helvetica", Font.BOLD, 18));
			g2d.drawString("Press I for instructions",395,600);
			if (instructions){
				g2d.setColor(Color.YELLOW);
				g2d.fillRect(0,0,1000,1000);
			}

		}

		g2d.drawImage(back1,-x*4, 0, null);
		g2d.drawImage(back2,-x*4+1000, 0, null);
		g2d.drawImage(back1,-x*4+2000, 0, null);
		g2d.drawImage(back2,-x*4+3000, 0, null);
		g2d.drawImage(back1,-x*4+4000, 0, null);
		g2d.drawImage(back2,-x*4+5000, 0, null);
		g2d.drawImage(back1,-x*4+6000, 0, null);
		g2d.drawImage(back2,-x*4+7000, 0, null);
		g2d.drawImage(back1,-x*4+8000, 0, null);
		g2d.drawImage(back2,-x*4+9000, 0, null);

		if (gameOn){
			g2d.setFont(new Font("Helvetica", Font.BOLD, 30));
			g2d.drawString("Score: "+score,420,100);
		}

		if (hits==false){
			if (gameOn==true){

				for (int i=0;i<squirrels.size();i++){
					g2d.drawImage(guys[squirrels.get(i).getImgCount()],squirrels.get(i).getX(),squirrels.get(i).getY(),null);
				}
			}
		}

		for (int i=0;i<rocks.size();i++){
			if (-x*4+rocks.get(i) <= -x*4+7700){

				g2d.drawImage(rock,-x*4+rocks.get(i),400,null);
			}
		}

		for (int i=0;i<appleHitBox.size();i++){
			g2d.drawImage(apple,-x*4+apples.get(i).getX(),250,null);
		}

		for (int i=0;i<acorns.size();i++){

			//g2d.fillRect(acorns.get(0).getX()+30,acorns.get(0).getY()+10,40,60);
			//if (acorns.get(i).getIsOn()){
				g2d.drawImage(acorn,acorns.get(i).getX(),acorns.get(i).getY(),null);
			//}

		}


		if (hits==true){
			g2d.drawImage(gameOver,0,0,null);
			g2d.drawImage(hit[imgCount],375,500,null);
			g2d.drawImage(hit[imgCount],475,500,null);
			g2d.drawImage(hit[imgCount],575,500,null);
			g2d.setFont(new Font("Helvetica", Font.BOLD, 18));
			g2d.drawString("Press R to restart",430,120);
			g2d.drawString("Press I for instructions",410,150);
		}

		if ((instructions)){
			g2d.setColor(new Color(106, 145, 242));
			g2d.fillRect(0,0,1000,1000);
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Helvetica", Font.BOLD, 70));
			g2d.drawString("Instructions",295,100);
			g2d.setFont(new Font("Helvetica", Font.ITALIC, 36));
			//g2d.drawString("Welcome to SquirrelRun",270,200);
			g2d.setFont(new Font("Helvetica", Font.BOLD, 18));
			g2d.drawString("The objective of the game is to help three baby squirrels get across the dangerous meadow!",100,230);
			g2d.drawString("Make sure to avoid the rocks and hit the apples three times to eat them for points",140,500);
			g2d.setFont(new Font("Helvetica", Font.BOLD, 20));
			g2d.drawString("Make sure to get the acorns for 5 points each",270,290);
			g2d.setFont(new Font("Helvetica", Font.ITALIC, 16));
			g2d.drawString("Press E to help the first squirrel jump",350,350);
			g2d.drawString("Press W to help the second squirrel jump",335,400);
			g2d.drawString("Press Q to help the third squirrel jump",350,450);
			g2d.setFont(new Font("Helvetica", Font.BOLD, 20));
			g2d.drawString("Press R to start",410,600);
		}

		if (end){
			g2d.setColor(new Color(106, 145, 242));
			g2d.fillRect(0,0,1000,700);
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Helvetica", Font.BOLD, 100));
			g2d.drawString("FINISH!",310,300);
			g2d.setFont(new Font("Helvetica", Font.BOLD, 60));
			g2d.drawString("Score: "+score,370,500);
			g2d.setFont(new Font("Helvetica", Font.BOLD, 18));
			g2d.drawString("Press R to restart",415,120);
		}

	}
	public void keyPressed(KeyEvent key)
	{
		//System.out.println(key.getKeyCode());
		if(key.getKeyCode()==68)
		{
			right=true;

		}
		if(key.getKeyCode()== 87)
		{
			jump1=true;

		}
		if(key.getKeyCode()== 69)
		{
			jump2=true;

		}
		if(key.getKeyCode()== 81)
		{
			jump3=true;

		}
		if(key.getKeyCode()== 73)
		{
			if (gameOn==false||end==true||hits==true){
				instructions=true;
				repaint();
			}

		}
		if(key.getKeyCode()==82){

			if (hits||gameOn==false){
				acorns.add(0,new acornn(1000,140));
				acorns.add(1,new acornn(3000,140));
				acorns.add(2,new acornn(6000,140));
				acorns.add(3,new acornn(9000,140));
				acorns.add(4,new acornn(12000,140));
				acorns.add(5,new acornn(15000,140));
				acorns.add(6,new acornn(18000,140));
				restart=true;
				x=0;
				imgCount=0;
				hits=false;
				y=450;
				score=0;
				end=false;
				instructions=false;
				jump1=false;
				jump2=false;
				jump3=false;
				counter=0;
				squirrels.set(0,new Squirrel(5,450));
				squirrels.set(1,new Squirrel(105,450));
				squirrels.set(2,new Squirrel(205,450));
				for (int i=0;i<apples.size();i++){
					apples.get(i).resetHit1();
					apples.get(i).resetHit2();
					apples.get(i).resetHit3();
					apples.get(i).resetX();
				}
				for (int i=0;i<acorns.size();i++){
					acorns.get(i).setX();
					//acorns.get(i).setIsOn(true);
				}
			}
		}
		key=null;
	}
	public void keyReleased(KeyEvent key)
	{
	}
	public void keyTyped(KeyEvent key)
	{
	}
	public void mouseClicked(MouseEvent e)
	{
		if (gameOn==false){
			clicked=true;
			gameOn=true;
		}
		/*Rectangle mouseClick = new Rectangle(e.getX(),e.getY()+800,75,75);
		System.out.println("MOUSE "+mouseClick);
		for (int i=0;i<acornHitBox.size();i++){
			System.out.println("ACORN "+acornHitBox.get(i));
			if (mouseClick.intersects(acornHitBox.get(i))){
				acorns.get(i).changeX();
				//acorns.get(i).setIsOn(false);
				try{
					playSound2();
				}catch(Exception ex){}
			}
		}*/
	}
	public void mousePressed(MouseEvent e)
	{
		if (gameOn==false){
			clicked=true;
			gameOn=true;
		}
		/*Rectangle mouseClick = new Rectangle(e.getX()-50,e.getY()+50,200,150);
		System.out.println("MOUSE "+mouseClick);
		for (int i=0;i<acornHitBox.size();i++){
			System.out.println("ACORN "+acornHitBox.get(i));
			if (mouseClick.intersects(acornHitBox.get(i))){
				acorns.get(i).changeX();
				acorns.get(i).setIsOn(false);
				try{
					playSound2();
				}catch(Exception ex){}
				System.out.println("HITTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
			}
		}*/
	}
	public void mouseReleased(MouseEvent e)
	{
	}
	public void mouseEntered(MouseEvent e)
	{
	}
	public void mouseExited(MouseEvent e)
	{
	}
	public BufferedImage resize(BufferedImage img, int newW, int newH) {
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}
	public void playSound()
	throws Exception
	{
	    String gongFile = "D:/Data Structures/Game/chime_up.wav";
	    InputStream in = new FileInputStream(gongFile);
	    AudioStream audioStream = new AudioStream(in);
	    AudioPlayer.player.start(audioStream);
	}
	public void playSound2()
	throws Exception
	{
	    String gongFile = "D:/Data Structures/Game/floop.wav";
	    InputStream in = new FileInputStream(gongFile);
	    AudioStream audioStream = new AudioStream(in);
	    AudioPlayer.player.start(audioStream);
	}
	public void move(){
		counter++;
		if (hits==true){
			if (counter%5==0){
				imgCount++;
				if (imgCount>=6){
					imgCount=0;
				}
			}
		}
		if (hits==false){

			squirrels.get(0).addCounter();
			if (squirrels.get(0).getCounter()%5==0){
				squirrels.get(0).addX();
				x+=1;
				if (x>=1950){
					gameOn=false;
					end=true;
					repaint();
					squirrels.get(0).subImgCount();
				}
				squirrels.get(0).addImgCount();
				if(squirrels.get(0).getImgCount()>7){
					squirrels.get(0).setImgCount(0);
				}
			}
			repaint();

			squirrels.get(1).addCounter();
			if (squirrels.get(1).getCounter()%5==0){
				squirrels.get(1).addX();
				x+=1;
				if (x>=2100){
					x=2100;
					squirrels.get(1).subImgCount();
				}
				squirrels.get(1).addImgCount();
				if(squirrels.get(1).getImgCount()>7){
					squirrels.get(1).setImgCount(0);
				}
			}

			squirrels.get(2).addCounter();
			if (squirrels.get(2).getCounter()%5==0){
				squirrels.get(2).addX();
				x+=1;
				if (x>=2100){
					x=2100;
					squirrels.get(2).subImgCount();
				}
				squirrels.get(2).addImgCount();
				if(squirrels.get(2).getImgCount()>7){
					squirrels.get(2).setImgCount(0);
				}
			}
		}

		if (jump1==true){
			squirrels.get(1).addJCounter();
			if (squirrels.get(1).getJCounter()<=70){
				squirrels.get(1).subY();
			}
			if (squirrels.get(1).getJCounter()>=90){
				squirrels.get(1).addY();
			}
			if (squirrels.get(1).getJCounter()>160){
				jump1=false;
				squirrels.get(1).setY();
				squirrels.get(1).setJCounter();
			}
		}


		if (jump3==true){
			squirrels.get(0).addJCounter();
			if (squirrels.get(0).getJCounter()<=70){
				squirrels.get(0).subY();
			}
			if (squirrels.get(0).getJCounter()>=90){
				squirrels.get(0).addY();
			}
			if (squirrels.get(0).getJCounter()>160){
				jump3=false;
				squirrels.get(0).setY();
				squirrels.get(0).setJCounter();
			}
		}

		if (jump2==true){

			squirrels.get(2).addJCounter();
			if (squirrels.get(2).getJCounter()<=70){
				squirrels.get(2).subY();
			}
			if (squirrels.get(2).getJCounter()>=90){
				squirrels.get(2).addY();
			}
			if (squirrels.get(2).getJCounter()>160){
				jump2=false;
				squirrels.get(2).setJCounter();
				squirrels.get(2).setY();
			}
		}
	}
	public static void main(String args[])
	{
		GameTemplate app=new GameTemplate();
	}
}