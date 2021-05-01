package com.ngtu.work.dyploma;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;

@SpringBootApplication
public class DyplomaApplication {
	public static void main(String[] args) {
		try {
				FileInputStream serviceAccount =
						new FileInputStream("C:/Users/Alex/Downloads/lab3/dyploma/src/main/resources/diplomtest1-8f630-firebase-adminsdk-452u3-1ffead7edb.json");
				FirebaseOptions options = FirebaseOptions.builder()
						.setCredentials(GoogleCredentials.fromStream(serviceAccount))
						.setDatabaseUrl("https://diplomtest1-8f630-default-rtdb.firebaseio.com")

						.build();
				FirebaseApp.initializeApp(options);
		} catch (Exception e){
			e.printStackTrace();
		}
		SpringApplication.run(DyplomaApplication.class, args);
	}

}
