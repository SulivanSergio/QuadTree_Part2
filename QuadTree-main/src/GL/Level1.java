package GL;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Level1 {
	

	
	ObjectDynamics[] objectDynamics = new ObjectDynamics[600];
	ObjectStatic[] objectStatic = new ObjectStatic[100];
	Player player;
	Game1 game1;
	Z_Buffer[] zBuffer;
	
	
	//construtor
	public Level1(Game1 game1) {	
		
		this.game1 = game1;
		
		//criação das particulas e player
		for(int i = 0; i< objectStatic.length; i++)
		{
			objectStatic[i] = new ObjectStatic();
		}
		player = new Player(objectStatic);
		
		for(int i = 0; i< objectDynamics.length; i++)
		{
			objectDynamics[i] = new ObjectDynamics(objectStatic,player);
		}
		
		
		//criação do buffer
		zBuffer = new Z_Buffer[objectStatic.length + objectDynamics.length + 1];
		for(int i = 0; i < objectStatic.length; i++) {
			zBuffer[i] = new Z_Buffer(objectStatic[i].rect,0,objectStatic[i].color);
		}
		
		for(int i = objectStatic.length; i < objectStatic.length + objectDynamics.length ; i++) {
			zBuffer[i] = new Z_Buffer(objectDynamics[i-objectStatic.length].rect,1,objectDynamics[i-objectStatic.length].color);
		}
		
		for(int i = objectStatic.length + objectDynamics.length; i < objectStatic.length + objectDynamics.length + 1 ; i++) {
			zBuffer[i] = new Z_Buffer(player.rect,2,player.color);
		}
		
		
		
		
	}
	//update da movimentação
	public void Update(float gameTime)
	{
		long inicio = System.currentTimeMillis();
		float speedUP = 0;
		
		//atualização da colisão
		for(int i = 0; i< objectDynamics.length; i++)
		{
			objectDynamics[i].Update(gameTime);
			objectDynamics[i].CollisionObjStatic();
			objectDynamics[i].CollisionPlayer();
		}
		
		CODinamics();
		
		player.Update(gameTime);
		player.CollisionObjStatic();
		
		
		long fim = System.currentTimeMillis();
		speedUP = 1.0f/ (float)(fim - inicio);
		System.out.println("SpeedUP: " + speedUP);
		
		
		//comando para retornar para o menu
		if(Form.instance.keyboard.Return_Esc())
		{
			Form.instance.ClearGrapphics(Form.instance.window.getGraphics(), new Color(100,100,100));
			game1.scene = SCENE_MANAGER.MENU;
			
		}
		
		
		
	}
	//função que "desenhava", pois agora tem o z-buffer
	public void Draw()
	{
		
		for(int i = 0; i< objectDynamics.length; i++)
		{
			objectDynamics[i].Draw(Form.instance.window.getGraphics(), Color.blue);
		}
		
		for(int i = 0; i< objectStatic.length; i++)
		{
			objectStatic[i].Draw(Form.instance.window.getGraphics());	
		}
		player.Draw(Form.instance.window.getGraphics());
		
		
		DrawBuffer();
		
		
		
	}
	//colisao dos objetos dinamicos
	private void CODinamics() 
	{
		for(int i = 0; i < objectDynamics.length; i++)
		{
			for(int j = 0; j < objectDynamics.length; j++)
			{
				if(objectDynamics[i].rect.BoundingCollision(objectDynamics[i].rect, objectDynamics[j].rect) && i != j)
				{
					objectDynamics[i].rect.color = Color.red;
					objectDynamics[i].direction.x *= -1;
					objectDynamics[i].direction.y *= -1;
					objectDynamics[i].rect.x +=  0.5f * objectDynamics[i].direction.x;
					objectDynamics[i].rect.y += 0.5f * objectDynamics[i].direction.y;
					
					objectDynamics[j].rect.color = Color.red;
					objectDynamics[j].direction.x *= -1;
					objectDynamics[j].direction.y *= -1;
					objectDynamics[j].rect.x +=  0.5f * objectDynamics[j].direction.x;
					objectDynamics[j].rect.y += 0.5f * objectDynamics[j].direction.y;
					
					
					
				}
			}
		}
	}
	//desenhar do z-buffer
	private void DrawBuffer() {
		
		Graphics g = Form.instance.window.getGraphics();
		
		for(int i = objectStatic.length; i < objectStatic.length + objectDynamics.length ; i++) {
			zBuffer[i].color = objectDynamics[i-objectStatic.length].color;
		}
		
		for(int i = 0; i < 3; i++) {
			
			for(int j = 0; j < objectStatic.length + objectDynamics.length + 1; j++)
			{
				
				if(i == zBuffer[j].depth)
				{
					
					g.setColor(zBuffer[j].color);
					g.fillRect((int)zBuffer[j].rect.x,(int)zBuffer[j].rect.y,(int)zBuffer[j].rect.width,(int)zBuffer[j].rect.height);
				}
			}
					
		}
	}
	
	
	
}
