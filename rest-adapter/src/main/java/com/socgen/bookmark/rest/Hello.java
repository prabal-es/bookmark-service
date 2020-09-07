package com.socgen.bookmark.rest;

/*import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

//@EnableOAuth2Sso
@RestController
@RequestMapping("/api/v1/hello")
public class Hello {

	
	@GetMapping
	@Operation(description = "This is description", summary = "This is summary", tags = { "testTag" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The request has succeeded or (your message)"),
			@ApiResponse(responseCode = "401", description = "The request requires user authentication or (your message)"),
			@ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden or (your message)"),
			@ApiResponse(responseCode = "404", description = "The server has not found anything matching the Request-URI or (your message)") })
	public String hello(@RequestParam(defaultValue = "world!!", required = false) @Parameter(description = "this is description") String test) {
		return "hello "+test;
	}
	
	

	/*@RequestMapping(method = { RequestMethod.GET}, value = "/me")
	public Map<String, Object> userInfo(OAuth2Authentication auth){
	    final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
	    //token
	    String accessToken = details.getTokenValue();
	    //reference
	    //final OAuth2AccessToken accessToken1 = tokenStore.readAccessToken(details.getTokenValue());
	   // clientid
	    String clientId = auth.getOAuth2Request().getClientId();
	    Map<String, Object> test = new HashMap<>();
	    test.put("clientId", clientId);
	    //test.put("accessToken1", accessToken1);
	    test.put("Authorization", "Bearer "+accessToken);
	    test.put("url", "https://api.github.com/user ");
	    return test;
	}*/

}
