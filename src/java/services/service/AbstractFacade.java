/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import jpa.Citoyen;

/**
 *
 * @author souhaib
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public List<T> findByName(Object nom) {
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<T> cf = cq.from(entityClass);
        cq.select(cf);
        cq.where(cb.equal(cf.get("nom"), nom));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findByIDNamePrenom_Criteria(Object id, Object nom, Object prenom) {
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root<T> cf = cq.from(entityClass);
        cq.select(cf);
        cq.where(
                cb.equal(cf.get("idcitoyen"), id),
                cb.equal(cf.get("nom"), nom),
                cb.equal(cf.get("prenom"), prenom)
        );
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public List<T> findByIDNamePrenom_JPQL(Object id, Object nom, Object prenom) {
        Query q = getEntityManager().createQuery("SELECT c FROM Citoyen c WHERE c.idcitoyen = :id and c.nom = :nom and c.prenom = :prenom", entityClass);
        q.setParameter("id", id);
        q.setParameter("nom", nom);
        q.setParameter("prenom", prenom);
        return q.getResultList();
    }
    
    public List<T> findByIDNamePrenom_JPQL_NamedQuery(Object id, Object nom, Object prenom) {
        Query q = getEntityManager().createNamedQuery("Citoyen.findByIdNomPrenom", entityClass);
        q.setParameter("idcitoyen", id);
        q.setParameter("nom", nom);
        q.setParameter("prenom", prenom);
        return q.getResultList();
    }

    public List<T> findByIDNamePrenom_SQL_NativeQuery(Object id, Object nom, Object prenom) {
        Query q = getEntityManager().createNativeQuery("SELECT c FROM Citoyen c WHERE c.idcitoyen = " + id + " AND c.nom = '" + nom + "' AND c.prenom = '" + prenom + "'"
                ,entityClass);                           
        return q.getResultList();
    }
    
    public int updateAdresse(Integer id, String adresse){
       Citoyen citoyen = (Citoyen) this.find(id);
       citoyen.setAdresse(adresse);
       getEntityManager().persist(citoyen);
       return 1;
    }
    
    public int updateAdresse_Criteria(Integer id, String adresse){
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaUpdate update = cb.createCriteriaUpdate(entityClass);
        javax.persistence.criteria.Root<T>  rt = update.from(entityClass);
        update.set(rt.get("adresse"),adresse);
        update.where(cb.equal(rt.get("idcitoyen"), id));
        return getEntityManager().createQuery(update).executeUpdate();
    }
    
    public int updateAdresse_JPQL(Integer id, String adresse){
        javax.persistence.Query q = getEntityManager().createQuery("Update Citoyen c SET c.adresse = :adresse WHERE c.idcitoyen= :idcitoyen",entityClass);
        q.setParameter("idcitoyen", id);
        q.setParameter("adress", adresse);
        return q.executeUpdate();
    }
    
    public int updateAdresse_Native(Integer id, String adresse){
        javax.persistence.Query q = getEntityManager().createNativeQuery("Update Citoyen c SET c.adresse = "+adresse+"WHERE c.idcitoyen= "+id,entityClass);

        return q.executeUpdate();
    }
        
    public int updateAdresse_Named(Integer id, String adresse){
        javax.persistence.Query q = getEntityManager().createNamedQuery("Citoyen.updateAdresse");
        return q.executeUpdate();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
