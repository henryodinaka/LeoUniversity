package service;
//08095267180 08181326516

import java.util.List;
import model.HibernateUtil;
import model.Studentdetail;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LEOGOLD
 */

public class BeanHelper {
    
    Session session = null;

    public BeanHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public List <Studentdetail> getStudent(){
        
        List <Studentdetail> student = null;
        try{
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("FROM model.Studentdetail");
            student = (List<Studentdetail>) query.list();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return student;
    }
}
