package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;

//@TestConfiguration(proxyBeanMethods = false)
@Configuration
public class MyTestConfiguration {

	@Bean
	Something something() {
		return new Something("sven");
	}

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer("postgres:latest")
//			.withDatabaseName("postgres")
			.withUsername("postgres")
			.withPassword("postgres");

/*
		return new PostgreSQLContainer(
			DockerImageName.parse("simas/postgres-sakila").asCompatibleSubstituteFor("postgres")
		)
			.withUsername("postgres")
			.withDatabaseName("postgres")
			.withPassword("sakila")
			;
*/
	}

//	@Bean
//	DataSource ds() {
//		return new Data postgresContainer().conn
//	}
}
