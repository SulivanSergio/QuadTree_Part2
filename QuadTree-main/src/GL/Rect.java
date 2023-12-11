package GL;

import java.awt.Color;
import java.awt.Point;

//classe para criar os retangulos
public class Rect {
	
	public float x;
	public float y;
	public float height;
	public float width;
	public Point direction;
	public Color color;
	
	//construtor
	public Rect(float x ,float y,float width,float height,Point direction,Color color)
	{
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.direction = direction;
		this.color = color;
	}
	
	//testa colisao entre dois retangulos
	public boolean BoundingCollision(Rect rect1,Rect rect2) 
	{
		if(rect1.x + rect1.width >= rect2.x && rect1.x <= rect2.x + rect2.width &&
		   rect1.y + rect1.height>= rect2.y && rect1.y <= rect2.y + rect2.height) 
		{
			
			return true;
			
		}else
		{
			return false;
		}
	}
	//testa colisão com a borda no eixo X
	public boolean BorderCollisionX(Rect rect1) 
	{
		if(rect1.x <= Form.instance.windowSize.x || rect1.x >= Form.instance.windowSize.width + Form.instance.windowSize.x - rect1.width) 
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	//testa colisão com a borda no eixo Y
	public boolean BorderCollisionY(Rect rect1) 
	{
		if(rect1.y <= Form.instance.windowSize.y || rect1.y >= Form.instance.windowSize.height + Form.instance.windowSize.y - rect1.height) 
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	//testa colisão com a borda no eixo X negativo para o player
	public boolean BorderPassNegativeX(Rect rect1) 
	{
		if(rect1.x >= Form.instance.windowSize.x) 
		{
			return true;
		}else
		{
			return false;
		}
	}
	//testa colisão com a borda no eixo X positivo para o player
	public boolean BorderPassPositiveX(Rect rect1) 
	{
		if( rect1.x <= Form.instance.windowSize.width + Form.instance.windowSize.x - rect1.width) 
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	//testa colisão com a borda no eixo X negativo para o player
	public boolean BorderPassNegativeY(Rect rect1) 
	{
		if(rect1.y >= Form.instance.windowSize.y) 
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	//testa colisão com a borda no eixo X positivo para o player
	public boolean BorderPassPositiveY(Rect rect1) 
	{
		if(rect1.y <= Form.instance.windowSize.height + Form.instance.windowSize.y - rect1.height) 
		{
			return true;
		}else
		{
			return false;
		}
	}
}
