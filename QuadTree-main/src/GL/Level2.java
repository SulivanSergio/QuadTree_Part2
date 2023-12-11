package GL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Level2 {

	

	
	ObjectDynamics[] objectDynamics = new ObjectDynamics[600];
	ObjectStatic[] objectStatic = new ObjectStatic[100];
	Player player;
	Game1 game1;
	QuadTree quadTree;
	static ArrayList<Rect> rectAux;
	Z_Buffer[] zBuffer;
	
	public static Level2 instance;
	
	//construtor
	public Level2(Game1 game1) {
		
		instance = this;
		
		this.game1 = game1;
		
		for(int i = 0; i< objectStatic.length; i++)
		{
			objectStatic[i] = new ObjectStatic();
		}
		player = new Player(objectStatic);
		
		for(int i = 0; i< objectDynamics.length; i++)
		{
			objectDynamics[i] = new ObjectDynamics(objectStatic,player);
		}
		
		//criação da quadTree
		quadTree = new QuadTree(new Rect(Form.instance.windowSize.x,Form.instance.windowSize.y,Form.instance.windowSize.width,Form.instance.windowSize.height,new Point(0,0),Color.black));
		
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
	
	//update da movimentação, colisão e criação da quadTree
	public void Update(float gameTime)
	{
		
		//criação da arvore
		quadTree.Insert(player.rect);
		for(int i = 0; i< objectStatic.length; i++)
		{
			quadTree.Insert(objectStatic[i].rect);
		}
		for(int i = 0; i< objectDynamics.length; i++)
		{
			quadTree.Insert(objectDynamics[i].rect);
		}
		for(int i = 0; i< objectDynamics.length; i++)
		{
			objectDynamics[i].Update(gameTime);
		}
		
		long inicio = System.currentTimeMillis();
		float speedUP = 0;
		
		CODynamicsQuadTree();
		
		
		player.Update(gameTime);
		
		
		long fim = System.currentTimeMillis();
		speedUP = 1.0f/ (float)(fim - inicio);
		
		System.out.println("SpeedUP: " + speedUP);
		
		
		
		//desenhar do buffer
		DrawBuffer();
		
		//limpeza da quadtree
		quadTree.ClearSubdivide(quadTree);
		
		if(Form.instance.keyboard.Return_Esc())
		{
			Form.instance.ClearGrapphics(Form.instance.window.getGraphics(), new Color(100,100,100));
			game1.scene = SCENE_MANAGER.MENU;
		}
		
	}
	
	//metodo que atualiza o desenho
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
		
		
	}
	
	
	
	//colisão dos objetos dinamicos da quadTree
	private void CODynamicsQuadTree() 
	{
		
		quadTree.Search(quadTree);
			
	}
	
	public void Colision() {
		
		for(int i = 0; i < rectAux.size(); i++)
		{
			for(int j = 0; j < rectAux.size(); j++)
			{
				
				if(rectAux.get(i).BoundingCollision(rectAux.get(i), rectAux.get(j)) && rectAux.get(i) != rectAux.get(j))
				{
					
					rectAux.get(i).color = Color.red;
					rectAux.get(i).direction.x *= -1;
					rectAux.get(i).direction.y *= -1;
					rectAux.get(i).x +=  0.5f * rectAux.get(i).direction.x;
					rectAux.get(i).y += 0.5f * rectAux.get(i).direction.y;
					
					rectAux.get(j).color = Color.red;
					rectAux.get(j).direction.x *= -1;
					rectAux.get(j).direction.y *= -1;
					rectAux.get(j).x +=  0.5f * rectAux.get(j).direction.x;
					rectAux.get(j).y += 0.5f * rectAux.get(j).direction.y;
					
					
					
				}
			
			}
		}
	}

	
	//retorna a lista de objetos que esta no quadrado da folha da arvore
	public static void CollectionObjects(ArrayList<Rect> rects) {

		rectAux = rects;
		
	}
	
	//desenha todos do buffer
	private void DrawBuffer() {
		
		Graphics g = Form.instance.window.getGraphics();
		
		for(int i = objectStatic.length; i < objectStatic.length + objectDynamics.length ; i++) {
			zBuffer[i].color = objectDynamics[i-objectStatic.length].color;
		}
		
		for(int i = 0; i < 4; i++) {
			
			for(int j = 0; j < objectStatic.length + objectDynamics.length + 1; j++)
			{
				
				if(i == zBuffer[j].depth)
				{
					
					g.setColor(zBuffer[j].color);
					g.fillRect((int)zBuffer[j].rect.x,(int)zBuffer[j].rect.y,(int)zBuffer[j].rect.width,(int)zBuffer[j].rect.height);
				}
				
			}
			if(i == 3)
			{
				quadTree.Print(quadTree,g);
			}
					
		}
		
	}



}
