package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();

    @Override
    public Boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.getId() == null) user.setId(0);
        return repository.put(user.getId(),user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        List<User> users = repository.entrySet().stream().
                map(user -> user.getValue()).
                sorted().
                collect(Collectors.toList());
//        for (Map.Entry e: repository.entrySet()
//             ) {
//            users.add((User) e.getValue());
//        }
//        Collections.sort(users);
        return users;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return null;
    }
}
