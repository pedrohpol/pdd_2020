import java.util.*;

class User{
    private String username;
    private Map<String, User> followers;
    private Map<String, User> following;
    private Map<Integer, Tweet> timeline;
    private int unreadCount;

    public User (String username){
        this.followers = new TreeMap<>();
        this.following = new TreeMap<>();
        this.timeline = new TreeMap<>();
        this.username = username;
        this.unreadCount = 0;
    }

    public void follow(User user){
        if(following.containsKey(user.username))
            throw new RuntimeException("fail: pessoa ja esta sendo seguida");
        following.put(user.username, this);
        user.followers.put(this.username, this);
    }

    public void unfollow(User user){
        if(!following.containsKey(user.username))
            throw new RuntimeException("fail: pessoa nao esta sendo seguida");
        following.remove(user.username);
        user.followers.remove(this.username);
    }

    public Tweet getTweet(int idtw){
        Tweet tweet = timeline.get(idtw);
        tweet.like(username);
        return tweet;
    }

    public void sendTweet(Tweet tweet){
        for(User user : followers.values()){
            user.timeline.put(tweet.getIdTw(), tweet);
            user.unreadCount += 1;
        }
    }

    public String getTimeline(){
        String saida = "";
        Map <Integer, Tweet> tl = new TreeMap<>();
        for (int i = timeline.size() - this.unreadCount; i < timeline.size(); i++)
            tl.put(i, timeline.get(i));
        if(this.unreadCount == 0)
            throw new RuntimeException("sem tweets novos");
        unreadCount = 0;
        for (int i = 0; i < tl.size(); i++)
            saida += tl.get(i) + "\n";
        return saida;
    }
    
    public String toString(){
        return username + "\n" + "  seguidos   " + following.keySet() + "\n" + "  seguidores " + followers.keySet() + ""; 
    }
}

class Tweet{
    int idTw;
    String username;
    String msg;
    TreeSet<String> likes;

    public Tweet(String username, String msg, int id){
        this.username = username;
        this.msg = msg;
        this.idTw = id;
        this.likes = new TreeSet<>();
    }

    public void like(String username){
        likes.add(username);
    }

    public int getIdTw(){
        return idTw;
    }

    public String getUsername(){
        return username;
    }

    public String getMsg(){
        return msg;
    }

    public String toString(){
        String saida = idTw + ":" + username + "( " + msg + ")";
        if (!likes.isEmpty()) saida += likes;
        return saida;
    }
}

class Controller{
    Map<String, User> users;
    Map<Integer, Tweet> tweets;
    int nextTwId;

    public Controller(){
        this.users = new TreeMap<>();
        this.tweets = new TreeMap<>();
    }

    public void addUser(String username){
        User user = new User(username);
        if(!users.containsKey(username))
            users.put(username,user);
    }

    public User getUser(String username){
        User user = users.get(username);
        if(user == null)
            throw new RuntimeException("fail: usuario inexistente");
        return user;
    }

    public String toString(){
        String saida = "";
            for(User user : users.values())
                saida += user + "\n";
        return saida;
    }

    public void sendTweet(String username, String msg){
        if(!users.containsKey(username))
            throw new RuntimeException("fail: usuario inexistente");
        Tweet tweet = new Tweet(username, msg, nextTwId);
        tweets.put(nextTwId, tweet);
        User user = getUser(username);
        user.sendTweet(tweet);
        nextTwId++; 
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controller sistema = new Controller();
        
        while(true){
            String line = scanner.nextLine();
            String ui[] = line.split(" ");
            try {
                if (ui[0].equals("end"))
                    break;
                else if (ui[0].equals("addUser")) {
                    sistema.addUser(ui[1]);
                } else if (ui[0].equals("show")) {
                    System.out.print(sistema);
                } else if (ui[0].equals("follow")) {
                    User one = sistema.getUser(ui[1]);
                    User two = sistema.getUser(ui[2]);
                    one.follow(two);
                }
                else if (ui[0].equals("twittar")) {
                    String username = ui[1];
                    String msg = "";
                    for(int i = 2; i < ui.length; i++)
                        msg += ui[i] + " ";
                    sistema.sendTweet(username, msg);
                }
                else if (ui[0].equals("timeline") || ui[0].equals("tl")) {
                    User user = sistema.getUser(ui[1]);
                    System.out.print(user.getTimeline());
                }
                else if (ui[0].equals("like")) {
                    User user = sistema.getUser(ui[1]);
                    Tweet tw = user.getTweet(Integer.parseInt(ui[2]));
                    tw.like(ui[1]);
                }else if (ui[0].equals("unfollow")) {
                    User one = sistema.getUser(ui[1]);
                    User two = sistema.getUser(ui[2]);
                    one.unfollow(two);
                }else{
                    System.out.println("fail: comando invalido");
                }
            }catch(RuntimeException rt){
                System.out.println(rt.getMessage());
            }
        }
        scanner.close();
    }
}