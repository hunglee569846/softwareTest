package solution;
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

	static int tc, row, col, gr, gc;
	//Kham pha 4 huong trong ma tran 2 chieu
	private static final int[] rowDirect = {-1, 1, 0, 0};
	private static final int[] colDirect = {0, 0, -1, 1};
	
	private static final String[] doneExcaliber = {"A","B","C"};
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new FileReader("src/exampleTest/excaliberTest.txt"));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer line;
		
		tc = Integer.parseInt(br.readLine());
		
		bw.write("test case: "+ tc+"\n");
		
		for(int c = 0; c < tc; c++) {
			
			int countBreck = 0;
		    ArrayList<String> doneExcaliber;
		    List<String> checkExcaliber=new ArrayList<String>();
//			int startRow, startCol;
		    
			line = new StringTokenizer(br.readLine());
			
			row = Integer.parseInt(line.nextToken());
			col = Integer.parseInt(line.nextToken());
			
			gr = Integer.parseInt(line.nextToken());
			gc = Integer.parseInt(line.nextToken());
			
			bw.write("Size: row:" + row + " col:" + col + "\n");
			bw.write("Gel[" +gr+ "][" +gc+ "]\n");
			
			String[][] map = new String[row][col];
			
			for(int n = 0; n < row; n++ ) {
				line = new StringTokenizer(br.readLine());
				
				for(int m = 0; m < col; m++) {
					map[n][m] = line.nextToken();
					
				}
			}
			
			while(checkExcaliber.size() != 3) {
				BFS(map,countBreck,checkExcaliber);
			}
			
			
			bw.write("#"+tc+" :" +countBreck+"\n");
			bw.write("==================================================\n");
		}
		
		br.close();
		bw.flush();

	}
	
	public static void BFS(String[][] map, int CountBreck, List<String> checkExcaliber) {
		boolean visited[][] = new boolean[row][col];
		Deque<int[]> dequeue = new LinkedList();
		int countStep = 0;
		
		dequeue.add(new int[] {gr,gc});
		visited[gr][gc] = true;
		
		
		while (!dequeue.isEmpty()) {
			int[] current = dequeue.poll();
			
			int currentRow = current[0];
			int currentCol = current[1];
			
			
			//check A,B,C of excaliber   && !checkExcaliber.equals(map[currentRow][currentCol])
//			System.out.printf("==> " + (Arrays.asList(doneExcaliber).contains(map[currentRow][currentCol])) + "\n");
			System.out.printf("==> countStep" + countStep + "\n");
			if((Arrays.asList(doneExcaliber).contains(map[currentRow][currentCol]))  && !checkExcaliber.equals(map[currentRow][currentCol])) {
				
				checkExcaliber.add(map[currentRow][currentCol]);
				
				gr = currentRow;
				gc = currentCol;
				break;
			}
			
			
			
			for(int i = 0; i < 4; i++) {
				
				int newRow = currentRow + rowDirect[i];
				int newCol = currentCol + colDirect[i];
				
				if(isValid(newRow, newCol) && !visited[newRow][newCol] && map[newRow][newCol] != "X") {
					countStep++;
					dequeue.add(new int[] {newRow, newCol});
					visited[newRow][newCol] = true;
				}
				
			}
			
		}
		CountBreck += countStep;
		
	}
	
	private static boolean isValid(int newRow, int newCol) {
		return newRow >= 0 && newRow < row && newCol >=0 && newCol < col; 
	}
	
//	private static void logicCheck() {
//		
//	}
//	
	

}
