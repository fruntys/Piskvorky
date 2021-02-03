package cz.osu.kip.TTT.db.entities;

import cz.osu.kip.TTT.db.DbException;
import cz.osu.kip.TTT.services.Game;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class GamesRepository {
    private static final String P_NAME = "TicTacToePls";
    private EntityManager em = null;
    private static EntityManagerFactory emf = null;

    private EntityManager getEntityManager(){
        if (emf == null) {
            try {
                emf = javax.persistence.Persistence.createEntityManagerFactory(P_NAME);
            } catch (Exception ex) {
                throw new DbException("Failed to create entity-manager-factory.", ex);
            }
        }
        if (em == null) {
            try {
                em = emf.createEntityManager();
            } catch (Exception ex) {
                throw new DbException("Failed to create entity-manager.", ex);
            }
        }

        return em;
    }

    public void insert(GamesEntity gamesEntity){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(gamesEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new DbException("Failed to insert record into database.", ex);
        }
    }

    public Optional<GamesEntity> getItemById(String key){
        EntityManager em = getEntityManager();
        List<GamesEntity> ret;

        try {
            TypedQuery<GamesEntity> query = em.createQuery(
                    "select i from GamesEntity i where i.keyCode like :key",
                    GamesEntity.class);
            query.setParameter("key",key);

            ret = query.getResultList();
        } catch (Exception e) {
            throw new DbException("Failed to list items.", e);
        }
        if (ret.size() == 0){
            return Optional.empty();
        } else {
            return Optional.of(ret.get(0));
        }
    }

    public void update(int game_id, int opponent_id) {
        EntityManager em = getEntityManager();

        try{
            em.getTransaction().begin();
            TypedQuery<GamesEntity> query = em.createQuery(
                    "update GamesEntity set opponent = :opp where id = :id",
                    GamesEntity.class);
            query.setParameter("opp",opponent_id);
            query.setParameter("id",game_id);
            query.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DbException("Failed to update item.", e);
        }
    }
}
