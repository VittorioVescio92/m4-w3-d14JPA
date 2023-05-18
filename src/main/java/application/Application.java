package application;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.DAO.EventoDAO;
import application.DAO.LocationDAO;
import application.DAO.PartecipazioneDAO;
import application.DAO.PersonaDAO;
import application.entities.Concerto;
import application.entities.GaraDiAtletica;
import application.entities.GenereConcerto;
import application.entities.Location;
import application.entities.Partecipazione;
import application.entities.PartitaDiCalcio;
import application.entities.Persona;
import application.entities.Sesso;
import application.entities.StatoPartecipazione;
import application.entities.TipoEvento;
import application.utils.JpaUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {
	public static Logger logger = LoggerFactory.getLogger(Application.class);
	private static EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		EventoDAO ed = new EventoDAO(em);
		PersonaDAO pd = new PersonaDAO(em);
		LocationDAO ld = new LocationDAO(em);
		PartecipazioneDAO pd2 = new PartecipazioneDAO(em);

		Location location1 = new Location("Parco Margherita", "Amantea (CS)");
		Location location2 = new Location("Stadio San Vito-Gigi Marulla", "Cosenza (CS)");
		Location location3 = new Location("Stadio Giuseppe Meazza in San Siro", "Milano (MI)");

		Partecipazione partecipazione1 = new Partecipazione(null, null, StatoPartecipazione.CONFERMATA);

		Partecipazione partecipazione2 = new Partecipazione(null, null, StatoPartecipazione.DA_CONFERMARE);

		Persona persona1 = new Persona("Ajeje", "Brazorf", "ajejeBrazorf@yahoo.com", LocalDate.of(1989, 4, 15),
				Sesso.MASCHIO, new HashSet<Partecipazione>(Arrays.asList(partecipazione1, partecipazione2)));

		Persona persona2 = new Persona("Signor", "Rezzonico", "rezzo.nico@yahoo.com", LocalDate.of(1985, 4, 15),
				Sesso.MASCHIO, new HashSet<Partecipazione>(Arrays.asList(partecipazione1, partecipazione2)));

		Concerto evento1 = new Concerto("Festival di Amantea", LocalDate.of(2023, 6, 15), "Evento benefico",
				TipoEvento.PUBBLICO, 1500, new HashSet<Partecipazione>(Arrays.asList(partecipazione1, partecipazione2)),
				location1, GenereConcerto.ROCK, false);

		PartitaDiCalcio evento2 = new PartitaDiCalcio("Cosenza-Cagliari", LocalDate.of(2023, 05, 19),
				"Il Cagliari si gioca la serie A, il Cosenza la salvezza!", TipoEvento.PUBBLICO, 1500,
				new HashSet<Partecipazione>(Arrays.asList(partecipazione1, partecipazione2)), location2, "Cosenza",
				"Cagliari", "Cosenza", 2, 1);

		GaraDiAtletica evento3 = new GaraDiAtletica("Salto in lungo", LocalDate.of(2023, 6, 18), "Ammazza che salto!",
				TipoEvento.PUBBLICO, 1000, new HashSet<Partecipazione>(Arrays.asList(partecipazione1, partecipazione2)),
				location1, new HashSet<Persona>(Arrays.asList(persona1, persona2)), persona1);

		PartitaDiCalcio evento4 = new PartitaDiCalcio("Milan-Cosenza", LocalDate.of(2023, 05, 19), "prova",
				TipoEvento.PUBBLICO, 1500, new HashSet<Partecipazione>(Arrays.asList(partecipazione1)), location3,
				"Milan", "Cosenza", "Milan", 2, 1);

		PartitaDiCalcio evento5 = new PartitaDiCalcio("Milan-Cosenza", LocalDate.of(2023, 05, 19), "prova",
				TipoEvento.PUBBLICO, 1500, new HashSet<Partecipazione>(Arrays.asList(partecipazione1)), location3,
				"Milan", "Cosenza", "Cosenza", 0, 3);

		PartitaDiCalcio evento6 = new PartitaDiCalcio("Inter-Cosenza", LocalDate.of(2023, 05, 19), "prova",
				TipoEvento.PUBBLICO, 1500, new HashSet<Partecipazione>(Arrays.asList(partecipazione2)), location3,
				"Inter", "Cosenza", null, 3, 3);

		GaraDiAtletica evento7 = new GaraDiAtletica("100m piani", LocalDate.of(2023, 6, 18), "Ammazza che corsa!",
				TipoEvento.PUBBLICO, 1000, new HashSet<Partecipazione>(Arrays.asList(partecipazione1, partecipazione2)),
				location1, new HashSet<Persona>(Arrays.asList(persona1, persona2)), persona1);

		GaraDiAtletica evento8 = new GaraDiAtletica("60m piani", LocalDate.of(2023, 6, 18), "Ammazza che corsaaaa!",
				TipoEvento.PUBBLICO, 1000, new HashSet<Partecipazione>(Arrays.asList(partecipazione1, partecipazione2)),
				location1, new HashSet<Persona>(Arrays.asList(persona1, persona2)), persona2);

//		ld.save(location1);
//		ld.save(location1);
//		pd2.save(partecipazione1);
//		ed.save(evento1);
//		pd.save(persona1);
//		partecipazione1.setPersona(persona1);
//		partecipazione1.setEvento(evento1);
//		pd2.update(partecipazione1);
//		pd2.save(partecipazione2);
//		pd.save(persona2);
//		partecipazione2.setPersona(persona2);
//		partecipazione2.setEvento(evento1);
//		pd2.update(partecipazione2);
//		ed.save(evento2);
//		ed.save(evento3);
//		ed.save(evento4);
//		ed.save(evento5);
//		ed.save(evento6);
//		ed.save(evento7);
//		ed.save(evento8);

		List<Concerto> found = ed.getConcertiInStreaming(false);
		if (found.size() > 0) {
			found.stream().forEach(c -> log.info("getConcertiInStreaming - " + c.toString()));
		} else {
			log.info("Nessun concerto trovato");
		}

		System.err.println("*************************************------*******************************");

		List<Concerto> found2 = ed.getConcertiPerGenere(GenereConcerto.ROCK);
		if (found2.size() > 0) {
			found2.stream().forEach(c -> log.info("getConcertiPerGenere - " + c.toString()));
		} else {
			log.info("Nessun concerto trovato");
		}

		System.err.println("*************************************------*******************************");

		List<PartitaDiCalcio> found3 = ed.getPartiteVinteInCasa();
		if (found3.size() > 0) {
			found3.stream().forEach(p -> log.info("getPartiteVinteInCasa - " + p.toString()));
		} else {
			log.info("Nessun partita vinta in casa trovata");
		}

		System.err.println("*************************************------*******************************");

		List<PartitaDiCalcio> found4 = ed.getPartiteVinteInTrasferta();
		if (found4.size() > 0) {
			found4.stream().forEach(p -> log.info("getPartiteVinteInTrasferta - " + p.toString()));
		} else {
			log.info("Nessun partita vinta in trasferta trovata");
		}

		System.err.println("*************************************------*******************************");

		List<PartitaDiCalcio> found5 = ed.getPartitePareggiate();
		if (found5.size() > 0) {
			found5.stream().forEach(p -> log.info("getPartitePartitePareggiate - " + p.toString()));
		} else {
			log.info("Nessun partita pareggiata trovata");
		}

		System.err.println("*************************************------*******************************");

		List<GaraDiAtletica> found6 = ed.getGaraDiAtleticaPerVincitore();
		if (found6.size() > 0) {
			found6.stream().forEach(g -> log.info("getGaraDiAtleticaPerVincitore - " + g.toString()));
		} else {
			log.info("Nessun gara trovata");
		}

//		Evento foundE = ed.getById(UUID.fromString("99c88d74-edbd-49dc-b3de-15a8d168280a"));
//		if (foundE != null) {
//			log.info(foundE.toString());
//			log.info(foundE.getListaPartecipazioni().toString());
//		} else {
//			log.info("evento non presente");
//		}

		em.close();
		emf.close();

	}

}
