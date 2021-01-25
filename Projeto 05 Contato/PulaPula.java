import java.util.ArrayList;
import java.util.Scanner;

class Crianca {
    String nome;
    String idade;

    public Crianca(String nome, String idade){
        this.nome = nome;
        this.idade = idade;
    }

    public String toString(){
        return nome + ":" + idade;
    }
}

public class PulaPula{
    static ArrayList<Crianca> fila = new ArrayList<>();
    static ArrayList<Crianca> brincando = new ArrayList<>();

    public void chegou(Crianca crianca){
        fila.add(crianca);
        System.out.println("criança colocada na fila");
    }

    public void entrar(){
        if(fila.size() == 0){
            System.out.println("não há crianças na fila");
        }else {
            brincando.add(fila.get(fila.size() - 1));
            fila.remove(fila.get(fila.size() - 1));
            System.out.println("criança colocada no pula pula");
        }
    }

    public void sair(){
        if(brincando.size() == 0){
            System.out.println("não há crianças no pula pula");
        }else {
            fila.add(0, brincando.get(brincando.size() - 1));
            brincando.remove(brincando.get(brincando.size() - 1));
            System.out.println("criança saiu do pula pula e foi para o fim da fila");
        }
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        PulaPula pula = new PulaPula();
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if(ui[0].equals("chegou")){
                pula.chegou(new Crianca(ui[1], ui[2]));
            }else if(ui[0].equals("show")){
                System.out.println("na fila:" + fila + " brincando:" + brincando);
            }else if(ui[0].equals("entrar")){
                pula.entrar();
            }else if(ui[0].equals("sair")){
                pula.sair();
            }else if(ui[0].equals("end")){
                break;
            }else{
                System.out.println("comando inválido");
            }
        }
        scanner.close();
    }
}