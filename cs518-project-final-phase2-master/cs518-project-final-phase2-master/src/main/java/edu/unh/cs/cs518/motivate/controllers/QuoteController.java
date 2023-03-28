package edu.unh.cs.cs518.motivate.controllers;

import edu.unh.cs.cs518.motivate.services.QuoteService;
import edu.unh.cs.cs518.motivate.models.Quote;
import edu.unh.cs.cs518.motivate.models.Exception;
import edu.unh.cs.cs518.motivate.models.InternalException;
import edu.unh.cs.cs518.motivate.models.InvalidAuthorException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class QuoteController {
	// define variable to store instance of QuoteService
	private final QuoteService quoteService;

	public QuoteController(QuoteService quoteService) {
		// assign passed instance of QuoteService to class variable
		this.quoteService = quoteService;
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/quote", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getQuote(HttpServletRequest request, HttpServletResponse response, Optional<String> author){
		// get random quote from quotes service
		Optional<Quote> quote = quoteService.getQuote();
		String headName = "Access-Control-Allow-Origin";
		String headVal = "*";
		response.setHeader(headName, headVal);
		// send InternalException with status code 500 internal server exception if quote is not present
		if (quote.equals(Optional.empty())) {
			response.setStatus(500);
			return new InternalException();
		}
		if (!author.isPresent()) {
			response.setStatus(200);
			return quote.get();
		} else {
			Optional<Quote> AretVal = quoteService.getQuote(author.get());
			if(AretVal.equals(Optional.empty())){
				// send InvalidAuthorException with status code 400 if quote with specified author is not present
				response.setStatus(400);
				return new InvalidAuthorException();
			} else {
				response.setStatus(200);
				return AretVal.get();
			}
		}
	}
}

