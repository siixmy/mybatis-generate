Res.includeFullCss('/js/lib/dialog/jquery_dialog.css');
$(function () {
    Res.include('/lib/validform/Validform_v5.3.2.js');
    Res.include('/lib/dialog/jquery_dialog.js');

    var form = $('#genForm');
    $('#testDb').click(function () {

        $.ajax({
            url: $(this).attr('data-href'),
            type: 'POST',
            dataType: 'json',
            data: form.serialize(),
            success: function (e) {
                JqueryDialog.alert(e.status.msg);
            }
        })
    });

    $('#genBtn').click(function () {
        $.ajax({
            url: form.attr("action"),
            type: 'POST',
            dataType: 'json',
            data: form.serialize(),
            beforeSend: function () {
                MaskLoad.add();
            },
            complete: function () {
                MaskLoad.remove();
            },
            success: function (e) {
                JqueryDialog.alert(e.status.msg);
                if (e.status.status == 0) {
                    location.href = form.attr("url-download");
                }
            },
            error: function (xhr) {
                JqueryDialog.alert('ajax error');
            }
        })
    })
});