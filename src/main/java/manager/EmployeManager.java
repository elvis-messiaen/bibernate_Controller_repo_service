package manager;

import controller.EmployeController;

public class EmployeManager {
    public static void main(String[] args) {

        EmployeController employeController = new EmployeController();
        employeController.setup();
        employeController.create();
        // manager.read(2);
        //Employes employes = manager.read(4);
       // employes.setFonction("Scrum master");
       // long id = 3;
       // manager.update(id, employes);
       // manager.delete(employes);
        employeController.exit();
    }
}











