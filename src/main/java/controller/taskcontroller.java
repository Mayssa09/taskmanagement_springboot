package controller;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import entity.Task;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import service.taskService;
@RestController
@AllArgsConstructor 

public class taskcontroller {
	private taskService<?> TaskService;
	@GetMapping("/task")
	public List<Task> getTasks(){
		return TaskService.getTasks();
	}
	@SuppressWarnings({ "unchecked", "hiding" })
	@GetMapping("/task/{id}")
	public <Task> Task getTask(@PathVariable Long id) {
		try {
			return (Task) TaskService.getTaskById(id).orElseThrow(
				()-> new EntityNotFoundException("Resquested task not found")	)
					;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@PostMapping("/task")
	public Task addTask(@RequestBody Task task) {
		return TaskService.save(task);
		
	}

	@PutMapping("/task/{id}")
	public ResponseEntity<?> addTask(@RequestBody Task task,@PathVariable Long id){
		if (TaskService.existsById(id)) {
			Task task1 = null;
			try {
				task1 = (entity.Task) TaskService.getTaskById(id).orElseThrow(()->new EntityNotFoundException("Requested task not found"));
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			task1.setTitle(task.getTitle());
			task1.setDescription(task.getDescription());
			task1.setDueDate(task.getDueDate());
			task1.setType(task.getType());
			TaskService.save(task);
			return ResponseEntity.ok().body(task1);
		}else {
		HashMap<String,String> message = new HashMap<>();
		message.put("message", id + "task not found or matched");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	}

	@DeleteMapping("/task/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id){
	if (TaskService.existsById(id))	{
		TaskService.deleteTask(id);
		HashMap<String,String> message = new HashMap<>();
		message.put("message",  "task with id" + id + "deleted successfully");
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		
	}
	else {
		HashMap<String,String> message = new HashMap<>();
		message.put("message",  "task with id" + id + "not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}

	}
}
