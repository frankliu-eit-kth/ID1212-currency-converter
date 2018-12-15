package app.converter.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.PositiveOrZero;
/**
 * 
 * @author Liming Liu
 * JPA entity used
 *
 */
@Entity
@Table(name="CONVERT_RATE")
public class ConvertRate implements ConvertRateDTO{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="CONVERT_ID")
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL) //one to one mapping
    @JoinColumn(name = "CURRENCY_FROM", nullable = false)// 
	private Currency curFrom;
	
	@OneToOne(cascade = CascadeType.ALL) //one to one mapping
    @JoinColumn(name = "CURRENCY_To", nullable = false)// 
	private Currency curTo;
	
	@PositiveOrZero(message = "{convertRate.rate.negative}")
    @Column(name = "CONVERT_RATE")
    private double rate;
	
	
	@Version
    @Column(name = "CONVERT_OPTLOCK_VERSION")
    private int optLockVersion;
	
	public ConvertRate(long id, Currency curFrom, Currency curTo, double rate) {
		super();
		this.id = id;
		this.curFrom = curFrom;
		this.curTo = curTo;
		this.rate=rate;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Currency getCurFrom() {
		return curFrom;
	}

	public void setCurFrom(Currency curFrom) {
		this.curFrom = curFrom;
	}

	public Currency getCurTo() {
		return curTo;
	}

	public void setCurTo(Currency curTo) {
		this.curTo = curTo;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public ConvertRate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ConvertRate [id=" + id + ", curFrom=" + curFrom + ", curTo=" + curTo + ", rate=" + rate
				+ ", optLockVersion=" + optLockVersion + "]";
	}


}
