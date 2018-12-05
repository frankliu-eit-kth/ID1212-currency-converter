package view;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

public class ChangeRateForm {
	 @NotBlank(message = "Please specify currency name you want change to convert from")
	    // The regex below should permit only characters, but asterisk is
	    // unfortunately also valid.
	 @Pattern(regexp = "^[a-zA-Z]+$")
	 private String fromCurr;
	 
	 @NotBlank(message = "Please specify currency name you want change to convert to")
	    // The regex below should permit only characters, but asterisk is
	    // unfortunately also valid.
	 @Pattern(regexp = "^[a-zA-Z]+$")
	 private String toCurr;
	 
	 @NotNull(message = "Please specify the new rate")
	 @PositiveOrZero(message = "the amount must be zero or greater")
	 private Double newRate;

	public String getFromCurr() {
		return fromCurr;
	}

	public void setFromCurr(String fromCurr) {
		this.fromCurr = fromCurr;
	}

	public String getToCurr() {
		return toCurr;
	}

	public void setToCurr(String toCurr) {
		this.toCurr = toCurr;
	}

	public Double getNewRate() {
		return newRate;
	}

	public void setNewRate(Double newRate) {
		this.newRate = newRate;
	}

	@Override
	public String toString() {
		return "ChangeRateForm [fromCurr=" + fromCurr + ", toCurr=" + toCurr + ", newRate=" + newRate + "]";
	}
	 
	 
	 

}
