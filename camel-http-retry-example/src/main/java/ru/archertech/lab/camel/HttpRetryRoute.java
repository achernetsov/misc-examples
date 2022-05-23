package ru.archertech.lab.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;

public class HttpRetryRoute extends RouteBuilder {
    @Override
    public void configure() {
        // https://camel.apache.org/manual/faq/how-do-i-retry-processing-a-message-from-a-certain-point-back-or-an-entire-route.html
        from("timer:starter?repeatCount=1")
                .to("direct:call-get")
                .log("${body}")
                .onException(HttpOperationFailedException.class)
                .maximumRedeliveries(2).redeliveryDelay(5000);
    }
}
