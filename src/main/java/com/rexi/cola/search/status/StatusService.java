package com.rexi.cola.search.status;

import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by moi on 05/02/2017.
 */
@Service
public class StatusService {

    public final Client client;

    @Autowired
    public StatusService(Client client){
        this.client = client;
    }

    public void getStatus(){

        boolean isUp =  false ;
    }


}
