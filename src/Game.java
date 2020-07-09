// imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import javax.swing.JFrame;

// Game class is the main class to run
public class Game extends JFrame implements Runnable {	
	private static final long serialVersionUID = 1L; // useless

	// position of all the walls is based on this. Numbers >0 represent walls, 0 represents blank space.
	public static int[][] map = 
		{
				{1,1,1,1,1,1,1,1,2,2},
				{1,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,0,0,1},
				{1,0,0,0,0,0,0,0,0,1},
				{1,1,1,1,1,1,1,1,1,1},
		};

	public int mapWidth; // looks at the map array and calculates width
	public int mapHeight; // looks at the map array and calculates height
	private Thread thread; // not sure
	private boolean running; // checks if the program is running?
	private BufferedImage image; // the "image" that is the screen
	public int[] pixels; // the pixels of said "image"
	public ArrayList<Texture> textures; // will try to remove this
	public Camera camera; // the players perspective
	public Screen screen; // what the player sees
	private int framex, framey; // size of the screen
	
	public Game() {
		thread = new Thread(this);
		framex = 850;
		framey = 600;
		image = new BufferedImage(framex, framey, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		textures = new ArrayList<Texture>();
		textures.add(Texture.wood);
		textures.add(Texture.brick);
		textures.add(Texture.bluestone);
		textures.add(Texture.stone);
		camera = new Camera(4.5, 4.5, 1, 0, 0, -.66);
		mapHeight = map.length;
		mapWidth = map[0].length;
		screen = new Screen(map, mapWidth, mapHeight, textures, framex, framey);
		addKeyListener(camera);
		setSize(framex, framey);
		setResizable(false);
		setTitle("Cafe Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.green);
		setLocationRelativeTo(null);
		setVisible(true);
		start();
	}
	
	public static void main(String [] args) {
		Game game = new Game();
	}

	
	private synchronized void start() {
		running = true;
		thread.start();
	}
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
		bs.show();
	}
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;//60 times per second
		double delta = 0;
		requestFocus();
		while(running) {
			long now = System.nanoTime();
			delta = delta + ((now-lastTime) / ns);
			lastTime = now;
			while (delta >= 1)//Make sure update is only happening 60 times a second
			{
				//handles all of the logic restricted time
				screen.update(camera, pixels);
				camera.update(map);
				delta--;
			}
			render();//displays to the screen unrestricted time
		}
	}
}
