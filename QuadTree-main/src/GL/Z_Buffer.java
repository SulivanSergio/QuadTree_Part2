package GL;

import java.awt.Color;

public class Z_Buffer {
	
	public Rect rect;
	public int depth = 0;
	public Color color;
	
	//construtor do zBuffer
	public Z_Buffer(Rect rect, int depth,Color color) {
		this.rect = rect;
		this.depth = depth;
		this.color = color;
	}
	
}
