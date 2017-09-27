package es.udc.rs.deliveries.jaxrs.filter;

import java.io.IOException;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

@Priority(0)
@PreMatching
@Provider
public class TrafficLoggerFilter implements ContainerRequestFilter,
		ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext reqContext,
			ContainerResponseContext respContext) throws IOException {
		logResponse(reqContext, respContext);
	}

	@Override
	public void filter(ContainerRequestContext reqContext) throws IOException {
		logRequest(reqContext);		
	}

    private static void logRequest(ContainerRequestContext reqContext) {
        UriInfo uriInfo = reqContext.getUriInfo();
        String method = reqContext.getMethod();
        MultivaluedMap<String,String> headers = reqContext.getHeaders();
        List<Object> matchedResources = uriInfo.getMatchedResources();
        System.out.println("\n* REQUEST: " + method + " " + uriInfo.getRequestUri() + 
                           "\n  Headers: " + headers +
                           "\n  Matched Resources: " + matchedResources);        
    }

    private static void logResponse(ContainerRequestContext reqContext, ContainerResponseContext respContext) {
        int status = respContext.getStatus();
    	MultivaluedMap<String, String> stringHeaders = respContext.getStringHeaders();
        UriInfo uriInfo = reqContext.getUriInfo();
        String method = reqContext.getMethod();
        System.out.println("\n* RESPONSE TO: " + method + " " + uriInfo.getRequestUri() + 
                "\n  Status: " + status + 
                "\n  Headers: " + stringHeaders);        
        
    }
}
