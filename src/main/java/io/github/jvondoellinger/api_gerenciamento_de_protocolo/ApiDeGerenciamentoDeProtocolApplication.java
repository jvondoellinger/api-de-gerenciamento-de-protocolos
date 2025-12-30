package io.github.jvondoellinger.api_gerenciamento_de_protocolo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@SpringBootApplication
@EnableReactiveCassandraRepositories
@EnableConfigurationProperties
public class ApiDeGerenciamentoDeProtocolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDeGerenciamentoDeProtocolApplication.class, args);
	}

}
