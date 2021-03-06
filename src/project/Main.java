package project;

import project.models.Cliente;
import project.models.Emprestimo;
import project.models.Livro;
import project.models.Periodico;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Bem vindo, bibliotecário!");
        
        Livro teste = new Livro.Builder().withTitulo("teste").build();
        System.out.println(teste.toString());
        
        //Biblioteca biblioteca = Biblioteca.getInstance();
        //biblioteca.insereLivro(teste);
        //biblioteca.insereLivro(new Livro.Builder().build());
        //Livro busca = biblioteca.buscaPorTitulo("teste");
        //System.out.println(busca.toString());
        
        BibliotecaFacade liber = new BibliotecaFacade();
        liber.novoCliente();
        liber.novoCliente(); // para testar a facade insira dois clientes com o mesmo email
    }

}
