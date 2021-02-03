package cz.osu.kip.TTT.services;

import cz.osu.kip.TTT.db.entities.UserRepository;
import cz.osu.kip.TTT.db.entities.UsersEntity;
import cz.osu.kip.TTT.utils.SecurityUtils;

import java.util.List;
import java.util.Optional;

public class LoginUserService {
    private final User user;

    public LoginUserService() {
        user = new User();
    }

    public void setAttributes(int id, String nickname, String email){
        user.setId(id);
        user.setNickname(nickname);
        user.setEmail(email);
    }

    public User getUser() {
        return user;
    }

    public void logUserIn(String inserted_nick_mail, String inserted_password){
        UserRepository userRepository = new UserRepository();
        Optional<UsersEntity> userEntity = userRepository.getByNick_Mail(inserted_nick_mail);
        if (userEntity.isPresent()){
            byte[] passwordHash = SecurityUtils.hashPassword(inserted_password, userEntity.get().getSalt());
            if (SecurityUtils.matchPassword(passwordHash, userEntity.get().getPasswordhash())){
                setAttributes(userEntity.get().getId(), userEntity.get().getNickname(), userEntity.get().getEmail());
            } else {
                System.out.println("Passwords do not match.");
            }
        } else {
            System.out.println("User entity is empty.");
        }


    }
}
