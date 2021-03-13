package eco.collectio.service;

import eco.collectio.domain.node.AppUser;
import eco.collectio.domain.relationship.Influence;
import eco.collectio.repository.InfluenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfluenceService {
    private final InfluenceRepository repository;
    private final AppUserService appUserService;

    public InfluenceService(InfluenceRepository repository, AppUserService appUserService) {
        this.repository = repository;
        this.appUserService = appUserService;
    }

    public Influence create(Influence influence) {
        if (influence == null) {
            return null;
        }
        return repository.save(influence);
    }

    public List<Influence> getAll() {
        return repository.findAll();
    }

    public Influence getById(Long id) {
        Optional<Influence> result = repository.findById(id);
        return result.orElse(null);
    }

    public Influence update(Influence influence) {
        if (influence == null || influence.getId() == null) {
            return null;
        }

        Optional<Influence> persistedInfluence = repository.findById(influence.getId());
        if (!persistedInfluence.isPresent()) {
            return null;
        }

        //TODO: Nu vine direct obiectul in loc sa mai fac eu inca un query?
        System.out.println("Beleste ocii aci:");
        System.out.println(influence.getWhoInfluenced());
        System.out.println(influence.getWhoIsInfluenced());

        AppUser whoInfluenced = appUserService.getById(influence.getWhoInfluenced().getId());
        AppUser whoIsInfluenced = appUserService.getById(influence.getWhoIsInfluenced().getId());
        if (whoInfluenced == null || whoIsInfluenced == null) {
            return null;
        }

        Influence influenceToUpdate = persistedInfluence.get();
        influenceToUpdate.setEnrolId(influence.getEnrolId());
        influenceToUpdate.setWhoInfluenced(influence.getWhoInfluenced());
        influenceToUpdate.setWhoIsInfluenced(influence.getWhoIsInfluenced());
        influenceToUpdate.setWhen(influence.getWhen());
        return repository.save(influenceToUpdate);
    }

    public Influence delete(Long id) {
        Optional<Influence> persistedInfluence = repository.findById(id);
        if (!persistedInfluence.isPresent()) {
            return null;
        }
        Influence deleted = persistedInfluence.get();
        repository.deleteById(id);
        return deleted;
    }
}
