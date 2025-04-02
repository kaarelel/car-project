package com.carproject.controller;

import com.carproject.domain.ClientSubmission;
import com.carproject.repo.ClientRepository;
import com.carproject.request.ClientRequest;
import com.carproject.logging.AuditLogger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

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

        AuditLogger.log("New submission saved: " +
                "Name=" + request.getFullName() +
                ", Phone=" + request.getPhoneNumber() +
                ", Brand=" + request.getSelectedBrand() +
                ", Model=" + request.getSelectedModel() +
                ", License=" + request.isHasDriverLicense());

        log.info("Client info saved, ID={}", saved.getId());

        return ResponseEntity.ok(saved.getId());
    }

    @GetMapping("/submission/{id}")
    public ResponseEntity<ClientSubmission> getOneSubmission(@PathVariable Integer id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/my-submission")
    public ResponseEntity<ClientSubmission> getMySubmission(HttpServletRequest httpRequest) {
        Object submissionId = httpRequest.getSession().getAttribute("submissionId");

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
}
