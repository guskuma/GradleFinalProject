package guskuma.com.jokreator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JokeFactory {

    private static JokeFactory singleton;

    private List<String> jokesList = new ArrayList<>();
    private Iterator<String> it;

    private JokeFactory(){
        jokesList.add("A professora chega para o Joãozinho e diz: \r\n" +
                "- Joãozinho qual é o tempo da frase: \n" +
                "Eu procuro um homem fiel? \r\n" +
                " E então Joãozinho responde: \r\n" +
                "- É tempo perdido!\r\n");

        jokesList.add("Joãozinho chamou o pai no meio da noite e disse: \r\n" +
                "- Pai, tem muitos mosquitos no meu quarto! \r\n" +
                "- Apague a luz que eles vão embora, filhote! — diz o pai, carinhosamente. \r\n" +
                "Logo depois apareceu um vaga-lume. O menino chamou o pai outra vez: \r\n" +
                "- Pai, socorro! Agora os mosquitos estão vindo com lanternas!");

        jokesList.add("Joãozinho chega em casa e diz: \r\n" +
                "- Mãe, eu descobri que sou mais inteligente que a professora. \r\n" +
                "E a mãe diz: \r\n" +
                "- Por que você acha isso? \r\n" +
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
