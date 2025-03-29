package in.bushansirgur.restapi.io;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseRequest {

    @NotBlank(message = "Expense name is required")
    @Size(min = 3, message= "Expense name should be atleast 3 charcaters")
    private String name;

    private String note;

    @NotBlank(message = "Expense category is required")
    private String category;

    @NotNull(message = "Expense date is required")
    private Date date;

    @NotNull(message = "Expense amount is required")
    private BigDecimal amount;
}
