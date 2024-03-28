package org.example.lab11.Service;

import lombok.RequiredArgsConstructor;
import org.example.lab11.API.ApiException;
import org.example.lab11.Model.User;
import org.example.lab11.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository ;


    public List<User> getAll(){
        return userRepository.findAll();
    }


    public void addUser(User user){
        user.setRegistrationDate(LocalDate.now());
        userRepository.save(user);
    }


    public void updateUser(Integer Id , User user){

        User u = userRepository.findUserByID(Id);

        if (u == null){
            throw new ApiException("Invalid Id");
        }

        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setRegistrationDate(user.getRegistrationDate());

        userRepository.save(u);

    }



    public void deleteUser(Integer Id){

        User u = userRepository.findUserByID(Id);

        if (u == null){
            throw new ApiException("Invalid Id");
        }

        userRepository.delete(u);

    }


//7 check username and password
    public User findByUsernameAndPassword(String username , String password){
        User u = userRepository.findUserByUsernameAndPassword(username, password);
        if(u==null){
            throw new ApiException("Invalid id");
        }
        return u;
    }
}
