import java.util.Scanner;

public class Tamagotchi{
    private int energia, energiaMax;
    private int saciedade, saciedadeMax;
    private int limpeza, limpezaMax;
    private int diamantes;
    private int idade;
    private boolean vivo;

    public Tamagotchi(int energiaMax, int saciedadeMax, int limpezaMax){
        this.energiaMax = energiaMax;
        this.saciedadeMax = saciedadeMax;
        this.limpezaMax = limpezaMax;

        this.energia = energiaMax;
        this.saciedade = saciedadeMax;
        this.limpeza = limpezaMax;

        this.diamantes = 0;
        this.idade = 0;
        this.vivo = true;
    }
    
    public void play(){
        if (!vivo){
            System.out.println("o tamagotchi está morto");
        }else {
            if (energia < 2){
                System.out.println("o tamagotchi está cansado");
            }else if (saciedade < 1){
                System.out.println("o tamagotchi está com fome");                
            }else if (limpeza < 3){
                System.out.println("o tamagotchi está sujo");
            }else {
                System.out.println("brincando!");
                energia -= 2;
                saciedade -= 1;
                limpeza -= 3;
                diamantes += 1;
                idade += 1;
                
                if (energia <= 0 || saciedade <= 0 || limpeza <= 0){
                    System.out.println("o tamagotchi morreu");
                    vivo = false;}
            }
        }
    }

    public void eat(){
        if (!vivo){
            System.out.println("o tamagotchi está morto");
        }else {
            if (energia < 1){
                System.out.println("o tamagotchi está cansado");
            }else if (limpeza < 2){
               System.out.println("o tamagotchi está sujo");
            }else if ((saciedadeMax - saciedade) < 4){
                System.out.println("o tamagotchi não está com fome");
            }else {
                System.out.println("comendo!");
                energia -= 1;
                saciedade += 4;
                limpeza -= 2;
                idade += 1;

                if (energia <= 0 || saciedade <= 0 || limpeza <= 0){
                    System.out.println("o tamagotchi morreu");
                    vivo = false;}
            }
        }
    }

    public void sleep(){
        if (!vivo){
            System.out.println("o tamagotchi está morto");
        }else {
            if ((energiaMax - energia) < 5){
                System.out.println("o tamagotchi não está com sono");
            }else {
                System.out.println("o tamagotchi dormiu " + (energiaMax - energia) + " turnos");
                idade += energiaMax - energia;
                energia = energiaMax;
            }
        }
    }

    public void clean(){
        if (!vivo){
            System.out.println("o tamagotchi está morto");
        }else {
            if (energia < 3){
                System.out.println("o tamagotchi está cansado");
            }else if (saciedade < 1){
                System.out.println("o tamagotchi está com fome");
            }else if (limpeza == limpezaMax){
                System.out.println("o tamagotchi já está limpo");
            }else {
                System.out.println("tomando banho!");
                energia -= 3;
                saciedade -= 1;
                limpeza = limpezaMax;
                idade += 2;

                if (energia <= 0 || saciedade <= 0 || limpeza <= 0){
                    System.out.println("o tamagotchi morreu");
                    vivo = false;}
            }
        }
    }
    public String toString(){
        return "E:" + energia + "/" + energiaMax + ", S:" + saciedade + "/" + saciedadeMax + ", L:" + limpeza + "/" + limpezaMax + ", D:" + diamantes + ", I:" + idade;
    }
}

class Jogo {
        public static void main(String[] args){
            Scanner scanner = new Scanner(System.in);
            Tamagotchi tam = new Tamagotchi(0, 0, 0);
            System.out.println("Digite \"ajuda\" para listar os comandos disponíveis");
            while(true){
                String line = scanner.nextLine();
                String[] ui = line.split(" ");
                if(ui[0].equals("ajuda")){
                    String ajuda = "\npara:";
                    ajuda += "\n1. criar um pet, digite \"init\" seguido da energia, saciedade e limpeza máximas;";
                    ajuda += "\n2. mostrar status do pet, digite \"show\";";
                    ajuda += "\n3. brincar, digite \"play\";";
                    ajuda += "\n4. dormir, digite \"sleep\";";
                    ajuda += "\n5. tomar banho, digite \"clean\";";
                    ajuda += "\n6. sair do programa, digite \"exit\".";
                    System.out.println(ajuda);
                }
                if (ui[0].equals("init")){
                    tam = new Tamagotchi(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]), Integer.parseInt(ui[3]));
                }else if (ui[0].equals("play")){
                    tam.play();
                }else if (ui[0].equals("show")){
                    System.out.println(tam);
                }else if (ui[0].equals("eat")){
                    tam.eat();
                }else if (ui[0].equals("sleep")){
                    tam.sleep();
                }else if (ui[0].equals("clean")){
                    tam.clean();
                }else if (ui[0].equals("exit")){
                    break;
                }
            }
        }
}