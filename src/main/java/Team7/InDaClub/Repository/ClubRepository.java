package Team7.InDaClub.Repository;

import Team7.InDaClub.Domain.Club;

import java.util.List;
import java.util.Optional;

public interface ClubRepository {
    Club save();
    Optional<Club> findById(long _id);
    Optional<Club> findByClubName(String _name);
    Optional<Club> findByClubRep(String _rep);
    List<Club> getAllClub();
}
