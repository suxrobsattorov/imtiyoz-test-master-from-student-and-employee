package com.javacode.service;

import com.javacode.interfaces.GetEmployeeByIdRequest;
import com.javacode.interfaces.GetEmployeeResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class SOAPRequestService extends WebServiceGatewaySupport {

    public GetEmployeeResponse getEmployeeById(Integer id) {
        GetEmployeeByIdRequest employeeByIdRequest = new GetEmployeeByIdRequest();
        employeeByIdRequest.setId(id);
        return (GetEmployeeResponse) getWebServiceTemplate().marshalSendAndReceive(employeeByIdRequest);
    }
}
