package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperTest {

    @Autowired
    TaskMapper taskMapper;


    public Task creatTask(){
        return new Task(1L, "test", "description");
    }

    public TaskDto createTaskDto(){
        return new TaskDto(1L, "test", "description");
    }


    @Test
    public void shouldMapToTask(){
        //Given
        TaskDto taskDto = createTaskDto();
        //When
        Task mapToTask = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(taskDto.getId(), mapToTask.getId());
        assertEquals(taskDto.getTitle(), mapToTask.getTitle());
        assertEquals(taskDto.getContent(), mapToTask.getContent());
    }

    @Test
    public void shouldMapToTaskDto(){
        //Given
        Task task = creatTask();
        //When
        TaskDto mapToTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(task.getId(), mapToTaskDto.getId());
        assertEquals(task.getContent(), mapToTaskDto.getContent());
        assertEquals(task.getTitle(), mapToTaskDto.getTitle());
    }

    @Test
    public void shouldMapToTaskDtoList(){
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(creatTask());
        //When
        List<TaskDto> mapToTaskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        Assert.assertEquals(1, mapToTaskDtoList.size());
    }

}