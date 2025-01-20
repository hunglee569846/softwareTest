package solution;

import java.util.ArrayList;
import java.util.List;

public class testfunction {
	public static void main(String[] args) throws Exception {
		
		String[] A = {"A","B","C","S"};
		List<String> prt = new ArrayList<>();
		
		for(String i : A) {
			for(String n : A) {
				if(i!=n && !prt.contains((i+n)) && !prt.contains((n+i))) {
					prt.add((i+n));
					System.out.println("result: "+ i + n);
				}
				
			}
		}
		
//		for (int i = 0; i < points.length; i++) {
//			for (int j = i + 1; j < points.length; j++) { 
//				
//				String segment = points[i] + points[j];
//				String reverseSegment = points[j] + points[i];
//				if (!printedSegments.contains(segment) && !printedSegments.contains(reverseSegment)) {
//					
//					printedSegments.add(segment); printedSegments.add(reverseSegment);
//					// Đảm bảo đoạn ngược lại cũng được lưu trữ 
//					System.out.println("Segment: " + segment + " -> (" + coordinates[i][0] + ","
//					+ coordinates[i][1] + ") to (" + coordinates[j][0] + "," + coordinates[j][1] + ")"); 
//				
//				}
//			}
//
//		}
	}
}
