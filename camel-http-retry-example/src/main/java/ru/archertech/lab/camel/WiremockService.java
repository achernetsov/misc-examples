package ru.archertech.lab.camel;

import io.quarkus.runtime.Startup;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Startup
public class WiremockService {
    @PostConstruct
    public void initWiremock() {
//        WireMock wm = new WireMock(8000);
//
//        // Configure a stub
//        wm.register(get(urlEqualTo("/test"))
//                .willReturn(aResponse().withBody("Hi!")));
    }
}
