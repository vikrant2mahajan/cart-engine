package com.mmt.services.utils;

import java.util.List;

import com.mmt.search.RequestHolder;

public interface ISuggestionService {

	public List<Suggestion> getSuggestions(RequestHolder request);
}
