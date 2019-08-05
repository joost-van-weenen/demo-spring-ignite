package com.example.demo.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.cluster.ClusterMetrics;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class IgniteFacade {

    private Ignite ignite = null;

    private final Object IgniteLock = new Object();

    private final ApplicationContext context;

    public IgniteFacade(ApplicationContext context) {
        this.context = context;
    }

    public ClusterMetrics metrics() {
        return lazyIgnite().cluster().metrics();
    }

    private Ignite lazyIgnite() {
        if (ignite == null) {
            synchronized (IgniteLock) {
                if (ignite == null) {
                    ignite = context.getBean(Ignite.class);
                }
            }
        }
        return ignite;
    }


}
