package com.redhat;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestAPI extends RouteBuilder {
	
	@Value("${server.port}")
    String serverPort;
    
    @Value("${baeldung.api.path}")
    String contextPath;

	@Override
	public void configure() throws Exception {
		
		// http://localhost:8080/camel/api-doc
        restConfiguration().contextPath(contextPath) //
            .port(serverPort)
            .enableCORS(true)
            .apiContextPath("/api-doc")
            .apiProperty("api.title", "Test REST API")
            .apiProperty("api.version", "v1")
            .apiProperty("cors", "true") // cross-site
            .apiContextRouteId("doc-api")
            .component("servlet")
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true");
        
        rest("/api/").description("Teste REST Service")
        			 .id("api-route")
        			 
        			 .get("/bean")
        			 	.produces(MediaType.APPLICATION_JSON)
        			 	.enableCORS(true)
        			 	.to("direct:getBeans")
        			 
        			 .get("/bean/protect")
        			 	.produces(MediaType.APPLICATION_JSON)
        			 	.enableCORS(true)
        			 	.to("direct:remoteService");
        
        from("direct:remoteService")
        	.routeId("direct-route")
        	.process(this::transform)
        	.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));
        
        from("direct:getBeans")
        	.routeId("direct-route-beans")
        	.process(e -> {
        		e.getIn().setBody(MyBean.beans());
        	})
        	.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
	}

	private void transform(Exchange exchange) {
		List<MyBean> beans = MyBean.beans();
		ExampleServices.example(beans);
		exchange.getIn().setBody(beans);
	}

}
