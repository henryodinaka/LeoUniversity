package service;


import java.util.Iterator;
import java.util.List;
import model.Studentdetail;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author LEOGOLD
 */
public class RegistrationDBOperation {
    Session session = null;
    boolean validate = true;
    
    public RegistrationDBOperation (){
        session = model.HibernateUtil.getSessionFactory().getCurrentSession();
    }
     
     //This method firstly check if the user with such username exist before committing new user
    public String save(Studentdetail student){
        Transaction transaction = session.beginTransaction();
        String userId = student.getUserid();
        System.out.println(userId);
        Query query = session.createQuery("FROM Studentdetail");
        List <Studentdetail> studentList = query.list();
        
        for(Iterator iterate = studentList.iterator(); iterate.hasNext();){
            Studentdetail regForm = (Studentdetail)iterate.next();
            if(regForm.getUserid().equals(userId)){
                validate = false;
            }
        }
        if(validate==false){
            return "error";
        }
        else{
            session.save(studentList);
            transaction.commit();
            return "true";
        }
   }


}
