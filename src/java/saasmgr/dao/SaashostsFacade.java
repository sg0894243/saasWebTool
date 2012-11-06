/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package saasmgr.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import saasmgr.entity.Saashosts;

/**
 *
 * @author SG0894243
 */
@Stateless
public class SaashostsFacade extends AbstractFacade<Saashosts> {
    @PersistenceContext(unitName = "SaaSManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SaashostsFacade() {
        super(Saashosts.class);
    }
    
}
