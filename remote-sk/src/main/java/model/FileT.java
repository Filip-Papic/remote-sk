package model;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.http.FileContent;

import users.User;

public class FileT implements File{
	
	public void uploadDrive(String name, String id) {
		// TODO Auto-generated method stub
		
	}
	public void checkPrivilegeFile(User user) {
		// TODO Auto-generated method stub
		
	}
	public void create(String path, String name) {
		// TODO Auto-generated method stub
		try {
	         com.google.api.services.drive.model.File metadata = new com.google.api.services.drive.model.File();
	         metadata.setName(name);
	         List<String> parents = new ArrayList<String>();
	         parents.add(path);
	         metadata.setParents(parents);
	         com.google.api.services.drive.model.File file =  StorageT.drive.files().create(metadata).setFields("id, parents").execute();
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
			StorageT.drive.files().delete(fileID).execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void move(String path1, String path2) {
		// TODO Auto-generated method stub
		try {
			String toMove = path1;
			String folderID = path2;
			com.google.api.services.drive.model.File file = StorageT.drive.files().get(toMove)
			    .setFields("parents")
			    .execute();
			StringBuilder previousParents = new StringBuilder();
			for (String parent : file.getParents()) {
			  previousParents.append(parent);
			  previousParents.append(',');
			}
			file = StorageT.drive.files().update(toMove, null)
				    .setAddParents(folderID)
				    .setRemoveParents(previousParents.toString())
				    .setFields("id, parents")
				    .execute();
			}catch(IOException e) {
				e.printStackTrace();
			}
		
	}
	
	public void upload(String path, String dest) {
		// TODO Auto-generated method stub
		try {
			java.io.File file = new java.io.File(path);
			if(file.isDirectory()) {
				System.out.println("Selected object is not a file, operation terminated.");
				return;
			}
			com.google.api.services.drive.model.File metadata = new com.google.api.services.drive.model.File();
			metadata.setName(file.getName());
			List<String> parents = new ArrayList<String>();
	        parents.add(dest);
			metadata.setParents(parents);
			StorageT.drive.files().create(metadata, new FileContent(Files.probeContentType(Paths.get(path)), file)).execute();
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void download(String path) {
		// TODO Auto-generated method stub
		try {
			OutputStream outputStream = new ByteArrayOutputStream();
			StorageT.drive.files().get(path)
			    .executeMediaAndDownloadTo(outputStream);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	public void downloadDrive(String name, String id) {
		// TODO Auto-generated method stub
		
	}
	public java.io.File[] lookup(String path, String name) {
		// TODO Auto-generated method stub
		return null;
	}
	public void lookupAllFilesInDate(String path, String name) {
		// TODO Auto-generated method stub
		
	}
	public void lookupAllFilesinDir(String path, String name) {
		// TODO Auto-generated method stub
		
	}
	public void lookupAllFilesInWholeDir(String path, String name) {
		// TODO Auto-generated method stub
		
	}
	public void lookupAllFilesSortedDate(String path, String name) {
		// TODO Auto-generated method stub
		
	}
	public void lookupAllFilesSortedEdit(String path, String name) {
		// TODO Auto-generated method stub
		
	}
	public void lookupAllFilesSortedName(String path, String name) {
		// TODO Auto-generated method stub
		
	}
	public void lookupAllFilesWithExtension(String path, String name) {
		// TODO Auto-generated method stub
		
	}
	
	

}
