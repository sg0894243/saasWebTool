/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package saasmgr.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import saasmgr.entity.People;

/**
 *
 * @author SG0894243
 */
@Stateless
public class PeopleFacade extends AbstractFacade<People> {
    @PersistenceContext(unitName = "SaaSManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeopleFacade() {
        super(People.class);
    }
    
    public int getMaxID() {
        javax.persistence.Query q = getEntityManager().createNamedQuery("People.getMaxID");
        return ((Integer) q.getSingleResult()).intValue();
    }
    
}
