package in.bushansirgur.restapi.service.impl;

import in.bushansirgur.restapi.dto.ExpenseDTO;
import in.bushansirgur.restapi.entity.ExpenseEntity;
import in.bushansirgur.restapi.exceptions.ResourceNotFoundException;
import in.bushansirgur.restapi.repository.ExpenseRepository;
import in.bushansirgur.restapi.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseServiceimpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;

    @Override
    public void deleteExpenseByExpenseId(String expenseId) {
        ExpenseEntity expenseEntity = getExpenseEntity(expenseId);
        log.info("Printing the expense entity {}", expenseEntity);
        expenseRepository.delete(expenseEntity);
    }

    @Override
    public List<ExpenseDTO> getAllExpenses(){
        //call therepository method
        List<ExpenseEntity> list = expenseRepository.findAll();
        log.info("Printing the data from repostory {}", list);


        //Cnvert the entity object to DTO object
        List<ExpenseDTO> listOfExpenses = list.stream().map(expenseEntity -> mapToExpenseDTO(expenseEntity)).collect(Collectors.toList());

        return listOfExpenses;
    }

    @Override
    public ExpenseDTO getExpenseByExpenseId(String expenseId) {
        ExpenseEntity expenseEntity = getExpenseEntity(expenseId);
        log.info("Printing the expense entity details {}", expenseEntity);
        return mapToExpenseDTO(expenseEntity);
    }

    private ExpenseEntity getExpenseEntity(String expenseId) {
       return  expenseRepository.findByExpenseId(expenseId).
                orElseThrow(() -> new ResourceNotFoundException("Expense not found for the expeense id " + expenseId));
    }

    private ExpenseDTO mapToExpenseDTO(ExpenseEntity expenseEntity) {
        return modelMapper.map(expenseEntity, ExpenseDTO.class);
    }
}
