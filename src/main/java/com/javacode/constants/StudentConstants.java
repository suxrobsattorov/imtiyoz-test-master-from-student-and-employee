package com.javacode.constants;

import com.javacode.dto.TokenDTO;

public class StudentConstants {

    public static final String GET_ALL_STUDENT_V1 = "http://127.0.0.1:8081/api/v1/student";
    public static final String STUDENT_BY_ID_V1 = "http://127.0.0.1:8081/api/v1/student/{id}";
    public static final String GET_GROUP_BY_ID_V1 = "http://127.0.0.1:8081/api/v1/student/groupId/{groupId}";
    public static final String POST_STUDENT_V1 = "http://127.0.0.1:8081/api/v1/student/public";
    public static final String POST_ROLE_V1 = "http://127.0.0.1:8081/api/v1/student/role/save";
    public static final String POST_ROLE_TO_STUDENT_V1 = "http://127.0.0.1:8081/api/v1/student/role/addtouser";
    public static final String TOKEN_BY_STUDENT = "http://127.0.0.1:8081/api/v1/student/login";



    public static final String EMPLOYEE_API = "http://127.0.0.1:8082/api";
//    public static final String EMPLOYEE_API = "http://127.0.0.1:8082/api";


    public static final TokenDTO TOKEN_DTO=new TokenDTO();

}
