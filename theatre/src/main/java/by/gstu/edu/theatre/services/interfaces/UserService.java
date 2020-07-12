package by.gstu.edu.theatre.services.interfaces;

import java.util.Optional;

public interface UserService {

    Optional<String> doSignIn(String jsonString);

    Optional<String> doSignUp(String jsonString);
}
