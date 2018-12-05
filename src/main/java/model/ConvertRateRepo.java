package model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvertRateRepo extends JpaRepository<ConvertRate, Integer> {
	List<ConvertRate> findAll();
	
	@SuppressWarnings("unchecked")
	ConvertRate saveAndFlush(ConvertRate convertRate);
}
