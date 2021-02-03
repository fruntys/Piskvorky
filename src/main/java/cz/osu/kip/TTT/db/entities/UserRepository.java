package cz.osu.kip.TTT.db.entities;

import cz.osu.kip.TTT.db.DbException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class UserRepository {
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

    public void insert(UsersEntity usersEntity){
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usersEntity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new DbException("Failed to insert record into database.", ex);
        }
    }

    public List<UsersEntity> getAll(){
        EntityManager em = getEntityManager();
        List<UsersEntity> ret;

        try {
            TypedQuery<UsersEntity> query = em.createQuery(
                    "select i from UsersEntity i order by i.id",
                    UsersEntity.class);
            ret = query.getResultList();
        } catch (Exception e) {
            throw new DbException("Failed to list items.", e);
        }

        return ret;
    }

    public Optional<UsersEntity> getByNick_Mail(String nick_mail){
        EntityManager em = getEntityManager();
        List<UsersEntity> ret;

        try {
            TypedQuery<UsersEntity> query = em.createQuery(
                    "select i from UsersEntity i where i.nickname like :nm or i.email like :nm",
                    UsersEntity.class);
            query.setParameter("nm",nick_mail);

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

}
