package com.todo.todoarqhexagonal.user.infrastructure.repositories;

import com.todo.todoarqhexagonal.user.domain.models.User;

import com.todo.todoarqhexagonal.user.domain.ports.out.UserRepositoryPort;
import com.todo.todoarqhexagonal.user.infrastructure.entities.UserEntity;
import com.todo.todoarqhexagonal.user.infrastructure.mappers.UserMapper;
import com.todo.todoarqhexagonal.user.infrastructure.repositories.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    public JpaUserRepositoryAdapter(JpaUserRepository jpaUserRepository, UserMapper userMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User create(User user) {
        UserEntity userEntity = userMapper.modelToEntity(user);
        UserEntity savedEntity = jpaUserRepository.save(userEntity);
        return userMapper.entityToModel(savedEntity);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return jpaUserRepository.findById(userId)
                .map(userMapper::entityToModel);
    }

    @Override
    public Optional<User> update(User user) {
        return jpaUserRepository.findById(user.getUserId())
                .map(existingEntity -> {

                    existingEntity.setEmail(user.getEmail());
                    existingEntity.setPassword(user.getPassword());
                    existingEntity.setFirstName(user.getFirstName());
                    existingEntity.setLastName(user.getLastName());

                    UserEntity updatedEntity = jpaUserRepository.save(existingEntity);
                    return userMapper.entityToModel(updatedEntity);
                });
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll()
                .stream()
                .map(userMapper::entityToModel)
                .collect(Collectors.toList());
    }



    @Override
    public Boolean delete(Long userId) {
        jpaUserRepository.deleteById(userId);
        return true;
    }

    @Override
    public Boolean existsById(Long userId) {
        return jpaUserRepository.existsById(userId);
    }

}