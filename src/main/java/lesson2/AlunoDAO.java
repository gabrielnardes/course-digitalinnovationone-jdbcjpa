package lesson2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    // 1 - Consulta todos os alunos
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
                        rs.getString("estado")
                );

                alunos.add(aluno);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return alunos;
    }

    // 2 - Consulta por id
    public Aluno getById(int id) {
        Aluno aluno = new Aluno();

        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM aluno WHERE id = ?";

            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setInt(1, id);

            ResultSet rs = prst.executeQuery();

            if(rs.next()) {
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println("ERRO: Listagem de alunos falhou");
            e.printStackTrace();
        }
        return aluno;
    }

    // 3 - Inserção
    public void create(Aluno aluno) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO aluno (nome, idade, estado) VALUES (?, ?, ?)";

            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setString(1, aluno.getNome());
            prst.setInt(2, aluno.getIdade());
            prst.setString(3, aluno.getEstado());

            int rowsAffected = prst.executeUpdate();

            System.out.println(rowsAffected + " registros foram adicionados");
        } catch (SQLException e) {
            System.out.println("ERRO: Inserção falhou");
            e.printStackTrace();
        }
    }

    // 4 - Delete
    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM aluno WHERE id = ?";

            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setInt(1, id);

            int rowsAffected = prst.executeUpdate();

            System.out.println(rowsAffected + " registros foram removidos");
        } catch (SQLException e) {
            System.out.println("ERRO: Remoção falhou");
            e.printStackTrace();
        }
    }
}
