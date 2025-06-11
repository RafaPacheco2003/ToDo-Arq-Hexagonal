package com.todo.todoarqhexagonal.task.infrastructure.repositories;

import com.todo.todoarqhexagonal.task.domain.models.Task;
import com.todo.todoarqhexagonal.task.domain.ports.out.TaskRepositoryPort;
import com.todo.todoarqhexagonal.task.infrastructure.entities.TaskEntity;
import com.todo.todoarqhexagonal.task.infrastructure.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaTaskRepositoryAdapter implements TaskRepositoryPort {

    private final JpaTaskRepository jpaTaskRepository;
    private final TaskMapper taskMapper;

    public JpaTaskRepositoryAdapter(JpaTaskRepository jpaTaskRepository, TaskMapper taskMapper) {
        this.jpaTaskRepository = jpaTaskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public Task create(Task task) {

        TaskEntity taskEntity = taskMapper.modelToEntity(task);
        TaskEntity savedTaskEntity = jpaTaskRepository.save(taskEntity);


        return taskMapper.entityToModel(savedTaskEntity);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return jpaTaskRepository.findById(id)
                .map(taskMapper::entityToModel);
    }

    @Override
    public Optional<Task> update(Task task) {
        return jpaTaskRepository.findById(task.getTaskId())
                .map(entity -> {
                    entity.setTitle(task.getTitle());
                    entity.setDescription(task.getDescription());
                    entity.setDateLimite(task.getDateLimite());
                    entity.setDateCreation(task.getDateCreation());
                    TaskEntity updated = jpaTaskRepository.save(entity);
                    return taskMapper.entityToModel(updated);
                });
    }

    @Override
    public List<Task> findAll() {
        return jpaTaskRepository.findAll()
                .stream()
                .map(taskMapper::entityToModel)
                .toList();
    }

    @Override
    public Boolean delete(Long id) {
        if (jpaTaskRepository.existsById(id)) {
            jpaTaskRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
