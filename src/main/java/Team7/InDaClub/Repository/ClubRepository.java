package Team7.InDaClub.Repository;

import Team7.InDaClub.Domain.Club;

import java.util.List;
import java.util.Optional;

public interface ClubRepository {
    Club save(Club _club);
    Optional<Club> findById(long _id);
    Optional<Club> findByClubName(String _name);
    Optional<Club> findByClubRep(String _rep);
    Optional<Club> findByClubRoom(String _room);
    List<String> getClubInfoByName(String _name);
    List<Club> getAllClub();
}
