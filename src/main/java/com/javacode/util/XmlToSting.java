package com.javacode.util;

import com.javacode.dto.EmployeeDTO;

public class XmlToSting {

    public static String getById(EmployeeDTO dto) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.javacode.com\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <int:getEmployeeByIdRequest>\n" +
                "         <int:id>" + dto.getId() + "</int:id>\n" +
                "      </int:getEmployeeByIdRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    public static String add(EmployeeDTO dto) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.javacode.com\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <int:addEmployeeRequest>\n" +
                "         <int:employeeInfo>\n" +
                "            <int:id>" + dto.getId() + "</int:id>\n" +
                "            <int:name>" + dto.getName() + "</int:name>\n" +
                "            <int:department>" + dto.getDepartment() + "</int:department>\n" +
                "            <int:phone>" + dto.getPhone() + "</int:phone>\n" +
                "            <int:address>" + dto.getAddress() + "</int:address>\n" +
                "         </int:employeeInfo>\n" +
                "      </int:addEmployeeRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    public static String update(EmployeeDTO dto) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.javacode.com\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <int:addEmployeeRequest>\n" +
                "         <int:employeeInfo>\n" +
                "            <int:id>" + dto.getId() + "</int:id>\n" +
                "            <int:name>" + dto.getName() + "</int:name>\n" +
                "            <int:department>" + dto.getDepartment() + "</int:department>\n" +
                "            <int:phone>" + dto.getPhone() + "</int:phone>\n" +
                "            <int:address>" + dto.getAddress() + "</int:address>\n" +
                "         </int:employeeInfo>\n" +
                "      </int:addEmployeeRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }

    public static String delete(EmployeeDTO dto) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:int=\"http://interfaces.javacode.com\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <int:deleteEmployeeRequest>\n" +
                "         <int:id>" + dto.getId() + "</int:id>\n" +
                "      </int:deleteEmployeeRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
