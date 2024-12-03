import java.io.IOException;

import co.poli.edu.controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
/**
 * Clase principal de la aplicación.
 * Se encarga de inicializar el controlador del juego y comenzar el flujo.
 */
public class MainApp extends Application{
	
	/**
	 * Método para iniciar la interfaz grafica de java FX
	 * 
	 * @param primaryStage es la primera vista en la que se inicia la interfaz por defecto
	 */
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Crear una instancia del Controlador y pasar el Stage principal
    	GameController controller = new GameController(primaryStage, getParameters().getRaw());
        controller.showMainView(); // Llama al método del controlador para mostrar la ventana principal
    }
    
    /**
     * Método principal que inicia la ejecución de la aplicación.
     *
     * @param args argumentos de línea de comandos.
     * @throws IOException si ocurre un error de entrada/salida durante la ejecución.
     */
    public static void main(String[] args) throws IOException {
    	
    	launch(args);     
    }

}
