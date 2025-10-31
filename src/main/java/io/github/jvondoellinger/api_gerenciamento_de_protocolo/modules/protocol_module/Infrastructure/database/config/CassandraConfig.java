package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.Infrastructure.database.config;

import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CassandraConfig {
    private String localDatacenter;

    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer() {
        return builder -> builder.withLocalDatacenter("datacenter1");
    }

    public String getLocalDatacenter() {
        return localDatacenter;
    }

    public void setLocalDatacenter(String local_datacenter) {
        this.localDatacenter = local_datacenter;
    }
}
