package cz.osu.kip.TTT.services;

public class Game {
    private int id;
    private int id_challenger;
    private int id_opponent;
    private String key;
    private int result;

    public Game(int id_challenger) {
        this.id_challenger = id_challenger;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_challenger() {
        return id_challenger;
    }

    public void setId_challenger(int id_challenger) {
        this.id_challenger = id_challenger;
    }

    public int getId_opponent() {
        return id_opponent;
    }

    public void setId_opponent(int id_opponent) {
        this.id_opponent = id_opponent;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
