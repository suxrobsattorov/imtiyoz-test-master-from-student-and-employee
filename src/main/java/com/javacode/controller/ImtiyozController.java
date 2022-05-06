package com.javacode.controller;

import com.javacode.dto.ImtiyozDTO;
import com.javacode.service.ImtiyozService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imtiyoz")
public class ImtiyozController {

    @Autowired
    private ImtiyozService imtiyozService;

    @PostMapping
    public ResponseEntity<ImtiyozDTO> create(@RequestBody ImtiyozDTO dto) {
        ImtiyozDTO response = imtiyozService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(imtiyozService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(imtiyozService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody ImtiyozDTO dto) {
        imtiyozService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        imtiyozService.delete(id);
        return ResponseEntity.ok().build();
    }
}


//
//        RestTemplate restTemplate = new RestTemplate();
//        String fooResourceUrl
//                = "http://localhost:8080/student";
//        return restTemplate.getForEntity(fooResourceUrl, String.class);
//        WebClient client = WebClient.builder()
//                .baseUrl("http://localhost:8080/student")
//                .defaultCookie("cookieKey", "cookieValue")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080/student"))
//                .build();


//        ResponseEntity<String> response = client.get().retrieve().toEntity(String.class).block();
//}