package GL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class ObjectDynamics {
	
	Rect rect;
	Random random;
	Point direction = new Point(1,1);
	float speed = 50;
	ObjectStatic[] objectStatic;
	Player player;
	float timerColor = 0;
	float timerColorMax = 0.1f;
	Color color = Color.blue;
	
	
	public ObjectDynamics(ObjectStatic[] objectStatic,Player player) {
		random = new Random();
		rect = new Rect(random.nextFloat(Form.instance.windowSize.x,Form.instance.windowSize.width - 10),
				        random.nextFloat(Form.instance.windowSize.y,Form.instance.windowSize.height - 10),
				        10,10,direction,color);
		
		this.objectStatic = objectStatic;
		this.player = player;
		
		
		
	}
	//atualiza o desenho
	public void Draw(Graphics g,Color colorAux)
	{
		
		//g.setColor(color);
		//g.fillRect((int)rect.x,(int)rect.y,(int)rect.width,(int)rect.height);
	}
	
	//update
	public void Update(float gameTime)
	{
		Move(gameTime);
		
		if(rect.color == Color.red)
		{
			
			if(timerColor > timerColorMax)
			{
				color = Color.red;
				timerColor = 0;
			}
		}
		
		timerColor += gameTime;
		
		
		if(timerColor > timerColorMax)
		{
			color = Color.blue;
			rect.color = Color.blue;
		}
		
	}
	
	//atualiza a movimentação dos objetos
	private void Move(float gameTime)
	{
		rect.x += direction.x * speed * gameTime ;
		rect.y += direction.y * speed * gameTime ;
		
		if(rect.BorderCollisionX(rect))
		{
			direction.x *= -1;
			rect.x +=  0.5f * direction.x;
		}
		if(rect.BorderCollisionY(rect))
		{
			direction.y *= -1;
			rect.y += 0.5f * direction.y;
		}
		
		
	}
	
	//testa colisão com objetos estaticos
	public void CollisionObjStatic() {
		
		for(int i = 0; i< objectStatic.length; i++)
		{
			if(rect.BoundingCollision(rect,objectStatic[i].rect))
			{
				Draw(Form.instance.window.getGraphics(), Color.red);
				direction.x *= -1;
				direction.y *= -1;
				rect.x +=  0.5f * direction.x;
				rect.y += 0.5f * direction.y;
			}
		}
		
	}
	
	//testa colisão com player
	public void CollisionPlayer() {
		
		
		if(rect.BoundingCollision(rect,player.rect))
		{
			Draw(Form.instance.window.getGraphics(), Color.red);
			direction.x *= -1;
			direction.y *= -1;
			rect.x +=  0.5f * direction.x;
			rect.y += 0.5f * direction.y;
		}
		
		
	}
	

}
