package br.com.daniel.service;

import br.com.daniel.dao.ServiceRequestDAO;
import br.com.daniel.domain.ServiceRequest;
import org.springframework.stereotype.Service;

@Service
public class ServiceRequestService {
    private final ServiceRequestDAO serviceRequestDAO;

    public ServiceRequestService(final ServiceRequestDAO serviceRequestDAO) {
        this.serviceRequestDAO = serviceRequestDAO;
    }

    public void create(final ServiceRequest serviceRequest) {
        this.serviceRequestDAO.insert(serviceRequest);
    }
}
