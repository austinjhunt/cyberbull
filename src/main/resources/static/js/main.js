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
        $('.datatable').DataTable();
    } catch(error){
    console.log(error);
    }

    $("#sp500stockstable tbody tr").on('click', function(){
        window.location.href = '/stock/' + $(this).data('stock-symbol');
        });

    try{
        if (window.location.href.includes("/stock/")){
            display_history_charts();
        }
    }
    catch(error){
        console.log(error);
    }

    try{
        // fix portfolio position history layout due to thymeleaf constraints with looping
        $('tr.position-history').each(function(i,history_row){
          var symbol = $(history_row).attr('id').split('-')[1];
          $("tr#position-" + symbol).after(history_row);
        });
    } catch(error){
    console.log(error);}
});

function prepare_add_stock_to_watchlist_modal(button){
    var brokerageAccountNumber = $(button).data('brokerage-account-number');
    var watchlistId = $(button).data('watchlist-id');
    var watchlistTitle = $(button).data('watchlist-title');
    $("#add-stock-to-watchlist-form").attr(
        'action', '/watchlists/addStock/' + watchlistId + '/' + brokerageAccountNumber);
    $("#modal-watchlist-title").text('Watchlist Title: ' + watchlistTitle);
    $('#add-stock-to-watchlist-modal').modal('show');
}


function display_history_charts(){
    setupMonthlyStockHistoryChart();
    setupWeeklyStockHistoryChart();
    setupDailyStockHistoryChart();
}
function configureHistoryCharts(data){
    var history = JSON.parse(data);
    var dates = [];
    var opens = [];
    var closes = [];
    var highs = [];
    var lows = [];
    var volumes = [];
    for (var i = 0 ; i < history.length; i ++){
        var historyItem = history[i];
        if ( historyItem["open"] == 0 || historyItem["close"] == 0 || historyItem["high"] == 0 ||
            historyItem["low"] == 0 || historyItem["volume"] == 0 ){  continue; }
        dates.push(historyItem["date"]);
        opens.push(historyItem["open"]);
        closes.push(historyItem["close"]);
        highs.push(historyItem["high"]);
        lows.push(historyItem["low"]);
        volumes.push(historyItem["volume"]);
    }
    var priceHistory = {
        labels: dates,
        datasets: [
            {
            label: 'Open',
            backgroundColor: 'rgb(255, 16, 83)',
            borderColor: 'rgb(255, 16, 83)',
            data: opens,
            },
            {
            label: 'Close',
            backgroundColor: 'rgb(108, 110, 160)',
            borderColor: 'rgb(108, 110, 160)',
            data: closes,
            },
            {
            label: 'High',
            backgroundColor: 'rgb(102, 199, 244)',
            borderColor: 'rgb(102, 199, 244)',
            data: highs,
            },
            {
            label: 'Low',
            backgroundColor: 'rgb(204, 164, 59)',
            borderColor: 'rgb(204, 164, 59)',
            data: lows,
            }
        ]
    };
    var volumeHistory = {
        labels: dates,
        datasets: [
            {
                label: 'Volume',
                backgroundColor: 'rgb(66, 3, 61)',
                borderColor: 'rgb(66, 3, 61)',
                data: volumes,
            }
        ]
    };
    const configPriceHistory = {
        type: 'line',
        data: priceHistory,
        options: {}
    };
    const configVolumeHistory = {
        type: 'line',
        data: volumeHistory,
        options: {}
    };
    return {1: configPriceHistory, 2: configVolumeHistory};
}
function setupMonthlyStockHistoryChart(){
    $.ajax({
        method: 'get',
        url: '/stock/' + $("#stock-symbol").val() + '/history/monthly',
        success: function(data){
            var configs = configureHistoryCharts(data);
            var priceHistoryChart = new Chart(
                document.getElementById('monthlyStockHistoryChart'),
                configs[1]
            );
            var volumeHistoryChart = new Chart(
                document.getElementById('monthlyStockVolumeHistoryChart'),
                configs[2]
            );
        },
        error: function(error){
            console.log(data);
        }
    });
}
function setupWeeklyStockHistoryChart(){
    $.ajax({
        method: 'get',
        url: '/stock/' + $("#stock-symbol").val() + '/history/weekly',
        success: function(data){
            var configs = configureHistoryCharts(data);
            var priceHistoryChart = new Chart(
                document.getElementById('weeklyStockHistoryChart'),
                configs[1]
            );
            var volumeHistoryChart = new Chart(
                document.getElementById('weeklyStockVolumeHistoryChart'),
                configs[2]
            );
        },
        error: function(error){
            console.log(data);
        }
    });
}
function setupDailyStockHistoryChart(){
    $.ajax({
        method: 'get',
        url: '/stock/' + $("#stock-symbol").val() + '/history/daily',
        success: function(data){
            var configs = configureHistoryCharts(data);
            var priceHistoryChart = new Chart(
                document.getElementById('dailyStockHistoryChart'),
                configs[1]
            );
            var volumeHistoryChart = new Chart(
                document.getElementById('dailyStockVolumeHistoryChart'),
                configs[2]
            );
        },
        error: function(error){
            console.log(data);
        }
    });
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