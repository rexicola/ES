package com.rexi.cola.es.index.service;

import com.carrotsearch.hppc.cursors.ObjectCursor;
import com.rexi.cola.es.index.model.Index;
import com.rexi.cola.es.index.model.IndexConfig;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequestBuilder;
import org.elasticsearch.action.admin.indices.alias.exists.AliasesExistResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsRequestBuilder;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moi on 05/02/2017.
 */
@Service
public class IndexManager {

	private static final Logger logger = LoggerFactory
			.getLogger(IndexManager.class);

	private final Client client;

	@Autowired
	public IndexManager(Client client){
		this.client=client;
	}

	public Index getCurrentIndex(String indexAlias) {
		Index index                   = new Index(indexAlias);
		AliasesExistResponse response = this.client.admin().indices().prepareAliasesExist(indexAlias).get();

		if (response.isExists() == false) {
			index = null;
		}

		return index;
	}

	public Index createIndex(String indexAlias, IndexConfig indexConfig) {
		String indexName = this.getNewIndexName(indexAlias);
		CreateIndexRequestBuilder request = this.client.admin().indices().prepareCreate(indexName);

		for (String type: indexConfig.getIndexTypes()) {
			request.addMapping(type, indexConfig.getIndexMapping(type));
		}

		request.setSettings(indexConfig.getIndexSettings());
		//TODO adding response handling
		request.get();

		return new Index(indexName);
	}

	private String getNewIndexName(String indexAlias) {
		return String.format("%s-%d", indexAlias, System.currentTimeMillis());
	}

	public Index installIndex(Index index, String indexAlias) {
		List<String> indicesToDelete                     = this.getIndicesToDelete(indexAlias);
		IndicesAliasesRequestBuilder aliasRequestBuilder = this.client.admin().indices().prepareAliases();

		aliasRequestBuilder.addAlias(index.getIndexAliasName(), indexAlias);

		//removal from the alias
		for (String oldIndexName : indicesToDelete) {
			logger.info(">>>I should remove index " + oldIndexName + " from alias " + indexAlias);
			aliasRequestBuilder.removeAlias(oldIndexName, indexAlias);
		}

		aliasRequestBuilder.get();


		//removal from the ES engine
		if (indicesToDelete.size() > 0) {
			logger.info(">>>wipe index " + indicesToDelete.toString() + " !!");
			this.client.admin().indices().prepareDelete(indicesToDelete.toArray(new String[0])).get();
		}

		return index;
	}

	private List<String> getIndicesToDelete(String indexAlias)
	{
		List <String> deletedIndices = new ArrayList<String>();

		try {
			GetSettingsRequestBuilder indicesRequest = this.client.admin().indices().prepareGetSettings(indexAlias);
			for (ObjectCursor<String> indexName : indicesRequest.get().getIndexToSettings().keys()) {
				deletedIndices.add(indexName.value);
			}
		} catch (org.elasticsearch.index.IndexNotFoundException e) {
		}

		return deletedIndices;
	}

}
