package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import users.User;

public class DirectoryRemote implements Directory{
	
	public void checkPrivilegeDir(User user) {
		// TODO Auto-generated method stub
		
	}
	public void create(String path, String name) {
		/*
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
		*/
		
		 //java.io.File source = new java.io.File(path);
		try {
	         com.google.api.services.drive.model.File metadata = new com.google.api.services.drive.model.File();
	         metadata.setName(name);
	         List<String> parents = new ArrayList<String>();
	         parents.add(path);
	         metadata.setParents(parents);
	         metadata.setMimeType("application/vnd.google-apps.folder");
	         com.google.api.services.drive.model.File file =  StorageRemote.drive.files().create(metadata).setFields("id, parents").execute();
	         System.out.println(file.getId());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public void createMultiple(String path, String name, int amount) {
		// TODO Auto-generated method stub
		for(int i = 0; i < amount;i++) {
			create(path, name + (i+1));
		}
		
	}
	public void delete(String path, String name) {
		// TODO Auto-generated method stub
		try {
			String fileID = path;
			StorageRemote.drive.files().delete(fileID).execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void move(String path1, String path2) {
		// TODO Auto-generated method stub
		
		
		try {
				String toMove = path1;
				String folderID = path2;
				com.google.api.services.drive.model.File file = StorageRemote.drive.files().get(toMove)
				    .setFields("parents")
				    .execute();
				StringBuilder previousParents = new StringBuilder();
				for (String parent : file.getParents()) {
				  previousParents.append(parent);
				  previousParents.append(',');
				}
				file = StorageRemote.drive.files().update(toMove, null)
					    .setAddParents(folderID)
					    .setRemoveParents(previousParents.toString())
					    .setFields("id, parents")
					    .execute();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int fileCount(int fileCount) {
		// TODO Auto-generated method stub
		return 0;
	}
	public boolean makeConnection(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
