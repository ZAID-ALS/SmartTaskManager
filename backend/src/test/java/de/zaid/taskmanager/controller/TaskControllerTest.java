package de.zaid.taskmanager.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.zaid.taskmanager.model.Priority;
import de.zaid.taskmanager.model.Task;
import de.zaid.taskmanager.model.TaskStatus;
import de.zaid.taskmanager.service.TaskManager;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskManager taskManager;

    @Test
    void postTasksReturnsCreated() throws Exception {
        Task task = task(1, "Presentation", Priority.HIGH, LocalDate.of(2026, 7, 20));
        when(taskManager.createTask(anyString(), anyString(), eq(Priority.HIGH),
                eq(LocalDate.of(2026, 7, 20))))
                .thenReturn(task);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "Presentation",
                                  "description": "Prepare slides",
                                  "priority": "HIGH",
                                  "dueDate": "2026-07-20"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/tasks/1"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("NEW"));
    }

    @Test
    void getTasksReturnsOk() throws Exception {
        when(taskManager.getAllTasks()).thenReturn(List.of(
                task(1, "First", Priority.MEDIUM, LocalDate.of(2026, 7, 20))));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("First"));
    }

    @Test
    void getTasksSortedByPriorityReturnsOk() throws Exception {
        when(taskManager.getTasksSortedByPriority()).thenReturn(List.of(
                task(1, "High", Priority.HIGH, LocalDate.of(2026, 7, 20))));

        mockMvc.perform(get("/api/tasks").param("sort", "priority"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].priority").value("HIGH"));

        verify(taskManager).getTasksSortedByPriority();
    }

    @Test
    void getTasksSortedByDueDateReturnsOk() throws Exception {
        when(taskManager.getTasksSortedByDueDate()).thenReturn(List.of(
                task(1, "Early", Priority.LOW, LocalDate.of(2026, 7, 10))));

        mockMvc.perform(get("/api/tasks").param("sort", "dueDate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dueDate").value("2026-07-10"));

        verify(taskManager).getTasksSortedByDueDate();
    }

    @Test
    void patchCompleteReturnsOk() throws Exception {
        Task task = task(1, "Complete", Priority.MEDIUM, LocalDate.of(2026, 7, 20));
        task.setStatus(TaskStatus.COMPLETED);
        when(taskManager.markTaskAsCompleted(1)).thenReturn(true);
        when(taskManager.findTaskById(1)).thenReturn(task);

        mockMvc.perform(patch("/api/tasks/1/complete"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }

    @Test
    void deleteTaskReturnsNoContent() throws Exception {
        when(taskManager.deleteTask(1)).thenReturn(true);

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    void invalidRequestReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "",
                                  "description": "Invalid task",
                                  "priority": "HIGH",
                                  "dueDate": "2026-07-20"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void unknownSortValueReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/tasks").param("sort", "unknown"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void unknownTaskIdReturnsNotFound() throws Exception {
        when(taskManager.markTaskAsCompleted(99)).thenReturn(false);

        mockMvc.perform(patch("/api/tasks/99/complete"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    private Task task(int id, String title, Priority priority, LocalDate dueDate) {
        return new Task(id, title, "Description", priority, dueDate);
    }
}
