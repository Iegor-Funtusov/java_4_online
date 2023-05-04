$(document).ready(function () {
    $('#messages').autocomplete({
            source: function (request, response) {
                $.get("http://localhost:8080/personal/post/suggestions?", { query: request.term }, function (data, status) {
                    $("#results").html("");
                    if (status === 'success') {
                        response(data);
                    }
                });
            }
        }
    );
})
