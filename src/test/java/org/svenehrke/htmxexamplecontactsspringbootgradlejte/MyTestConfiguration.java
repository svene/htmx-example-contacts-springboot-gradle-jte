package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class MyTestConfiguration {

	@Bean
	Something something() {
		return new Something("sven");
	}

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		int containerPort = 5432 ;
		int localPort = 5432 ;
		var container = new PostgreSQLContainer<>("postgres:latest")
			.withUsername("postgres")
			.withPassword("postgres")
			.withReuse(false)
			.withExposedPorts(5432, 5432)
			.withCreateContainerCmdModifier(
				cmd -> cmd.withHostConfig(
					new HostConfig()
						.withPortBindings(
							new PortBinding(
								Ports.Binding.bindPort(localPort),
								new ExposedPort(containerPort))
						))
			);
				return container;
	}

}
