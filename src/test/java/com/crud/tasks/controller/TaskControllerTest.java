package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DbService service;

    @MockBean
    TaskMapper taskMapper;

    @Test
    public void shouldFetchEmptyTaskList() throws Exception{
        //Given
        List<Task> taskList = new ArrayList<>();
        List<TaskDto> taskDtoList = new ArrayList<>();
        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchTaskList() throws Exception{
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "test task", "test desc"));
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "test task", "test desc"));
        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    /*@Test
    public void shouldFetchTask() throws Exception{
        //Given
        Task task = new Task(1l,"test task", "test desc");

        TaskDto taskDto = new TaskDto(1L, "test task", "test desc");
        
        when(service.getTask(task.getId())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When & Then
        mockMvc.perform(get("/v1/task/getTask")
                .contentType(MediaType.APPLICATION_JSON))
                //.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((1L))))
                .andExpect(jsonPath("$.name", is("test task")))
                .andExpect(jsonPath("$.description", is("test desc")));
    }*/

    @Test
    public void shouldCreateTask() throws Exception{
        //Given
        Task task = new Task(1l,"test task", "test desc");
        TaskDto taskDto = new TaskDto(1L, "task", "desc");
        when(service.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void shouldUpdateTask() throws Exception{
        //Given
        Task task = new Task(1l,"test task", "test desc");
        TaskDto taskDto = new TaskDto(1L, "update test task", "update test desc");
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(service.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void shouldDeleteTask() throws Exception{
       this.mockMvc.perform(MockMvcRequestBuilders
       .delete("/v1/task/{taskId}", 1)
       .contentType(MediaType.APPLICATION_JSON)
       .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(MockMvcResultHandlers.print());
    }


}