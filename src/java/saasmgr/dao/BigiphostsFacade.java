/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package saasmgr.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import saasmgr.entity.Bigiphosts;

/**
 *
 * @author SG0894243
 */
@Stateless
public class BigiphostsFacade extends AbstractFacade<Bigiphosts> {
    @PersistenceContext(unitName = "SaaSManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BigiphostsFacade() {
        super(Bigiphosts.class);
    }
    
}
