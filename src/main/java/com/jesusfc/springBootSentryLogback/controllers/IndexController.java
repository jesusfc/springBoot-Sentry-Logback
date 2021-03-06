package com.jesusfc.springBootSentryLogback.controllers;

import io.sentry.Sentry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By Jesús Fdez. Caraballo on 2021.
 */
@Slf4j
@Controller
public class IndexController {

    @Value("${app.name}")
    private String appName;

    @Value("${app.environment}")
    private String appEnvironment;

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        try {

            model.addAttribute("appName", appName);
            model.addAttribute("env", appEnvironment);

            log.debug("debug message");
            log.info("info message");
            log.warn("warn message");
            log.error("error message");
            log.trace("trace message");

            String a = null;
            System.out.println(a.length());

        } catch (Exception ex) {
            log.error("Error: " + ex);
           // Sentry.captureException(ex);
        }

        return "index";
    }
}
