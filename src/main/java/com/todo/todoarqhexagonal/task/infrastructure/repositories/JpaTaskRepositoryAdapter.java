package com.todo.todoarqhexagonal.task.infrastructure.repositories;

import com.todo.todoarqhexagonal.task.domain.models.Task;
import com.todo.todoarqhexagonal.task.domain.ports.out.TaskRepositoryPort;
import com.todo.todoarqhexagonal.task.infrastructure.entities.TaskEntity;
import com.todo.todoarqhexagonal.task.infrastructure.mappers.TaskMapper;
import com.todo.todoarqhexagonal.user.infrastructure.entities.UserEntity;
import com.todo.todoarqhexagonal.user.infrastructure.repositories.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaTaskRepositoryAdapter implements TaskRepositoryPort {

    private final JpaTaskRepository jpaTaskRepository;
    private final TaskMapper taskMapper;

    private final JpaUserRepository jpaUserRepository; // Añadir dependencia

    public JpaTaskRepositoryAdapter(JpaTaskRepository jpaTaskRepository,
                                    TaskMapper taskMapper,
                                    JpaUserRepository jpaUserRepository) {
        this.jpaTaskRepository = jpaTaskRepository;
        this.taskMapper = taskMapper;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Task create(Task task) {

        TaskEntity taskEntity = taskMapper.modelToEntity(task);


        if (task.getUserId() != null) {
            UserEntity userEntity = jpaUserRepository.findById(task.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            taskEntity.setUser(userEntity);
        }

        TaskEntity savedTaskEntity = jpaTaskRepository.save(taskEntity);


        return taskMapper.entityToModel(savedTaskEntity);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return jpaTaskRepository.findById(id)
                .map(taskEntity -> {
                    // Cargar el UserEntity si es necesario
                    if (taskEntity.getUser() != null) {
                        taskEntity.getUser().getUserId();
                    }
                    return taskMapper.entityToModel(taskEntity);
                });
    }

    @Override
    public List<Task> findAll() {
        return jpaTaskRepository.findAll()
                .stream()
                .peek(taskEntity -> {
                    // Cargar el UserEntity si es necesario
                    if (taskEntity.getUser() != null) {
                        taskEntity.getUser().getUserId();
                    }
                })
                .map(taskMapper::entityToModel)
                .toList();
    }
    @Override
    public Optional<Task> update(Task task) {
        return jpaTaskRepository.findById(task.getTaskId())
                .map(entity -> {
                    // Actualizar campos básicos
                    entity.setTitle(task.getTitle());
                    entity.setDescription(task.getDescription());
                    entity.setDateLimite(task.getDateLimite());


                    UserEntity userEntity = jpaUserRepository.findById(task.getUserId())
                                .orElseThrow(() -> new IllegalArgumentException("User not found"));
                    entity.setUser(userEntity);


                    TaskEntity updated = jpaTaskRepository.save(entity);
                    return taskMapper.entityToModel(updated);
                });
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
