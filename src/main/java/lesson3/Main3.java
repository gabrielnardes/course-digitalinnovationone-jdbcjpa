package lesson3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main3 {

    public static void main(String[] args) {

        // 1 - Objects to use in the examples
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part2");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Estado estadoParaAdicionar = new Estado("Rio de Janeiro", "RJ");
        entityManager.persist(estadoParaAdicionar);
        entityManager.persist(new Estado("Sao Paulo", "SP"));
        entityManager.persist(new Aluno("Daniel", 29, estadoParaAdicionar));
        entityManager.persist(new Aluno("Joao", 20, estadoParaAdicionar));
        entityManager.persist(new Aluno("Pedro", 30, estadoParaAdicionar));
        entityManager.getTransaction().commit();

        // 2 - We will use EntityManager find(), native SQL, JPQL e JPA Criteria API to these queries
        // SELECT * FROM Aluno WHERE id = 1
        // SELECT * FROM Aluno WHERE nome = 'Daniel'

        // 2.1 Search parameter
        String nome = "Daniel";

        // =============================================================================================================
        // 2.2 - Entity Manager
        // Only one result with entityManager.find(), not possible to get a list
        Aluno alunoEntityManager = entityManager.find(Aluno.class, 1);
        System.out.println("Consulta alunoEntityManager: " + alunoEntityManager);

        // =============================================================================================================
        // 2.3 - Native SQL
        // One result
        String sql = "SELECT * FROM Aluno WHERE nome = :nome ";
        Aluno alunoSQL = (Aluno) entityManager
                .createNativeQuery(sql, Aluno.class)
                .setParameter("nome", nome)
                .getSingleResult();

        // List result
        String sqlList = "SELECT * FROM Aluno";
        List<Aluno> alunoSQLList = entityManager
                .createNativeQuery(sqlList, Aluno.class)
                .getResultList();

        // Print
        System.out.println("Consulta alunoSQL: " + alunoSQL);
        alunoSQLList.forEach(Aluno -> System.out.println("Consulta alunoSQLList: " + Aluno));

        // =============================================================================================================
        // 2.4 - JPQL
        // One result
        String jpql = "select a from Aluno a where a.nome = :nome";
        Aluno alunoJPQL = entityManager
                .createQuery(jpql, Aluno.class)
                .setParameter("nome", nome)
                .getSingleResult();

        // List result
        String jpqlList = "select a from Aluno a where a.estado = :estado";
        List<Aluno> alunoJPQLList = entityManager
                .createQuery(jpqlList, Aluno.class)
                .setParameter("estado", estadoParaAdicionar)
                .getResultList();

        // Print
        System.out.println("Consulta alunoJPQL: " + alunoJPQL);
        alunoJPQLList.forEach(Aluno -> System.out.println("Consulta alunoJPQLList: " + Aluno));

        // =============================================================================================================
        // 2.5 - JPA Criteria API + JPA Metamodel
        // One result
        CriteriaQuery<Aluno> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
        Root<Aluno> alunoRoot = criteriaQuery.from(Aluno.class);
        CriteriaBuilder.In<String> inClause = entityManager.getCriteriaBuilder().in(alunoRoot.get(Aluno_.nome));
        inClause.value(nome);
        criteriaQuery.select(alunoRoot).where(inClause);
        Aluno alunoAPICriteria = entityManager.createQuery(criteriaQuery).getSingleResult();

        // List result
        CriteriaQuery<Aluno> criteriaQueryList = entityManager.getCriteriaBuilder().createQuery(Aluno.class);
        Root<Aluno> alunoRootList = criteriaQueryList.from(Aluno.class);
        List<Aluno> alunoAPICriteriaList = entityManager.createQuery(criteriaQueryList).getResultList();

        // Print
        System.out.println("Consulta alunoAPICriteria: " + alunoAPICriteria);
        alunoAPICriteriaList.forEach(Aluno -> System.out.println("Consulta alunoAPICriteriaList: " + Aluno));
    }
}
