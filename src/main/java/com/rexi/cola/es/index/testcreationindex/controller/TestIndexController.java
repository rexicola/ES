package com.rexi.cola.es.index.testcreationindex.controller;

import com.rexi.cola.es.index.model.Index;
import com.rexi.cola.es.index.model.IndexConfig;
import com.rexi.cola.es.index.service.IndexManager;
import com.rexi.cola.es.index.testcreationindex.component.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by moi on 05/02/2017.
 */
@RestController
public class TestIndexController {

	private final IndexManager indexManager;
	private final IndexConfig indexConfig;


	@Autowired
	public TestIndexController(IndexManager indexManager, IndexConfig indexConfig){
		this.indexManager=indexManager;
		this.indexConfig=new TestConfig();
	}

	@RequestMapping(path = "/testcreate",method= RequestMethod.GET)
	public Index testCreateIndex() {
		Index index = this.indexManager.createIndex(indexConfig.getIndexAlias(), indexConfig);

		return this.indexManager.installIndex(index, indexConfig.getIndexAlias());
	}

//TODO add erase all indices

}
