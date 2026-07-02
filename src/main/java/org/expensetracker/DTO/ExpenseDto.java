package org.expensetracker.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDto {
    private Long id;
    private String type;
    private Float amount;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate date;
}
