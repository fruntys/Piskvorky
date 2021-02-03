package cz.osu.kip.TTT.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "USERS", schema = "FRUNTYS", catalog = "")
public class UsersEntity {
    private int id;
    private String nickname;
    private String email;
    private byte[] salt;
    private byte[] passwordhash;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NICKNAME")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "SALT")
    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "PASSWORDHASH")
    public byte[] getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(byte[] passwordhash) {
        this.passwordhash = passwordhash;
    }
}
