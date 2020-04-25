package project;

import java.time.LocalDate;
import java.util.Scanner;

import project.models.Cliente;
import project.models.Livro;

public class BibliotecaFacade {

    Biblioteca biblioteca = Biblioteca.getInstance();
    Scanner scanner = new Scanner(System.in);
    Cliente ultimoCliente = null;
    Livro ultimoLivro = null;

    public void novoCliente() {
        System.out.println("Entre com os dados do novo cliente: ");
        Cliente novoCliente = leDadosCliente();
        System.out.println("Autenticando novo cliente...");
        int indiceCliente = biblioteca.buscaIndiceCliente(novoCliente.getEmail());
        if (indiceCliente == -1) {
            System.out.println("Inserindo cliente aos registros...");
            biblioteca.insereCliente(novoCliente);
            ultimoCliente = novoCliente;
            System.out.println("Cliente registrado com sucesso!");
        } else {
            System.out.println("Cadastro rejeitado! Cliente já registrado no sistema: ");
            Cliente registrado = biblioteca.buscaPorEmail(novoCliente.getEmail());
            ultimoCliente = registrado;
            System.out.println(registrado.toString());
        }
    }

    public void buscaCliente() {
        Cliente cliente = null;
        String emailCliente = "";
        Integer idCliente = null;
        System.out.println("Selecione o campo de pesquisa: ");
        System.out.println("0) Sair \n1) ID \n2) Email ");
        System.out.print("Seleção: ");
        switch (scanner.nextInt()) {
            case 0:
                break;
            case 1:
                System.out.println("Digite o ID do cliente: ");
                idCliente = scanner.nextInt();
                // cliente = biblioteca.buscaPorId(idCliente);
                break;
            case 2:
                System.out.println("Digite o email do cliente: ");
                while(emailCliente.isEmpty())
                    emailCliente = scanner.next();
                cliente = biblioteca.buscaPorEmail(emailCliente);
                break;
            default:
                System.err.println("Opção inválida!");
                break;
        }
        if (emailCliente != null || idCliente != null) {
            if (cliente != null) {
                System.out.println("Cliente encontrado: ");
                System.out.println(cliente.toString());
                ultimoCliente = cliente;
            } else {
                System.out.println("Nenhum cliente atende ao campo pesquisado!");
            }
        }
        System.out.println("Retornando...");
    }

    public void excluiCliente() {
        boolean excluindo = true;
        while (excluindo) {
            if (ultimoCliente != null) {
                System.out.println("Deseja excluir o cadastro do seguinte cliente?");
                System.out.println(ultimoCliente.toString());
                System.out.println("1) Sim \n2) Não ");
                System.out.print("Seleção: ");
                int selecao = 0;
                selecao = scanner.nextInt();
                switch (selecao) {
                    case 1:
                        System.out.println("Excluindo...");
                        biblioteca.removeCliente(ultimoCliente);
                        System.out.println("Cliente removido com sucesso!");
                        excluindo = false;
                        break;
                    case 2:
                        System.out.println("Encaminhando para busca do cliente...");
                        ultimoCliente = null;
                        break;
                    default:
                        System.err.println("Opção inválida!");
                        break;
                }
            } else {
                buscaCliente();
            }
        }
        System.out.println("Retornando ao menu cliente...");
    }

    public void adicionaNovoLivro() {
        System.out.println("Entre com os dados do novo livro: ");
        Livro novoLivro = lerDadosLivro();
        System.out.println("Autenticando novo cliente...");
        int indiceLivro = biblioteca.buscaIndiceLivro(novoLivro.getIsbn());

        if (indiceLivro == -1) {
            System.out.println("Adicionando livro no acervo...");
            biblioteca.insereLivro(novoLivro);
            System.out.println("Livro adicionado com sucesso!");
            System.out.println(novoLivro.toString());
        } else {
            System.out.println("Cadastro rejeitado! Livro já consta no acervo: ");
            Livro registrado = biblioteca.buscaPorISBN(novoLivro.getIsbn());
            System.out.println(registrado.toString());
        }
    }

    public void buscaLivro() {
        Livro livro = null;
        String titulo = "";
        Integer isbn = null;
        System.out.println("Selecione o campo de pesquisa: ");
        System.out.println("0) Sair \n1) ISBN \n2) Titulo ");
        System.out.print("Seleção: ");
        switch (scanner.nextInt()) {
            case 0:
                break;
            case 1:
                System.out.println("Digite o ISBN do livro: ");
                isbn = scanner.nextInt();
                livro = biblioteca.buscaPorISBN(isbn);
                break;
            case 2:
                System.out.println("Digite o titulo do livro: ");
                while (titulo.isEmpty())
                    titulo = scanner.nextLine();
                livro = biblioteca.buscaPorTitulo(titulo);
                break;
            default:
                System.err.println("Opção inválida!");
                break;
        }
        if (titulo != null || isbn != null) {
            if (livro != null) {
                System.out.println("Livro encontrado: ");
                System.out.println(livro.toString());
                ultimoLivro = livro;
            } else {
                System.out.println("Nenhum livro atende ao campo pesquisado!");
            }
        }
        System.out.println("Retornando...");
    }

