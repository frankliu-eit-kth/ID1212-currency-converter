package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "COUNTER")
public class Counter implements CounterDTO {
	
	@Id
    @Column(name = "COUNTER_ID")
    private long id;
	
	
	@Column(name="count")
	private int count;
	
	@Version
	@Column(name = "COUNTER_OPTLOCK_VERSION")
	private int optLockVersion;
	
	public Counter() {
		// TODO Auto-generated constructor stub
	}
	
	public Counter(int id,int count) {
		this.id=id;
		this.count=count;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void addCount() {
		this.count++;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void setCount(int count) {
		this.count=count;
	}

	@Override
	public String toString() {
		return "Counter [id=" + id + ", count=" + count + "]";
	}

	


}
