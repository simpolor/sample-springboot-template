package io.simpolor.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.simpolor.api.model.dto.StudentRequest;
import io.simpolor.api.repository.StudentRepository;
import io.simpolor.api.repository.entity.Student;
import io.simpolor.api.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = {StudentController.class, StudentService.class, StudentRepository.class})
public class StudentControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testList() throws Exception{

        // given
        Student student = new Student(1L, "하니", 18, "달리기");
        Mockito.when(studentRepository.findAll()).thenReturn(Arrays.asList(student));


        // when
        mockMvc.perform(
            MockMvcRequestBuilders.get("/students")
        )
        .andDo(MockMvcResultHandlers.print())


        // then
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(is("Success")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value[0].seq").value(is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value[0].age").value(is(18)));
    }

    @Test
    public void testDetail() throws Exception{

        // given
        long seq = 1L;
        Student student = new Student(1L, "하니", 18, "달리기");
        Mockito.when(studentRepository.findById(seq)).thenReturn(Optional.of(student));


        // when
        mockMvc.perform(
            MockMvcRequestBuilders.get("/students/1")
        )


        // then
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(is("Success")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value.seq").value(is(1)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.value.age").value(is(18)));
    }

    @Test
    public void testRegister() throws Exception{

        // given
        StudentRequest student = new StudentRequest(1L, "하니", 18, "달리기");
        String request = objectMapper.writeValueAsString(student);


        // when
        mockMvc.perform(
            MockMvcRequestBuilders.post("/students")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request)
        )


        // then
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(is("Success")));
        Mockito.verify(studentRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void testModify() throws Exception{

        // given
        long seq = 1L;
        StudentRequest student = new StudentRequest(1L, "하니", 18, "달리기");
        Mockito.when(studentRepository.findById(seq)).thenReturn(Optional.of(student.toStudent()));
        String request = objectMapper.writeValueAsString(student);


        // when
        mockMvc.perform(
            MockMvcRequestBuilders.put("/students/{seq}", seq)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request)
        )


        // then
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(is("Success")));
        Mockito.verify(studentRepository, Mockito.times(1)).findById(ArgumentMatchers.any());
        Mockito.verify(studentRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    public void testDelete() throws Exception{

        // given
        long seq = 1L;


        // when
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/students/{seq}", seq)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        )


        // then
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(is(0)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(is("Success")));
        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
    }
}
