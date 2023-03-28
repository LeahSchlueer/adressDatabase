package com.hermes.adressDatabase.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    static
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()){
            return optionalUser.get();
        } else {
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
        }
    }

    void signUpUser(User user) {
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        final User createdUser = userRepository.save(user);

    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public Optional<User> findByEmail(String email) {
        Optional<User> thisUser = userRepository.findByEmail(email);
        return thisUser;
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
