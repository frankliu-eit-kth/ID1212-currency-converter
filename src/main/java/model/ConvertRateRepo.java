package model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface ConvertRateRepo extends JpaRepository<ConvertRate, Integer> {
	List<ConvertRate> findAll();
	
	@SuppressWarnings("unchecked")
	ConvertRate saveAndFlush(ConvertRate convertRate);
}
