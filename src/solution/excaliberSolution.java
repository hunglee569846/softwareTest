package solution;
import java.awt.Window;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class excaliberSolution {

	static int tc, row, col, gr, gc, result;
	//Kham pha 4 huong trong ma tran 2 chieu
	private static final int[] rowDirect = {-1, 1, 0, 0};
	private static final int[] colDirect = {0, 0, 1, -1};
	
	private static final String[] doneExcaliber = {"A","B","C", "S"};
	
	
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
		    ArrayList<Object[]> coordinates = new ArrayList<>() {};
		    
		    ArrayList<Object[]> result = new ArrayList<>() {};
		    
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
					if((Arrays.asList(doneExcaliber).contains(map[n][m]))) {
						coordinates.add(new Object[] {n, m, map[n][m]});
					}
					
				}
			}
			

			List<String> prt = new ArrayList<>();
			
			for(String i : doneExcaliber) {
				for(String n : doneExcaliber) {
					if(i!=n && !prt.contains((i+n)) && !prt.contains((n+i))) {
						prt.add((i+n));
						System.out.println("result: "+ i + n);
					}
					
				}
			}
			
			for(Object[] condi : coordinates) {
				System.out.println("list value: " + Arrays.toString(condi));
				BFS(map, condi, "C");
			}
			
			
//			while(checkExcaliber.size() != 4) {
//				BFS(map,checkExcaliber);
//			}
			
			
			bw.write("#"+tc+" :" +result+"\n");
			bw.write("==================================================\n");
		}
		
		br.close();
		bw.flush();

	}
	
	public static void BFS(String[][] map, Object[] startPoint,String endPoint) {
		boolean visited[][] = new boolean[row][col];
		Deque<Object[]> dequeue = new LinkedList<>();
		
		dequeue.add(startPoint);
		visited[gr][gc] = true;
		
		int st = 0;
		
//		Object[] current = dequeue.poll();
		
//		System.out.println("Dequeue: " +  Arrays.toString(current));
		
		
		while (!dequeue.isEmpty()) {
			Object[] current = dequeue.poll();
			
			int currentRow = (int) current[0];
			int currentCol = (int) current[1];
			String str = (String) current[2];
			
			System.out.print("Value: " +str+"\n");

			// check A,B,C of excaliber   && !checkExcaliber.equals(map[currentRow][currentCol])
			if((Arrays.asList(doneExcaliber).contains(map[currentRow][currentCol]))) {
				System.out.printf("nextPoint: "+ map[currentRow][currentCol]+"\n");
				System.out.print("Strat x: " +currentRow+ "y: "+currentCol+"\n");
				
//				checkExcaliber.add(map[currentRow][currentCol]);
				
				result += st;
				System.out.printf("resutl=1====>  : "+result+"\n");
				dequeue.clear();
				st=0;
				
				System.out.printf("dequeue : "+dequeue.size()+"\n");
				gr = currentRow;
				gc = currentCol;
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
