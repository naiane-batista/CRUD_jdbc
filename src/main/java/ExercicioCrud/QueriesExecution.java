package ExercicioCrud;

import parte3.Aluno;
import parte3.AlunoDAO;

import java.util.List;

public class QueriesExecution {
    public static void main(String[] args) {


        CursoDao cursoDAO = new CursoDao();

        //1------consulta--------
        List<Curso> cursos = cursoDAO.list();

       // cursos.stream().forEach(System.out::println);

        //-----Consulta com filtro
        Curso cursoComFiltro=cursoDAO.getById(1);
        System.out.println(cursoComFiltro);

        //---inserção
        Curso cursoInsercao=new Curso(
                //"Nelcy",
               // 257
                );

        //cursoDAO.create(cursoInsercao);


        //-----------delete-----------
     //   cursoDAO.delete(1);

        //----------------update-------------

        Curso cursoParaAtualizar = cursoDAO.getById(5);
        cursoParaAtualizar.SetNome("Alcimar");
        cursoParaAtualizar.setDuracao_horas(350.0);



        cursoDAO.update(cursoParaAtualizar);
    }







}
