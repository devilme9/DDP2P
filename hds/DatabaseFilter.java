package hds;

import static util.Util._;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import util.Util;

public class DatabaseFilter extends FileFilter {
	public boolean accept(File f) {
	    if (f.isDirectory()) {
	    	return false;
	    }

	    String extension = Util.getExtension(f);
	    if (extension != null) {
	    	//System.out.println("Extension: "+extension);
	    	if (extension.equals("db") ||
	    		extension.equals("sqlite")) {
	    			//System.out.println("Extension: "+extension+" passes");
		        	return true;
	    	} else {
    			//System.out.println("Extension: "+extension+" fails");
	    		return false;
	    	}
	    }
		//System.out.println("Extension: absent - "+f);
	    return false;
	}

	@Override
	public String getDescription() {
		return _("DD Address File Type (.db, .sqlite)");
	}
}