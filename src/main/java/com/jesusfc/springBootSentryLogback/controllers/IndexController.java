package com.jesusfc.springBootSentryLogback.controllers;

import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By Jesús Fdez. Caraballo on 2021.
 */
@Slf4j
@Controller
public class IndexController {


    @Value("${sentry.dsn}")
    private String dsn;

    @Value("${app.name}")
    private String appName;

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        try {
            System.out.println(appName);
            System.out.println(dsn);
            String a = null;
            System.out.println(a.length());
        } catch (Exception ex) {
            log.error("Error: " + ex);
            Sentry.captureException(ex);
        }

        return "index";
    }
}
