package app.converter.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import app.converter.model.ConvertRate;
/**
 * 
 * @author Liming Liu
 * @role kind of like the auto-implementation of DAO class, has built in methods in JpaRepository<T,T>, 
 * 		also implements byType API, which means the method xxxByxxx will be auto-implemented by @Autowired annotation, don't need to implement by self
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ConvertRateRepo extends JpaRepository<ConvertRate, Integer> {
	List<ConvertRate> findAll();
	
	@SuppressWarnings("unchecked")
	ConvertRate saveAndFlush(ConvertRate convertRate);
}
