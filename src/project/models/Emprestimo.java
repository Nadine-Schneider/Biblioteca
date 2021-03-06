package project.models;

import java.time.LocalDate;

public class Emprestimo {
    private Cliente cliente;
    private Livro livro;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    
    public Emprestimo(Cliente cliente, Livro livro, LocalDate dataRetirada) {
        this.cliente = cliente;
        this.livro = livro;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataRetirada.plusMonths(1);
    }
    
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public Livro getLivro() {
        return this.livro;
    }
    
    public LocalDate getDataRetirada() {
        return this.dataRetirada;
    }
    
	public LocalDate getDataDevolucao() {
        return this.dataDevolucao;
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Emprestimo [cliente=");
		sb.append(cliente);
		sb.append(", livro=");
		sb.append(livro);
		sb.append(", dataRetirada=");
		sb.append(dataRetirada);
		sb.append(", dataDevolucao=");
		sb.append(dataDevolucao);
		sb.append("]");
		return sb.toString();
	}
}
