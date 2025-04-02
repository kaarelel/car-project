package com.carproject.controller;

import com.carproject.domain.ClientSubmission;
import com.carproject.repo.ClientRepository;
import com.carproject.request.ClientRequest;
import com.carproject.logging.AuditLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClientControllerTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private AuditLogger auditLogger;

    @Mock
    private HttpServletRequest httpRequest;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSubmitClientInfo_shouldReturnSavedId() {
        ClientRequest request = new ClientRequest();
        request.setFullName("John Doe");
        request.setPhoneNumber("12345678");
        request.setSelectedBrand("BMW");
        request.setSelectedModel("316");
        request.setHasDriverLicense(true);

        ClientSubmission savedSubmission = new ClientSubmission();
        savedSubmission.setId(1);

        when(httpRequest.getSession()).thenReturn(httpSession);
        when(clientRepository.save(any(ClientSubmission.class))).thenReturn(savedSubmission);

        ResponseEntity<Integer> response = clientController.submitClientInfo(request, httpRequest);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody());

        verify(httpSession).setAttribute(eq("submissionId"), eq(1));
        AuditLogger.log(anyString());
    }
}
