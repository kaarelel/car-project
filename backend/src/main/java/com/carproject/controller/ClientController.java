package com.carproject.controller;

import com.carproject.domain.ClientSubmission;
import com.carproject.repo.ClientRepository;
import com.carproject.request.ClientRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> submitClientInfo(@Valid @RequestBody ClientRequest request, HttpServletRequest httpRequest) {
        ClientSubmission submission = new ClientSubmission();
        submission.setFullName(request.getFullName());
        submission.setPhoneNumber(request.getPhoneNumber());
        submission.setSelectedBrand(request.getSelectedBrand());
        submission.setSelectedModel(request.getSelectedModel());
        submission.setHasDriverLicense(request.isHasDriverLicense());

        ClientSubmission saved = clientRepository.save(submission);

        httpRequest.getSession().setAttribute("submissionId", saved.getId());

        return ResponseEntity.ok(saved.getId());
    }

    @GetMapping("/submission/{id}")
    public ResponseEntity<ClientSubmission> getOneSubmission(@PathVariable Integer id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/my-submission")
    public ResponseEntity<ClientSubmission> getMySubmission(HttpServletRequest request) {
        Object submissionId = request.getSession().getAttribute("submissionId");

        if (submissionId instanceof Integer id) {
            return clientRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/submissions")
    public List<ClientSubmission> getAllSubmissions() {
        return clientRepository.findAll();
    }

    @RestControllerAdvice
    public static class ValidationExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<List<String>> handleValidation(MethodArgumentNotValidException ex) {
            List<String> errors = ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> resetSession(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok().build();
    }

}