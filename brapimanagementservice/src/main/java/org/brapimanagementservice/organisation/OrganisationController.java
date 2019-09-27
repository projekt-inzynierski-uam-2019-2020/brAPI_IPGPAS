package org.brapimanagementservice.organisation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/organisations")
public class OrganisationController {

    private OrganisationRepository organisationRepository;

    OrganisationController(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @GetMapping
    public List<Organisation> findAll() {
        return organisationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organisation> findById(@PathVariable long id) {
        return organisationRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Organisation create(@RequestBody Organisation organisation) {
        return organisationRepository.save(organisation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organisation> update(@PathVariable long id, @RequestBody Organisation organisation) {
        return organisationRepository.findById(id)
                .map(record -> {
                    record.setName(organisation.getName());
                    record.setDescription(organisation.getDescription());
                    record.setWebsite(organisation.getWebsite());
                    Organisation updated = organisationRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return organisationRepository.findById(id)
                .map(record -> {
                    organisationRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
