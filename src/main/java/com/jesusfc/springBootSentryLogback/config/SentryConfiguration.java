package com.jesusfc.springBootSentryLogback.config;

import io.sentry.Sentry;
import io.sentry.SentryClient;
import io.sentry.spring.SentryExceptionResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.PostConstruct;

/**
 * Created By Jesús Fdez. Caraballo on 2021.
 */

@Configuration
@ConditionalOnProperty(value = "sentry.enable")
public class SentryConfiguration {

    @Value("${sentry.dsn}")
    String dsn;

    @Value("${sentry.environment}")
    String environment;

    @Value("${sentry.release}")
    String release;

    public SentryConfiguration(){
        sentryClient = Sentry.init(dsn);
        sentryClient.setEnvironment(environment);
        sentryClient.setRelease(release);
    }

    @Bean
    @ConditionalOnMissingBean(SentryExceptionResolver.class)
    public HandlerExceptionResolver sentryExceptionResolver() {
        return new SentryExceptionResolver();
    }

    @Bean
    @ConditionalOnMissingBean(SentryServletContextInitializer.class)
    public ServletContextInitializer sentryServletContextInitializer() {
        return new SentryServletContextInitializer();
    }
