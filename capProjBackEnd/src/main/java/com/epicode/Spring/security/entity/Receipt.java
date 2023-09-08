package com.epicode.Spring.security.entity;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;

import com.epicode.Spring.enums.TicketPrice;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name="receipts")
public class Receipt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
	private Set<Booking> bookings;
	
	@Column(nullable=false, updatable = false)
	private LocalDateTime purchaseTime;
	
	@Column(nullable=false)
	private Double totPrice;
	
	public void setUser(User user) {
		if(!user.equals(null)) this.user = user;
		else throw new DataIntegrityViolationException("Please, specify the customer for this ticket.");
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
		for(Booking b : bookings) b.setReceipt(this);
		
		int selectedRow=bookings.iterator().next().getSeat().getNRow();
		int hallRows=bookings.iterator().next().getViewSchedule().getHall().getNRows();
		
		this.totPrice=hallRows-selectedRow<=2 ? bookings.size()*TicketPrice.VIP.getValue() : bookings.size()*TicketPrice.REGULAR.getValue();
		if(bookings.iterator().next().getViewSchedule().getMovie().getIsTridimensional()) this.totPrice+=bookings.size()*TicketPrice.TRIDIMENSIONAL.getValue();
		
		this.purchaseTime = LocalDateTime.now();
	}
}
