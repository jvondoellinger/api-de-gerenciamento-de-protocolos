package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.database.startup;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.boot.CommandLineRunner;

public class CassandraLoggerRunner implements CommandLineRunner {
    private final CqlSession session;

    public CassandraLoggerRunner(CqlSession session) {
        this.session = session;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Conectado ao cluster: %s".formatted(session.getMetadata().getClusterName()));

    }
}
