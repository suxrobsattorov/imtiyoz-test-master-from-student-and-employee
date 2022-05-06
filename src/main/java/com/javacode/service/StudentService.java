package com.javacode.service;

import com.javacode.dto.StudentDTO;
import com.javacode.dto.TokenDTO;
import com.javacode.exception.ClientDataException;
import com.javacode.exception.StudentServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

import static com.javacode.constants.StudentConstants.*;

@Slf4j
@Service
//@RequiredArgsConstructor
public class StudentService {

    @Value("${app.username}")
    private String username;

    @Value("${app.password}")
    private String password;

    private WebClient webClient;

    @Autowired
    public StudentService(@Qualifier("webClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<RuntimeException> handle4xxErrorResponse(ClientResponse clientResponse) {
        Mono<String> errorResponse = clientResponse.bodyToMono(String.class);
        return errorResponse.flatMap((message) -> {
            log.error("ErrorResponse Code is " + clientResponse.rawStatusCode() + " and the exception message is : " + message);
            throw new ClientDataException(message);
        });
    }

    public Mono<StudentServiceException> handle5xxErrorResponse(ClientResponse clientResponse) {
        Mono<String> errorResponse = clientResponse.bodyToMono(String.class);
        return errorResponse.flatMap((message) -> {
            log.error("ErrorResponse Code is " + clientResponse.rawStatusCode() + " and the exception message is : " + message);
            throw new StudentServiceException(message);
        });
    }

    public TokenDTO getToken() {
        try {
            TokenDTO token = WebClient.builder()
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .build()
                    .post()
                    .uri(TOKEN_BY_STUDENT)
                    .body(BodyInserters.fromFormData("username", username)
                            .with("password", password))
                    .retrieve()
                    .bodyToMono(TokenDTO.class)
                    .block();
            assert token != null;
            TOKEN_DTO.setAccess_token(token.getAccess_token());
            TOKEN_DTO.setRefresh_token(token.getRefresh_token());
            return token;
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in getToken", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in getToken ", ex);
            throw ex;
        }
    }

    public List<StudentDTO> getAllStudents() {
        getToken();
        Mono<List<StudentDTO>> dtoList;
        try {
            dtoList = webClient.get().uri(GET_ALL_STUDENT_V1)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN_DTO.getAccess_token())
                    .retrieve()
                    .bodyToFlux(StudentDTO.class)
                    .collectList();
            for (StudentDTO dto : Objects.requireNonNull(dtoList.block())) {
                if (dto.getUsername() != null) {
                    return dtoList.block();
                }
            }
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in getAllStudents", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in getAllStudents ", ex);
            throw ex;
        }
        return null;
    }

    public StudentDTO getStudentById(int studentId) {
        getToken();
        try {
            StudentDTO dto = webClient.get().uri(STUDENT_BY_ID_V1, studentId)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN_DTO.getAccess_token())
                    .retrieve()
                    .bodyToMono(StudentDTO.class)
                    .block();
            assert dto != null;
            if (dto.getUsername() != null) {
                return dto;
            }
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in getStudentById", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in getStudentById ", ex);
            throw ex;
        }
        return null;
    }

    public StudentDTO getStudentById_Custom_Error_Handling(int studentId) {
        getToken();
        return webClient.get().uri(STUDENT_BY_ID_V1, studentId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN_DTO.getAccess_token())
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> handle4xxErrorResponse(clientResponse))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> handle5xxErrorResponse(clientResponse))
                .bodyToMono(StudentDTO.class)
                .block();
    }

    public StudentDTO addNewStudent(StudentDTO student) {
        getToken();
        try {
            return webClient.post().uri(POST_STUDENT_V1)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN_DTO.getAccess_token())
                    .bodyValue(student)
                    .retrieve()
                    .bodyToMono(StudentDTO.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in addNewStudent", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in addNewStudent ", ex);
            throw ex;
        }
    }

    public StudentDTO addNewStudent_custom_Error_Handling(StudentDTO student) {
        getToken();
        return webClient.post().uri(POST_STUDENT_V1)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN_DTO.getAccess_token())
                .bodyValue(student)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> handle4xxErrorResponse(clientResponse))
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> handle5xxErrorResponse(clientResponse))
                .bodyToMono(StudentDTO.class)
                .block();
    }

    public StudentDTO updateStudentById(int id, StudentDTO student) {
        getToken();
        try {
            return webClient.put().uri(STUDENT_BY_ID_V1, id)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN_DTO.getAccess_token())
                    .bodyValue(student)
                    .retrieve()
                    .bodyToMono(StudentDTO.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in updateStudentById", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in updateStudentById ", ex);
            throw ex;
        }
    }

    public String deleteStudentById(int id) {
        getToken();
        try {
            return webClient.delete().uri(STUDENT_BY_ID_V1, id)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN_DTO.getAccess_token())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error Response code is : {} and the message is {}", ex.getRawStatusCode(), ex.getResponseBodyAsString());
            log.error("WebClientResponseException in deleteStudentById", ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Exception in deleteStudentById ", ex);
            throw ex;
        }

    }
}
