package application.DAO;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import application.Application;
import application.entities.Concerto;
import application.entities.Evento;
import application.entities.GaraDiAtletica;
import application.entities.GenereConcerto;
import application.entities.PartitaDiCalcio;

public class EventoDAO {
	private final EntityManager em;

	public EventoDAO(EntityManager em) {
		this.em = em;
	}

	public void save(Evento e) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(e);
		transaction.commit();
		Application.logger.info("Evento salvato");
	}

	public Evento getById(UUID id) {
		Evento found = em.find(Evento.class, id);
		return found;
	}

	public void delete(UUID id) {
		Evento found = em.find(Evento.class, id);
		if (found != null) {
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			em.remove(found);
			transaction.commit();
			Application.logger.info("Evento con id " + id + " eliminato!");
		}
	}

	public void refresh(UUID id, String titolo) {
		Evento found = em.find(Evento.class, id);
		found.setTitolo(titolo);
		Application.logger.info("Prerefresh" + found);
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.refresh(found);
		transaction.commit();
		Application.logger.info("" + found);
	}

	public EntityManager getEm() {
		return em;
	}

	public List<Concerto> getConcertiInStreaming(boolean b) {
		TypedQuery<Concerto> getAllQuery = em.createQuery("SELECT c FROM Concerto c WHERE c.inStreaming = :bool",
				Concerto.class);
		getAllQuery.setParameter("bool", b);
		return getAllQuery.getResultList();
	}

	public List<Concerto> getConcertiPerGenere(GenereConcerto g) {
		TypedQuery<Concerto> getAllQuery = em.createQuery("SELECT c FROM Concerto c WHERE c.genere = :genere",
				Concerto.class);
		getAllQuery.setParameter("genere", g);
		return getAllQuery.getResultList();
	}

	public List<PartitaDiCalcio> getPartiteVinteInCasa() {
		TypedQuery<PartitaDiCalcio> p = em.createNamedQuery("findPartiteVinteInCasa", PartitaDiCalcio.class);
		return p.getResultList();
	}

	public List<PartitaDiCalcio> getPartiteVinteInTrasferta() {
		TypedQuery<PartitaDiCalcio> p = em.createNamedQuery("findPartiteVinteInTrasferta", PartitaDiCalcio.class);
		return p.getResultList();
	}

	public List<PartitaDiCalcio> getPartitePareggiate() {
		TypedQuery<PartitaDiCalcio> p = em.createNamedQuery("findPartitePareggiate", PartitaDiCalcio.class);
		return p.getResultList();
	}

	public List<GaraDiAtletica> getGaraDiAtleticaPerVincitore() {
		TypedQuery<GaraDiAtletica> g = em.createNamedQuery("findGaraDiAtleticaPerVincitore", GaraDiAtletica.class);
		return g.getResultList();
	}
}
