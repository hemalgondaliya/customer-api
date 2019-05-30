package com.example.demo.modal;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cheque_payment")
public class ChequePayment {
    @Id
    @GeneratedValue(generator = "chequepayment_id_generator")
    @SequenceGenerator(
            name = "chequepayment_id_generator",
            sequenceName = "cheque_id_number_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "cheque_number", columnDefinition = "INTEGER")
    @NotNull(message = "Cheque number can not be null")
    private Integer chequeNumber;

    @Column(name = "bank_name",columnDefinition = "VARCHAR(50)")
    @NotBlank(message = "Bank name can not be blank")
    private String bankName;

    public Integer getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(Integer chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
