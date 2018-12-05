package model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepo extends JpaRepository<Counter, Integer> {
	Counter findCounterById(int id);
}
