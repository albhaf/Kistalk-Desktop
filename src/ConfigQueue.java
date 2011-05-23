import java.io.File;
import java.util.LinkedList;


public class ConfigQueue {

	LinkedList<String> Queue;
	
	public ConfigQueue(){
		Queue = new LinkedList<String>();
	}
	
	public void add(String tmp){
		if(tmp.equalsIgnoreCase("Number_of_Hans%-")==false){
		Queue.addLast(tmp);
		}
	}
	
	public boolean contains(String tmp){
		String[] tmpS=null;
		for(int i =0; i<Queue.size();i++){
			tmpS = Queue.get(i).split("%");
			if(tmpS[0]==tmp){
				return true;
			}
		}
		return false;
	}
	
	public int size(){
		return Queue.size();
	}
	
	public void remove(String tmp){
		String[] compare = new String[2];
		for(int i =0;i<Queue.size();i++){
			compare = Queue.get(i).split("%");
			if(compare[0].equalsIgnoreCase(tmp)){
				Queue.remove(i);
				return;
			}
		}
	}
	
	public boolean replaceValue(String tmpString,String tmpValue){
		String[] compare = new String[2];
		for(int i =0;i<Queue.size();i++){
			compare = Queue.get(i).split("%");
			if(compare[0].equalsIgnoreCase(tmpString)){
				if(tmpString.equalsIgnoreCase("Path_to_Pubslides")){	
				//tmpValue =tmpValue.replace(" ","  ");
				//tmpValue = (+ tmpValue);
				}
				Queue.set(i, compare[0] +"%" + tmpValue);
				return true;
			}
		}		
		
		return false;
	}
		
	public String getValue(String tmp){
		String[] compare = new String[2];
		for(int i =0;i<Queue.size();i++){
			compare = Queue.get(i).split("%");
			if(compare[0].equalsIgnoreCase(tmp)){
				return compare[1]; 
			}
		}
		return null;
	}
	
	public String peek(){
		return Queue.peekFirst().split("%")[0];
	}
	
	public String getIndex(int i){
		String tmp = "";
		
		try{
		tmp = Queue.get(i).split("%")[1];
		}catch(ArrayIndexOutOfBoundsException e){
			return "";
		}
		return tmp;
	}
	
	public String get(){		
		return Queue.pop().split("%")[1];
	}
	
}
