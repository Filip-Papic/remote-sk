package model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import users.User;

public class DirectoryRemote implements Directory{
	
	public void checkPrivilegeDir(User user) {
		// TODO Auto-generated method stub
		
	}
	public void create(String path, String name) {
		Path path2 = null;
		if(path != null && path.length() > 0) 					//!!!!!
			path2 = Paths.get(path);
		
		Path path3 = Paths.get(path2 + java.io.File.separator + name);
		
		if(Files.exists(path2) && Files.exists(path3) == false) {
			try {
				Path path4 = Files.createDirectory(path3);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}
	public void delete(String path, String name) {
		// TODO Auto-generated method stub
		
	}
	public int fileCount(int fileCount) {
		// TODO Auto-generated method stub
		return 0;
	}
	public boolean makeConnection(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	public void move(String path1, String path2) {
		// TODO Auto-generated method stub
		
	}

}