    public void excluiLivro() {
        boolean excluindo = true;
        while (excluindo) {
            if (ultimoLivro != null) {
                System.out.println("Deseja excluir o cadastro do seguinte livro?");
                System.out.println(ultimoLivro.toString());
                System.out.println("1) Sim \n2) Não ");
                System.out.print("Seleção: ");
                int selecao = 0;
                selecao = scanner.nextInt();
                switch (selecao) {
                    case 1:
                        System.out.println("Excluindo...");
                        biblioteca.removeLivro(ultimoLivro);
                        System.out.println("Livro removido com sucesso!");
                        excluindo = false;
                        break;
                    case 2:
                        System.out.println("Encaminhando para busca do livro...");
                        ultimoLivro = null;
                        break;
                    default:
                        System.err.println("Opção inválida!");
                        break;
                }
            } else {
                buscaLivro();
            }
        }
        System.out.println("Retornando ao menu livro...");
    }

    public void registraRetirada() {
        Cliente clienteRetirando = null;
        while (clienteRetirando == null) {
            if (ultimoCliente != null) {
                System.out.println("Este cliente está requisitando a retirada?");
                System.out.println(ultimoCliente.toString());
                System.out.println("1) Sim \n2) Não ");
                System.out.print("Seleção: ");
                int selecao = 0;
                selecao = scanner.nextInt();
                switch (selecao) {
                    case 1:
                        clienteRetirando = ultimoCliente;
                        break;
                    case 2:
                        System.out.println("Encaminhando para busca do cliente...");
                        ultimoCliente = null;
                        break;
                    default:
                        System.err.println("Opção inválida!");
                        break;
                }
            } else {
                buscaCliente();
            }
        }
        System.out.println("Checando status do cliente...");
        // ver se tem debito pendente ou livro atrasado
        System.out.println("Encaminhando para busca de livros...");
        boolean adicionandoLivros = true;
        Livro livroRetirado = null;
        String livrosRetirados = "";
        while (adicionandoLivros) {
            buscaLivro();
            livroRetirado = ultimoLivro;
            biblioteca.registraRetirada(clienteRetirando, livroRetirado, LocalDate.now());
            livrosRetirados += livroRetirado.toString() + "\n";
            System.err.println("Deseja retirar mais livros?");
            System.out.println("1) Sim \n2) Não ");
            System.out.print("Seleção: ");
            int escolha = 0;
            while (escolha != 1 || escolha != 2) {
                escolha = scanner.nextInt();
            }
            if (escolha == 1) {
                continue;
            } else {
                adicionandoLivros = false;
            }
        }
        System.out.println("Registrando retirada...");
        System.out.println("Dados da retirada: ");
        System.out.println(clienteRetirando.toString());
        System.out.println(livrosRetirados);
        System.out.println("Data de devolução: " + LocalDate.now().plusMonths(1));
    }

    private Livro lerDadosLivro() {
        System.out.println("Digite o titulo do livro: ");
        String titulo = scanner.nextLine();

        System.out.println("Digite o autor do livro: ");
        String autor = scanner.nextLine();

        System.out.println("Digite a editora do livro: ");
        String editora = scanner.nextLine();

        System.out.println("Digite o isbn do livro: ");
        Integer isbn = scanner.nextInt();

        System.out.println("Digite a quantidade de páginas do livro: ");
        Integer numeroPaginas = scanner.nextInt();

        System.out.println("Digite a data de publicação do livro no formato DD-MM-AA: ");
        String data = scanner.next();

        String arr[] = data.split("-");
        LocalDate dataPublicacao = LocalDate.of(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]),
                Integer.parseInt(arr[0]));

        return new Livro.Builder().withTitulo(titulo).withAutor(autor).withEditora(editora).withIsbn(isbn)
                .withNumeroPaginas(numeroPaginas).withDataPublicacao(dataPublicacao).build();
    }

    private Cliente leDadosCliente() {
        String nome = "";
        String email = "";

        System.out.println("Digite o nome do cliente: ");
        while (nome.isEmpty())
            nome = scanner.nextLine();

        System.out.println("Digite o email do cliente: ");
        while (email.isEmpty())
            email = scanner.next(); // leitura do nome e email(validar? @ e .com)

        return new Cliente(nome, email);
    }
}
