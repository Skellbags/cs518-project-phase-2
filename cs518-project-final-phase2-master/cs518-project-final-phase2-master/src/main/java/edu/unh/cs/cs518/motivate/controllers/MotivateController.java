package edu.unh.cs.cs518.motivate.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MotivateController {
    public MotivateController() {
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public Object getQuote(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_OK);
        return "/";
    }
}
