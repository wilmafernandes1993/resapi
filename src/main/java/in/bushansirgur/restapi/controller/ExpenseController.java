package in.bushansirgur.restapi.controller;

import in.bushansirgur.restapi.dto.ExpenseDTO;
import in.bushansirgur.restapi.io.ExpenseResponse;
import in.bushansirgur.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ModelMapper modelMapper;

    @GetMapping("/expenses")
    public List<ExpenseResponse> getExpenses() {
        log.info("API GET /expenses called");

        //call the service method
        List<ExpenseDTO> list = expenseService.getAllExpenses();

        log.info("Printing the data from service {}", list);


        //convert the expense DTO to expense Response
        List<ExpenseResponse> response = list.stream().map(expenseDTO -> mapToExpenseResponse(expenseDTO)).
                collect(Collectors.toList());

        return response;


    }


    @GetMapping("/expenses/{expenseId}")
    public ExpenseResponse getExpenseById(@PathVariable String expenseId) {
        log.info("API GET /expenses/{} called", expenseId);
        ExpenseDTO expenseDTO = expenseService.getExpenseByExpenseId(expenseId);
        log.info("Printing the expense details {}", expenseDTO);
        return mapToExpenseResponse(expenseDTO);
    }

    private ExpenseResponse mapToExpenseResponse(ExpenseDTO expenseDTO){
        return modelMapper.map(expenseDTO, ExpenseResponse.class);
    }
}
