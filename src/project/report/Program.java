package project.report;

import java.util.Scanner;

public class Program {
	 public static void main(String[] args) {
	
	Scanner scanner = new Scanner(System.in);
	
	System.out.println("Digite o nome do Relat�rio: ");
	String nomeRelatorio = scanner.nextLine();
	IReportStrategy rs = ReportFactory.GetRelatorio(nomeRelatorio);
	  
	
	if (rs != null){
		rs.showData();
	} else
		System.out.println("Relat�rio "+ nomeRelatorio + " n�o encontrado.");
		  
		System.out.println("\nPressione Enter para Sair...");
		scanner.nextLine();
	}
}