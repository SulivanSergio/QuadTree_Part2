package GL;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class QuadTree {
	
	
	Rect dimension; 
	boolean divided;
	QuadTree noroeste;
	QuadTree nordeste;
	QuadTree sudoeste;
	QuadTree sudeste;
	
	ArrayList<Rect> objects = new ArrayList<Rect>();
	int capacity = 4;
	
	public QuadTree(Rect dimension)
	{
		divided = false;
		this.dimension = dimension;
		
	}
	
	public void Draw(Graphics g)
	{
		
		g.setColor(Color.black);
		g.drawRect((int)dimension.x,(int)dimension.y,(int)dimension.width,(int)dimension.height);
	}
	
	//subidivide o no da arvore
	public void Subdivide()
	{
		
		if(!divided)
		{
			
			
			Rect no = new Rect(dimension.x, dimension.y,dimension.width/2,dimension.height/2);
			noroeste = new QuadTree(no);
			//noroeste.Draw(Form.instance.window.getGraphics());
			
			Rect ne = new Rect(dimension.x + dimension.width/2, dimension.y,dimension.width/2,dimension.height/2);
			nordeste = new QuadTree(ne);
			//nordeste.Draw(Form.instance.window.getGraphics());
			
			Rect so = new Rect(dimension.x, dimension.y + dimension.height/2,dimension.width/2,dimension.height/2);
			sudoeste = new QuadTree(so);
			//sudoeste.Draw(Form.instance.window.getGraphics());
			
			Rect se = new Rect(dimension.x + dimension.width/2, dimension.y + dimension.height/2,dimension.width/2,dimension.height/2);
			sudeste = new QuadTree(se);
			//sudeste.Draw(Form.instance.window.getGraphics());
			
			this.divided = true;
		}
		
		
	}
	//desenha a arvore toda
	public void Print(QuadTree quad, Graphics g) 
	{
		if(quad != null) {
			if(quad.noroeste != null)
			{
				//System.out.println("Noroeste");
				quad.noroeste.Draw(g);
				quad.noroeste.Print(quad.noroeste,g);
			}
			if(quad.nordeste != null)
			{
				//System.out.println("Nordeste");
				quad.nordeste.Draw(g);
				quad.nordeste.Print(quad.nordeste,g);
			}
			if(quad.sudoeste != null)
			{
				//System.out.println("Sudoeste");
				quad.sudoeste.Draw(g);
				quad.sudoeste.Print(quad.sudoeste,g);
			}
			if(quad.sudeste != null)
			{
				//System.out.println("Sudeste");
				quad.sudeste.Draw(g);
				quad.sudeste.Print(quad.sudeste,g);
			}
		}
		
		
	}
	//insere elementos na arvore
	public void Insert(Rect rect) 
	{
		if(!this.dimension.BoundingCollision(rect,dimension))
		{
			return;
		}
		
		for(int i = 0; i< objects.size(); i++)
		{
			if(this.objects.get(i) == rect)
			{
				return;
			}
		}
		
		if(this.objects.size() < this.capacity)
		{
			
			this.objects.add(rect);
			
		}else {
			if(!this.divided) {
				this.Subdivide();
			}
			this.objects.add(rect);
			for(int i = 0; i< objects.size(); i++)
			{
				if(this.objects.get(i).BoundingCollision(this.objects.get(i), this.noroeste.dimension))
				{
					this.noroeste.Insert(this.objects.get(i));
				}
				if(this.objects.get(i).BoundingCollision(this.objects.get(i), this.nordeste.dimension))
				{
					this.nordeste.Insert(this.objects.get(i));
				}
				if(this.objects.get(i).BoundingCollision(this.objects.get(i), this.sudoeste.dimension))
				{
					this.sudoeste.Insert(this.objects.get(i));
				}
				if(this.objects.get(i).BoundingCollision(this.objects.get(i), this.sudeste.dimension))
				{
					this.sudeste.Insert(this.objects.get(i));
				}
				
			}
			this.objects.clear();
			
		}
	}
	
	//limpa a arvore
	public void ClearSubdivide(QuadTree quad) {
		
		
		if(quad != null) {
			if(quad.noroeste != null)
			{
				quad.noroeste = null;
				quad.objects.clear();
				quad.divided = false;
				ClearSubdivide(quad.noroeste);
			}
			if(quad.nordeste != null)
			{
				quad.nordeste = null;
				quad.objects.clear();
				quad.divided = false;
				ClearSubdivide(quad.nordeste);
			}
			if(quad.sudoeste != null)
			{
				quad.sudoeste = null;
				quad.objects.clear();
				quad.divided = false;
				ClearSubdivide(quad.sudoeste);
			}
			if(quad.sudeste != null)
			{
				quad.sudeste = null;
				quad.objects.clear();
				quad.divided = false;
				ClearSubdivide(quad.sudeste);
			}
		}
		
		
		
		
		
		
	}
	
	//faz uma busca para retornar em qual retangulo o objeto esta
	public void Search(QuadTree quad,Rect rect) 
	{
		
		if(quad.dimension.BoundingCollision(rect,quad.dimension))
		{
			
			for(int i = 0; i< quad.objects.size(); i++)
			{
				if(quad.objects.get(i) == rect)
				{
					Level2.CollectionObjects(quad.dimension);
					return;
				}
				
			}
			if(quad.divided)
			{
				this.noroeste.Search(quad.noroeste,rect);
				this.nordeste.Search(quad.nordeste,rect);
				this.sudoeste.Search(quad.sudoeste,rect);
				this.sudeste.Search(quad.sudeste,rect);
			}
		}
		
	}
	
}
