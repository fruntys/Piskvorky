package cz.osu.kip.TTT.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "GAMES", schema = "FRUNTYS", catalog = "")
public class GamesEntity {
    private int id;
    private int challenger;
    private Integer opponent;
    private String keyCode;
    private Integer result;

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
    @Column(name = "CHALLENGER")
    public int getChallenger() {
        return challenger;
    }

    public void setChallenger(int challenger) {
        this.challenger = challenger;
    }

    @Basic
    @Column(name = "OPPONENT")
    public Integer getOpponent() {
        return opponent;
    }

    public void setOpponent(Integer opponent) {
        this.opponent = opponent;
    }

    @Basic
    @Column(name = "KEY_CODE")
    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    @Basic
    @Column(name = "RESULT")
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GamesEntity that = (GamesEntity) o;

        if (id != that.id) return false;
        if (challenger != that.challenger) return false;
        if (opponent != null ? !opponent.equals(that.opponent) : that.opponent != null) return false;
        if (keyCode != null ? !keyCode.equals(that.keyCode) : that.keyCode != null) return false;
        if (result != null ? !result.equals(that.result) : that.result != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + challenger;
        result1 = 31 * result1 + (opponent != null ? opponent.hashCode() : 0);
        result1 = 31 * result1 + (keyCode != null ? keyCode.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        return result1;
    }
}
