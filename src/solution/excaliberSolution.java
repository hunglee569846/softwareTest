package solution;
import java.awt.Window.Type;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class excaliberSolution {

	static int tc, row, col, gr, gc, result;
	//Kham pha 4 huong trong ma tran 2 chieu
	private static final int[] rowDirect = {-1, 1, 0, 0};
	private static final int[] colDirect = {0, 0, 1, -1};
	
	private static final String[] doneExcaliber = {"A","B","C"};
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new FileReader("src/exampleTest/excaliberTest.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer line;
		
		tc = Integer.parseInt(br.readLine());
		
		bw.write("test case: "+ tc+"\n");
		
		for(int c = 0; c < tc; c++) {
			
			result = 0;
		    List<String> checkExcaliber=new ArrayList<String>();
		    
			line = new StringTokenizer(br.readLine());
			
			row = Integer.parseInt(line.nextToken());
			col = Integer.parseInt(line.nextToken());
			
			gr = Integer.parseInt(line.nextToken())-1;
			gc = Integer.parseInt(line.nextToken())-1;
			
			bw.write("Size: row:" + row + " col:" + col + "\n");
			bw.write("Gel[" +gr+ "][" +gc+ "]\n");
			
			String[][] map = new String[row][col];
			
			for(int n = 0; n < row; n++ ) {
				line = new StringTokenizer(br.readLine());
				for(int m = 0; m < col; m++) {
					map[n][m] = line.nextToken();
					
				}
			}
			while(checkExcaliber.size() != 4) {
				BFS(map,checkExcaliber);
			}
			
			
			bw.write("#"+tc+" :" +result+"\n");
			bw.write("==================================================\n");
		}
		
		br.close();
		bw.flush();

	}
	
	public static void BFS(String[][] map, List<String> checkExcaliber) {
		boolean visited[][] = new boolean[row][col];
		Deque<int[]> dequeue = new LinkedList();
		
		dequeue.add(new int[] {gr,gc,0});
		visited[gr][gc] = true;
		
		
		while (!dequeue.isEmpty()) {
			int[] current = dequeue.poll();
			
			int currentRow = current[0];
			int currentCol = current[1];
			int st = current[2];

			// check A,B,C of excaliber   && !checkExcaliber.equals(map[currentRow][currentCol])
			if((Arrays.asList(doneExcaliber).contains(map[currentRow][currentCol]))  && !checkExcaliber.contains(map[currentRow][currentCol])) {
				System.out.printf("nextPoint: "+ map[currentRow][currentCol]+"\n");
				System.out.print("Strat x: " +currentRow+ "y: "+currentCol+"\n");
				
				checkExcaliber.add(map[currentRow][currentCol]);
				
				result += st;
				System.out.printf("resutl=1====>  : "+result+"\n");
				dequeue.clear();
				st=0;
				
				System.out.printf("dequeue : "+dequeue.size()+"\n");
				gr = currentRow;
				gc = currentCol;
				
			}
			
// Check done excaliber
			if(checkExcaliber.size() == 3 ) {
				if(map[currentRow][currentCol].equals("S")) {
					checkExcaliber.add(map[currentRow][currentCol]);
					result += st;
					dequeue.clear();
					break;
				}
				st++;
				for(int i = 0; i < 4; i++) {
					int newRow = currentRow + rowDirect[i];
					int newCol = currentCol + colDirect[i];
					
					if(isValid(newRow, newCol) && !visited[newRow][newCol]) {
						dequeue.add(new int[] {newRow, newCol, st});
						visited[newRow][newCol] = false;
					}
				}
			}
			
			st++;
			for(int i = 0; i < 4; i++) {
				
				int newRow = currentRow + rowDirect[i];
				int newCol = currentCol + colDirect[i];
				
				if(isValid(newRow, newCol) && (!visited[newRow][newCol]) && !(map[newRow][newCol].equals("X"))) {
					dequeue.add(new int[] {newRow, newCol,st});
					visited[newRow][newCol] = false;
				}
			}
		}
		
	}
	
	private static boolean isValid(int newRow, int newCol) {
		return newRow >= 0 && newRow < row && newCol >=0 && newCol < col; 
	}

}
