var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime",
                "render" : function (data, type, row) {
                    if (type == "display") {
                        return data.replace('T',' ');
                    }
                    return data;
                },
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                // "defaultContent": "Edit",
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            },
            {
                // "defaultContent": "Delete",
                "orderable": false,
                "defaultContent": "",
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            $(row).attr("data-mealExceed", data.exceed);
        },
    "initComplete": makeEditable
    });
});

jQuery(function(){
    jQuery('#date_timepicker_start').datetimepicker({
        format:'Y-m-d',
        onShow:function( ct ){
            this.setOptions({
                maxDate:jQuery('#date_timepicker_end').val()?jQuery('#date_timepicker_end').val():false
            })
        },
        timepicker:false
    });
    jQuery('#date_timepicker_end').datetimepicker({
        format:'Y-m-d',
        onShow:function( ct ){
            this.setOptions({
                minDate:jQuery('#date_timepicker_start').val()?jQuery('#date_timepicker_start').val():false
            })
        },
        timepicker:false
    });
});

jQuery('#datetimepicker5').datetimepicker({
    datepicker:true,
    allowTimes:[
        '12:00', '13:00', '15:00',
        '17:00', '17:05', '17:20', '19:00', '20:00'
    ]
});
jQuery('#datetimepicker6').datetimepicker({
    datepicker:false,
    format:'H:i'
})

jQuery('#timepicker1').datetimepicker({
    datepicker:false,
    format:'H:i'
});

jQuery('#datetimepicker').datetimepicker(
    {
        format:'Y-m-d\\TH:i'

    }
);