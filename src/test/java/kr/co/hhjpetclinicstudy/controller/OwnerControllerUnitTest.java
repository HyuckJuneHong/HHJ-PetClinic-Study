package kr.co.hhjpetclinicstudy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.service.OwnerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @WebMvcTest : 스프링 MVC 컨트롤러에 대한 테스트를 작성할 때 사용되는 애너테이션.
 * - OwnerController.class : 특정 컨트롤러 클래스를 지정하여 해당 컨트롤러에 대한 테스트 환경 구성.
 * - 이 애너테이션을 사용하면 스프링 MVC에서 제공하는 MockMvc 객체를 주입 받아서 컨트롤러의 동작을 테스트 가능.
 * - 단, @WebMvcTest는 단위 테스트가 아닌 통합 테스트에 가깝다.
 * <p>
 * MockMvc : Spring MVC 테스트를 위한 클래스
 * - 컨트롤러의 테스트를 할 수 있게 한다.
 * - 이를 이용해 HTTP 요청을 보내고, 그 결과를 검증이 가능.
 */
@WebMvcTest(controllers = OwnerController.class)
@AutoConfigureMockMvc
public class OwnerControllerUnitTest {

    @MockBean(name = "ownerService")
    private OwnerService ownerService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper jsonConvertor = new ObjectMapper();

    @Test
    @DisplayName("OwnerController - createOwner - 성공")
    void createOwnerTest_success() throws Exception {

        // given
        OwnerReqDTO.CREATE create = OwnerReqDTO.CREATE.builder()
                .firstName("t1")
                .lastName("t2")
                .city("city")
                .address("address")
                .telephone("01011111111")
                .build();

        doAnswer(invocation -> null)
                .when(ownerService)
                .createOwner(any(OwnerReqDTO.CREATE.class));

        // then
        MvcResult result = mockMvc.perform(
                        post("/api/v1/owners")
                                .content(jsonConvertor.writeValueAsString(create))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.successful").value(true))
                .andExpect(jsonPath("$.message").value(ResponseStatus.SUCCESS_CREATE.getMessage()))
                .andReturn();
    }
}
