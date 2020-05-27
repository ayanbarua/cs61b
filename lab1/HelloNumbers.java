public class HelloNumbers {
	public static void main(String[] args) {
		int x = 0;
		int y = 0;
		while (x < 10) {
			System.out.print(y + " ");
			x += 1;
			y += x;
		}
		System.out.println();
	}
}
			