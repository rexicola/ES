package com.rexi.cola.es.client.factory;

import com.rexi.cola.es.client.component.ClientConfig;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by moi on 05/02/2017.
 */
@Component
public class ClientFactory {

	//private final Client client;

	@Autowired
	public ClientFactory(ClientConfig clientConfig) {

	}

	private void initClient(ClientConfig clientConfig){

		Settings settings = clientConfig.getSettings();
		InetSocketTransportAddress[] hosts = clientConfig.getHosts();

		//PreBuiltTransportClient client = new PreBuiltTransportClient(settings);

	}

}
