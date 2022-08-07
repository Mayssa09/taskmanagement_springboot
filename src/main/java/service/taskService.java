package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Repository.taskRepo;

import entity.Task;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class taskService<TaskRepository> {
	private taskRepo TaskRepo;

	public List<Task> getTasks() {
		// TODO Auto-generated method stub
		return TaskRepo.getAllTaskByDueDate();
	}
	public Optional<Task> getTaskById(Long id) {
		// TODO Auto-generated method stub
		return TaskRepo.findById(id);
	}

	public Task save(Task task) {
		// TODO Auto-generated method stub
		return TaskRepo.saveAndFlush(task);
	}

	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return TaskRepo.existsById(id);
	}

	public  void deleteTask(Long id) {
		// TODO Auto-generated method stub
		TaskRepo.deleteById(id);
	}

}
