package com.example.demo;

import com.example.demo.ignite.IgniteFacade;
import org.apache.ignite.cluster.ClusterMetrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class MyController {
    private IgniteFacade igniteFacade;

    public MyController(IgniteFacade igniteFacade) {
        this.igniteFacade = igniteFacade;
    }

    // https://stackoverflow.com/questions/812415/why-is-springs-applicationcontext-getbean-considered-bad
    @GetMapping
    ClusterMetrics load() {
        return igniteFacade.metrics();
    }
}
