package lk.nexttravel.apigateway.service.transaction;

import lk.nexttravel.apigateway.dto.TransactionDTO;

import java.util.List;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 09:05
 */
public interface TransactionCordinator {

    //For create method
    boolean preparePhaseForCreate(List<TransactionDTO> transactionDTOList);

    void commitPhaseForCreate(List<TransactionDTO> transactionDTOList);

    void rollbackPhaseForCreate(List<TransactionDTO> transactionDTOList);

    //For update method
    boolean preparePhaseForUpdate(List<TransactionDTO> transactionDTOList);

    void commitPhaseForUpdate(List<TransactionDTO> transactionDTOList);

    void rollbackPhaseForUpdate(List<TransactionDTO> transactionDTOList);

    //For delete method
    boolean preparePhaseForDelete(List<TransactionDTO> transactionDTOList);

    void commitPhaseForDelete(List<TransactionDTO> transactionDTOList);
}
