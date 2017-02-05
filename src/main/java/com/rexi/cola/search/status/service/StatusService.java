package com.rexi.cola.search.status.service;

import com.rexi.cola.search.status.model.Status;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by moi on 05/02/2017.
 */
@Service
public class StatusService {

    public final Client client;

    @Autowired
    public StatusService(Client client) {
        this.client = client;
    }

    public Status getStatus() {

        boolean isUp   = false;
        String code    = "green";
        String message = null;

        try {
            ClusterHealthStatus status = this.client.admin().cluster().prepareHealth().get().getStatus();
            isUp  = status.equals(ClusterHealthStatus.RED) == false;
            if (isUp && status.equals(ClusterHealthStatus.YELLOW)) {
                code = "yellow";
            } else if (isUp == false) {
                code = "red";
            }
        } catch (Exception e) {
            code = "red";
            message = e.getMessage();
        }

        return new Status(isUp, code, message);
    }
}
