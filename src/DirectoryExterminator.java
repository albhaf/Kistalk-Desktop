import java.io.File;


public class DirectoryExterminator {
	
	String sPath;
	
	public DirectoryExterminator(String tmpPath){
		sPath=tmpPath;
	}
		
/*	public boolean exterminate(String tmpPath) {
		File path = new File(tmpPath);
		return delDir(path);
	}*/
	
	/**
	 * path should be absolute or relative to the .jar position.
	 */
	public boolean exterminate() {
		File path = new File(sPath);
		return delDir(path);
	}
	
	   private boolean delDir(File path){
	    if( path.exists() ) {
	      File[] files = path.listFiles();
	      for(int i=0; i<files.length; i++) {
	         if(files[i].isDirectory()) {
	           delDir(files[i]);
	         }else {
	           files[i].delete();
	         }
	      }
	    }
	    return( path.delete() );
	   }
}
