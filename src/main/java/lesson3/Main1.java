package lesson3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main1 {
    public static void main(String[] args) {
        // 1 - Create Entity Manager with factory, pointing to persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part1");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 2 - Create instances
        Estado estadoParaAdicionar = new Estado("Rio de Janeiro", "RJ");
        Aluno alunoParaAdicionar = new Aluno("Daniel", 29, estadoParaAdicionar);

        // 3 - Begin transaction
        entityManager.getTransaction().begin();

        // 4 - Persist data
        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(alunoParaAdicionar);

        // 5 - Commit
        entityManager.getTransaction().commit();

        // 6 - Close Entity Manager and its factory
        entityManager.close();
        entityManagerFactory.close();
    }
}