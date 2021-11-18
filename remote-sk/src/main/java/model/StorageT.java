package model;

import users.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class StorageT implements Storage{
	
	private static final String credentialsPath = "/client_secret.json";
	
	private static FileDataStoreFactory DATA_STORE_FACTORY;
	
	private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	
	private static HttpTransport HTTP_TRANSPORT;
	
	private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE);
	
	static Drive drive;
	
	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}
	
	public static Credential authorize() throws IOException{
		InputStream in = StorageT.class.getResourceAsStream(credentialsPath);
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
		
		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
		clientSecrets, SCOPES).setAccessType("offline").build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		return credential;
	}
	
	public static Drive getDriveService() throws IOException {
		Credential credential = authorize();
		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
			.setApplicationName("SK Drive")
			.build();
	}
	
	public StorageT() throws IOException{
		this.drive = getDriveService();
		/*
		FileList result = drive.files().list()
				.setPageSize(10)
				.setFields("nextPageToken, files(id, name)")
				.execute();
			List<com.google.api.services.drive.model.File> files = result.getFiles();
			if (files == null || files.isEmpty()) {
				System.out.println("No files found.");
			} else {
				System.out.println("Files:");
				for (com.google.api.services.drive.model.File file : files) {
					System.out.printf("%s (%s)\n", file.getName(), file.getId());
				}
			}
		*/
	}
	
	public boolean auth(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	public void createDir(String path, String name) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		DirectoryT dir;
		try {
			dir = (DirectoryT) Class.forName("model.DirectoryRemote").newInstance();
			dir.create(path, name);
			//java.io.File metaData = new java.io.File(path);
			
			
			
			/*
			File metaData = new File();
			metaData.setName(name);
			//List<String> parents = new ArrayList<String>();
			//parents.add("smog");
			//metaData.setParents(parents);
			metaData.setMimeType("application/vnd.google-apps.folder");

			File file = drive.files().create(metaData)
			    .setFields("id")
			    .execute();
			*/
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createMultipleDirs(String path, String name, int i) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		DirectoryT dir;
		try {
			dir = (DirectoryT) Class.forName("model.DirectoryRemote").newInstance();
			dir.createMultiple(path, name, i);
		} catch (InstantiationException e) {
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deleteDir(String path, String name) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		DirectoryT dir;
		try {
			dir = (DirectoryT) Class.forName("model.DirectoryRemote").newInstance();
			dir.delete(path, name);
		} catch (InstantiationException e) {
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void moveDir(String path1, String path2) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		DirectoryT dir;
		try {
			dir = (DirectoryT) Class.forName("model.DirectoryRemote").newInstance();
			dir.move(path1, path2);
		} catch (InstantiationException e) {
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void createFile(String path, String name) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		FileT file;
		DirectoryT dir;
		try {
			file = (FileT) Class.forName("model.FileRemote").newInstance();
			file.create(path, name);
		} catch (InstantiationException e) {
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void createMultipleFiles(String path, String name, int i) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		FileT file;
		DirectoryT dir;
		try {
			file = (FileT) Class.forName("model.FileRemote").newInstance();
			file.createMultiple(path, name, i);
		} catch (InstantiationException e) {
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void deleteFile(String path, String name) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		FileT file;
		DirectoryT dir;
		try {
			file = (FileT) Class.forName("model.FileRemote").newInstance();
			file.delete(path, name);
		} catch (InstantiationException e) {
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void moveFile(String path1, String path2) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		FileT file;
		DirectoryT dir;
		try {
			file = (FileT) Class.forName("model.FileRemote").newInstance();
			file.move(path1, path2);
		} catch (InstantiationException e) {
			e.printStackTrace();
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}
	public Directory rootDirectory(String path) {
		// TODO Auto-generated method stub
		return null;
	}
	public int setSize(int size) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
