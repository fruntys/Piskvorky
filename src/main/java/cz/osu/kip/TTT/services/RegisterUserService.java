package cz.osu.kip.TTT.services;

import cz.osu.kip.TTT.db.entities.UserRepository;
import cz.osu.kip.TTT.db.entities.UsersEntity;
import cz.osu.kip.TTT.utils.SecurityUtils;

public class RegisterUserService {
    private final User user;

    public RegisterUserService(String nickname, String email, String password) {
        user = new User(nickname, email, password);
    }

    //TODO: hash password

    public void insert(){
        byte[] salt = SecurityUtils.generateSalt();
        byte[] passwordHash = SecurityUtils.hashPassword(user.getPassword(), salt);

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setNickname(user.getNickname());
        usersEntity.setEmail(user.getEmail());
        usersEntity.setSalt(salt);
        usersEntity.setPasswordhash(passwordHash);

        UserRepository userRepository = new UserRepository();
        userRepository.insert(usersEntity);
    }

    public User getUser() {
        return user;
    }
}
