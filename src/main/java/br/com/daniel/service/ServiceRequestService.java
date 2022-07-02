package br.com.daniel.service;

import br.com.daniel.dao.ServiceRequestDAO;
import br.com.daniel.domain.ServiceRequest;
import br.com.daniel.exception.RequestNotFoundException;
import br.com.daniel.model.Response;
import br.com.daniel.model.decorator.ServiceRequestWithUsersData;
import br.com.daniel.model.decorator.ServiceRequestWithUsersDataAndComments;
import br.com.daniel.security.domain.UserPrincipal;
import br.com.daniel.security.permissions.ViewRoles;
import br.com.daniel.security.service.UserService;
import br.com.daniel.utils.Principal;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ServiceRequestService {
    private final ServiceRequestDAO serviceRequestDAO;
    private final UserService userService;

    public ServiceRequestService(final ServiceRequestDAO serviceRequestDAO, final UserService userService) {
        this.serviceRequestDAO = serviceRequestDAO;
        this.userService = userService;
    }

    public void create(final ServiceRequest serviceRequest) {
        this.serviceRequestDAO.insert(serviceRequest);
    }

    public Response<ServiceRequestWithUsersData> findAll(
            final int page,
            final int size,
            final String status
    ) {
        if (ViewRoles.isAdmin())
            return mapResults(this.serviceRequestDAO.findAll(page, size, status));

        UserPrincipal loggedUser = Principal.extract();

        if (ViewRoles.isHelper())
            return mapResults(this.serviceRequestDAO.findAllAnalyzedBy(page, size, status, loggedUser.getId()));

        return mapResults(this.serviceRequestDAO.findAllFromUser(page, size, status, loggedUser.getId()));
    }

    public ServiceRequestWithUsersDataAndComments findById(final String id) {
        return this.serviceRequestDAO
                .findById(id)
                .map(req -> new ServiceRequestWithUsersDataAndComments(new ServiceRequestWithUsersData(
                        req,
                        this.userService.findUserById(req.getCreatedBy()),
                        this.userService.findUserById(req.getAnalyzedBy())
                )))
                .orElseThrow(() -> new RequestNotFoundException(id));
    }

    private Response<ServiceRequestWithUsersData> mapResults(final Response<ServiceRequest> result) {
        return result.map(req -> new ServiceRequestWithUsersData(
                req,
                this.userService.findUserById(req.getCreatedBy()),
                this.userService.findUserById(req.getAnalyzedBy())
        ), Collectors.toList());
    }
}
