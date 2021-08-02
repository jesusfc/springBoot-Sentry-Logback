package com.jesusfc.springBootSentryLogback.config;

import io.sentry.Sentry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created By Jesús Fdez. Caraballo on 2021.
 */
@Profile("production")
@Configuration
public class SentryConfiguration {

    @Value("${sentry.dsn}")
    private String dsn;

    @Value("${sentry.environment}")
    private String environment;

    @Value("${sentry.release}")
    private String release;

    @Bean
    public void sentryInit() {
        Sentry.init(options -> {
            options.setDsn(dsn);
            options.setEnvironment(environment);
            options.setRelease(release);
        });
    }

}