import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class GameEngine extends JFrame {
	
		boolean isRunning = true;
		int fps = 30;
		int fps2 = 10;
		int windowWidth = 500;
		int windowHeight = 500;
		
		BufferedImage backBuffer;
		Insets insets;
		InputHandler input;
		Graphics g;
		Graphics bbg;
		
		int xRigidBody = 0;	
		int yRigidBody =50;
		int xBlockRigidBody = 250;
		int yBlockRigidBody = 250;			
		
		public static void main(String[] args) {
			GameEngine game = new GameEngine();
			game.run();
			System.exit(0);
		}
		public void run(){
			
			intialize();
			
			while(isRunning){
			
				long time = System.currentTimeMillis();
				
				
				if(((xRigidBody >= yBlockRigidBody-20 && xRigidBody <= yBlockRigidBody+50) && 
				     (yRigidBody >= xBlockRigidBody-20 && yRigidBody <= xBlockRigidBody+50)))
				{
					
					System.out.println("made it inside if");
					System.out.println(xRigidBody);
					System.out.println(yBlockRigidBody);
					System.out.println(yBlockRigidBody+50);
					System.out.println(yRigidBody);
					System.out.println(xBlockRigidBody);
					System.out.println(xBlockRigidBody+50);
					drawBackwards();
				}
				update();
				draw();
				
				//delaying for each frame - time it took for one frame
				time = (1000/fps) - (System.currentTimeMillis()-time);
				
				if (time > 0)
				{
					try{
						Thread.sleep(time);
					}
					catch(Exception e){
						}
				}
		  }
		  setVisible(false);
		}
		public void intialize(){
			
			
			setTitle("Game Tutorial");
			setSize(windowWidth, windowHeight);
			setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
			
			insets = getInsets();
			setSize(insets.left+windowWidth+insets.right,insets.top+windowHeight + insets.bottom);
			
		    backBuffer = new BufferedImage(windowWidth,windowHeight,BufferedImage.TYPE_INT_RGB);
		    input = new InputHandler(this);
			
		}
		public void update()
		{
			int timer = 5;
			if(input.isKeyDown(KeyEvent.VK_RIGHT))
			{
				xRigidBody+=5;
			}
			if(input.isKeyDown(KeyEvent.VK_LEFT))
			{
				xRigidBody-=5;
			}
			if(input.isKeyDown(KeyEvent.VK_DOWN))
			{
				yRigidBody+=5;
			}
			if(input.isKeyDown(KeyEvent.VK_UP))
			{
				yRigidBody-=5;
			}	
		}
		public void draw()
		{
			
			g = getGraphics();
			bbg = backBuffer.getGraphics();
			bbg.setColor(Color.BLACK);
			bbg.fillRect(0, 0, windowWidth, windowHeight);

			bbg.setColor(Color.RED);
			bbg.drawOval(xRigidBody,yRigidBody,20,20);
	
					
			bbg.setColor(Color.WHITE);
			bbg.fillRect(xBlockRigidBody, yBlockRigidBody, 50, 50);
			
			g.drawImage(backBuffer, insets.left, insets.top, this);
		}
		
		public void drawCollision()
		{
			Graphics c = getGraphics();
			Graphics cc = backBuffer.getGraphics();
			
			cc.setColor(Color.BLACK);
			cc.fillRect(0, 0, windowWidth, windowHeight);

			cc.setColor(Color.RED);
			cc.drawOval(xRigidBody,yRigidBody,20,20);
			
			cc.setColor(Color.WHITE);
			cc.fillRect(xBlockRigidBody, yBlockRigidBody, 50, 50);
			
			c.drawImage(backBuffer, insets.left, insets.top, this);
		}
		public void drawBackwards()
		{
			int i = 0;
			while(i < 25)
			{
				
				long time = System.currentTimeMillis();
				
				if(yRigidBody >= yBlockRigidBody && yRigidBody < yBlockRigidBody+50 && xRigidBody >= xBlockRigidBody){
					xRigidBody+=5;
				}
				else if(yRigidBody >= yBlockRigidBody-25 && xRigidBody < xBlockRigidBody){
					xRigidBody-=5;
				}
				else if(xRigidBody >= xBlockRigidBody && xRigidBody <= xBlockRigidBody+50 && yRigidBody <= yBlockRigidBody)
				{
					yRigidBody-=5;
				}
				else if(xRigidBody > xBlockRigidBody && yRigidBody > yBlockRigidBody)
				{
					yRigidBody+=5;
				}
				i+=5;
	            
	            drawCollision();
				time = (1000/fps2) - (System.currentTimeMillis()-time);
				
				if (time > 0)
				{
					try
					{
						Thread.sleep(time);
					}
					catch(Exception e)
					{
						
						
					}
			   }
			}
				
		}
	}

