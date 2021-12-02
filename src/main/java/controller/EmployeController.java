package controller;

import entities.Employes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class EmployeController<EmployeRepository> {
    public SessionFactory sessionFactory;
    // essaie de recuperation du repository sans les annotations
    public EmployeRepository employeRepository;


    public void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
            ex.getStackTrace();
        }
    }

    public  void exit(){
        sessionFactory.close();
    }



    // creation d'un employes
    public void create() {
        Employes louis = new Employes();
        louis.setNom("paliou");
        louis.setPrenom("pilaf");
        louis.setEmail("paliou@gmail.com");
        louis.setAge(22);
        louis.setFonction("caissier");
        louis.setTel("0303030303");
        louis.setAdresse("14 rue des pikachu");

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(louis);
        session.getTransaction().commit();
        session.close();
    }

// affichage en console ensemble d'un employé by id

    /**
     * @param id
     * @return
     */
    public Employes read(long id) {
        Session session = sessionFactory.openSession();
        Employes employes = session.get(Employes.class, id);
        System.out.println("nom " + employes.getNom());
        System.out.println("prenom " + employes.getPrenom());
        System.out.println("email " + employes.getEmail());
        System.out.println("age " + employes.getAge());
        System.out.println("fonction " + employes.getFonction());
        System.out.println("tel " + employes.getTel());
        System.out.println("adresse " + employes.getAdresse());
        session.close();
        return employes;
    }

    // mise à jour d'un employé

    /**
     * @param id
     * @param newEmployes
     */
    public void update(long id, Employes newEmployes) {
        Employes employes = this.read(id);

        if (newEmployes.getNom() != null) {
            employes.setNom(newEmployes.getNom());
        }
        if (newEmployes.getPrenom() != null) {
            employes.setPrenom(newEmployes.getPrenom());
        }
        if (newEmployes.getEmail() != null) {
            employes.setEmail(newEmployes.getEmail());
        }
        if (newEmployes.getAge() < 0) {
            employes.setAge(newEmployes.getAge());
        }
        if (newEmployes.getFonction() != null) {
            employes.setFonction(newEmployes.getFonction());
        }
        if (newEmployes.getTel() != null) {
            employes.setTel(newEmployes.getTel());
        }
        if (newEmployes.getAdresse() != null) {
            employes.setAdresse(newEmployes.getAdresse());
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(employes);
        session.getTransaction().commit();
        session.close();
    }

    // suppression d'un employes

    /**
     * @param employes
     */
    public void delete(Employes employes) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(employes);
        session.getTransaction().commit();
        session.close();
    }
}