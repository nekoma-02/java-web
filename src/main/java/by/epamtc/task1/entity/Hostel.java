package by.epamtc.task1.entity;

import java.io.Serializable;

public class Hostel implements Serializable {

	private static final long serialVersionUID = -1589770324127624695L;
	
	private int id;
	private int numberHostel;
	private int occupiedPlaces;
	private int totalPlaces;
	
	public Hostel() {
	}
	
	
	public Hostel(int id) {
		super();
		this.id = id;
	}


	public Hostel(int id, int numberHostel, int occupiedPlaces, int totalPlaces) {
		super();
		this.id = id;
		this.numberHostel = numberHostel;
		this.occupiedPlaces = occupiedPlaces;
		this.totalPlaces = totalPlaces;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumberHostel() {
		return numberHostel;
	}

	public void setNumberHostel(int numberHostel) {
		this.numberHostel = numberHostel;
	}

	public int getOccupiedPlaces() {
		return occupiedPlaces;
	}

	public void setOccupiedPlaces(int occupiedPlaces) {
		this.occupiedPlaces = occupiedPlaces;
	}

	public int getTotalPlaces() {
		return totalPlaces;
	}

	public void setTotalPlaces(int totalPlaces) {
		this.totalPlaces = totalPlaces;
	}


	@Override
	public String toString() {
		return "Hostel [id=" + id + ", numberHostel=" + numberHostel + ", occupiedPlaces=" + occupiedPlaces
				+ ", totalPlaces=" + totalPlaces + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + numberHostel;
		result = prime * result + occupiedPlaces;
		result = prime * result + totalPlaces;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Hostel other = (Hostel) obj;
		if (id != other.id) {
			return false;
		}
		if (numberHostel != other.numberHostel) {
			return false;
		}
		if (occupiedPlaces != other.occupiedPlaces) {
			return false;
		}
		if (totalPlaces != other.totalPlaces) {
			return false;
		}
		return true;
	}
	
	
	

}
