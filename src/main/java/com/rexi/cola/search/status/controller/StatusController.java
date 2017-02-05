package com.rexi.cola.search.status.controller;

import com.rexi.cola.search.status.Status;
import com.rexi.cola.search.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by moi on 05/02/2017.
 */
@RestController
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @RequestMapping(path = "/search/status", method = RequestMethod.GET)
    public Status getStatus() {
        return this.statusService.getStatus();
    }
}
