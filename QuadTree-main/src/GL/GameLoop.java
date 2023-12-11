package GL;

public class GameLoop {
	
	
	public static boolean rodando = true;
	
	int FRAMES_PER_SECOND = 60;
	//public static int FPS =0;
	float next_frame = System.currentTimeMillis();
	float SKIP = 1000/FRAMES_PER_SECOND;
	boolean print = true;
	
	public GameLoop() {
		Start();
		Loop();
	}
	
	//loop do gameLoop
	public void Loop() {
		
		float gameTime = 0;
		while(rodando) {
			
			long inicio = System.currentTimeMillis();
			
			
			next_frame += SKIP;
			
			float time = next_frame - inicio;
			if(time <= 0)
			{
				Update(gameTime);
				Draw();
				
			}
				
			
			long fim = System.currentTimeMillis();
			gameTime = (float)(fim - inicio)* 0.001f;
			
		}
	}
	
	public void Start(){
		
		
	}
	
	public void Update(float gameTime) {
		System.out.println("x");
	}
	
	public void Draw() {
		
	}
	
	
}
