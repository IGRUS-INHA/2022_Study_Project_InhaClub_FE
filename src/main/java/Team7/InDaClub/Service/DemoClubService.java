package Team7.InDaClub.Service;

import Team7.InDaClub.Domain.Club;
import Team7.InDaClub.Repository.MemoryClubRepository;

import java.util.List;
import java.util.Optional;

public class DemoClubService {
    MemoryClubRepository memoryClubRepository = new MemoryClubRepository();

    public Optional<Club> findOneByName(String _name) {
        return memoryClubRepository.findByClubName(_name);
    }

    public List<Club> makeListOfAllClub() {
        return memoryClubRepository.getAllClub();
    }

    public String register(Club _club) {
        memoryClubRepository.save(_club);
        return _club.getClubName();
    }
}
