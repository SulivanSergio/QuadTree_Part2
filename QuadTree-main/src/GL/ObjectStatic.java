package GL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class ObjectStatic {
	
	Rect rect;
	Random random;
	Color color = Color.ORANGE;
	
	//construtor
	public ObjectStatic() {
		random = new Random();
		rect = new Rect(random.nextFloat(Form.instance.windowSize.x,Form.instance.windowSize.width - 10),
				        random.nextFloat(Form.instance.windowSize.y,Form.instance.windowSize.height - 10),
				        20,20,new Point(0,0),color);
		
	}
	//desenhava o objeto, mas agora tem um buffer
	public void Draw(Graphics g)
	{
		//g.setColor(Color.ORANGE);
		//g.fillRect((int)rect.x,(int)rect.y,(int)rect.width,(int)rect.height);
	}
}
