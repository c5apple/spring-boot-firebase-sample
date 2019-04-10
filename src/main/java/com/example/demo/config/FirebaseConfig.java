package com.example.demo.config;

import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@Configuration
public class FirebaseConfig {

	@Bean
	public DatabaseReference firebaseDatabse() {
		return FirebaseDatabase.getInstance().getReference();
	}

	@Value("${firebase.database.url}")
	private String databaseUrl;

	@Value("${firebase.config.path}")
	private String configPath;

	@PostConstruct
	public void init() {

		/*
		 * see https://firebase.google.com/docs/server/setup
		 */
		InputStream inputStream = FirebaseConfig.class.getClassLoader().getResourceAsStream(configPath);

		FirebaseOptions options = new FirebaseOptions.Builder().setServiceAccount(inputStream)
				.setDatabaseUrl(databaseUrl).build();
		FirebaseApp.initializeApp(options);

	}
}
