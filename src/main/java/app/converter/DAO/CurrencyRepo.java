package app.converter.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import app.converter.model.Currency;
/**
 * 
 * @author Liming Liu
 * @role kind of like the auto-implementation of DAO class, has built in methods in JpaRepository<T,T>, 
 * 		also implements byType API, which means the method xxxByxxx will be auto-implemented by @Autowired annotation, don't need to implement by self
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface CurrencyRepo extends JpaRepository<Currency, Integer> {
	Currency findCurrencyByName(String name);
}
