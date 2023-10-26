
public class Game {
	int round;
	
	public Game() {
		round = 0;
	}
	
	public Game(int r) {
		round = r;
	}
	
	public int getRound() {
		return round;
	}
	
	public void setRound(int r) {
		this.round = r;
	}
	
	public static void main(String args[]) {
		int N = 15;
		int n = 100;
		int numsup = 4;
		Game game = new Game(1);
		Board board = new Board(N, numsup, (N * N * 3 + 1) / 2 ); //, (N*N +1)/4
		board.createBoard();
		Player[] players = new Player[2];
		players[0] = new Player(0, "Theseus", board, 0, 0, 0);
		players[1] = new Player(1, "Minotaur", board, 0, N/2, N/2);
		int[] currentPosition = new int[players.length];
		int newPosition = 0;
		String winnerName = null;
		currentPosition[0] = 0;
		currentPosition[1] = N * N / 2;
		String[][]x = board.getStringRepresentation(currentPosition[0], currentPosition[1]);
		for(int i = 0; i< 2*N + 1;i++) {
			for(int j=0; j< N;j++) {
				if(j == N - 1) {
					System.out.println(x[i][j]);
				}else {
					System.out.print(x[i][j]);
				}
			}
		}
		
		System.out.println("*********** The game begins **********");
		System.out.println();
		game.round = 0;
		boolean minFlag = false;
		boolean thFlag = false;
		
		for (;;) {
			game.round++;
			System.out.println("********************************** Round " + game.round + " **********************************");
			for (int i = 0; i < players.length; i++) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!! Player: " + players[i].getName() + " !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				int dice = 0;
				do {
					dice = (int) (Math.random()*8);
				}while(dice % 2 != 1);
				newPosition = players[i].move(currentPosition[i], i, dice)[0];
				if (players[i].getScore() == numsup) {
					winnerName = players[i].getName();
					thFlag= true;
					break;
				} 
				if (i == 1){
					if(newPosition == currentPosition[i-1]) {
						winnerName = players[i].getName();
						minFlag= true;
						break;
					}
				}
				if (i == 0){
					if(newPosition == currentPosition[i+1]) {
						winnerName = players[i+1].getName();
						minFlag= true;
						break;
					}
				}
				if (i == 0) {
					System.out.println(" Current Position: " + currentPosition[i] + " New Position: " + newPosition
							+ " Player Score: " + players[i].getScore());
					
				}else {
					System.out.println("Player: " + players[i].getName() + " Current Position: " + currentPosition[i] + " New Position: " + newPosition);

				}
				currentPosition[i] = newPosition;
				players[i].setX(newPosition/N);
				players[i].setY(newPosition%N);
			}
			String[][]x2 = board.getStringRepresentation(currentPosition[0], currentPosition[1]);
			for(int ii = 0; ii< 2*N + 1;ii++) {
				for(int j=0; j< N;j++) {
					if(j == N - 1) {
						System.out.println(x2[ii][j]);
					}else {
						System.out.print(x2[ii][j]);
					}
				}
			}
			int i = 0;
			if (game.round >= 2*n || minFlag || thFlag) {
				break;
			}
		}
		
		System.out.println();
		System.out.println("*********** The game is over *********");
		System.out.println();
		System.out.println("Rounds played: "+game.round);
		if(game.round >= 2*n) {
			System.out.println("The game is a tie!!!");
		}else {
			System.out.println(winnerName +" won the game!!!");
		}
	}
}
