package org.expensetracker.Controller;

import org.expensetracker.DTO.ExpenseDto;
import org.expensetracker.DTO.UpdateDto;
import org.expensetracker.Entity.Expense;
import org.expensetracker.Repository.ExpenseRepo;
import org.expensetracker.Service.ExpenseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    ExpenseRepo expenseRepo;
    @Autowired
    ExpenseService expenseService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto) {
        Expense expense=expenseService.convertToEntity(expenseDto);
        expenseRepo.save(expense);
        return ResponseEntity.ok().body(expenseService.convertToDto(expense));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
      Expense expense=  expenseRepo.findById(id).orElse(null);
        if(expense != null) {
            expenseRepo.delete(expense);
            return ResponseEntity.ok("Deleted Successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ExpenseDto>> getAllExpense() {
        List<Expense> expenseList=expenseRepo.findAll();
        List<ExpenseDto> expenseDtoList=new ArrayList<>();
        for(Expense expense:expenseList){
            expenseDtoList.add(modelMapper.map(expense, ExpenseDto.class));
        }
        return ResponseEntity.ok().body(expenseDtoList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ExpenseDto> UpdateExpense(@PathVariable Long id,@RequestBody UpdateDto updateDto ) {
        Expense expense=expenseRepo.findById(id).orElse(null);
        if(expense != null) {
             expense=expenseService.updateEntity(updateDto);
            expenseRepo.save(expense);
            return ResponseEntity.ok().body(modelMapper.map(expense, ExpenseDto.class));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getTotal")
    public ResponseEntity<List<Float>> totalExpense(){
        List<Float> list=new ArrayList<>();
        list.add(expenseService.calculateTotal(expenseRepo.findAll()));
        list.add(expenseService.calculateTotalIncome(expenseRepo.findAll()));
        list.add(expenseService.calculateTotalExpense(expenseRepo.findAll()));
        return ResponseEntity.ok().body(list);
    }





}
