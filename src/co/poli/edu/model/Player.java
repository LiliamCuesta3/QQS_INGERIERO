package co.poli.edu.model;

public class Player {
    
    // Atributos privados
    /**
     * El número de vidas del jugador.
     */
    public int numberLives;
    
    /**
     * El número de puntos del jugador.
     */
    public int numberPoints;

    // Constructor sin parámetros
    /**
     * Constructor sin parámetros para inicializar un objeto Player.
     */
    public Player() {
    }

    // Constructor con parámetros
    /**
     * Constructor con parámetros para inicializar un objeto Player con valores específicos.
     * @param numberLives El número de vidas iniciales del jugador.
     * @param numberPoints El número de puntos iniciales del jugador.
     */
    public Player(int numberLives, int numberPoints) {
        this.numberLives = numberLives;
        this.numberPoints = numberPoints;
    }

    // Getter para numberLives
    /**
     * Obtiene el número de vidas del jugador.
     * @return El número de vidas del jugador.
     */
    public int getNumberLives() {
        return numberLives;
    }

    // Setter para numberLives
    /**
     * Establece el número de vidas del jugador.
     * @param numberLives El número de vidas a establecer.
     */
    public void setNumberLives(int numberLives) {
        this.numberLives = numberLives;
    }

    // Getter para numberPoints
    /**
     * Obtiene el número de puntos del jugador.
     * @return El número de puntos del jugador.
     */
    public int getNumberPoints() {
        return numberPoints;
    }

    // Setter para numberPoints
    /**
     * Establece el número de puntos del jugador.
     * @param numberPoints El número de puntos a establecer.
     */
    public void setNumberPoints(int numberPoints) {
        this.numberPoints = numberPoints;
    }

    // Método toString para representación del objeto
    /**
     * Retorna una representación en forma de cadena del jugador.
     * @return La cadena con los valores de numberLives y numberPoints.
     */
    @Override
    public String toString() {
        return "Player{" +
               "numberLives=" + numberLives +
               ", numberPoints=" + numberPoints +
               '}';
    }

    // Método para restar vidas
    /**
     * Reduce en 1 el número de vidas del jugador, si hay vidas disponibles.
     */
    public void loseLive() {
        int lives = getNumberLives();
        if (lives > 0) {
            setNumberLives(lives - 1);
        }
    }

    // Método para ganar puntos
    /**
     * Aumenta en 1 el número de puntos del jugador.
     */
    public void addPoints() {
        int points = getNumberPoints();
        setNumberPoints(points++);
    }
    
    // Método para reiniciar las vidas
    /**
     * Reinicia las vidas del jugador a 3.
     */
    public void resetLive() {
        int lives = 3;
        setNumberLives(lives);
    }
    
    // Método para reiniciar los puntos
    /**
     * Reinicia los puntos del jugador a 0.
     */
    public void resetPoints() {
        int points = 0;
        setNumberPoints(points);
    }
    
}
