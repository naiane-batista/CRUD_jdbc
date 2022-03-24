package parte3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {    //classe que vai acessar ao banco de dados

    // 1 - Consulta
    //o object list vai m trazer todos os alunos do bd e vai me retornar uma lista de alunos
    public List<Aluno> list(){

        List<Aluno>alunos =new ArrayList<>();

        //acessar o banco entao fazr uma conexao

        try(Connection conn= ConnectionFactory.getConnection()){

            //criar uma consulta sql
          PreparedStatement stmt =  conn.prepareStatement("select * from aluno");

          ResultSet rs= stmt .executeQuery();//executeQuery=usado p executar select

            //percorrer esses valores ,e retorna o proximo, retorna um boolean,
           while(rs.next()) {

               Aluno aluno=new Aluno(
                       rs.getInt("id"),
                       rs.getString("nome"),
                       rs.getInt("idade"),
                       rs.getString("estado")
               );

               //enquanto houver registros vammos add na nossa lista alunos
               alunos.add(aluno);   //aluno ta vazio, nao da p pegar os valores do nosso resultSet,usamos construtor
           }

        }catch (SQLException e){
            System.out.println("Listagem de alunos FALHOU!");
            e.printStackTrace();
        }
        return  alunos;
    }
    //outro estilo de consulta
    /* try (Connection conn = ConnectionFactory.getConnection()) {
            //Preparar consulta SQL.
            String sql = "SELECT * FROM aluno";

            //Preparar statement com os parâmetros recebidos (nesta função não tem parâmetros, pois irá retornar todos os valores da tabela aluno)
            PreparedStatement stmt = conn.prepareStatement(sql);

            //Executa consulta e armazena o retorno da consulta no objeto "rs".
            ResultSet rs = stmt.executeQuery();

            //Criar um objeto aluno e guardar na lista de alunos.
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String estado = rs.getString("estado");

                alunos.add(new Aluno(
                        id,
                        nome,
                        idade,
                        estado
                ));
            }
        } catch (SQLException e) {
            System.out.println("Listagem de alunos FALHOU!");
            e.printStackTrace();
        }

        //Retornar todos os alunos encontrados no banco de dados.
        return alunos;
    }*/

    // 1.1 - Consulta com filtro
    public Aluno getById(int id) {
        //Preparar objeto aluno para receber os valores do banco de dados.
        Aluno aluno = new Aluno();

        try (Connection conn = ConnectionFactory.getConnection()) {
            //Preparar consulta SQL
            String sql = "SELECT * FROM aluno WHERE id = ?";

            //Preparar statement com os parâmetros recebidos
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            //Executa consulta e armazena o retorno da consulta no objeto "rs".
            ResultSet rs = stmt.executeQuery();

            //Guardar valores retornados da tabela aluno no objeto aluno
            if (rs.next()){
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setEstado(rs.getString("estado"));
            }

        } catch (SQLException e) {
            System.out.println("Listagem de alunos FALHOU!");
            e.printStackTrace();
        }

        //Retorna aluno encontrado no banco de dados.
        return aluno;
    }

    // ---2- inserção---
    public void create(Aluno aluno){
        try(Connection conn=ConnectionFactory.getConnection()){

            //Preparar SQL para inserção de dados do aluno.
            String sql="INSERT INTO aluno(nome,idade,estado)VALUES(?,?,?)";

            //Preparar statement com os parâmetros recebidos
            PreparedStatement stmt=conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2,aluno.getIdade());
            stmt.setString(3,aluno.getEstado());

            //Executa inserção e armazena o numero de linhas afetadas
                 int rowsAffected  =stmt.executeUpdate();//dira o numero de linhas q foi afetada

             System.out.println("Inserção BEM SUCEDIDA!. Foi adicionada " + rowsAffected + " linha");

        }catch (SQLException e){
            System.out.println("Inserção de Aluno falhou");
            e.printStackTrace();
        }


    }
        //-------3-delete----
    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            //Preparar SQL para deletar uma linha.
            String sql = "DELETE FROM aluno WHERE id = ?";

            //Preparar statement com os parâmetros recebidos
            PreparedStatement stmt= conn.prepareStatement(sql);
            stmt.setInt(1,id);

            //Executa delete e armazena o numero de linhas afetadas
            int rowsAffected=stmt.executeUpdate();

            System.out.println("Delete BEM SUCEDIDA! Foi deletada " + rowsAffected + " linha");

        }catch (SQLException e){
            System.out.println("Delete FALHOU");
            e.printStackTrace();
        }
    }
            ///4----Atualizar
            public void update(Aluno aluno) {
                try (Connection conn = ConnectionFactory.getConnection()) {

                    //Preparar SQL para atualizar linhas.
                    String sql = "UPDATE aluno SET nome = ?, idade = ?, estado = ? WHERE id = ?";

                    //Preparar statement com os parâmetros recebidos
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, aluno.getNome());//primeira ? coloco nome, o proximo idade e etc..
                    stmt.setInt(2, aluno.getIdade());
                    stmt.setString(3, aluno.getEstado());
                    stmt.setInt(4, aluno.getId());

                    //Executa atualização e armazena o numero de linhas afetadas
                    int rowsAffected = stmt.executeUpdate();

                    System.out.println("Atualização BEM SUCEDIDA! Foi atualizada: " + rowsAffected + " linha");
                } catch (SQLException e) {
                    System.out.println("Atualização FALHOU!");
                    e.printStackTrace();
                }
            }


}







