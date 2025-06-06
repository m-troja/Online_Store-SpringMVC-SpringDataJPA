package com.michal.onlinestore.web.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.michal.onlinestore.persistence.entities.User;
import com.michal.onlinestore.core.facades.UserFacade;

@RestController("userRestControllerApi")
public class UserRestController {
	
	@Autowired
	UserFacade userFacade;
	
	// GET all users
	
	@RequestMapping(value = "/v1/users", method = RequestMethod.GET)
	public List<User> getUsers() {
		return userFacade.getUsers();
	}
	
	// GET user by ID
	
	@RequestMapping(value = "v1/users/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable Integer id)
	{
		return userFacade.getUserById(id);
	}
	
	// GET users by first-name
	
	@RequestMapping(value = "/v1/users", method = RequestMethod.GET, params = "first-name")
	public List<User> getUsersByFirstName(@RequestParam("first-name") String firstName)
	{
		return userFacade.findByFirstNameCaseInsensitive(firstName);
	}
	
	@RequestMapping(value = "/v1/users", method = RequestMethod.GET, params = "sort-by")
	public List<User> getAllUsersOrderByFirstName(@RequestParam("sort-by") String sortBy)
	{
		if (sortBy.equals("firstName")) {
			return userFacade.getAllUsersOrderByFirstName();
		}
		else {
			return userFacade.getUsers();
		}
	}
	
	// Request param
	
	@RequestMapping(value = "/v1/request-parameter", method = RequestMethod.POST)
	public String requestParameter(@RequestParam(required = false) Optional<String> param)
	{
		return "Requested param " + param.orElseGet(() -> "Not provided");
	}
	
	
	@RequestMapping(value = "/v1/request-parameter-demo", method = RequestMethod.POST)
	public String requestParameterDemo(@RequestParam(required = false) Optional<String> id) {
		return "Request parameter received: " + id.orElseGet(() -> "not provided");
	}
	
	@RequestMapping(value = "/v1/request-parameters-demo", method = RequestMethod.POST)
	public String requestParametersDemo(@RequestParam Map<String, String> allParams) {
		return "Request parameters received: " + allParams.entrySet();
	}
	
	@RequestMapping(value="/v1/request-form-data-map",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String createRole(@RequestBody MultiValueMap<String, String> formData) {
		formData.entrySet().stream().forEach(System.out::println);
		return "Form data is printed to console";
	}
	
	/*
	 * http://localhost:8080/api-demo?ids=1,2,3
	 * http://localhost:8080/api-demo?ids=1&ids=2
	 */
	@RequestMapping(value = "/v1/request-parameters-list-demo", method = RequestMethod.POST)
	public String requestParametersDemo(@RequestParam List<String> ids) {
		return "Request parameters list received: " + ids;
	}
	
	// ResponseStatus examples
	
	@RequestMapping(value = "/v1/response-status-demo", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.I_AM_A_TEAPOT, reason = "Demo Endpoint Reason")
	public String requestParametersDemo() {
		return "Returning 100 CONTINUE response status";
	}
	
	@RequestMapping(value = "/v1/users/{id}", method = RequestMethod.DELETE)
	public HttpStatus deleteUser(@PathVariable Integer id) {
		userFacade.deleteUser(id);
		return HttpStatus.NO_CONTENT;
	}

	@RequestMapping(value = "/v1/users", method = RequestMethod.POST)
	public HttpStatus insertUser(@RequestBody User user) {
		return userFacade.addUser(user) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
	}

//	@RequestMapping(value = "/v1/users", method = RequestMethod.PUT)
//	public HttpStatus updateUser(@RequestBody User user) {
//		return userFacade.updateUser(user) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
//	}
//	
	
	// Response Entity DEMO
	
	@GetMapping("/v1/response-entity-demo")
	public ResponseEntity<String> responseEntityDemo() {
		/*
		
		BUILDERS: 
			BodyBuilder accepted();
			BodyBuilder badRequest();
			BodyBuilder ok();
			BodyBuilder created(java.net.URI location);
			HeadersBuilder<?> noContent();
			HeadersBuilder<?> notFound();
		*/
		
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Custom-Header", "foo");
	        
	    return new ResponseEntity<>(
	      "Custom response body", headers, HttpStatus.OK);
		
//	    return ResponseEntity.ok()
//	        .header("Custom-Header", "foo")
//	        .body("Custom response body");
	}

	

	// RequestHeader demo
	
	@GetMapping("/v1/request-header-demo")
	public ResponseEntity<String> greeting(@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String language) {
		
		// Insert code here that uses header value
		
	    return new ResponseEntity<String>("Accept-language header value is: " + language, HttpStatus.OK);
	}
	
	@GetMapping("/v1/request-header-all-demo")
	public ResponseEntity<String> listAllHeaders(
	  @RequestHeader Map<String, String> headers) {
	    headers.forEach((key, value) -> {
	    });

	    return new ResponseEntity<String>(
	      String.format("Received %d headers", headers.size()), HttpStatus.OK);
	}
}
