package com.epicode.Spring.security.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;

import com.epicode.Spring.enums.HallStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="halls")
public class Hall {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Integer nRows;
	
	@Column(nullable = false)
	private Integer nSeatsPerRow;
	
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private HallStatus status=HallStatus.AVAILABLE;
	
//	@OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
//	private Set<Seat> seats;

	public void setnRows(Integer nRows) {
		if(!nRows.equals(null) && nRows>0) this.nRows = nRows;
		else throw new DataIntegrityViolationException("Please, insert a correct amount of rows.");
	}

	public void setnSeatsPerRow(Integer nSeatsPerRow) {
		if(!nSeatsPerRow.equals(null) && nSeatsPerRow>0) this.nSeatsPerRow = nSeatsPerRow;
		else throw new DataIntegrityViolationException("Please, insert a correct amount of seats.");
	}

//	@PrePersist
//	private void setSeats() {
//		this.seats = new HashSet<Seat>();
//		for(int i=0; i<this.nRows; i++) {
//			for(int j=0; j<this.nSeatsPerRow; j++) {
//				Seat s=new Seat();
//				s.setNRow(i);
//				s.setNSeat(j);
//				s.setHall(this);
//				this.seats.add(s);
//			}
//		}
//	}

	public void setStatus(HallStatus status) {
		if(!status.equals(null)) this.status = status;
	}
}
