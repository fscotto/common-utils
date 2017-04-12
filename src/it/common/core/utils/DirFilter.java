package it.common.core.utils;

import java.io.File;
import java.io.FilenameFilter;

public class DirFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		return new File(dir, name).isDirectory();
	}

}
