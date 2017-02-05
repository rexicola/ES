package com.rexi.cola.es.client.component;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by moi on 05/02/2017.
 */
@Component
public class ClientConfig {

	private final String clusterName;
	private final InetSocketTransportAddress[] hosts;

	public ClientConfig(
			@Value("#{'${es.hosts}'.split(',')}") String[] hosts,
			@Value("${es.port:9300}") int port,
			@Value("${es.clustername:clusterDefaultName}") String clusterName
	) throws UnknownHostException {

		this.hosts = this.initHosts(hosts,port);
		this.clusterName = "elasticsearch";
	}

	private InetSocketTransportAddress[] initHosts(String[] hosts,int port) throws UnknownHostException {

		List<InetSocketTransportAddress> addresses = new ArrayList<>();
		/*
		for (int i = 0 ; i < hosts.length ; i++)
		{
			InetAddress address = InetAddress.getByName(hosts[i]);
			addresses.add(new InetSocketTransportAddress(address, port));
		}
		*/
		InetAddress address = InetAddress.getByName("localhost");
		addresses.add(new InetSocketTransportAddress(address, 9300));

		InetSocketTransportAddress[] inetSocketTransportAddressesArray = new InetSocketTransportAddress[addresses.size()];
		return addresses.toArray(inetSocketTransportAddressesArray);
	}

    public Settings getSetting(){
		Settings.Builder settingsBuilder = Settings.builder();
		settingsBuilder.put("cluster.name",this.clusterName);
		return settingsBuilder.build();
	}


}
