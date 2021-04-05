import java.util.*;

class Fone{
    String nome;
    String numero;

    public boolean validar(String numero){
        numero = numero.split(":")[1];
        String validos = "0123456789()-";
        int contador = 0;
        for (int i = 0; i < numero.length(); i++){
            for (int j = 0; j < validos.length(); j++){
                if(numero.charAt(i) == validos.charAt(j)){
                    contador++;
                }
            }
        }
        if (contador != numero.length()){
            System.out.println("numero(s) invalido(s)");
            return false;
        }
        return true;
    }

    public Fone(String fone){
        if (validar(fone)){
            nome = fone.split(":")[0];
            numero = fone.split(":")[1];
        }
        
    }

    public String toString(){
        return nome + ":" + numero;
    }
}

class Contato{
    String nome;
    ArrayList<Fone> fones = new ArrayList<Fone>();

    public Contato(String nome){
        this.nome = nome;
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

    public void add(String[] ui){      
        if (busca(ui[1]) != -1 && ui.length <3) 
            throw new RuntimeException("contato já existe");
        
        if (busca(ui[1]) == -1){
            contatos.add(new Contato(ui[1]));
            for (int i = 2; i < ui.length; i++)
                contatos.get(busca(ui[1])).fones.add(new Fone(ui[i]));  
        }else {
            for (int i = 2; i < ui.length; i++) 
                contatos.get(busca(ui[1])).fones.add(new Fone(ui[i]));  
        }
    }

    public void rmFone(String nome, int pos){
        if (pos > contatos.get(busca(nome)).fones.size() || pos < 0){
            throw new RuntimeException("indice invalido");
        }
        if (busca(nome) != -1){
            contatos.get(busca(nome)).fones.remove(pos);
        }else {
            throw new RuntimeException("contato inexistente");
        }
    }

    public void rm(String nome){
        if (busca(nome) != -1){
            contatos.remove(busca(nome));
        }else {
            throw new RuntimeException("contato inexistente");
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
        AgendaStar agenda= new AgendaStar();
        for (ContatoStar contato : agenda.contatos){
            if (contato.favorito == true){
                saida += "@ " + contato.toString() + "\n";
            }else {
                saida += "- " + contato.toString() + "\n";
            }
        }
        return saida;
    }

}

class ContatoStar extends Contato{
    boolean favorito;
    public ContatoStar(String nome) {
        super(nome);
        
    }

    public String toString(){
        return "";
    }
}

class AgendaStar extends Agenda {
    ArrayList<ContatoStar> contatos = new ArrayList<>();

    public void star(String nome){
        
        if (contatos.get(busca(nome)).favorito == false){
            contatos.get(busca(nome)).favorito = true;
            return;
        }
        throw new RuntimeException("contato já é favorito");
    }
    public void unstar(String nome){
        if (contatos.get(busca(nome)).favorito == true){
            contatos.get(busca(nome)).favorito = false;
            return;
        }
        throw new RuntimeException("contato não é favorito");
    }
    public void starred(){
        String saida = "";
        for (ContatoStar contato : contatos){
            if (contato.favorito == true){
                saida += "@ " + contato.toString() + "\n";
            }
        }
        System.out.println(saida);
    }
}

class Solver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        AgendaStar agendastar = new AgendaStar();

        while (true){
            try {
                String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if (ui[0].equals("end")){
                break;
            }else if (ui[0].equals("add")){
                agenda.add(ui);
            }else if (ui[0].equals("show")){
                System.out.println(agenda);
            }else if (ui[0].equals("rmFone")){
                agenda.rmFone(ui[1], Integer.parseInt(ui[2]));
            }else if (ui[0].equals("rm")){
                agenda.rm(ui[1]);
            }else if (ui[0].equals("search")){
                agenda.search(ui[1]);
            }else if (ui[0].equals("star")){
                agendastar.star(ui[1]);
            }else if (ui[0].equals("unstar")){
                agendastar.unstar(ui[1]);
            }else if (ui[0].equals("starred")){
                agendastar.starred();
            }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        scanner.close();
    }
}