function showListOfCities() {
    var content = $('#cities');
    content.empty();
    $.get(
        "/city",
        function (data) {
            data.forEach(function (item) {
                content.append("<option>" + item.name + "</option>");
            });
        }
    );
}

$(document).ready(function() {
    showListOfCities();
});