package view;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import controller.LogicController;
import model.Counter;

@Controller
@Scope("session")
public class ClientViewController {

	static final String DEFAULT_PAGE_URL="/";
	static final String CLIENT_PAGE_URL="client";
	static final String ADMIN_PAGE_URL="admin";
	static final String RESULT_URL="result";
	static final String CHANGE_RATE_RESULT_URL="change-result";
	
	private static final String CALCULATE_FORM="calculateAmountForm";
	private static final String CHANGE_RATE_FORM="changeRateForm";
	
	@Autowired
    private LogicController controller;
    
	@GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView() {
        return "redirect:" + CLIENT_PAGE_URL;
    }
	
	@GetMapping("/" + CLIENT_PAGE_URL)
    public String showClientView(CalculateAmountForm calculateAmountForm) {
        return CLIENT_PAGE_URL;
    }
	
	@GetMapping("/"+ADMIN_PAGE_URL)
	public String showAdminView(ChangeRateForm changeRateForm,Model model) {
		Counter counter=controller.getCounter();
		if(counter==null) {
			model.addAttribute(ExceptionHandlers.ERROR_PATH,ExceptionHandlers.GENERIC_ERROR );
			return ExceptionHandlers.ERROR_PAGE_URL;
		}else {
			model.addAttribute("counter",counter);
			return ADMIN_PAGE_URL;
		}
	}
	
	/*
	@GetMapping("/" + ADMIN_PAGE_URL)
    public String showAdminView(ChangeRateForm changeRateForm) {
        return ADMIN_PAGE_URL;
    }
	*/
	
	@PostMapping("/"+RESULT_URL)
	public String getResultPage(@Valid @ModelAttribute(CALCULATE_FORM)CalculateAmountForm calculateAmountForm,
			BindingResult bindingResult,Model model) throws Exception{
		if (!bindingResult.hasErrors()) {
			CalculateAmountForm resultForm=controller.calculateAmount(calculateAmountForm.getCurrFrom(), calculateAmountForm.getCurrTo(), calculateAmountForm.getAmountFrom());
			model.addAttribute(CALCULATE_FORM, resultForm);
			return RESULT_URL;
        }else {
        	model.addAttribute(ExceptionHandlers.ERROR_PATH,ExceptionHandlers.GENERIC_ERROR );
			return ExceptionHandlers.ERROR_PAGE_URL;
        }
	}
	@PostMapping("/"+CHANGE_RATE_RESULT_URL)
	public String getChangeReultPage(@Valid @ModelAttribute(CHANGE_RATE_FORM)ChangeRateForm changeRateForm,
			BindingResult bindingResult,Model model) throws Exception{
		if (!bindingResult.hasErrors()) {
			controller.changeConvertRate(changeRateForm.getCurrFrom(), changeRateForm.getCurrTo(), changeRateForm.getNewRate());
			model.addAttribute(CHANGE_RATE_FORM	, changeRateForm);
			return CHANGE_RATE_RESULT_URL;
        }
		else {
        model.addAttribute(ExceptionHandlers.ERROR_PATH,ExceptionHandlers.GENERIC_ERROR );
		return ExceptionHandlers.ERROR_PAGE_URL;
		}
	}
}
