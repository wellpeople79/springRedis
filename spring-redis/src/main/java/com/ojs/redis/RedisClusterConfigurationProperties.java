package com.ojs.redis;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="spring.redis.cluster")
public class RedisClusterConfigurationProperties {
    /**
     * spring.redis.cluster.nodes[0]=127.0.0.1:6379
     * spring.redis.cluster.nodes[1]=127.0.0.1:6380
     * spring.redis.cluster.nodes[2]=127.0.0.1:6381
     */
    List<String> nodes;
 
    public List<String> getNodes() {
        return nodes;
    }
 
    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
    
}
