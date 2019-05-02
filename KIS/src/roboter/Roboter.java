package roboter;

public interface Roboter {
	
	public void doAction(int action);		
	public void fetchData(int pos);
	public int findBarrier();	
	public void look();	
	public boolean isBumped();	
	public boolean isGoal();
	public void forward();
	public void backward();
	public void left();
	public void right();
	public void stop();

}
