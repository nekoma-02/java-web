package by.epamtc.task1.entity;

import java.io.Serializable;

public class Application implements Serializable {

	private static final long serialVersionUID = -6269264116059419635L;
	
	private int id;
	private String adress;
	private int certificate;
	private Privilege privilege;
	private User user;
	private School school;
	private boolean needHostel;
	private Hostel hostel;
	private Specialty specialties;
	private boolean confirmation;
	
	public Application() {
		// TODO Auto-generated constructor stub
	}

	
	public Application(int id) {
		super();
		this.id = id;
	}


	public Application(int id, String adress, int certificate, Privilege privilege, User user, School school,
			boolean needHostel, Specialty specialty, boolean confirmation) {
		this.id = id;
		this.adress = adress;
		this.certificate = certificate;
		this.privilege = privilege;
		this.user = user;
		this.school = school;
		this.needHostel = needHostel;
		this.specialties = specialty;
		this.confirmation = confirmation;
	}

	public Application(int id, String adress, int certificate, Privilege privilege, User user, School school,
			boolean needHostel, Hostel hostel, Specialty specialties, boolean confirmation) {
		this(id,adress,certificate,privilege,user,school,needHostel,specialties,confirmation);
		this.hostel = hostel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getCertificate() {
		return certificate;
	}

	public void setCertificate(int certificate) {
		this.certificate = certificate;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public boolean isNeedHostel() {
		return needHostel;
	}

	public void setNeedHostel(boolean needHostel) {
		this.needHostel = needHostel;
	}

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}

	public Specialty getSpecialty() {
		return specialties;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialties = specialty;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}


	@Override
	public String toString() {
		return "Application [id=" + id + ", adress=" + adress + ", certificate=" + certificate + ", privilege="
				+ privilege + ", user=" + user + ", school=" + school + ", needHostel=" + needHostel + ", hostel="
				+ hostel + ", specialties=" + specialties + ", confirmation=" + confirmation + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + certificate;
		result = prime * result + (confirmation ? 1231 : 1237);
		result = prime * result + ((hostel == null) ? 0 : hostel.hashCode());
		result = prime * result + id;
		result = prime * result + (needHostel ? 1231 : 1237);
		result = prime * result + ((privilege == null) ? 0 : privilege.hashCode());
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		result = prime * result + ((specialties == null) ? 0 : specialties.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Application other = (Application) obj;
		if (adress == null) {
			if (other.adress != null) {
				return false;
			}
		} else if (!adress.equals(other.adress)) {
			return false;
		}
		if (certificate != other.certificate) {
			return false;
		}
		if (confirmation != other.confirmation) {
			return false;
		}
		if (hostel == null) {
			if (other.hostel != null) {
				return false;
			}
		} else if (!hostel.equals(other.hostel)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (needHostel != other.needHostel) {
			return false;
		}
		if (privilege == null) {
			if (other.privilege != null) {
				return false;
			}
		} else if (!privilege.equals(other.privilege)) {
			return false;
		}
		if (school == null) {
			if (other.school != null) {
				return false;
			}
		} else if (!school.equals(other.school)) {
			return false;
		}
		if (specialties == null) {
			if (other.specialties != null) {
				return false;
			}
		} else if (!specialties.equals(other.specialties)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}
	
	
	
	
	
}
