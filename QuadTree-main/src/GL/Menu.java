package GL;

import java.awt.Font;

import javax.swing.JLabel;

public class Menu {
	
	Form form;
	JLabel titulo;
	JLabel level1;
	JLabel level2;
	Game1 game1;
	
	public Menu(Form form, Game1 game1) {
		this.form = form;
		this.game1 = game1;
		titulo = this.form.CreateJLabel(titulo,new Font("Calibri",Font.BOLD,30),(int)form.windowSize.x ,10,(int)form.windowSize.width,100 );
		
		level1 = this.form.CreateJLabel(level1,new Font("Calibri",Font.BOLD,30),(int)form.windowSize.x ,200,(int)form.windowSize.width,100 );
		level2 = this.form.CreateJLabel(level2,new Font("Calibri",Font.BOLD,30),(int)form.windowSize.x ,300,(int)form.windowSize.width,100 );
			
			
			
			
	}
	
	//atualiza o sceneManager do game1
	public void Update(float gameTime) {
		
		titulo.setText("Game QuadTree");
		level1.setText("Pressione 1 para iniciar o level1");
		level2.setText("Pressione 2 para iniciar o level2");
		
		if(form.keyboard.Return_Um())
		{
			
			level2.setText("Pressione 2 para iniciar o level2");
			game1.scene = SCENE_MANAGER.LEVEL1;
		}
		if(form.keyboard.Return_Dois())
		{
			game1.scene = SCENE_MANAGER.LEVEL2;
		}
		
		if(game1.scene == SCENE_MANAGER.MENU)
		{
			titulo.setVisible(true);
			level1.setVisible(true);
			level2.setVisible(true);
		}else {
			titulo.setVisible(false);
			level1.setVisible(false);
			level2.setVisible(false);
		}
		
		
	}
	
}
