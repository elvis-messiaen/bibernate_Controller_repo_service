import entities.Employes;

public interface EmployeRepository {
/*
    repository pour la déclarations des signature
 */
    Employes employes = new Employes();

    // sauvegarde des employe
    Employes save (Employes employes);

    // recuperer un employe par son id
    Employes findById (long id);

    // delete un employe par son id
    Employes deleteById (long id);

    // update un employe par son id
    Employes updateById (long id);


}
