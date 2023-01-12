package com.springrds.config;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.springrds.dto.AwsSecrets;

@Configuration
public class RdsConfiguration {

	private Gson gson = new Gson();
	
	@Bean
	public DataSource dataSource() {
		AwsSecrets secrets = getSecret();

		return DataSourceBuilder.create()
				/* .driverClassName("com.mysql.cj.jdbc.driver") */.url("jdbc:" + secrets.getEngine() + "://"
						+ secrets.getHost() + ":" + secrets.getPort() + "/simpleDemoDB")
				.username(secrets.getUsername()).password(secrets.getPassword()).build();
	}

	

	private AwsSecrets getSecret() {

		String secretName = "simpleDemoCredential";
		Region region = Region.of("us-east-1");

		// Create a Secrets Manager client
		SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();

		GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder().secretId(secretName).build();

		GetSecretValueResponse getSecretValueResponse;

		try {
			getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
		} catch (Exception e) {
			// For a list of exceptions thrown, see
			// https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
			throw e;
		}

		String secret = getSecretValueResponse.secretString();

		// Your code goes here.

		return gson.fromJson(secret, AwsSecrets.class);
	}

}
