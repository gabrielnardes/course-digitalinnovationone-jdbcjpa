package lesson2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    // 1 - Consulta
    public List<Aluno> list() {
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement prst = conn.prepareStatement("select * from aluno");
            ResultSet rs = prst.executeQuery();

            while(rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("nome")
                );

                alunos.add(aluno);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
            return alunos;
    }
}
