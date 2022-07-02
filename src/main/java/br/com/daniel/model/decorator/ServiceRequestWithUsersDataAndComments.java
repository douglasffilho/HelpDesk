package br.com.daniel.model.decorator;

import br.com.daniel.domain.ServiceRequestComment;

import java.util.ArrayList;
import java.util.List;

public class ServiceRequestWithUsersDataAndComments extends ServiceRequestWithUsersData {
    private final List<ServiceRequestComment> comments;

    public ServiceRequestWithUsersDataAndComments(
            final ServiceRequestWithUsersData request,
            final List<ServiceRequestComment> comments
    ) {
        super(request, request.createdBy, request.analyzedBy);

        this.comments = comments;
    }

    public ServiceRequestWithUsersDataAndComments(final ServiceRequestWithUsersData request) {
        this(request, new ArrayList<>());
    }

    public List<ServiceRequestComment> getComments() {
        return this.comments;
    }
}
