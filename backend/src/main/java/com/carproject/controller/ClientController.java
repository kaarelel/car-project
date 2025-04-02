package com.carproject.controller;

import com.carproject.domain.ClientSubmission;
import com.carproject.repo.ClientRepository;
import com.carproject.request.ClientRequest;
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
    public void submitClientInfo(@RequestBody ClientRequest request) {
        ClientSubmission submission = new ClientSubmission();
        submission.setFullName(request.getFullName());
        submission.setPhoneNumber(request.getPhoneNumber());
        submission.setSelectedBrand(request.getSelectedBrand());
        submission.setSelectedModel(request.getSelectedModel());
        clientRepository.save(submission);
    }

    @GetMapping("/submissions")
    public List<ClientSubmission> getAllSubmissions() {
        return clientRepository.findAll();
    }
}