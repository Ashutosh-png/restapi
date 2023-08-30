package com.workshop.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class roundTrip {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String sourceState;
	    private String sourceCity;
	    private String destinationState;
	    private String destinationCity;
	    private int hatchback;
	    private int sedan;
	    private int sedanPremium;
	    private int suv;
	    private int suvPlus;
	    private int status;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getSourceState() {
			return sourceState;
		}
		public void setSourceState(String sourceState) {
			this.sourceState = sourceState;
		}
		public String getSourceCity() {
			return sourceCity;
		}
		public void setSourceCity(String sourceCity) {
			this.sourceCity = sourceCity;
		}
		public String getDestinationState() {
			return destinationState;
		}
		public void setDestinationState(String destinationState) {
			this.destinationState = destinationState;
		}
		public String getDestinationCity() {
			return destinationCity;
		}
		public void setDestinationCity(String destinationCity) {
			this.destinationCity = destinationCity;
		}
		public int getHatchback() {
			return hatchback;
		}
		public void setHatchback(int hatchback) {
			this.hatchback = hatchback;
		}
		public int getSedan() {
			return sedan;
		}
		public void setSedan(int sedan) {
			this.sedan = sedan;
		}
		public int getSedanPremium() {
			return sedanPremium;
		}
		public void setSedanPremium(int sedanPremium) {
			this.sedanPremium = sedanPremium;
		}
		public int getSuv() {
			return suv;
		}
		public void setSuv(int suv) {
			this.suv = suv;
		}
		public int getSuvPlus() {
			return suvPlus;
		}
		public void setSuvPlus(int suvPlus) {
			this.suvPlus = suvPlus;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public roundTrip(Long id, String sourceState, String sourceCity, String destinationState,
				String destinationCity, int hatchback, int sedan, int sedanPremium, int suv, int suvPlus, int status) {
			super();
			this.id = id;
			this.sourceState = sourceState;
			this.sourceCity = sourceCity;
			this.destinationState = destinationState;
			this.destinationCity = destinationCity;
			this.hatchback = hatchback;
			this.sedan = sedan;
			this.sedanPremium = sedanPremium;
			this.suv = suv;
			this.suvPlus = suvPlus;
			this.status = status;
		}
		public roundTrip() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "roundTrip [id=" + id + ", sourceState=" + sourceState + ", sourceCity=" + sourceCity
					+ ", destinationState=" + destinationState + ", destinationCity=" + destinationCity + ", hatchback="
					+ hatchback + ", sedan=" + sedan + ", sedanPremium=" + sedanPremium + ", suv=" + suv + ", suvPlus="
					+ suvPlus + ", status=" + status + "]";
		}
	    
	    
	    

}
