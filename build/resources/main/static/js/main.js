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
    try{
        $('#sp500stockstable').DataTable();
    } catch(error){
    console.log(error);
    }

    $("#sp500stockstable tbody tr").on('click', function(){
        window.location.href = '/stock/' + $(this).data('stock-symbol');
        });


});

function display_history_charts(){
    setupMonthlyStockHistoryChart();
}
function setupMonthlyStockHistoryChart(){
    const labels = [
      'January',
      'February',
      'March',
      'April',
      'May',
      'June',
    ];
    const data = {
      labels: labels,
      datasets: [{
        label: 'My First dataset',
        backgroundColor: 'rgb(255, 99, 132)',
        borderColor: 'rgb(255, 99, 132)',
        data: [0, 10, 5, 2, 20, 30, 45],
      }]
    };
    const config = {
          type: 'line',
          data,
          options: {}
        };
    var myChart = new Chart(
        document.getElementById('monthlyStockHistoryChart'),
        config
    );
}


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