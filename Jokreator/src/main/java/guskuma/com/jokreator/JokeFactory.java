package guskuma.com.jokreator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JokeFactory {

    private static JokeFactory singleton;

    private List<String> jokesList = new ArrayList<>();
    private Iterator<String> it;

    private JokeFactory(){
        jokesList.add("A professora chega para o Joãozinho e diz: <br/>" +
                "- Joãozinho qual é o tempo da frase: Eu procuro um homem fiel? <br/>" +
                " E então Joãozinho responde: <br/>" +
                "- É tempo perdido!<br/>");

        jokesList.add("Joãozinho chamou o pai no meio da noite e disse: <br/>" +
                "- Pai, tem muitos mosquitos no meu quarto! <br/>" +
                "- Apague a luz que eles vão embora, filhote! — diz o pai, carinhosamente. <br/>" +
                "Logo depois apareceu um vaga-lume. O menino chamou o pai outra vez: <br/>" +
                "- Pai, socorro! Agora os mosquitos estão vindo com lanternas!");

        jokesList.add("Joãozinho chega em casa e diz: <br/>" +
                "- Mãe, eu descobri que sou mais inteligente que a professora. <br/>" +
                "E a mãe diz: <br/>" +
                "- Por que você acha isso? <br/>" +
                "- Porque eu passei de ano e ela continuou no mesmo.");

        it = jokesList.iterator();
    }

    private String nextJoke(){
        if(!it.hasNext())
            it = jokesList.iterator();

        return it.next();
    }

    public static String getNewJoke(){
        if(singleton == null){
            singleton = new JokeFactory();
        }
        return singleton.nextJoke();
    }

}
