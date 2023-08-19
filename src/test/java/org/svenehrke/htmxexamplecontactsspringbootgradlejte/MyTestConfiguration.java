package org.svenehrke.htmxexamplecontactsspringbootgradlejte;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@Configuration
public class MyTestConfiguration {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		new WaitForPortRelease(5432, 15, 1000).waitUntilReady();

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

	@Bean
	DSLContext jooq(DataSource dataSource) {
		return DSL.using(dataSource, SQLDialect.POSTGRES);
	}

}
