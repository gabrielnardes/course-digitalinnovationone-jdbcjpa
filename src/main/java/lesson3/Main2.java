package lesson3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main2 {
    public static void main(String[] args) {
        // 1 - Create Entity Manager with factory, pointing to persistence.xml
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part2");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 2 - Create instances
        Estado estadoParaAdicionar = new Estado("Rio de Janeiro", "RJ");
        Aluno alunoParaAdicionar1 = new Aluno("Daniel", 29, estadoParaAdicionar);
        Aluno alunoParaAdicionar2 = new Aluno("Joao", 40, estadoParaAdicionar);

        // 3 - Persist data
        entityManager.getTransaction().begin();
        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(alunoParaAdicionar1);
        entityManager.persist(alunoParaAdicionar2);
        entityManager.getTransaction().commit();

        // 4 - Find instances from database
        Estado estadoEncontrado = entityManager.find(Estado.class, 1);
        Aluno alunoEncontrado = entityManager.find(Aluno.class, 1);
        System.out.println(estadoEncontrado);
        System.out.println(alunoEncontrado);

        // 5 - Set identities
        entityManager.getTransaction().begin();
        alunoEncontrado.setNome("Karam");
        alunoEncontrado.setIdade(20);
        alunoEncontrado = entityManager.find(Aluno.class, 1);
        System.out.println(alunoEncontrado);
        entityManager.getTransaction().commit();

        // 6 - Remove identity
        entityManager.getTransaction().begin();
        entityManager.remove(alunoEncontrado);
        entityManager.getTransaction().commit();

        // 7 - Close Entity Manager and its factory
        entityManager.close();
        entityManagerFactory.close();

    }
}