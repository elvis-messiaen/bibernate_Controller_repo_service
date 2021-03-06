package controller;

import entities.Employes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class EmployeController {
    public SessionFactory sessionFactory;
    // essaie de recuperation du repository sans les annotations
    private EmployeRepository employeRepository;

    public void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
            ex.getStackTrace();
        }
    }

    public void exit() {
        sessionFactory.close();
    }

    //creation d'un employes avec repository
    public void createRepo() {
        Session session = sessionFactory.openSession();
        Employes aramis = new Employes();

        aramis.setNom("lamarepo");
        aramis.setPrenom("lamarepobleu");
        aramis.setEmail("lamarepo@gmail.com");
        aramis.setAge(55);
        aramis.setFonction("caiboulangerssier");
        aramis.setTel("0258741025");
        aramis.setAdresse("999 rue des ours, 27000, berry");
        
        session.close();
        employeRepository.save(aramis);
    }

    // methode pour lire depuis l'interface repository sans data jpa
    public Employes readRepo(long id) {
        Session session = sessionFactory.openSession();
        Employes employes = session.get(Employes.class, id);
       // employeRepository.findById(employes.getId());
        System.out.println("id " + employes.getId());
        System.out.println("nom " + employes.getNom());
        System.out.println("prenom " + employes.getPrenom());
        System.out.println("email " + employes.getEmail());
        System.out.println("tel " + employes.getTel());
        System.out.println("adresse " + employes.getAdresse());
        System.out.println("fonction " + employes.getFonction());
        System.out.println("age " + employes.getAge());
        session.close();
        return employes;
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

// affichage en console ensemble d'un employ?? by id

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

    // mise ?? jour d'un employ??

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
