package com.javacode.service;

import com.javacode.dto.EmployeeDTO;
import com.javacode.util.XmlToSting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static com.javacode.constants.StudentConstants.EMPLOYEE_API;

@Slf4j
@Service
public class EmployeeService {

    private final WebClient webClient;

    @Autowired
    public EmployeeService(@Qualifier("webClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public String getEmployeeById(EmployeeDTO dto) throws JAXBException, IOException {
        try {
            /*JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeXML.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            FileReader fileReader = new FileReader("src//main//resources//employee.xml");
            marshaller.marshal(s, file);
            System.out.println(s+"***\n");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            String s1 = "";
            while (line != null) {
                System.out.println(line);
                s1 += line + "\n";
                line = bufferedReader.readLine();
            }
            System.out.println("***\n" + s1);
            //JAXBContext jaxbContext = JAXBContext.newInstance(GetEmployeeByIdRequest.class);



           /* String result = webClient.post().uri(EMPLOYEE_API)
                    .accept(MediaType.TEXT_XML)
                    .contentType(MediaType.TEXT_XML)
                    .bodyValue(s)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            System.out.println(result);

//            GetEmployeeResponse getEmployeeResponse = soapRequestService.getEmployeeById(1534);
//            System.out.println(getEmployeeResponse);
//            return getEmployeeResponse.toString();

          /*  GetEmployeeByIdRequest employeeByIdRequest = new GetEmployeeByIdRequest();
            employeeByIdRequest.setId(1534);
            JAXBContext context = JAXBContext.newInstance(GetEmployeeByIdRequest.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter out = new StringWriter();
            marshaller.marshal(employeeByIdRequest, out);
            String xml = out.toString();
            System.out.println(xml);


            JAXBContext jaxbContext = JAXBContext.newInstance(GetEmployeeResponse.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            GetEmployeeResponse employee = (GetEmployeeResponse) jaxbUnmarshaller.unmarshal(new StringReader(xmlresult));
            System.out.println(employee);*/

            String xml = XmlToSting.getById(dto);

            return webClient.post().uri(EMPLOYEE_API)
                    .accept(MediaType.TEXT_XML)
                    .contentType(MediaType.TEXT_XML)
                    .bodyValue(xml)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in getEmployeeById", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in getEmployeeById ", ex);
            throw ex;
        }
    }

    public String addNewEmployee(EmployeeDTO employee) {
        try {
            String xml = XmlToSting.add(employee);

            return webClient.post().uri(EMPLOYEE_API)
                    .contentType(MediaType.TEXT_XML)
                    .bodyValue(xml)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in addNewEmployee", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in addNewEmployee ", ex);
            throw ex;
        }
    }

    public String updateEmployeeById(EmployeeDTO employee) {
        try {
            String xml = XmlToSting.update(employee);

            return webClient.post().uri(EMPLOYEE_API)
                    .contentType(MediaType.TEXT_XML)
                    .bodyValue(xml)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in updateEmployeeById", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in updateEmployeeById ", ex);
            throw ex;
        }
    }

    public String deleteEmployeeById(EmployeeDTO dto) {
        try {
            String xml = XmlToSting.delete(dto);

            return webClient.post().uri(EMPLOYEE_API)
                    .contentType(MediaType.TEXT_XML)
                    .bodyValue(xml)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in deleteEmployeeById", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in deleteEmployeeById ", ex);
            throw ex;
        }

    }
}
