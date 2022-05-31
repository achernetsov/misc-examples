package ru.archertech.lab.camel;

import org.apache.camel.builder.RouteBuilder;

public class HttpCallGetRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("direct:call-get")
                .routeId("call-get")
                .log("trying call http")
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        throw new RuntimeException("test");
//                    }
//                })
                .to("http://localhost:8000/thing/1")
                .errorHandler(noErrorHandler())
                .log("${body}");
    }
}
