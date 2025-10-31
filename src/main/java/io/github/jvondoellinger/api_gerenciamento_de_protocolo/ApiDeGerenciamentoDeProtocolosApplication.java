package io.github.jvondoellinger.api_gerenciamento_de_protocolo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableCassandraRepositories(basePackages = "io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.Infrastructure.database")
public class ApiDeGerenciamentoDeProtocolosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDeGerenciamentoDeProtocolosApplication.class, args);
	}

}
