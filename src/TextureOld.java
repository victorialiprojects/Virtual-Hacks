import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class TextureOld {
	public static TextureOld wood = new TextureOld("res/IMG_3597.jpg", 64);
	public static TextureOld brick = new TextureOld("res/redbrick.jpg", 64);
	public static TextureOld bluestone = new TextureOld("res/bluestone.jpg", 64);
	public static TextureOld stone = new TextureOld("res/greystone.jpg", 64);
	public int[] pixels;
	private String loc;
	public final int SIZE;
	public ArrayList<TextureOld> textures;
	
	public TextureOld(String location, int size) {
		loc = location;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
		textures = new ArrayList<TextureOld>();
		textures.add(TextureOld.wood);
		textures.add(TextureOld.brick);
		textures.add(TextureOld.bluestone);
		textures.add(TextureOld.stone);
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(new File(loc));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
