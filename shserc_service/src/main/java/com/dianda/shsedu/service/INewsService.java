package com.dianda.shsedu.service;

import com.dianda.shsedu.entity.News;

import java.util.List;

public interface INewsService {
	
	List<News> findByPhrase();
	News findById( long id );
}
