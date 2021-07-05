package com.jesusfc.springBootSentryLogback.controllers;

import io.sentry.Sentry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By Jesús Fdez. Caraballo on 2021.
 */
@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){
        try {
            String a = null;
            System.out.println(a.length());
        } catch (Exception ex) {
            Sentry.captureException(ex);
        }

        return "index";
    }
}
