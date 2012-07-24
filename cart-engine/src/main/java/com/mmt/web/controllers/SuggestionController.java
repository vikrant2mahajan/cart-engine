package com.mmt.web.controllers;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mmt.data.models.SuggestionRequest;
import com.mmt.services.utils.ISuggestionService;

@Controller
public class SuggestionController {

	@Autowired
	private ISuggestionService suggestionService;

	public ISuggestionService getSuggestionService() {
		return suggestionService;
	}

	public void setSuggestionService(ISuggestionService suggestionService) {
		this.suggestionService = suggestionService;
	}

	@RequestMapping("getSuggestions.htm")
	public ModelAndView getSuggestions(@RequestParam("data") String data) {

		ObjectMapper mapper = new ObjectMapper();
		ModelAndView modelAndView = new ModelAndView("suggestions");

		SuggestionRequest request;
		try {
			request = mapper.readValue(data, SuggestionRequest.class);
			modelAndView.addObject("suggestions",
					suggestionService.getSuggestions(request));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelAndView;
	}
	
	
	@RequestMapping("getFinalReview.htm")
	public ModelAndView getFinalReview() {
		ModelAndView modelAndView = new ModelAndView("final_review");
		return modelAndView;
	}
	
	
	
	

}
