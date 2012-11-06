/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package saasmgr.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import saasmgr.entity.Componentgroups;

/**
 *
 * @author SG0894243
 */
@Stateless
public class ComponentgroupsFacade extends AbstractFacade<Componentgroups> {
    @PersistenceContext(unitName = "SaaSManagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComponentgroupsFacade() {
        super(Componentgroups.class);
    }
    
}
