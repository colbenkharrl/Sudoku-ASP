
public class Board {
	
	public int[] numbers;
	public int size;
	
	public Board(int size) {
		this.size = size;
		numbers = new int[(int)Math.pow(size, 2)];
	}
	
	public Board(int size, int[] numbers) {
		this.size = size;
		this.numbers = numbers;
	}
	
	public String MVSMFile() {
		return "File goes here.";
	}
}
