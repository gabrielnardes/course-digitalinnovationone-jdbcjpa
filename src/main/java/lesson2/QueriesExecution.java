package lesson2;

public class QueriesExecution {
    public static void main(String[] args) {
        AlunoDAO alunoDAO = new AlunoDAO();

//        System.out.println("\n1 - Consulta todos os alunos");
//        List<Aluno> alunos = alunoDAO.list();
//        alunos.stream().forEach(System.out::println);
//
//        System.out.println("\n2 - Consulta por id");
//        Aluno aluno0 = alunoDAO.getById(3);
//        System.out.println(aluno0);
//
//        System.out.println("\n3 - Inserção");
//        Aluno aluno1 = new Aluno("Gabriel", 90, "DF");
//        alunoDAO.create(aluno1);
//
//        System.out.println("\n4 - Remoção");
//        alunoDAO.delete(5);

        System.out.println("\n5 - Atualização");
        Aluno alunoAtualizado = alunoDAO.getById(7);
        alunoAtualizado.setNome("Gabriel Silva e Silva");
        alunoAtualizado.setIdade(1);
        alunoAtualizado.setEstado("XX");
        alunoDAO.update(alunoAtualizado);
    }
}
