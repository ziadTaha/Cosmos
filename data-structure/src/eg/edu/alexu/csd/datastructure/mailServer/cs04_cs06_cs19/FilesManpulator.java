package eg.edu.alexu.csd.datastructure.mailServer.cs04_cs06_cs19;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FilesManpulator {
	public boolean deleteDirectory(File directoryToBeDeleted) {
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectory(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
public void copyFiles(String original,String copyPath)
{
	    Path copied = Paths.get(copyPath);
	    Path originalPath = Paths.get(original);
	    try {
			Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
public String getExtention(String path)
{
	String ex ="";
	int i = path.lastIndexOf('.');
	if(i>0)
	{
		ex=path.substring(i+1);
	}
	return ex;
}
}
