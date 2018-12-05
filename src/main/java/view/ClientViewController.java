package view;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import bank.model.IllegalBankTransactionException;
import controller.Controller;

public class ClientViewController {

	static final String DEFAULT_PAGE_URL="/";
	static final String CLIENT_PAGE_URL="client";
	static final String ADMIN_PAGE_URL="admin";
	
	private static final String CALCULATE_FORM="calculateForm";
	private static final String CHANGE_RATE_FORM="changeRateForm";
	
	@Autowired
    private Controller controller;
    
	@GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView() {
        return "redirect:" + CLIENT_PAGE_URL;
    }
	
	@GetMapping("/" + CLIENT_PAGE_URL)
    public String showClientView(CalculateAmountForm calculateAmountForm) {
        return CLIENT_PAGE_URL;
    }
	
	@GetMapping("/" + ADMIN_PAGE_URL)
    public String showAdminView(ChangeRateForm changeRateForm) {
        return ADMIN_PAGE_URL;
    }
	
	@PostMapping("/"+ CLIENT_PAGE_URL)
	public String calculateRate(@Valid CalculateAmountForm calculateAmountForm,
			BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
            model.addAttribute(CALCULATE_FORM, new CalculateAmountForm());
            return CLIENT_PAGE_URL;
        }
	}
	
}
