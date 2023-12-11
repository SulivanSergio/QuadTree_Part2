package GL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Player {
	
	Rect rect;
	Random random;
	Point direction = new Point(0,0);
	float speed = 70;
	ObjectStatic[] objectStatic;
	Color color = Color.pink;
	
	
	public Player(ObjectStatic[] objectStatic) {
		random = new Random();
		rect = new Rect(random.nextFloat(Form.instance.windowSize.x,Form.instance.windowSize.width - 10),
				        random.nextFloat(Form.instance.windowSize.y,Form.instance.windowSize.height - 10),
				        20,20,direction,color);
		
		this.objectStatic = objectStatic;
		
	}
	
	public void Draw(Graphics g)
	{
		//g.setColor(Color.pink);
		//g.fillRect((int)rect.x,(int)rect.y,(int)rect.width,(int)rect.height);
	}
	
	public void Update(float gameTime)
	{
		Move(gameTime);
	}
	
	//move as particulas dinamicas
	private void Move(float gameTime)
	{
		direction.x = 0;
		direction.y = 0;
		
		if(rect.BorderPassNegativeX(rect))
		{
			if(Form.instance.keyboard.Return_A())
				direction.x = -1;
		}
		if(rect.BorderPassPositiveX(rect))
		{
			if(Form.instance.keyboard.Return_D())
				direction.x = 1;
		}
		
		if(rect.BorderPassNegativeY(rect))
		{
			if(Form.instance.keyboard.Return_W())
				direction.y = -1;
		}
		if(rect.BorderPassPositiveY(rect))
		{
			if(Form.instance.keyboard.Return_S())
				direction.y = 1;
		}
		
		rect.x += direction.x * speed * gameTime ;
		rect.y += direction.y *speed * gameTime ;
	}
	
	//testa colisao com os objetos estaticos
	public void CollisionObjStatic() {
		
		for(int i = 0; i< objectStatic.length; i++)
		{
			if(rect.BoundingCollision(rect,objectStatic[i].rect))
			{
				
				direction.x *= -1;
				direction.y *= -1;
				rect.x += 0.5f * direction.x ;
				rect.y += 0.5f * direction.y ;
			}
		}
		
	}
	
}
