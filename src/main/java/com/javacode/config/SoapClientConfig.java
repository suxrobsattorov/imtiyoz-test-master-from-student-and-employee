package com.javacode.config;

import com.javacode.service.SOAPRequestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapClientConfig {
    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
//        jaxb2Marshaller.setContextPath("com.javacode.soap");
        jaxb2Marshaller.setPackagesToScan("com.javacode.interfaces");
        return jaxb2Marshaller;
    }

    @Bean
    public SOAPRequestService soapRequestService(Jaxb2Marshaller jaxb2Marshaller) {
        SOAPRequestService articleClient = new SOAPRequestService();
        articleClient.setDefaultUri("http://127.0.0.1:8082/api");
        articleClient.setMarshaller(jaxb2Marshaller);
        articleClient.setUnmarshaller(jaxb2Marshaller);
        return articleClient;
    }
}
