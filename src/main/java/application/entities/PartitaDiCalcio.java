package application.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "partite_di_calcio")
@Getter
@Setter
@NoArgsConstructor
@NamedQuery(name = "findPartiteVinteInCasa", query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente = p.squadraDiCasa")
@NamedQuery(name = "findPartiteVinteInTrasferta", query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente = p.squadraOspite")
@NamedQuery(name = "findPartitePareggiate", query = "SELECT p FROM PartitaDiCalcio p WHERE p.squadraVincente = null")
public class PartitaDiCalcio extends Evento {
	private String squadraDiCasa;
	private String squadraOspite;
	private String squadraVincente = null;
	private int golSquadraDiCasa;
	private int golSquadraOspite;

	public PartitaDiCalcio(String titolo, LocalDate dataEvento, String descrizione, TipoEvento tipoEvento,
			int numeroMassimopartecipazioni, Set<Partecipazione> listapartecipazioni, Location location,
			String squadraDiCasa, String squadraOspite, String squadraVincente, int golSquadraDiCasa,
			int golSquadraOspite) {
		super(titolo, dataEvento, descrizione, tipoEvento, numeroMassimopartecipazioni, listapartecipazioni, location);
		this.squadraDiCasa = squadraDiCasa;
		this.squadraOspite = squadraOspite;
		this.squadraVincente = squadraVincente;
		this.golSquadraDiCasa = golSquadraDiCasa;
		this.golSquadraOspite = golSquadraOspite;
	}

	@Override
	public String toString() {
		return "PartitaDiCalcio [squadraDiCasa=" + squadraDiCasa + ", squadraOspite=" + squadraOspite
				+ ", squadraVincente=" + squadraVincente + ", numeroGolSquadraDiCasa=" + golSquadraDiCasa
				+ ", numeroGolSquadraOspite=" + golSquadraOspite + ", location=" + location + "]";
	}

}
