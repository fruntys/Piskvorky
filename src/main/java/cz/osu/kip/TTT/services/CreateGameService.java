package cz.osu.kip.TTT.services;

import cz.osu.kip.TTT.db.entities.GamesEntity;
import cz.osu.kip.TTT.db.entities.GamesRepository;
import cz.osu.kip.TTT.utils.StringUtils;

import java.util.Optional;

public class CreateGameService {
    private Game game;

    public CreateGameService(int id_challenger){
        game = new Game(id_challenger);
    }

    public CreateGameService(){}

    public Game getGame() {
        return game;
    }

    public void setAttributes(int id_opponent, String key, int result){

        game.setId_opponent(id_opponent);
        game.setResult(result);
    }

    public void insert(){
        GamesEntity gamesEntity = new GamesEntity();
        gamesEntity.setChallenger(game.getId_challenger());

        String key = StringUtils.getRandomString();
        gamesEntity.setKeyCode(key);
        game.setKey(key);

        GamesRepository gamesRepository = new GamesRepository();
        gamesRepository.insert(gamesEntity);
    }

    public void update(int game_id, String key_code, int opponent_id){
        GamesEntity gamesEntity = new GamesEntity();
        gamesEntity.setId(game_id);
        gamesEntity.setKeyCode(key_code);

        GamesRepository gamesRepository = new GamesRepository();
        gamesRepository.update(game_id, opponent_id);
    }
    public void update(int game_id, String key_code){
        GamesEntity gamesEntity = new GamesEntity();
        gamesEntity.setId(game_id);
        gamesEntity.setKeyCode(key_code);

        GamesRepository gamesRepository = new GamesRepository();
        gamesRepository.update(game_id, -1);
    }

    public String getKeyCodeOfGame(){
        GamesRepository gamesRepository = new GamesRepository();
        Optional<GamesEntity> gamesEntity = gamesRepository.getItemById(game.getKey());
        if (gamesEntity.isPresent()) return gamesEntity.get().getKeyCode();
        else return "";
    }

    public int getIdOfGame(){
        GamesRepository gamesRepository = new GamesRepository();
        Optional<GamesEntity> gamesEntity = gamesRepository.getItemById(game.getKey());
        if (gamesEntity.isPresent()) return gamesEntity.get().getId();
        else return -1;
    }
}
