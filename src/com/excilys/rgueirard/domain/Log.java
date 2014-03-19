package com.excilys.rgueirard.domain;

import java.sql.Timestamp;

public class Log {
	private long id;
	private Timestamp time;
	private String message;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
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

	@Override
	public String toString() {
		return "Log [id=" + id + ", time=" + time + ", message=" + message
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		
		public Builder time(Timestamp time) {
			this.log.time = time;
			return this;
		}
		
		public Builder message(String message) {
			this.log.message = message;
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
