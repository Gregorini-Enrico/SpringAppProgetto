package gg.project.DBapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import gg.project.DBapp.Exception.*;
import gg.project.DBapp.service.*;
import gg.project.DBapp.Filter.*;
import gg.project.DBapp.model.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.*;

/**
 * Classe per eseguire test su alcuni metodi 
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
@SpringBootTest
class DBappApplicationTests {
	
	//inizializzo alcuni oggetti che mi serviranno per i tests
	private RecordDeleted rd1 = null;   
	private RecordDeleted rd2 = null;
	private RecordFile rf1 = null;
	private RecordFile rf2 = null;
	List<RecordDeleted> lrd = null;
	List<RecordFile> lrf = null;
	Date datainizio = null, datafine = null;
	
	@BeforeEach
	void setUp() throws Exception {   //eseguo le operazioni prima di effettuare i test
		rd1 = new RecordDeleted("ereditarieta.pdf", "deleted", "/ereditarieta.pdf", "98765", "2020-09-12T07:45:12Z", "mnbvc12", 125);
		rd2 = new RecordDeleted("quiz_2.pdf", "deleted", "/prova1/quiz_2.pdf", "98765", "2020-09-11T07:45:12Z", "mlkbvc12", 3200);
		rf1 = new RecordFile("java.exe", "file", "/java.exe", "123456", "12/09/2020", "abcdef123", 50);
		rf2 = new RecordFile("eclipse.txt", "file", "/programmazione/eclipse.txt", "654321", "14/09/2020", "123qwerty", 500);
		lrd = new ArrayList<RecordDeleted>();
		lrd.add(rd1);
		lrd.add(rd2);
		lrf = new ArrayList<RecordFile>();
		lrf.add(rf1);
		lrf.add(rf2);
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test  //testa i metodi get di RecordFile e RecordDeleted
	void RecordsTest() {
		assertEquals("ereditarieta.pdf", rd1.getName());   //controllo se il risultato aspettato è uguale a quello effettivo con AsserEquals
		assertEquals("deleted", rd2.getTag());
		assertEquals("123456", rf1.getId());
		assertEquals("/programmazione/eclipse.txt", rf2.getPath_lower());
	}
	
	@Test
	void ExceptionTest() {
		//controllo con assertThrows se con questi input(sbagliati), il programma genera le eccezioni previste
		assertThrows(DateFilterIncorrectException.class, ()->{DateFilter.betweenDate(lrd, "[01/09/2020, 01/08/2020]");}); //data fine antecedente a data inizio
		assertThrows(TypeNotFoundException.class, ()->{TypeFilter.type(lrf, "aaa");});   //non esiste un file di tipo "aaa"
		assertThrows(FileInDateNotFoundException.class, ()->{DateFilter.afterDate(lrd, "18/12/2020");});   //non esistono file modificati dopo il 18/12/2020
	}
	
	@Test //test per il filtro del path
	void testPath() {
		lrd = NameFilter.getFileDeleted(lrd, "/prova1/quiz_2.pdf");   
		assertTrue(lrd.contains(rd2));
	}
	
	@Test //test per il filtro sul tipo dei file
	void testFilterType() {
		lrf = TypeFilter.type(lrf, "txt");
		assertTrue(lrf.size()==1);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
