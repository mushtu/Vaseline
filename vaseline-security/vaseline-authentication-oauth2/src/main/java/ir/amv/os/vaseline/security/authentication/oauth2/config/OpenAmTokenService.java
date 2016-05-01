package ir.amv.os.vaseline.security.authentication.oauth2.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

/**
 * @author mushtu
 * @since 4/30/16.
 */
public class OpenAmTokenService implements ResourceServerTokenServices {

    private final String OPENAM_BASE_URI ;
    private final String OPENAM_TOKENINFO = "oauth2/tokeninfo?access_token=";


    private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
    private RestTemplate restTemplate = new RestTemplate();

    public OpenAmTokenService(String openAmBaseUri)
    {
        if(!openAmBaseUri.endsWith("/"))
            OPENAM_BASE_URI = openAmBaseUri + "/";
        else
            OPENAM_BASE_URI = openAmBaseUri;
        ((RestTemplate) restTemplate).setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            // Ignore 401
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });
    }



    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        HttpHeaders headers = new HttpHeaders();
        //headers.set("Authorization", getAuthorizationHeader(clientId, clientSecret));
        Map<String, Object> map =  restTemplate.exchange(OPENAM_BASE_URI+OPENAM_TOKENINFO + accessToken, HttpMethod.GET,
                new HttpEntity<MultiValueMap<String, String>>(null, headers), Map.class).getBody();

        if (map.containsKey("error")) {
            System.out.println("check_token returned error: " + map.get("error"));
            throw new InvalidTokenException(accessToken);
        }
        for (Map.Entry<String,Object> entry : map.entrySet())
        {
            System.out.println(entry.getKey() +":" + entry.getValue());
        }
        Assert.state(map.containsKey("client_id"), "Client id must be present in response from auth server");
        OAuth2Authentication authentication = tokenConverter.extractAuthentication(map);
        return authentication;
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        throw new UnsupportedOperationException("Not supported: read access token");
    }

    public void setTokenConverter(AccessTokenConverter tokenConverter) {
        this.tokenConverter = tokenConverter;
    }
}
