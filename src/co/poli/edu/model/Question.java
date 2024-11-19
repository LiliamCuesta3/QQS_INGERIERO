package co.poli.edu.model;

public class Question {
    
    // Atributos privados
    /**
     * La pregunta en formato de texto.
     */
    public String statementQuestion;
    
    /**
     * Pista relacionada con la pregunta.
     */
    public String clue;
    
    /**
     * Respuesta A de la pregunta.
     */
    public Answer answerA;
    
    /**
     * Respuesta B de la pregunta.
     */
    public Answer answerB;
    
    /**
     * Respuesta C de la pregunta.
     */
    public Answer answerC;
    
    /**
     * Respuesta D de la pregunta.
     */
    public Answer answerD;
    
    /**
     * Nivel de la pregunta.
     */
    public int numberLevel;
    
    /**
     * Indica si la pregunta está disponible.
     */
    public boolean isAvaliable;
    
    // Constructor vacío
    /**
     * Constructor vacío para inicializar un objeto Question sin valores iniciales.
     */
    public Question() {
    }

    // Constructor con parámetros
    /**
     * Constructor con parámetros para inicializar un objeto Question con valores específicos.
     * @param statementQuestion La pregunta en formato de texto.
     * @param answerA Respuesta A de la pregunta.
     * @param answerB Respuesta B de la pregunta.
     * @param answerC Respuesta C de la pregunta.
     * @param answerD Respuesta D de la pregunta.
     * @param numberLevel Nivel de la pregunta.
     * @param isAvaliable Indica si la pregunta está disponible.
     */
    public Question(String statementQuestion, Answer answerA, Answer answerB, Answer answerC, Answer answerD, int numberLevel, boolean isAvaliable) {
        this.statementQuestion = statementQuestion;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.numberLevel = numberLevel;
        this.isAvaliable = isAvaliable;
    }

    // Getter para statementQuestion
    /**
     * Obtiene la pregunta en formato de texto.
     * @return La pregunta.
     */
    public String getStatementQuestion() {
        return statementQuestion;
    }

    // Setter para statementQuestion
    /**
     * Establece la pregunta en formato de texto.
     * @param statementQuestion La pregunta a establecer.
     */
    public void setStatementQuestion(String statementQuestion) {
        this.statementQuestion = statementQuestion;
    }
    
    // Getter para clue
    /**
     * Obtiene la pista relacionada con la pregunta.
     * @return La pista de la pregunta.
     */
    public String getClue() {
        return clue;
    }

    // Setter para clue
    /**
     * Establece la pista relacionada con la pregunta.
     * @param clue La pista a establecer.
     */
    public void setClue(String clue) {
        this.clue = clue;
    }

    // Getter para answerA
    /**
     * Obtiene la respuesta A de la pregunta.
     * @return La respuesta A.
     */
    public Answer getAnswerA() {
        return answerA;
    }

    // Setter para answerA
    /**
     * Establece la respuesta A de la pregunta.
     * @param answerA La respuesta A a establecer.
     */
    public void setAnswerA(Answer answerA) {
        this.answerA = answerA;
    }

    // Getter para answerB
    /**
     * Obtiene la respuesta B de la pregunta.
     * @return La respuesta B.
     */
    public Answer getAnswerB() {
        return answerB;
    }

    // Setter para answerB
    /**
     * Establece la respuesta B de la pregunta.
     * @param answerB La respuesta B a establecer.
     */
    public void setAnswerB(Answer answerB) {
        this.answerB = answerB;
    }

    // Getter para answerC
    /**
     * Obtiene la respuesta C de la pregunta.
     * @return La respuesta C.
     */
    public Answer getAnswerC() {
        return answerC;
    }

    // Setter para answerC
    /**
     * Establece la respuesta C de la pregunta.
     * @param answerC La respuesta C a establecer.
     */
    public void setAnswerC(Answer answerC) {
        this.answerC = answerC;
    }

    // Getter para answerD
    /**
     * Obtiene la respuesta D de la pregunta.
     * @return La respuesta D.
     */
    public Answer getAnswerD() {
        return answerD;
    }

    // Setter para answerD
    /**
     * Establece la respuesta D de la pregunta.
     * @param answerD La respuesta D a establecer.
     */
    public void setAnswerD(Answer answerD) {
        this.answerD = answerD;
    }

    // Getter para numberLevel
    /**
     * Obtiene el nivel de la pregunta.
     * @return El nivel de la pregunta.
     */
    public int getNumberLevel() {
        return numberLevel;
    }

    // Setter para numberLevel
    /**
     * Establece el nivel de la pregunta.
     * @param numberLevel El nivel a establecer.
     */
    public void setNumberLevel(int numberLevel) {
        this.numberLevel = numberLevel;
    }
    
    // Getter para isUsed
    /**
     * Obtiene si la pregunta está disponible.
     * @return true si la pregunta está disponible, false si no lo está.
     */
    public boolean getIsAvaliable() {
        return isAvaliable;
    }

    // Setter para isUsed
    /**
     * Establece si la pregunta está disponible.
     * @param isUsed true si la pregunta está disponible, false si no lo está.
     */
    public void setIsAvaliable(boolean isUsed) {
        this.isAvaliable = isUsed;
    }
}
