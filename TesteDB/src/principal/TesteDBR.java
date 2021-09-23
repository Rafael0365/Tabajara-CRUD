package principal;
import java.util.Scanner;
import java.sql.*;



public class TesteDBR {static Connection con = null;
	public static void main(String[] args) {
		int opcao = 0;
		Scanner scanner = new Scanner(System.in);
		try {
            	con = DriverManager.getConnection("jdbc:ucanaccess://C:\\Projetos\\Tabajara-CRUD/TabajaraDB.accdb");
			}
		catch(Exception e)  { e.printStackTrace();   }
		while (opcao != 9)  {



	        System.out.println("\nSistema Bancário Tabajara\n");
	        System.out.println("Digite a sua opção:");
	        System.out.println("1 - Cadastrar Conta");
	        System.out.println("2 - Consultar Conta");
	        System.out.println("3 - Alterar Conta");
	        System.out.println("4 - Remover Conta");
	        System.out.println("5 - Exibe todas as contas");
	        System.out.println("9 - Sair do sistema");
	        System.out.print("Sua opção: ");
	        opcao = scanner.nextInt();
	        switch (opcao)  {
	        case 1: // cadastrar conta
	            inserirConta();
	            break;
	        
	        case 2: // consultar conta
	            consultarConta();
	            break;
	        case 3: // alterar conta
	            alterarConta();
	            break;
	        case 4: // remover conta
	            removerConta();
	            break;
	        case 5: // exibe todas as contas
	            System.out.println(exibeTodos());
	            break;
        }
	        try {     Thread.sleep(4000);  }
	        
	        catch(Exception e)  {   e.printStackTrace(); }
		}
		// encerramento do programa...
		System.out.println("Tchau...");
}
	public static String Leia(String param) {
	    java.io.DataInputStream dado_lido;
	    String stemporario = "";
	    try   {
	        dado_lido = new java.io.DataInputStream(System.in);
	        stemporario = dado_lido.readLine();
	    }catch (Exception e) { }
	    return stemporario;
	}
	
	public static String exibeTodos() {
		String stemp = "";
		
		try  {  
     
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT numero,     nome_cliente , saldo FROM CONTA");
            System.out.println("Contas na base de dados:\n");
        
            while (rs.next()) {
                 stemp = stemp + "\ncódigo: " + rs.getString("numero") + " nome do cliente: "+ rs.getString("nome_cliente") + "\t saldo: "+ rs.getDouble("saldo");
            }
            rs.close();
            s.close();
            con.close();
            }  catch(Exception e)  {  
                System.out.println("Hi, deu bronca...");  
            }
		return stemp;
	}
	
	public static void consultarConta()  {
        Scanner s = new Scanner(System.in);
        System.out.println("Digite o número da conta:");
        String numero = s.next();
        try  {
            Statement stmt = con.createStatement();
            String query = "SELECT numero, nome_cliente , saldo FROM CONTA WHERE numero=" + numero + "";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                System.out.println("Dados da conta: ");
                System.out.println("Numero da conta: " + rs.getString("numero"));
               
                System.out.println("Nome do cliente: " + rs.getString("nome_cliente"));
                System.out.println("Saldo da conta : " + rs.getDouble("saldo"));

 

            } else {
                System.out.println("Conta não localizada");
            }
            rs.close();
            stmt.close();        
        }
        catch (Exception e) {
            e.printStackTrace();
            }
        }
        
	public static void inserirConta()  {
        String numero = "", nome = "";
        Scanner s = new Scanner(System.in);
        System.out.println("Digite o número da conta:");
        numero = s.next();
        System.out.println("Digite o nome do cliente dono da conta:");
        nome = Leia(nome);
        System.out.println("Digite o saldo inicial da conta:");
        double saldo = s.nextDouble();
        try  {
            Statement stmt = con.createStatement();
            String query = "INSERT INTO CONTA (numero, nome_cliente, saldo) VALUES(" + numero + " , '" + nome + "' , " + saldo + ")";
            stmt.executeUpdate(query);
            stmt.close();
            System.out.println(" Conta cadastrada.");

 

        }
        catch (Exception e) {
            e.printStackTrace();}
        
        }

        
        public static void alterarConta()  {
            String numero = "", nome = "";
            Scanner s = new Scanner(System.in);
            System.out.println("Digite o número da conta:");
            numero = s.next();
            System.out.println("Digite o nome do cliente dono da conta:");
            nome = Leia(nome);
            System.out.println("Digite o saldo inicial da conta:");
            double saldo = s.nextDouble();
            try  {
                String query = "UPDATE CONTA SET nome_cliente='" + nome + "'" + ", saldo = " + saldo + " WHERE " + "numero =" + numero + "";
                Statement stmt = con.createStatement();
                int numLinhas = stmt.executeUpdate(query);
                stmt.close();
                if (numLinhas == 0) {
                    System.out.println("Conta não localizada.");
                }
                else
                    System.out.println(" Conta alterada.");
            }            
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
        public static void removerConta()  {
            String numero = "", nome = "";
            Scanner s = new Scanner(System.in);
            System.out.println("Digite o número da conta:");
            numero = s.next();
            try  {
                String query = "DELETE FROM CONTA WHERE " + "numero =" + numero + "";
                Statement stmt = con.createStatement();
                int numLinhas = stmt.executeUpdate(query);
                stmt.close();
                if (numLinhas == 0) {
                    System.out.println("Conta não localizada.");
                }
                else
                    System.out.println(" Conta removida.");
            }            
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        
      
	
}
