package bbr.bbrcompanyservice.service;

import bbr.bbrcompanyservice.entity.Company;
import bbr.bbrcompanyservice.repository.CompanyRepository;
import bbr.bbrcompanyservice.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

    public Response addCompany(Company company) {
        Company savedCompany = companyRepository.save(company);
        return new Response<>(1, "[addCompany] Success", savedCompany);
    }

    public Response findAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        if (companies.isEmpty()) {
            LOGGER.info("Find all companies: {}", "No Content");
            return new Response<>(1, "No content", companies);
        }
        return new Response<>(1, "[findAllCompanies] Success", companies);
    }

    public Response findById(Integer id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            LOGGER.info("Find company by id. No Content");
            return new Response<>(1, "No content", company);
        }
        return new Response<>(1, "[findById] Success", company);
    }

    public Response updateCompany(Company company) {
        Optional<Company> foundCompany = companyRepository.findById(company.getId());
        if (!foundCompany.isPresent()) {
            LOGGER.info("[Update Company] Company Id Is Non-Existent, companyId: {}", company.getId());
            return new Response<>(0, "[updateCompany] Company Id " + company.getId() + " not found.", null);
        } else {
            Company updateCompany = foundCompany.get();
            updateCompany.setAddress(company.getAddress());
            updateCompany.setCif(company.getCif());
            updateCompany.setEmail(company.getEmail());
            updateCompany.setName(company.getName());
            updateCompany.setReg(company.getReg());
            updateCompany.setPaymentDays(company.getPaymentDays());

            companyRepository.save(updateCompany);
            LOGGER.info("[Update Company] Success.");
            return new Response<>(1, "[updateCompany] Success", updateCompany);
        }
    }

    public Response deleteCompany(Integer companyId) {
        Optional<Company> foundCompany = companyRepository.findById(companyId);
        if (!foundCompany.isPresent()) {
            LOGGER.error("[Delete Company] Company Id Is Non-Existent, companyId: {}", companyId);
            return new Response<>(0, "[deleteCompany] Company id do not exist", null);
        } else {
            companyRepository.deleteById(companyId);
            LOGGER.info("[Delete Company] Success.");
            return new Response<>(1, "[deleteCompany] Success", null);
        }
    }
}
