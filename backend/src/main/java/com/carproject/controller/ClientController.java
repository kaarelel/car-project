package com.carproject.controller;

import com.carproject.domain.ClientSubmission;
import com.carproject.repo.ClientRepository;
import com.carproject.request.ClientRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping("/submit")
    public ResponseEntity<Integer> submitClientInfo(@RequestBody ClientRequest request) {
        ClientSubmission submission = new ClientSubmission();
        submission.setFullName(request.getFullName());
        submission.setPhoneNumber(request.getPhoneNumber());
        submission.setSelectedBrand(request.getSelectedBrand());
        submission.setSelectedModel(request.getSelectedModel());
        submission.setHasDriverLicense(request.isHasDriverLicense());
        ClientSubmission saved = clientRepository.save(submission);
        return ResponseEntity.ok(saved.getId());
    }

    @GetMapping("/submission/{id}")
    public ResponseEntity<ClientSubmission> getOneSubmission(@PathVariable Integer id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/submissions")
    public List<ClientSubmission> getAllSubmissions() {
        return clientRepository.findAll();
    }
}