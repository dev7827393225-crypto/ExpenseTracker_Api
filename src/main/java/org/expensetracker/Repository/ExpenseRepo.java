package org.expensetracker.Repository;

import org.expensetracker.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Long> {
}
