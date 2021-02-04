import java.util.ArrayList;
import java.util.Scanner;

class Espiral{
    public String nome;
    public int qtd;
    public float preco;

    public Espiral(String nome, int qtd, float preco){
        this.nome = nome;
        this.qtd = qtd;
        this.preco = preco;
    }

    public String getNome(){
        return nome;
    }
    public float getPreco(){
        return preco;
    }
    public int getQtd(){
        return qtd;
    }

    public void comp(){
        qtd --;
    }

    public String toString(){
        return " [N: " + nome + " |Q: " + qtd + " U |P: R$ " + preco + "]\n";
    }

}

class Maquina{
    ArrayList<Espiral> espirais;
    int nespirais;
    float saldoCliente;
    float lucro;
    int maxProdutos;
    int pos;

    public Maquina(int nespirais, int maxProdutos) {
        this.maxProdutos = maxProdutos;
        this.espirais = new ArrayList<>();
        this.nespirais = nespirais;

        for(int i = 0; i < nespirais; i++)
            this.espirais.add(new Espiral("vazio", 0, 0f));
        
    }

    public void inserirDinheiro(float valor){
        saldoCliente += valor;
    }

    public void troco(){
        System.out.println("voce recebeu R$ " + saldoCliente);
        saldoCliente = 0;
    }

    public void comprar(int indice){
        if (indice > nespirais){
            System.out.println("fail: índice inválido");
        }else if (espirais.get(indice).getPreco() > saldoCliente){
            System.out.println("fail: saldo insuficiente");
        }else if (espirais.get(indice).getQtd() <= 0){
            System.out.println("fail: espiral sem produtos");
        }else {
            espirais.get(indice).comp();
            saldoCliente -= espirais.get(indice).getPreco();
            System.out.println("voce comprou um(a) " + espirais.get(indice).getNome());
        }
    }

    public void alterarEspiral(int indice, String nome, int qtd, float preco){
        if (indice > nespirais){
            System.out.println("fail: indice nao existe");
        }else if (qtd > maxProdutos){
            System.out.println("fail: limite excedido");            
        }else {
            espirais.set(indice, new Espiral(nome, qtd, preco));
        }
    }

    public void limparEspiral(int indice){
        if (indice > nespirais){
            System.out.println("fail: espiral inválido");
        }else{
            espirais.set(indice, new Espiral("vazio", 0, 0));
        }
    }

    public String toString(){
        String res = "";
        for (int i = 0; i < nespirais; i++)
            res += i + espirais.get(i).toString();

        return "saldo: R$ " + Float.toString(saldoCliente) + "\n" + res;
    }

    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        Maquina maquina = new Maquina(0, 0);
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if(ui[0].equals("init")){
                maquina = new Maquina(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
            }else if(ui[0].equals("show")){
                System.out.println(maquina);
            }else if(ui[0].equals("set")){
                //indice, nome, quantidade e preço
                maquina.alterarEspiral(Integer.parseInt(ui[1]), ui[2], Integer.parseInt(ui[3]), Float.parseFloat(ui[4]));
            }else if(ui[0].equals("limpar")){
                maquina.limparEspiral(Integer.parseInt(ui[1]));
            }else if(ui[0].equals("dinheiro")){
                maquina.inserirDinheiro(Float.parseFloat(ui[1]));
            }else if(ui[0].equals("comprar")){
                maquina.comprar(Integer.parseInt(ui[1]));
            }else if(ui[0].equals("troco")){
                maquina.troco();
            }else if(ui[0].equals("end")){
                break;
            }else {
                System.out.println("comando inválido");
            }
        }
        scanner.close();
    }    
}