// Global vars
var update_balance_modal;
var transfer_modal;

$(document).ready(function(){
try{
    update_balance_modal = new bootstrap.Modal(document.getElementById('bank-account-update-balance-modal'));
    transfer_modal =  new bootstrap.Modal(document.getElementById('transfer-modal'));

} catch(error){
console.log(error);
}

});
function updateBalance(button){
    var currentBalance = $(button).data('currentbalance');
    var accountNumber = $(button).data('accountnumber');
    $("#modal-bank-account-current-balance").html(currentBalance);
    $("#modal-bank-account-hidden-input-account-number").val(accountNumber);
    $("#modal-bank-account-number").html(accountNumber);
    console.log(currentBalance,accountNumber);
    update_balance_modal.show();
}

function addFundsToBrokerageAccount(button){
    var currentBalance = $(button).data('currentbalance');
    var accountNumber = $(button).data('accountnumber');
    $("#transfer-modal-brokerage-account-number").html(accountNumber);
    $("#transfer-modal-brokerage-account-current-balance").html(currentBalance);
    $("#modal-hidden-input-account-number").val(accountNumber);
}