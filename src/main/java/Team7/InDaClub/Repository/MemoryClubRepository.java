package Team7.InDaClub.Repository;

import Team7.InDaClub.Domain.Club;
import Team7.InDaClub.Domain.User;

import java.util.*;

public class MemoryClubRepository implements ClubRepository{
    private static Map<Long, Club> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Club save(Club _club) {
        _club.setClubId(++sequence);
        store.put(_club.getClubId(), _club);
        return _club;
    }

    @Override
    public Optional<Club> findById(long _id) {
        return Optional.ofNullable(store.get(_id));
    }

    @Override
    public Optional<Club> findByClubName(String _name) {
        return store.values().stream()
                .filter(club -> club.getClubName().equals(_name))
                .findAny();
    }

    @Override
    public Optional<Club> findByClubRep(String _rep) {
        return store.values().stream()
                .filter(club -> club.getClubRep().equals(_rep))
                .findAny();

    }

    @Override
    public Optional<Club> findByClubRoom(String _room) {
        return store.values().stream()
                .filter(club -> club.getClubRoom().equals(_room))
                .findAny();
    }

    @Override
    public List<String> getClubInfoByName(String _name) {
        Optional<Club> opClub = store.values().stream()
                .filter(club -> club.getClubName().equals(_name))
                .findAny();
        return opClub.get().getClubData();
    }

    @Override
    public List<Club> getAllClub() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
