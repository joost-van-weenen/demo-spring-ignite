package com.example.demo.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.cluster.ClusterMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class IgniteFacade {

    @Lazy
    @Autowired
    private Ignite ignite = null;

    public ClusterMetrics metrics() {
        return ignite.cluster().metrics();
    }


}
