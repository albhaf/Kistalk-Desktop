import java.util.List;
import java.util.Scanner;


public class DivideString { //Ej testad, är till för De nya configraderna
	public DivideString() {
		
	}
	
	public List<String> ninja(String line){
		Scanner scanner = new Scanner(line);
		List<String> part = null;
		int i = 0;
		
		scanner.useDelimiter("|");
		
		while(scanner.hasNext()){
			part.add(scanner.next());
			System.out.println(part.get(i));
			i++;
		}
		
		return part;
	}
	
	
}
