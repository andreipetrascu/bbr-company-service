package bbr.bbrcompanyservice.controller;


import bbr.bbrcompanyservice.entity.Company;
import bbr.bbrcompanyservice.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class CompanyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @GetMapping(path = "/welcome")
    public String home() {
        LOGGER.info("welcome");
        return "Welcome to [ Company Service ] !";
    }

    @GetMapping(path = "/companies")
    public HttpEntity findAllCompanies() {
        return ok(companyService.findAllCompanies());
    }

    @GetMapping(path = "/companies/{companyId}")
    public HttpEntity findById(@PathVariable Integer companyId) {
        return ok(companyService.findById(companyId));
    }

    @PostMapping(path = "/companies")
    public HttpEntity addCompany(@RequestBody Company company) {
        return ok(companyService.addCompany(company));
    }

    @PutMapping(path = "/companies")
    public HttpEntity updateCompany(@RequestBody Company company) {
        return ok(companyService.updateCompany(company));
    }

    @DeleteMapping(path = "/companies/{companyId}")
    public HttpEntity deleteCompany(@PathVariable Integer companyId) {
        LOGGER.info("[Company Service]Try to delete company!");
        return ok(companyService.deleteCompany(companyId));
    }
}
