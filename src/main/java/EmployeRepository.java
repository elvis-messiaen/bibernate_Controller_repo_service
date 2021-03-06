import entities.Employes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<Employes,Long> {
/*
    repository pour la déclarations des signature
 */
    public static Employes employes = new Employes();

    // sauvegarde des employe
      Employes save (Employes employes);

    // recuperer un employe par son id
    Employes findById (long id);

    // delete un employe par son id
    Employes deleteById (long id);

    // update un employe par son id
    Employes updateById (long id);


}
