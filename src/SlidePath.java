import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class SlidePath {
	
	public String[] add(String name, String path, String[] confValues){
		List<String> names;
		StringBuffer sb = new StringBuffer();
		
		names = ninja(confValues[9]);
		if (names.indexOf(name) == -1){
			sb.append(confValues[9] + "|");
			sb.append(name);
			confValues[9] = sb.toString();
			
			sb.delete(0, sb.length()-1);
			
			sb.append(confValues[10] + "|");
			sb.append(path);
			confValues[10] = sb.toString();
			
		}else{
			//The name already exist, pick another
		}
		
		return confValues;
		
	}
	
	public String[] remove(String name, String[] confValues){
		List<String> names, paths;
		StringBuffer sb = new StringBuffer();
		
		names = ninja(confValues[9]);
		paths = ninja(confValues[10]);
		
		int i = names.indexOf(name);
		if (i != -1){
			names.remove(i);
			paths.remove(i);
			
			for (int j = 0; j <= names.size(); j++){
				sb.append(names.get(j) + "|");
			}
			confValues[9] = sb.toString();
			
			sb.delete(0, sb.length()-1);
			
			for (int j = 0; j <= names.size(); j++){
				sb.append(paths.get(j));
				sb.append("|");
			}
			confValues[10] = sb.toString();
			
		}else{ //No such item
			
		}
		
		return confValues;
	}
	
	public List<String> ninja(String line){
		LinkedList<String> part = new LinkedList<String>();
		Scanner scanner = new Scanner(line);
		
		scanner.useDelimiter("|");
		
		while(scanner.hasNext()){
			part.add(scanner.next());
		}
		
		return part;
	}
	
}
