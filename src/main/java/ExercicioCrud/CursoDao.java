package ExercicioCrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDao {

    //1----------------------------consulta-----------------
    public List<Curso>list(){

        List<Curso> cursos=new ArrayList<>();

        try (Connection conn=ConnectionFactory.getConnection()){
            PreparedStatement stmt=conn.prepareStatement("select * from curso");
            ResultSet rs=stmt.executeQuery();

            while (rs.next()){
                Curso curso=new Curso(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("duracao_horas")
                );

                cursos.add(curso);
            }


        }catch (SQLException e){
            System.out.println("Listagem de cursos FALHOU!");
            e.printStackTrace();
        }

        return cursos;


    }

            //--consulta com filtro

        public Curso getById(int id){
            Curso curso=new Curso();

            try(Connection conn=ConnectionFactory.getConnection()){

                String sql="select * from curso where id =?";

                PreparedStatement stmt=conn.prepareStatement(sql);
                stmt.setInt(1,id);

               ResultSet rs= stmt.executeQuery();

               if (rs.next()){
                   curso.setId(rs.getInt("id"));
                   curso.SetNome(rs.getString("nome"));
                   curso.setDuracao_horas(rs.getDouble("duracao_horas"));
               }


            }catch (SQLException e){
                System.out.println("Listagem do filtro FALHOU!");
                e.printStackTrace();
            }

            return curso;

        }
        //--------------------inserção------------
        public void create(Curso curso){
            try(Connection conn=ConnectionFactory.getConnection()){

                String sql="INSERT INTO curso(nome,duracao_horas)VALUES(?,?)";


                PreparedStatement stmt=conn.prepareStatement(sql);
                stmt.setString(1, curso.getNome());
                stmt.setDouble(2,curso.getDuracao_horas());

              int rowsAffected = stmt.executeUpdate();
                System.out.println("Inserção BEM SUCEDIDA!. Foi adicionada " + rowsAffected + " linha");


            }catch (SQLException e){
                System.out.println("Inserção FALHOU!");
                e.printStackTrace();
            }
            }

            //delete
        public  void delete(int  id){
            try (Connection conn = parte3.ConnectionFactory.getConnection()) {

                //Preparar SQL para deletar uma linha.
                String sql = "DELETE FROM curso WHERE id = ?";

                //Preparar statement com os parâmetros recebidos
                PreparedStatement stmt= conn.prepareStatement(sql);
                stmt.setInt(1,id);

                //Executa delete e armazena o numero de linhas afetadas
                int rowsAffected=stmt.executeUpdate();

                System.out.println("Delete BEM SUCEDIDA! Foi deletada " + rowsAffected + " linha");

            }catch (SQLException e){
                System.out.println("Inserção FALHOU!");
                e.printStackTrace();
            }

        }
            //--atualizar
            public void update(Curso curso){
                try(Connection conn=ConnectionFactory.getConnection()) {

                    String sql="update curso set nome= ?,duracao_horas= ? where id=? ";

                    PreparedStatement stmt=conn.prepareStatement(sql);
                    stmt.setString(1,curso.getNome());
                    stmt.setDouble(2,curso.getDuracao_horas());
                    stmt.setInt(3,curso.getId());

                  int rowsAffected  =stmt.executeUpdate();

                    System.out.println("Update BEM SUCEDIDA! Foi deletada " + rowsAffected + " linha");


                }catch (SQLException e){
                    System.out.println("Update FALHOU!");
                    e.printStackTrace();
                }
            }




}
