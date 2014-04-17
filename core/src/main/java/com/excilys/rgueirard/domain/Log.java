package com.excilys.rgueirard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name="log")
public class Log {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
	private long id;
	
	@Column(name="time")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime time;
	
	@Column(name="message")
	private String message;
	
	
	@Column(name="computer_id")
	private long computerId;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DateTime getTime() {
		return time;
	}

	public void setTime(DateTime time) {
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Log() {
		super();
		
	}
	
	public long getComputerId() {
		return computerId;
	}

	public void setComputerId(long computerId) {
		this.computerId = computerId;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", time=" + time + ", message=" + message
				+ ", computerId=" + computerId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (computerId ^ (computerId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		if (computerId != other.computerId)
			return false;
		if (id != other.id)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	public static class Builder {
		private Log log;

		private Builder() {
			log = new Log();
		}

		public Builder id(long id) {
			this.log.id = id;
			return this;
		}
		
		public Builder time(DateTime time) {
			this.log.time = time;
			return this;
		}
		
		public Builder message(String message) {
			this.log.message = message;
			return this;
		}
		
		public Builder computerId(long computerId) {
			this.log.computerId = computerId;
			return this;
		}
		
		public Log build() {
			return this.log;
		}

	}

	public static Builder builder() {
		return new Builder();
	}
}
