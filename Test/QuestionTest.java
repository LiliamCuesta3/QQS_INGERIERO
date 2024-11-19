import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import co.poli.edu.model.Question;

class QuestionTest {

	@Test
	// Traer Pregunta
	void GetStatementQuestionTest() {
		Question question = new Question();
		question.statementQuestion = "Pregunta Test";
		assertEquals("Pregunta Test", question.getStatementQuestion());
	}
	
	@Test
	// Configurar pegunta
	void SetStatementQuestionTest() {
		Question question = new Question();
		question.setStatementQuestion("Pregunta Test");
		assertEquals("Pregunta Test", question.getStatementQuestion());
	}
	
	@Test
	// Traer Pregunta
	void getClueTest() {
		Question question = new Question();
		question.clue = "Pista Test";
		assertEquals("Pista Test", question.getClue());
	}
	
	@Test
	// Configurar pegunta
	void setClueTest() {
		Question question = new Question();
		question.setClue("Pista Test");
		assertEquals("Pista Test", question.getClue());
	}

}
