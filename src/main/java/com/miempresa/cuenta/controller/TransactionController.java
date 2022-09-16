package com.miempresa.cuenta.controller;

import com.miempresa.cuenta.dto.Message;
import com.miempresa.cuenta.dto.TransactionDto;
import com.miempresa.cuenta.entity.Transaction;
import com.miempresa.cuenta.service.TransactionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/transactionsList")
    public ResponseEntity<List<Transaction>> transactionsList() {
        List<Transaction> transactions = transactionService.transactionList();
        return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
    }

    @GetMapping("/transactionDetails/{transactionId}")
    public ResponseEntity<Transaction> transactionById(@PathVariable("transactionId") int transactionId) {
        if (!transactionService.existsByTransactionId(transactionId)) {
            return new ResponseEntity(new Message("No existe la transaction"), HttpStatus.NOT_FOUND);
        }
        Transaction transaction = transactionService.getTransaction(transactionId).get();
        return new ResponseEntity(transaction, HttpStatus.OK);
    }

    //Con el ? le decimos que no devulve ningún tipo de dato
    //El body va a ser un JSON
    //Aqui se usa el apache commons lang
    // El import de StringUtils es import org.apache.commons.lang3.StringUtils;
    @PostMapping("/createTransaction")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto transactionDto) {

        if (StringUtils.isBlank(transactionDto.getDescription()))
            return new ResponseEntity(new Message("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);

        if (transactionDto.getValue()<0 || (Double) transactionDto.getValue() == null)
            return new ResponseEntity(new Message("El valor debe ser mayor a 0"), HttpStatus.BAD_REQUEST);

        Transaction transaction = new Transaction(transactionDto.getValue(), transactionDto.getDescription(), transactionDto.getDate());
        transactionService.saveTransaction(transaction);
        return new ResponseEntity(new Message("Transaction creada"), HttpStatus.OK);
    }

    @PutMapping("/updateTransaction/{transactionId}")
    public ResponseEntity<?> updateTransaction(@PathVariable("transactionId") int transactionId, @RequestBody TransactionDto transactionDto){

        if (!transactionService.existsByTransactionId(transactionId))
            return new ResponseEntity(new Message("No existe la transacción"), HttpStatus.NOT_FOUND);

        if(StringUtils.isBlank(transactionDto.getDescription()))
            return new ResponseEntity(new Message("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);

        if(transactionDto.getValue()<0 || (Double) transactionDto.getValue() == null)
            return new ResponseEntity(new Message("El valor debe ser mayor a 0"), HttpStatus.BAD_REQUEST);

        Transaction transaction = transactionService.getTransaction(transactionId).get();
        transaction.setValue(transactionDto.getValue());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setDate(transactionDto.getDate());
        transactionService.saveTransaction(transaction);
        return new ResponseEntity(new Message("Transaction actualizada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteTransaction/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("transactionId") int transactionId){
        if (!transactionService.existsByTransactionId(transactionId))
            return new ResponseEntity(new Message("No existe la transacción"), HttpStatus.NOT_FOUND);
        transactionService.deleteTransaction(transactionId);
        return new ResponseEntity(new Message("Transacción eliminada"), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s", name);
    }
}
