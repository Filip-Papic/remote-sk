package model;

import users.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class StorageRemote implements Storage{
	
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
		InputStream in = StorageRemote.class.getResourceAsStream(credentialsPath);
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
	
	public StorageRemote() throws IOException{
		this.drive = getDriveService();
		
	}
	
	public boolean auth(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	public void create(String path, String name) {
		// TODO Auto-generated method stub
		
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
