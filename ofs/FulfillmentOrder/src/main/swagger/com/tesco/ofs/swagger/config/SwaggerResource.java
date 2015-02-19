
package com.tesco.ofs.swagger.config;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/swagger")
@Produces(MediaType.TEXT_HTML)
public class SwaggerResource {

    private final String applicationContextPath;

    public SwaggerResource(String applicationContextPath) {
        this.applicationContextPath = applicationContextPath;
    }

    @GET
    public SwaggerView get() {
        return new SwaggerView(applicationContextPath);
    }
}
