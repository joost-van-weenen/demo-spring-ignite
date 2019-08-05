package com.example.demo.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.IgniteSpring;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.TcpDiscoveryIpFinderAdapter;
import org.apache.ignite.spi.discovery.tcp.ipfinder.sharedfs.TcpDiscoverySharedFsIpFinder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
public class IgniteAppConfig {

    @Bean
    IgniteConfiguration createConfig(TcpDiscoveryIpFinderAdapter finderAdapter) throws IOException {
        var config = new IgniteConfiguration();
        var discoSpi = new TcpDiscoverySpi().setIpFinder(finderAdapter);
        config.setDiscoverySpi(discoSpi);

        return config;
    }

    @Bean
    public TcpDiscoverySharedFsIpFinder createIpFinder() throws IOException {
        var path = Path.of("/tmp/ignite12345");
        if (!Files.exists(path)) {
            path = Files.createDirectory(path);
        }
        return new TcpDiscoverySharedFsIpFinder().setPath(path.toAbsolutePath().toString());
    }

    @Bean
    CacheConfiguration<String, String> createCacheConfig() {
        return new CacheConfiguration<>("joost");
    }

    @Lazy
    @Bean(destroyMethod = "close")
    Ignite createIgnite(IgniteConfiguration config, ApplicationContext context) throws IgniteCheckedException {
        return IgniteSpring.start(config, context);
    }
}
