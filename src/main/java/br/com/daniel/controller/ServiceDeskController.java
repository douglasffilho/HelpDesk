package br.com.daniel.controller;

import br.com.daniel.annotations.Authorized;
import br.com.daniel.domain.ServiceRequest;
import br.com.daniel.model.Response;
import br.com.daniel.model.decorator.ServiceRequestWithUsersData;
import br.com.daniel.model.dto.ServiceRequestDTO;
import br.com.daniel.security.domain.UserPrincipal;
import br.com.daniel.security.service.UserService;
import br.com.daniel.service.ServiceRequestService;
import br.com.daniel.utils.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.daniel.model.mapper.ServiceRequestMapper.map;
import static br.com.daniel.model.validation.ServiceRequestValidation.validate;

@Controller
@Authorized(roles = "#canViewRequests")
@RequestMapping("/requests")
public class ServiceDeskController {
    private final ServiceRequestService service;
    private final UserService userService;

    public ServiceDeskController(final ServiceRequestService service, final UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public String requests(
            @RequestParam(required = false, defaultValue = "1", name = "page") int page,
            @RequestParam(required = false, defaultValue = "10", name = "size") int size,
            @RequestParam(required = false, defaultValue = "", name = "status") String status,
            Model model
    ) {
        if (page < 1) page = 1;
        if (size < 1) size = 10;

        final Response<ServiceRequestWithUsersData> result = this.service
                .findAll(page, size, status)
                .map(req -> new ServiceRequestWithUsersData(
                        req,
                        this.userService.findUserById(req.getCreatedBy()),
                        this.userService.findUserById(req.getAnalyzedBy())
                ), Collectors.toList());

        final int thisPage = result.getPage();
        final int thisSize = result.getSize();
        final List<ServiceRequestWithUsersData> requests = new ArrayList<>(result.getResults());

        model.addAttribute("thisPage", thisPage);
        model.addAttribute("nextPage", result.hasNext() ? thisPage + 1 : thisPage);
        model.addAttribute("previousPage", result.hasPrevious() ? thisPage - 1 : thisPage);
        model.addAttribute("thisSize", thisSize);
        model.addAttribute("requests", requests);

        return "service-desk/index";
    }

    @GetMapping("/create")
    @Authorized(roles = "#canCreateRequests")
    public String create() {
        return "service-desk/create";
    }

    @PostMapping("/create")
    @Authorized(roles = "#canCreateRequests")
    public String create(final HttpSession session, @ModelAttribute final ServiceRequestDTO dto) throws IOException {
        validate(dto);

        UserPrincipal principal = Principal.extract();

        final ServiceRequest serviceRequest = map(dto, principal.getId());

        this.service.create(serviceRequest);

        session.setAttribute("message", "Chamado aberto com sucesso");
        return "redirect:/requests";
    }
}
