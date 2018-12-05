package view;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

public class CalculateAmountForm {
	 @NotBlank(message = "Please specify currency name you want convert from")
	    // The regex below should permit only characters, but asterisk is
	    // unfortunately also valid.
	 @Pattern(regexp = "^[a-zA-Z]+$")
	 private String fromCurr;
	 
	 @NotBlank(message = "Please specify currency name you want convert to")
	    // The regex below should permit only characters, but asterisk is
	    // unfortunately also valid.
	 @Pattern(regexp = "^[a-zA-Z]+$")
	 private String toCurr;
	 
	 @NotNull(message = "Please specify the amount of currency you want to convert")
	 @PositiveOrZero(message = "the amount must be zero or greater")
	 private Double amount;

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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "CalculateAmountForm [fromCurr=" + fromCurr + ", toCurr=" + toCurr + ", amount=" + amount + "]";
	}
	 
	 

}
