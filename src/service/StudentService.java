package service;
import domain.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class StudentService implements Service{
    private StandardServiceRegistry ssr;
    private Metadata meta;
    private SessionFactory factory;

    public StudentService() {
        this.ssr = new StandardServiceRegistryBuilder().configure("file/persistence/hibernate.cfg.xml").build();
        this.meta = new MetadataSources(ssr).getMetadataBuilder().build();
        this.factory = meta.getSessionFactoryBuilder().build();

    }


    public void addNewStudent(Student student) {

        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();

            session.save(student);
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen())
                session.close();

        }


    }

    public void readDataOfStudent() {
        Session session = null;
        try {
            session = factory.openSession();
            EntityManager entityManager = session.getEntityManagerFactory().createEntityManager();

            TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM domain.Student s", Student.class);
            List<Student> studentList = query.getResultList();

            for (Student student : studentList) {
                System.out.println(student);
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    public void updateDataOfStudent(Long id, String newName, String newEmail, Integer newAge) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            Query updateQuery = session.createQuery("UPDATE domain.Student s SET s.name = :newName, s.email = :newEmail, s.age = :newAge WHERE s.id = :studentId");
            updateQuery.setParameter("newName", newName);
            updateQuery.setParameter("newEmail", newEmail);
            updateQuery.setParameter("newAge", newAge);
            updateQuery.setParameter("studentId", id);

            int rowsAffected = updateQuery.executeUpdate();
            transaction.commit();
            if (rowsAffected > 0) {
                System.out.println("domain.Student data updated successfully.");
            } else {
                System.out.println("No student data was updated.");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteStudentById(Long id) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            Query deleteQuery = session.createQuery("DELETE FROM domain.Student WHERE id = :studentId");
            deleteQuery.setParameter("studentId", id);

            int rowsAffected = deleteQuery.executeUpdate();
            transaction.commit();

            if (rowsAffected > 0) {
                System.out.println("domain.Student data deleted successfully.");
            } else {
                System.out.println("No student data was deleted.");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}


