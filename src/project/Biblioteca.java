package project;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Biblioteca {
    private static Biblioteca instance = new Biblioteca();
    
    public static Biblioteca getInstance() {
        return instance;
    }
    
    private Biblioteca() {
    }
    
    private static List<Livro> livros = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Emprestimo> emprestimos = new ArrayList<>();
    
    // Livros
    public void insereLivro(Livro livro) {
        livros.add(livro);
    }
    
    public void removeLivro(Livro livro) {
        int indice = livros.indexOf(livro);
        livros.remove(indice);
    }
    
    public void removeLivroPorISBN(Integer isbnLivro) {
        int indice = buscaIndiceLivro(isbnLivro);
        livros.remove(indice);
    }
    
    public void removeLivroPorNome(String tituloLivro) {
        int indice = buscaIndiceLivro(tituloLivro);
        livros.remove(indice);
    }
    
    public Livro buscaPorTitulo(String tituloLivro) {
        int indice = buscaIndiceLivro(tituloLivro);
        return livros.get(indice);
    }
    
    public Livro buscaPorISBN(Integer isbnLivro) {
        int indice = buscaIndiceLivro(isbnLivro);
        return livros.get(indice);
    }
    
    // Clientes
    public void insereCliente(Cliente cliente) {
        clientes.add(cliente);
    }
    
    public void removeCliente(Cliente cliente) {
        int indice = clientes.indexOf(cliente);
        clientes.remove(indice);
    }
    
    public void removeClientePorEmail(String emailCliente) {
        int indice = buscaIndiceCliente(emailCliente);
        clientes.remove(indice);
    }
    
    public void removeClientePorId(Integer idCliente) {
        int indice = buscaIndiceCliente(idCliente);
        clientes.remove(indice);
    }
    
    public Cliente buscaPorEmail(String emailCliente) {
        int indice = buscaIndiceCliente(emailCliente);
        return clientes.get(indice);
    }
    
    public Cliente buscaPorId(Integer idCliente) {
        int indice = buscaIndiceCliente(idCliente);
        return clientes.get(indice);
    }
    // Empréstimos
    public void registraRetirada(Cliente cliente, Livro livro, LocalDate dataRetirada) {
        Emprestimo emprestimo = new Emprestimo(cliente, livro, dataRetirada);
        emprestimos.add(emprestimo);
    }
    
    public void registraRetirada(Cliente cliente, Livro[] livros, LocalDate dataRetirada) {
        for (Livro livro : livros) {
	        Emprestimo emprestimo = new Emprestimo(cliente, livro, dataRetirada);
            emprestimos.add(emprestimo);
	    }
    }
    
    public void registraDevolucao(Cliente cliente, Livro livro) {
        for (Emprestimo emprestimo : emprestimos) {
	        if (emprestimo.getCliente().equals(cliente) && emprestimo.getLivro().equals(livro)) {
                emprestimos.remove(emprestimo);
                break;
            }
	    }
    }
    
    public void registraDevolucao(Cliente cliente, Livro[] livros) {
        for (Livro livro : livros) {
	        registraDevolucao(cliente, livro);
	    }
    }
    
    // busca indice
    public static int buscaIndiceLivro(String tituloLivro) {
        int indice = 0;

        for(Livro livro : livros) {
            if(tituloLivro.equalsIgnoreCase(livro.getTitulo()))
                return indice;
            indice++;
        }
        return -1;
    }
    
    public static int buscaIndiceLivro(Integer isbn) {
        int indice = 0;

        for(Livro livro : livros) {
            if(isbn == livro.getIsbn())
                return indice;
            indice++;
        }
        return -1;
    }
    
    public static int buscaIndiceCliente(String emailCliente) {
        int indice = 0;

        for(Cliente cliente : clientes) {
            if(emailCliente.equalsIgnoreCase(cliente.getEmail()))
                return indice;
            indice++;
        }
        return -1;
    }
    
    public static int buscaIndiceCliente(Integer idCliente) {
        int indice = 0;

        for(Cliente cliente : clientes) {
            if(idCliente == cliente.getId())
                return indice;
            indice++;
        }
        return -1;
    }
}
