package main;
import java.applet.Applet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.net.URL;

import javax.swing.JFrame;

/** This is the set up for a default game. Change fields and values as needed */
public class Game extends Canvas implements Runnable{
	/** The Height of our Screen */
	public static final int HEIGHT = 900;
	/** The Width of our Screen */
	public static final int WIDTH = HEIGHT * 12/9;
	/** Whether this Game is currently running or not */
	private boolean running;
	/** Sound Effects Player */
	private SFXPlayer SFX;
	/** Music Player */
	private MusicPlayer MP;
	/** Container for all our threads */
	private ThreadPool pool;
	/** Manager for retrieving and displaying images */
	public ImageManager IM;
	/** Graphic Manager to assist with drawing selection boxes */
	public static GraphicManager GM = new GraphicManager();

	/** Controls the State of the game and which fields should be active or rendering */
	public enum STATE {
		Menu,
		Game
	}
	/** Current State of the Game */
	public STATE gameState = STATE.Game;
	
	/** Creates a new Game with a blank gray screen */
	public Game() {
		
		IM = new ImageManager();
		this.addKeyListener(new KeyInput(this));
		new Window(WIDTH, HEIGHT, "Empty Game", this);
		
	}
	
	/** UPDATE METHOD! This is where all in-game values are updated to support the next frame being loaded*/
	public void tick() {
		// for example:
		// box.x += 1;
		// box.y += 1;
		// this will make your box move diagonally downward every frame of the game
	}
	
	/** GRAPHIC METHOD! This is where all in-game graphics are drawn */
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		if (gameState == STATE.Game) {
			
			//render main game screen

			// draws a rectangle at Pos(200,200) with height,width (100,100)
			// g.drawRect(200, 200, 100, 100);
			
		} else if (gameState == STATE.Menu) {
			
			//render Menu screen
			
		}
		

	}
	
	
	/** The continuous Game loop. Controls the tick to render ratio */
	public void run() {
		running = true;
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		// int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			renderGame();
			// framerate information
			/* frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}*/
		}
		stop();
	}
	/** Creates the draw-able graphics for this game and passes that to the main
	 *  graphics rendering function named render()
	 */
	public void renderGame() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		render(g);
		
		g.dispose();
		bs.show();
	}
	/** Starts this game with multi-threading capabilities
	 *  The ThreadPool is responsible for running and stopping tasks in its queue
	 *  To add a new thread use the pool.runTask() method
	 */
	public synchronized void start() {
		pool = new ThreadPool(2); //adjust this number based on the number of threads
		pool.runTask(this);
//		MP = new MusicPlayer("FireEmblemTheme", "FireEmblemHomeTune");
//		pool.runTask(MP);
		SFX = new SFXPlayer(); //provide the filenames of sound effects in res folder
		pool.runTask(SFX);
		running = true;
	}
	/** Halts all threads currently running */
	public synchronized void stop() {
		try {
			pool.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Constrains a variable between a minimum and maximum value
	 *  var = clamp(var, min, max) would ensure var never falls below min or above max
	 * @param var
	 * @param min
	 * @param max
	 * @return
	 */
	public static int clamp(int var, int min, int max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}
	/** The window that will hold this Game */
	public class Window extends Canvas {
		public JFrame frame;
	
		private static final long serialVersionUID = 21L;

		public Window(int width, int height, String title, Game game) {
			frame = new JFrame(title);
		
			frame.setPreferredSize(new Dimension(width, height));
			frame.setMaximumSize(new Dimension(width, height));
			frame.setMinimumSize(new Dimension(width, height));
		
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.add(game);
			frame.setVisible(true);
			game.start();
		
		}
	}
	/** Responsible for processing and delivering an Image via path */
	public class ImageManager extends Applet {
	
		private static final long serialVersionUID = 1L;
	
		public Image getImage(String path) {
			Image tempImage = null;
			try {
				URL imageURL = ImageManager.class.getResource(path);
				tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
			} catch(Exception e) {
				System.out.println("Getting the image failed");
			}
			return tempImage;
		}
	}
}