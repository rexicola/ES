package com.rexi.cola.es.client.factory;

import com.rexi.cola.es.client.component.ClientConfig;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by moi on 05/02/2017.
 */
@Component
public class ClientFactory {

	private static final Logger logger = LoggerFactory
			.getLogger(ClientFactory.class);

	private final Client client;

	@Autowired
	public ClientFactory(ClientConfig clientConfig) throws UnknownHostException {
		this.client = initClient(clientConfig);

	}

	private Client initClient(ClientConfig clientConfig) throws UnknownHostException {

		logger.info("Retrieving setting from ClientConfig...");
		Settings settings = clientConfig.getSettings();
		logger.info("Retrieving hosts from ClientConfig...");
		InetSocketTransportAddress[] hosts = clientConfig.getHosts();

		logger.info("User management view controller loaded...");
		PreBuiltTransportClient client = new PreBuiltTransportClient(settings);


		/*Settings.Builder settingsBuilder = Settings.builder();
		settingsBuilder.put("cluster.name","clusterNAAAME");
		Settings settings = settingsBuilder.build();
		PreBuiltTransportClient client = new PreBuiltTransportClient(settings);*/
		InetAddress address = InetAddress.getByName("localhost");
		client.addTransportAddresses(hosts);
		return client;
	}

	@Bean
	public Client getClient()
	{
		return this.client;
		//return null;
	}

}
