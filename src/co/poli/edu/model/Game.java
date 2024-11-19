package co.poli.edu.model;

import java.util.List;

public class Game {

    public int numberLevel;
    public int numberTohelps;
    public boolean useHelp;
    public int numberQuestion;
    public List<Question> questions;
    
    // Constructor sin parámetros
    /**
     * Constructor sin parámetros que inicializa el objeto Game.
     */
    public Game() {
        
    }
    
    // Constructor con parámetros
    /**
     * Constructor con parámetros para inicializar un objeto Game con valores específicos.
     * @param numberLevel El nivel actual del juego.
     * @param numberTohelps El número de ayudas disponibles.
     * @param useHelp Indica si el jugador ha decidido usar ayudas.
     */
    public Game(int numberLevel, int numberTohelps, boolean useHelp) {
        this.numberLevel = numberLevel;
        this.numberTohelps = numberTohelps;
        this.useHelp = useHelp;
    }
    
    // Getter para numberLevel
    /**
     * Obtiene el número de nivel actual del juego.
     * @return El número de nivel.
     */
    public int getNumberLevel() {
        return numberLevel;
    }

    // Setter para numberLevel
    /**
     * Establece el número de nivel actual del juego.
     * @param numberLevel El número de nivel a establecer.
     */
    public void setNumberLevel(int numberLevel) {
        this.numberLevel = numberLevel;
    }

    // Getter para numberTohelps
    /**
     * Obtiene el número de ayudas disponibles para el jugador.
     * @return El número de ayudas.
     */
    public int getNumberTohelps() {
        return numberTohelps;
    }

    // Setter para numberTohelps
    /**
     * Establece el número de ayudas disponibles para el jugador.
     * @param cantTohelps El número de ayudas a establecer.
     */
    public void setNumberTohelps(int cantTohelps) {
        this.numberTohelps = cantTohelps;
    }

    // Getter para useHelp
    /**
     * Verifica si el jugador ha decidido usar ayudas.
     * @return true si el jugador ha optado por usar ayudas, false en caso contrario.
     */
    public boolean isUseHelp() {
        return useHelp;
    }

    // Setter para useHelp
    /**
     * Establece si el jugador ha decidido usar ayudas.
     * @param useHelp true si el jugador quiere usar ayudas, false si no.
     */
    public void setUseHelp(boolean useHelp) {
        this.useHelp = useHelp;
    }
    
    // Getter para questions
    /**
     * Obtiene la lista de preguntas del juego.
     * @return La lista de preguntas.
     */
    public List<Question> getQuestions() {
        return questions;
    }

    // Setter para questions
    /**
     * Establece la lista de preguntas del juego.
     * @param questions La lista de preguntas a establecer.
     */
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    // Getter para numberQuestion
    /**
     * Obtiene el número de la pregunta actual.
     * @return El número de la pregunta.
     */
    public int getNumberQuestion() {
        return numberQuestion;
    }

    // Setter para numberQuestion
    /**
     * Establece el número de la pregunta actual.
     * @param numberQuestion El número de la pregunta a establecer.
     */
    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }
    
    //Metodo para reiniciar las variables globales del juego
    /**
     * Reinicia las variables globales del juego a sus valores iniciales.
     */
    public void resetVariablesGame() {
        setNumberLevel(1);
        setNumberTohelps(3);
        setNumberQuestion(0);
        setUseHelp(false);
    }
    
    //Metodo para iniciar el juego
    /**
     * Inicia el juego, reiniciando las variables globales.
     */
    public void startGame() {
        resetVariablesGame();
    }
}
