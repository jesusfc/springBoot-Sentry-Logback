package com.jesusfc.springBootSentryLogback.config;

import io.sentry.Sentry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created By Jesús Fdez. Caraballo on 2021.
 */

//@Configuration
//@Profile("development")
//@ConditionalOnProperty(value = "sentry.enable")
public class SentryConfiguration {


    @Value("${sentry.dsn}")
    private static String dsn;

    @Value("${sentry.environment}")
    private static String environment;


    @Value("${sentry.release}")
    private String release;

    public SentryConfiguration() {
        Sentry.init(options -> {
            options.setDsn(dsn);
            options.setEnvironment(environment);
            options.setRelease(release);
        });

    }

}