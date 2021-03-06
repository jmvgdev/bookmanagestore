package dev.jmvg.bookstoremanager.custoninfoendpoint;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class InfoWebPointExtension {

    private final InfoEndpoint delegate;

    public InfoWebPointExtension(InfoEndpoint delegate) {
        this.delegate = delegate;
    }

    @ReadOperation
    public WebEndpointResponse<Map> info(){
        Map<String, Object> info = this.delegate.info();
        Integer status = getStatus(info);
        Map<String, Object> customInfo = new HashMap<>(info);
        customInfo.put("customInfo", "handOn");
        return new WebEndpointResponse<>(customInfo, status);
    }

    private Integer getStatus(Map<String, Object> info) {
        return 200;
    }


}
