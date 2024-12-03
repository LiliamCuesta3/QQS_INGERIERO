package co.poli.edu.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.poli.edu.model.Game;
import co.poli.edu.model.Player;
import co.poli.edu.model.Question;
import co.poli.edu.model.QuestionList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Controlador principal del juego. Se encarga de la lógica del juego, manejo de preguntas,
 * control de puntos y vidas del jugador, y la interacción con el usuario.
 */
public class GameController {
	
	private final Scanner scanner;
    private final Stage stage;
    private final List<String> arguments;
    
	Game game= new Game();
	Player player = new Player();
	Question actualQuestion = new Question();

    /**
     * Constructor de la clase GameController.
     * Inicializa el scanner para la entrada de datos por consola.
     */
    public GameController(Stage stage, List<String> arguments) {
        this.stage = stage;
        this.arguments = arguments; 
        this.scanner = new Scanner(System.in);
    }

    /**
     * Inicia el juego, muestra el nombre del juego, la explicación, y carga las preguntas.
     * @throws IOException Si ocurre un error al cargar las preguntas.
     */
	public void startGame() throws IOException {
		game.startGame();
		initPlayer();
		loadListQuestions();
	}
	
	/**
	 * Método que contruye el primer modal o stage de la interfaz de la aplicación.
	 * @throws IOException si ocurre un error al obtener las respuesta
	 */
    public void showMainView() throws IOException {
    	
    	startGame();
    	
        // Crear el texto principal
        Text paragraph = new Text(
            "Bienvenido a ¿ Quien quiere ser Ingeniero ?.\n" +
            "¡El objetivo es contestar 6 preguntas correctamente! \n" +
            "Si fallas alguna, pierdes una vida. Si te quedas sin vidas, pierdes. \n" +
            "Tienes una ayuda por cada nivel y cada dos preguntas \n" +
            "cambias de nivel, mucha suerte!."
        );
        paragraph.setFont(Font.font("Arial", 16));  // Tamaño y fuente del texto
        paragraph.setTextAlignment(TextAlignment.CENTER);  // Centrar el texto
        paragraph.setFill(Color.DARKBLUE);  // Color del texto

        // Botón "Comenzar"
        Button startButton = new Button("Comenzar");
        startButton.setStyle("-fx-font-size: 14px; -fx-background-color: lightblue;");

        // Acción del botón
        startButton.setOnAction(e -> {
			try {
				nextQuestion();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
        
        VBox textBox = new VBox(20, paragraph, startButton);
        textBox.setStyle("-fx-border-width: 2; -fx-padding: 10; -fx-alignment: center;");
        textBox.setSpacing(10);
     
        Label title = new Label("¿Quien quiere ser ingeniero?");
        title.setFont(Font.font("Verdana", 20));
        title.setTextFill(Color.DARKRED);

        VBox layout = new VBox(20, title, textBox, startButton);
        layout.setStyle("-fx-background-color: lightgray;");
        layout.setSpacing(20);
        layout.setPrefSize(400, 300);
        layout.setStyle("-fx-alignment: center;");

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        double centerX = bounds.getMinX() + (bounds.getWidth() - stage.getWidth()) / 2;
        double centerY = bounds.getMinY() + (bounds.getHeight() - stage.getHeight()) / 2;

        stage.setX(centerX);
        stage.setY(centerY);
        
        Scene scene = new Scene(layout);
        stage.setTitle("¿ Quien quiere ser Ingeniero ?");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Método que se invoca cuando el juego finaliza para preguntarle al usuario si desea o no continuar 
     * @param message es el mensaje que se le muestra al usuario dependiendo de como finalizo el juego
     */
    
    private void repeatGame(String message) {
        Stage modalStage = new Stage();

        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(stage);
        modalStage.setTitle("Confirmación");

        Label messageLabel = new Label(message);
        messageLabel.setFont(Font.font("Arial", 16));
        messageLabel.setWrapText(true);
        messageLabel.setTextAlignment(TextAlignment.CENTER);
        
        Label messageLabel2 = new Label("¿Desea volver a intentar?");
        messageLabel2.setFont(Font.font("Arial", 16));
        messageLabel2.setWrapText(true);
        messageLabel2.setTextAlignment(TextAlignment.CENTER);
        
        Button yesButton = new Button("Sí");
        yesButton.setStyle("-fx-font-size: 14px; -fx-background-color: lightgreen;");
        yesButton.setOnAction(e -> {
            modalStage.close();
            try {
				showMainView();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        });

        Button noButton = new Button("No");
        noButton.setStyle("-fx-font-size: 14px; -fx-background-color: lightcoral;");
        noButton.setOnAction(e -> Platform.exit());

        HBox buttonLayout = new HBox(10, yesButton, noButton);
        buttonLayout.setAlignment(Pos.CENTER);

        VBox layout = new VBox(20, messageLabel, messageLabel2, buttonLayout);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f9f9f9;");

        Scene modalScene = new Scene(layout, 500, 300);
        modalStage.setScene(modalScene);

        modalStage.setOnShown(event -> {

            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            modalStage.setX(bounds.getMinX() + (bounds.getWidth() - modalStage.getWidth()) / 2);
            modalStage.setY(bounds.getMinY() + (bounds.getHeight() - modalStage.getHeight()) / 2);
        });

        modalStage.showAndWait();
    }

    
    /**
     * Método usado para construir el stage donde se van a mostrar las preguntas en las diferentes etapas del juego
     * @param boardOfPoints mensaje de puntos, vidas, ayudas y niveles (informativo)
     * @param questionText testo de la pregunta
     * @param answerA texto de la respuesta a
     * @param answerB texto de la respuesta b
     * @param answerC texto de la respuesta c
     * @param answerD texto de la respuesta d
     */
    private void questionsScene(String boardOfPoints, String questionText, String answerA, String answerB, String answerC, String answerD) {

    	Text boardOfPointsText = new Text(boardOfPoints + "\n\n\n");
    	boardOfPointsText.setFill(Color.BLACK);
    	boardOfPointsText.setFont(Font.font("Arial", FontWeight.BOLD, 16));


    	Text questionTextPart = new Text(
    	    questionText + "\n\n" +
    	    "Opciones de respuesta: \n\n" +
    	    answerA + "\n" +
    	    answerB + "\n" +
    	    answerC + "\n" +
    	    answerD + "\n\n" +
    	    "Presione el botón con la opción que considera correcta.\n\n" +
    	    "Si desea utilizar su ayuda presione el boton Help."
    	);
    	
    	questionTextPart.setFill(Color.DARKBLUE); // Cambiar el color del texto restante
    	questionTextPart.setFont(Font.font("Arial", 16));
    	questionTextPart.setWrappingWidth(600);
    	
    	TextFlow paragraph = new TextFlow(boardOfPointsText, questionTextPart);
    	paragraph.setTextAlignment(TextAlignment.LEFT);
        paragraph.setTextAlignment(TextAlignment.LEFT);  // Alinear el texto a la izquierda

        Button aButton = new Button("A");
        Button bButton = new Button("B");
        Button cButton = new Button("C");
        Button dButton = new Button("D");

        String buttonStyle = "-fx-font-size: 14px; -fx-background-color: lightblue;";
        aButton.setStyle(buttonStyle);
        bButton.setStyle(buttonStyle);
        cButton.setStyle(buttonStyle);
        dButton.setStyle(buttonStyle);

        aButton.setOnAction(e -> {
			try {
				evaluateUserAnswer("A");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
        bButton.setOnAction(e -> {
			try {
				evaluateUserAnswer("B");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
        cButton.setOnAction(e -> {
			try {
				evaluateUserAnswer("C");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
        dButton.setOnAction(e -> {
			try {
				evaluateUserAnswer("D");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

        HBox buttonLayout = new HBox(20, aButton, bButton, cButton, dButton);
        buttonLayout.setStyle("-fx-alignment: center;");
        buttonLayout.setSpacing(20);

        VBox textBox = new VBox(10, paragraph);
        textBox.setStyle("-fx-border-width: 2; -fx-border-color: gray; -fx-padding: 10;");
        textBox.setSpacing(10);

        Label title = new Label("¿Quién quiere ser ingeniero?");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        title.setTextFill(Color.DARKRED);

        Button helpButton = new Button("Help");
        helpButton.setStyle(buttonStyle);
        
        helpButton.setOnAction(e -> useHelp());
        
        HBox buttonLayout2 = new HBox(20, helpButton);
        buttonLayout2.setStyle("-fx-alignment: center;");
        buttonLayout2.setSpacing(20);
        
        VBox layout = new VBox(20, title, textBox, buttonLayout, buttonLayout2);
        
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setPrefWidth(600);
        layout.setPrefHeight(Region.USE_COMPUTED_SIZE);
        
        layout.setStyle("-fx-background-color: lightgray;");
        layout.setSpacing(20);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        stage.setTitle("¿Quién quiere ser Ingeniero?");
        stage.setScene(scene);
        stage.setOnShown(event -> stage.centerOnScreen());
        stage.show();
    }
    
    /**
     * Método utilizado para mostrar diferentes mensajes en un modal
     * @param message mensaje que se muestra en el modal
     */
    private void showModal(String message) {
    	
        Stage modalStage = new Stage();

        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(stage);
        modalStage.setTitle("Información");

        Label messageLabel = new Label(message);
        messageLabel.setFont(Font.font("Arial", 16));
        messageLabel.setWrapText(true);
        messageLabel.setTextAlignment(TextAlignment.CENTER);

        Button closeButton = new Button("Cerrar");
        closeButton.setStyle("-fx-font-size: 14px; -fx-background-color: lightblue;");
        closeButton.setOnAction(e -> modalStage.close());

        VBox layout = new VBox(20, messageLabel, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f9f9f9;");


        Scene modalScene = new Scene(layout, 300, 200);
        modalStage.setScene(modalScene);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        double centerX = bounds.getMinX() + (bounds.getWidth() - modalStage.getWidth()) / 2;
        double centerY = bounds.getMinY() + (bounds.getHeight() - modalStage.getHeight()) / 2;

        modalStage.setX(centerX);
        modalStage.setY(centerY);

        modalStage.showAndWait();
    }

    /**
     * Carga la lista de preguntas y procede con la siguiente pregunta.
     * @throws IOException Si ocurre un error al cargar las preguntas desde el archivo.
     */
	public void loadListQuestions() throws IOException {
		game.setQuestions(loadQuestions());
	}
	
    /**
     * Avanza a la siguiente pregunta y actualiza el estado del juego.
     * Si el jugador llega a 6 puntos o se queda sin vidas, termina el juego.
     * @throws IOException Si ocurre un error al cargar las preguntas.
     */
	public void nextQuestion() throws IOException{
		int points = getPoints();
		
		if(points == 6) {
			endGame("Felicidades, haz completado el juego, eres todo un ingeniero! :)");
		}
		int level = reviewLevel(points);
		
		getNewQuestion(game.getQuestions(), level, game.getNumberQuestion());
		
	}
	
	/**
	 * Método para recontar los puntos del juego
	 * @param response Indica si respondio o no la pregunta inmediatamente anterior
	 * @throws IOException por si falla el metodo nextQuestion
	 */
	public void resumePointsToGame(boolean response) throws IOException {
		if(response) {
			addNewPoint();
			nextQuestion();
		}else {
			loseLive();
			int lives = getLives();
			if(lives == 0) {
				endGame("¡No tienes mas vidas, fin del juego!");
			}else {
				nextQuestion();
			}
		}
	}

    /**
     * Aumenta el puntaje del jugador en 1.
     */
	public void addNewPoint() {
		player.setNumberPoints(player.getNumberPoints() + 1);
	}
	
    /**
     * Revisa el nivel del jugador basado en los puntos obtenidos.
     * @param points Los puntos actuales del jugador.
     * @return El nivel del jugador.
     */
	public int reviewLevel(int points) {
		int actuallyLevel = game.getNumberLevel();
		
		if(actuallyLevel == 1 && (points >= 2 && points < 4)) {
			changueLevel(2);
			return 2;
		}else if(actuallyLevel == 2 && points >= 4){
			changueLevel(3);
			return 3;
		}else if(actuallyLevel == 1 && points < 2){
			return 1;
		}
		
		return actuallyLevel;
	}
	
    /**
     * Cambia el nivel del juego.
     * @param level El nuevo nivel del juego.
     */
	public void changueLevel(int level) {
		game.setNumberLevel(level);
		game.setUseHelp(false);
	}
	
    /**
     * Finaliza el juego mostrando un mensaje.
     * @param message El mensaje final del juego.
     * @throws IOException Si ocurre un error al finalizar el juego.
     */
	public void endGame(String message) throws IOException {
		repeatGame(message);
	}
	
	
    /**
     * Carga las preguntas desde un archivo JSON.
     * @return Lista de preguntas cargadas desde el archivo.
     * @throws IOException Si ocurre un error al cargar el archivo o procesar las preguntas.
     */
    public List<Question> loadQuestions() throws IOException {
    	
        // Usar la ruta relativa para acceder al archivo dentro de recursos
        URL resourceUrl = getClass().getClassLoader().getResource("resources/questions_list.json");

        // Verificar si el archivo existe
        if (resourceUrl == null) {
            throw new IOException("El archivo no se encontró en la ruta especificada.");
        }

        // Convertir el recurso URL a un objeto File
        File file = new File(resourceUrl.getFile());
        
        // Crear un ObjectMapper de Jackson
        ObjectMapper objectMapper = new ObjectMapper();

        // Convertir el JSON a un objeto List<Question>
        QuestionList questionList = objectMapper.readValue(file, QuestionList.class);
        
        return questionList.getQuestions();
    }
    
    /**
     * Obtiene una pregunta disponible del listado de preguntas filtradas por nivel.
     * @param questionList Lista de preguntas.
     * @param level El nivel de la pregunta que se desea obtener.
     * @return Una pregunta disponible.
     */
    public Question getAvailableQuestion(List<Question> questionList, int level) {
    
        // Filtrar las preguntas por nivel y disponibilidad
        List<Question> filteredQuestions = questionList.stream()
                .filter(question -> question.getNumberLevel() == level) // Filtrar por nivel
                .filter(Question::getIsAvaliable) // Filtrar por disponibilidad
                .collect(Collectors.toList());

        // Seleccionar una pregunta
        return filteredQuestions.get(0);
    }
    
    /**
     * Obtiene una nueva pregunta y muestra la pregunta.
     * @param questionList Lista de preguntas.
     * @param level El nivel de dificultad de la pregunta.
     * @param numberQuestion El número de la pregunta en el juego.
     * @return true si la respuesta es correcta, false si es incorrecta.
     */
    public void getNewQuestion(List<Question> questionList, int level, int numberQuestion) {
    	
    	try {
    		actualQuestion = getAvailableQuestion(questionList, level);
    		
    		//Aumentar el numero de la pregunta
    		game.setNumberQuestion(numberQuestion + 1);
    		
            // Mostrar la pregunta
            showQuestion(actualQuestion, game.getNumberQuestion());

		} catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());

		}
	}
    
    /**
     * Método que evalua si la respuesta es correcta
     * @param userAnswer es la respuesta del usuario
     * @throws IOException por si falla el metodo resumePointsToGame
     */
    public void evaluateUserAnswer(String userAnswer) throws IOException {
        	
         // Validar la respuesta
         boolean isCorrect = validateAnswer(userAnswer, actualQuestion);	
        
         // Actualizar el estado de disponibilidad
         actualQuestion.setIsAvaliable(false);
        
        if (isCorrect) {
            showModal("¡Respuesta correcta!");
            resumePointsToGame(true);
        } else {
            showModal("Respuesta incorrecta :(");
            resumePointsToGame(false);
        }
    }
    
    /**
     * Método que evalua si el usuario puede usar una ayuda o no
     */
    public void useHelp() {
    	if(!game.isUseHelp()) {
        	adjustHelps();
        	showModal("Pista: "+actualQuestion.getClue());
    	}else {
    		showModal("Ya usaste tu ayuda en esta ronda, selecciona alguna de las opciones de respuesta.");
    		
    	}

    }
    
    /**
     * Ajusta las ayudas disponibles para el jugador.
     */
    public void adjustHelps() { 
    	game.setUseHelp(true);
    	game.setNumberTohelps(game.getNumberTohelps() - 1);
    }
    

    /**
     * Muestra la pregunta y sus opciones.
     * @param question La pregunta que se mostrará.
     * @param numberQuestion El número de la pregunta.
     */
	public void showQuestion(Question question, int numberQuestion) {
	
		String boardOfPoints = "Pregunta N°: " + numberQuestion + " | vidas: " + player.getNumberLives() + " | Puntos: " + player.getNumberPoints() + " | Ayudas: " + game.getNumberTohelps() + "      Nivel: " + game.getNumberLevel(); 
		String questionText = question.getStatementQuestion();
		String answerA = "A. "+ question.getAnswerA().getStatement();
		String answerB = "B. "+ question.getAnswerB().getStatement();
		String answerC = "C. "+ question.getAnswerC().getStatement();
		String answerD = "D. "+ question.getAnswerD().getStatement();
		
		questionsScene(boardOfPoints, questionText, answerA, answerB, answerC, answerD);
	}
	
    /**
     * Valida la respuesta del jugador.
     * @param userAnswer La respuesta proporcionada por el jugador.
     * @param question La pregunta correspondiente.
     * @return true si la respuesta es correcta, false si es incorrecta.
     */
    private boolean validateAnswer(String userAnswer, Question question) {
        switch (userAnswer) {
            case "A":
                return question.getAnswerA().getIsCorrect();
            case "B":
                return question.getAnswerB().getIsCorrect();
            case "C":
                return question.getAnswerC().getIsCorrect();
            case "D":
                return question.getAnswerD().getIsCorrect();
            default:
                System.out.println("Opción inválida. Asegúrate de escribir A, B, C o D.");
                userAnswer = scanner.nextLine().trim().toUpperCase();
                return validateAnswer(userAnswer, question);
        }
    }

    /**
     * Inicializa al jugador con los valores iniciales.
     */
	public void initPlayer() {
		player.setNumberLives(3);
		player.setNumberPoints(0);
	}
	
    /**
     * Obtiene el puntaje actual del jugador.
     * @return El puntaje actual del jugador.
     */
	public int getPoints() {
		return player.getNumberPoints();
	}
	
    /**
     * Obtiene el número de vidas restantes del jugador.
     * @return El número de vidas del jugador.
     */
	public int getLives() {
		return player.getNumberLives();
	}
	
    /**
     * Reduce una vida al jugador.
     */
	public void loseLive() {
		player.setNumberLives(player.getNumberLives() - 1);
	}
	
	/**
	 * Devuelve los argumentos si necesitas usarlos
	 * @return Los argumentos
	 */
    public List<String> getArguments() {
        return arguments;
    }
	
}
