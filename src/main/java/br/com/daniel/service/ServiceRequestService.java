package br.com.daniel.service;

import br.com.daniel.dao.ServiceRequestDAO;
import br.com.daniel.domain.ServiceRequest;
import br.com.daniel.model.Response;
import br.com.daniel.security.domain.UserPrincipal;
import br.com.daniel.security.permissions.ViewRoles;
import br.com.daniel.utils.Principal;
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

    public Response<ServiceRequest> findAll(
            final int page,
            final int size,
            final String status
    ) {
        if (ViewRoles.isAdmin())
            return this.serviceRequestDAO.findAll(page, size, status);

        UserPrincipal loggedUser = Principal.extract();

        if (ViewRoles.isHelper())
            return this.serviceRequestDAO.findAllAnalyzedBy(page, size, status, loggedUser.getId());

        return this.serviceRequestDAO.findAllFromUser(page, size, status, loggedUser.getId());
    }
}
