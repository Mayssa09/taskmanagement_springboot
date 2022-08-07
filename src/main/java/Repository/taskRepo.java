package Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entity.Task;

public interface taskRepo extends JpaRepository<Task,Long>{
	@Query(value = "select * from task order by due_date desc", nativeQuery = true)
	public List<Task> getAllTaskByDueDate();
}
