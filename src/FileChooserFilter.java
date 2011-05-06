import java.io.File;

import javax.swing.filechooser.FileFilter;


public class FileChooserFilter extends FileFilter {

	@Override
	public boolean accept(File arg0) {
		return arg0.isDirectory() || arg0.getName().toLowerCase().endsWith(".ppt");

	}

	@Override
	public String getDescription() {
		 return ".ppt files";
	}

}
