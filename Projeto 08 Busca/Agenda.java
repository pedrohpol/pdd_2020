import java.util.*;
class Fone{
    String nome;
    String numero;

    public Fone(String nome, String numero){
        this.nome = nome;
        this.numero = numero;
    }

    public String toString(){
        return nome + ":" + numero;
    }
}

class Contato{
    String nome;
    ArrayList<Fone> fones = new ArrayList<Fone>();
    String nomeContato;
    String numero;

    public Contato(String nome){
        this.nome = nome;
    }

    public Contato(String nome, String nomeContato, String numero){
        this.nome = nome;
        this.nomeContato = nomeContato;
        this.numero = numero;
    }

    public String toString(){
        String saida = nome;
        int i = 0;
        for (Fone fone : fones){
            saida += " " + "[" + i + ":" + fone + "]";
            i++;
        }
        return saida;
    }
}

class Agenda{
    static ArrayList<Contato> contatos = new ArrayList<Contato>();
    public static int busca(String nome){
        for (int i = 0; i < contatos.size(); i++){
            Contato contato = contatos.get(i);
            if (contatos.get(i) != null && contato.nome.equals(nome))
                return i;
        }
        return -1;
    }

    public ArrayList<String> separador(String dados){
        ArrayList<String> saida = new ArrayList<>();
        int contador = 0;
        String nome = "";
        String numero = "";
        for (int i = 0; i < dados.length(); i++){
            if (dados.charAt(i) == ':'){
                break;
            }
            nome += dados.charAt(i);
            contador++;
        }
        for (int j = contador + 1; j < dados.length();j++ ){
            numero += dados.charAt(j);
        }
        saida.add(nome);
        saida.add(numero);
        return saida;  
    }

    public boolean validar(String numero){
        String validos = "0123456789()-";
        int contador = 0;
        for (int i = 0; i < numero.length(); i++){
            for (int j = 0; j < validos.length() ;j++ ){
                if(numero.charAt(i) == validos.charAt(j)){
                    contador++;
                }
            }
        }
        if (contador != numero.length()) return false;
        return true;
    }

    public void add(String nome, String contato){
        String nomeContato = separador(contato).get(0);
        String numero = separador(contato).get(1);
        if (validar(numero) == false){
            System.out.println("numero invalido");
            return;
        }
        if (busca(nome) == -1){
            contatos.add(new Contato(nome));
            contatos.get(busca(nome)).fones.add(new Fone(nomeContato, numero));
        }else {
            contatos.get(busca(nome)).fones.add(new Fone(nomeContato, numero));
        }
    }

    public void rmFone(String nome, int pos){
        if (pos > contatos.get(busca(nome)).fones.size() || pos < 0){
            System.out.println("indice invalido");
            return;
        }
        if (busca(nome) != -1){
            contatos.get(busca(nome)).fones.remove(pos);
        }else {
            System.out.println("contato inexistente");
        }
    }

    public void rm(String nome){
        if (busca(nome) != -1){
            contatos.remove(busca(nome));
        }else {
            System.out.println("contato inexistente");
        }
    }

    public void search(String padrao){
        ArrayList<Integer> resultados = new ArrayList<>();
        for (Contato contato : contatos){
            if (contato.nome.contains(padrao)){
                resultados.add(busca(contato.nome));
            }
            for (Fone fone : contato.fones){
                if (fone.nome.contains(padrao) || fone.numero.contains(padrao)){
                    resultados.add(busca(contato.nome));
                }
            }
        }
        for (int i = 0; i < resultados.size(); i++){
            System.out.println("- " + contatos.get(resultados.get(i)));
        }
    }

    public String toString(){
        String saida = "";
        for (Contato contato : contatos){
            saida += "- " + contato.toString() + "\n";
        }
        return saida;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();

        while (true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if (ui[0].equals("end")){
                break;
            }else if (ui[0].equals("add")){
                agenda.add(ui[1], ui[2]);
            }else if (ui[0].equals("show")){
                System.out.println(agenda);
            }else if (ui[0].equals("rmFone")){
                agenda.rmFone(ui[1], Integer.parseInt(ui[2]));
            }else if (ui[0].equals("rm")){
                agenda.rm(ui[1]);
            }else if (ui[0].equals("search")){
                agenda.search(ui[1]);
            }
        }
        scanner.close();
    }
}