package org.expensetracker.Service;


import org.expensetracker.DTO.ExpenseDto;
import org.expensetracker.DTO.UpdateDto;
import org.expensetracker.Entity.Expense;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ModelMapper modelMapper;

    public ExpenseService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public ExpenseDto convertToDto(Expense expense){
        return modelMapper.map(expense, ExpenseDto.class);
    }

    public Expense convertToEntity(ExpenseDto expenseDto){
        return modelMapper.map(expenseDto, Expense.class);
    }

    public Expense updateEntity(UpdateDto updateDto){
        return modelMapper.map(updateDto, Expense.class);
    }


    public float calculateTotal(List<Expense> list){
        float total = 0;
        for(Expense expense : list){
            total += expense.getAmount();
        }
        return total;
    }

    public float calculateTotalIncome(List<Expense> list){
        float total = 0;
        for(Expense expense : list){
            if(expense.getType().equals("Income")){
                total += expense.getAmount();
            }
        }
        return total;
    }



    public float calculateTotalExpense(List<Expense> list){
        float total = 0;
        for(Expense expense : list){
            if(expense.getType().equals("Expense")){
                total += expense.getAmount();
            }
        }
        return total;
    }


}
