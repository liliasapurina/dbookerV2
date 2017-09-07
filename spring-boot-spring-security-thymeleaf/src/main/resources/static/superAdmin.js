var cities = {};
var rooms = {};

function showListOfCities() {
    var content = $('#cities');
    content.empty();
    content.append("<option data-hidden=\"true\">Choose City</option>");
    $.get(
        "/city",
        function (data) {
            data.forEach(function (item) {
                content.append("<option>" + item.name + "</option>");
                cities[item.name] = item.id;
            });
        }
    );
}

function showListOfRooms(cityName) {
    var content = $('#rooms');
    content.empty();
    content.append("<option data-hidden=\"true\">Choose Room</option>");
    $.get(
        "/room/cityId=" + cities[cityName],
        function (data) {
            data.forEach(function (item) {
                content.append("<option>" + item.name + "</option>");
                rooms[item.name] = item.id;
            });
        }
    );
}

function clearTable() {
    $('#availableTable').empty();
}

function showListOfSeats(roomName) {
    var content = $('#availableTable');
    content.empty();
    $.get(
        "/seat/roomId=" + rooms[roomName],
        function (data) {
            data.forEach(function (item) {
                content.append("<tr class=\"success\"><td>" + item.name + "</td>"
                    + " <td><div class=\"checkbox\"><input type=\"checkbox\" value=\"\"/></div></td></tr>");
            });
        }
    );
}

$(document).ready(function () {
    showListOfCities();

    $("#cities").on('change', function () {
        var selectedCity = $(this).find("option:selected").val();
        if(cities[selectedCity]){
            showListOfRooms(selectedCity);
        } else {
            clearTable();
        }
    });

    $("#rooms").on('change', function () {
        var selectedRoom = $(this).find("option:selected").val();
        if(rooms[selectedRoom]){
            showListOfSeats(selectedRoom);
        } else {
            clearTable();
        }
    });
});