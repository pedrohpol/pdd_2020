import java.util.*;

class Pass {
    String id;
    int idade;

    Pass(String id, int idade){
        this.id = id;
        this.idade = idade;
    }
    public String toString(){
        return id + ":" + idade;
    }
}

class Topic {
    static ArrayList<Pass> cadeiras;
    int tamanho;
    int qtdPref;

    public Topic(int tamanho, int qtdPref){
        this.tamanho = tamanho;
        this.qtdPref = qtdPref;
        cadeiras = new ArrayList<>();
        for (int i = 0; i < tamanho; i++)
            cadeiras.add(null);
    }

    private int busca(String id){
        for (int i = 0; i < cadeiras.size(); i++){
            Pass pass = cadeiras.get(i);
            if (cadeiras.get(i) != null && pass.id.equals(id))
                return i;
        }
        return -1;
    }

    int sentar(int tam, int inicio){
        for (int i = inicio; i < tam; i++){
            if (cadeiras.get(i) == null){
                return i;
            }
        }
        return -1;
    }

    void subir(String id, int idade){
        if (busca(id) != -1){
            System.out.println("pessoa ja esta na topic");
            return;
        }
        if (idade >= 60){
            if (sentar(qtdPref, 0) != -1){
                cadeiras.set(sentar(qtdPref, 0), new Pass(id, idade));
            }else if (sentar(tamanho, 0) != -1){
                cadeiras.add(qtdPref, new Pass(id, idade));
            }else {
                System.out.println("topic cheia");
            }
        }else {
            if (sentar (tamanho, qtdPref) != -1){
                cadeiras.set(sentar(tamanho, qtdPref), new Pass(id, idade));
            }else if (sentar(tamanho, 0) != -1){
                cadeiras.set(sentar(tamanho, 0), new Pass(id, idade));
            }else {
                System.out.println("topic cheia");
            }
        }
    }

    void descer(String id){
        if (busca(id) == -1){
            System.out.println("pessoa nao esta na topic");
            return;
        }else {
            cadeiras.set(busca(id), null);
        }
    }
    public String toString(){
        String saida = "[ ";
        for (int i = 0; i < qtdPref; i++){
            if (cadeiras.get(i) == null){
                saida += "@ ";
            }else {
                saida += "@" + cadeiras.get(i) + " ";
            }
        }

        for (int i = qtdPref; i < tamanho; i++){
            if (cadeiras.get(i) == null){
                saida += "= ";
            }else {
                saida += "=" + cadeiras.get(i) + " ";
            }
        }

        return saida + "]";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Topic topic = new Topic(0, 0);
        while (true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if (ui[0].equals("show")){
                System.out.println(topic);
            }else if (ui[0].equals("init")){
                topic = new Topic(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
            }else if (ui[0].equals("in")){
                topic.subir(ui[1], Integer.parseInt(ui[2]));
            }else if (ui[0].equals("out")){
                topic.descer(ui[1]);
            }else if (ui[0].equals("end")){
                break;
            }else {
                System.out.println("comando invalido");
            }
        }
        scanner.close();
    }
}
