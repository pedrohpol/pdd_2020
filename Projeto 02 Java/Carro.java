import java.util.Scanner;
public class Carro {
    int gas;
    int pass;
    int km;
    Carro(int gas, int pass, int km){
        this.gas = gas;
        this.pass = pass;
        this.km = km;
    }

    void embarcar(){
        if(pass < 2){
            System.out.println("\n01 (um) passageiro entrou.");
            pass += 1;
        }else {
            System.out.println("\nO carro está cheio.");
        }
    }

    void desembarcar(){
        if (pass > 0){
            System.out.println("\n01 (um) passageiro saiu.");
            pass -= 1;
        }else {
            System.out.println("\nNão há ninguém no carro.");
        }
    }

    void dirigir(int qnt){ 
        if(gas > 0){
            if(pass == 0){
                System.out.println("\nNão há passageiros no carro.");
            }else if (pass > 0 && gas >= qnt){
                System.out.println("\nDirigindo " + qnt + " km.");
                gas -= qnt;
                km += qnt;
            }else if(gas < qnt){
                System.out.println("\nNão há combustível suficiente para dirigir a distância requisitada. Dirigindo apenas " + gas + " km.");
                km += gas;
                gas = 0;   
            }
        }
        if(gas == 0){
            System.out.println("Não há combustivel no carro.");
            if (pass == 0){
                System.out.println("Não há passageiros no carro.");
            }
        }

    }
    
    void abastecer(int qnt){
        if(gas < 100 && gas + qnt < 100){
            System.out.println("\nAdicionado " + qnt + " l de combustível.");
            gas += qnt;
        }else if(gas < 100 && gas + qnt >= 100){
            System.out.println("\nNão foi possível adicionar a quantidade de combustível por inteiro. Adicionando o que faltava para encher o tanque e descartando o resto.");
            gas = 100;
        }
        else {
            System.out.println("O tanque já está cheio.");
        }
    }

    public String toString(){
        return "\nnº. de passageiros: " + pass + "\ncombustível: " + gas + " l\nquilometragem: " + km + " km";
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Carro carro = new Carro(0, 0, 0);
        System.out.println("Digite \"ajuda\" para listar os comandos disponíveis");
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if(ui[0].equals("ajuda")){
                System.out.println("\npara:");
                System.out.println("\n1. embarcar passageiros, digite: \"embarcar\";");
                System.out.println("2. abastecer o carro, digite \"abastecer\" e a quantidade;");
                System.out.println("3. dirigir, digite \"dirigir\" e a distância;");
                System.out.println("4. ver informações sobre o carro, digite \"info\";");
                System.out.println("5. sair do programa, digite \"sair\".\n");
            }
            if(ui[0].equals("sair")){
                break;
            }else if(ui[0].equals("info")){
                System.out.println(carro);
            }else if(ui[0].equals("embarcar")){
                carro.embarcar();
            }else if(ui[0].equals("desembarcar")){
                carro.desembarcar();
            }else if(ui[0].equals("abastecer")){
                carro.abastecer(Integer.parseInt(ui[1]));
            }else if(ui[0].equals("dirigir")){
                carro.dirigir(Integer.parseInt(ui[1]));
            }
        }
    }
}
