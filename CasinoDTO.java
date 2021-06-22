package casino;

public class CasinoDTO {
	private int cno;
	private String cname;
	private String cbirth;
	private String cgrade;
	private String center;
	
	public CasinoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public CasinoDTO(int cno, String cname, String cbirth, String cgrade, String center) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.cbirth = cbirth;
		this.cgrade = cgrade;
		this.center = center;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCbirth() {
		return cbirth;
	}

	public void setCbirth(String cbirth) {
		this.cbirth = cbirth;
	}

	public String getCgrade() {
		return cgrade;
	}

	public void setCgrade(String cgrade) {
		this.cgrade = cgrade;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}
	
	
	
}
