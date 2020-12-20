import java.util.Scanner;
class Grafite{
	float calibre;
	String dureza;
	int tamanho;

	public Grafite(float calibre, String dureza, int tamanho){
		this.calibre = calibre;
		this.dureza = dureza;
		this.tamanho = tamanho;
	}

	public int desgaste(){
		if (dureza.equals("HB")){
			return 1;
		}else if (dureza.equals("2B")){
			return 2;
		}else if (dureza.equals("4B")){
			return 4;
		}else if (dureza.equals("6B")){
			return 6;
		}
		return 2;
	}

	public String toString(){
		return "[C: " + calibre + "; D: " + dureza + "; T: " + tamanho + "]";
	}
}

public class Lapiseira {
	float calibre;
	Grafite grafite;

	public Lapiseira(float calibre, Grafite grafite){
		this.calibre = calibre;
		this.grafite = grafite;
	}

	public boolean inserir(Grafite grafite){
		if(this.grafite == null){
			if(this.calibre == grafite.calibre){
				this.grafite = grafite;
				return true;
			} else{
				System.out.println("O calibre é incompatível");
				return false;
			}
		} else{
			System.out.println("Já há grafite na lapiseira");
			return false;
		}
	}

	void escrever(int folhas){
		int desgaste = grafite.desgaste();
		if(grafite != null){
			for (int i = folhas; i >= 0; i--){
				if(grafite.tamanho > 0){
					grafite.tamanho -= desgaste;
				} else{
					System.out.println("O grafite acabou");
					if(i > 0){
				               System.out.println("Foi possível completar apenas " + (folhas - i) + " folhas");
					}
					grafite = null;
				}
			}
		}
	}

	public Grafite remover(){
		if(this.grafite != null){
			System.out.println("O grafite foi removido");
			grafite = null;
			return grafite;
		} else{
			System.out.println("Não há grafite na lapiseira");
			return grafite;
		}
	}

	public String toString(){
		return "Calibre: " + calibre + "; Grafite: " + grafite; 
	}
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		Lapiseira lapiseira = new Lapiseira(0, null);
		Grafite grafite = new Grafite(0, null, 0);
		boolean lap = false;
		boolean graf = false;
		System.out.println("Digite \"ajuda\" para listar os comandos disponíveis");

		while(true){
			String semLap = "sem lapiseira! use o comando \"nova\" seguido do calibre desejado p/ criar uma lapiseira nova";
			String semGraf = "sem grafite! use o comando \"inserir\" seguido do calibre, dureza (HB, 2B, 4B ou 6B) e tamanho do grafite";
			String line = scanner.nextLine();
			String[] ui = line.split(" ");
			if(ui[0].equals("ajuda")){
				String ajuda = "\npara:";
				ajuda += "\n1. criar uma nova lapiseira, digite \"nova\" seguido do calibre desejado;";
				ajuda += "\n2. colocar um grafite na lapiseira criada, digite \"inserir\" seguido do calibre, dureza (HB, 2B, 4B ou 6B) e tamanho;";
				ajuda += "\n3. escrever, digite \"escrever\" seguido da quantidade de páginas;";
				ajuda += "\n4. remover o grafite, digite \"remover\";";
				ajuda += "\n5. ver informações sobre a lapiseira, digite \"info\";";
				ajuda += "\n6. sair do programa, digite \"sair\".";
				System.out.println(ajuda);
			}else if(ui[0].equals("nova")){
				lapiseira = new Lapiseira(Float.parseFloat(ui[1]), null);
				lap = true;
			}else if(ui[0].equals("inserir")){
				if(lap){
					grafite = new Grafite(Float.parseFloat(ui[1]), ui[2], Integer.parseInt(ui[3]));
					lapiseira.inserir(grafite);
				}else if (lap = false){
					System.out.println(semLap);
				}
			}else if(ui[0].equals("escrever")){
				if (lap){
					lapiseira.escrever(Integer.parseInt(ui[1]));
				}else if (lap = false){
					System.out.println(semLap);
				}else if (lapiseira.grafite == null){
					System.out.println(semGraf);
				}
			}else if(ui[0].equals("remover")){
				lapiseira.remover();
			}else if(ui[0].equals("info")){
				if (lapiseira.calibre == 0){
					System.out.println(semLap);
				}else {
					System.out.println("\n" + lapiseira + "\n");
				}
			}else if(ui[0].equals("sair")){
				break;
			}else{
				System.out.println("comando inválido, digite \"ajuda\" para listar os comandos disponíveis");
			}
		}
	}
}