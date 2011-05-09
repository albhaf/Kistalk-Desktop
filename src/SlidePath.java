import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class SlidePath {
	
	public ConfigQueue add(String name, String path, ConfigQueue confValues){
		List<String> names;
		StringBuffer sb = new StringBuffer();
		
		names = ninja(confValues.getValue("Saved_Pubslides"));
		if (names.indexOf(name) == -1){
			sb.append(confValues.getValue("Saved_Pubslides") + "|");
			sb.append(name);
			confValues.replaceValue("Saved_Pubslides", sb.toString());
			
			sb.delete(0, sb.length());
			
			sb.append(confValues.getValue("Saved_Paths") + "|");
			sb.append(path);
			confValues.replaceValue("Saved_Paths", sb.toString());
			
		}else{
			//The name already exist, pick another
		}
		
		return confValues;
		
	}
	
	public ConfigQueue remove(String name, ConfigQueue confValues){
		List<String> names, paths;
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		
		names = ninja(confValues.getValue("Saved_Pubslides"));
		paths = ninja(confValues.getValue("Saved_Paths"));
		
		int i = names.indexOf(name);
		if (i != -1){
			names.remove(i);
			paths.remove(i);
			
			for (int j = 0; j < names.size(); j++){
				sb1.append(names.get(j).toString());
				if (j < names.size()-1)
					sb1.append("|");
			}
			confValues.replaceValue("Saved_Pubslides", sb1.toString());
			
			for (int j = 0; j < paths.size(); j++){
				sb2.append(paths.get(j));
				if (j < paths.size()-1)
					sb2.append("|");
			}
			confValues.replaceValue("Saved_Path", sb2.toString());
			
		}else{ //No such item
			//Failframe(); ?
		}
		
		return confValues;
	}
	
	public List<String> ninja(String line){
		LinkedList<String> part = new LinkedList<String>();
		Scanner scanner = new Scanner(line);
		
		scanner.useDelimiter("\\|");
		
		while(scanner.hasNext()){
			part.add(scanner.next());
		}
		
		return part;
	}
	
}
