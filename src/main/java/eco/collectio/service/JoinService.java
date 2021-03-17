package eco.collectio.service;

import eco.collectio.domain.Join;
import eco.collectio.repository.JoinRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinService {
    private final JoinRepository repository;

    public JoinService(JoinRepository repository) {
        this.repository = repository;
    }

    public List<Join> getAll() {
        return repository.findAll();
    }

}
